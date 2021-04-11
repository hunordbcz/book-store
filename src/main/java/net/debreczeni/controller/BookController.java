package net.debreczeni.controller;

import net.debreczeni.exception.InvalidOrderException;
import net.debreczeni.exception.OutOfStockException;
import net.debreczeni.exception.ValidationException;
import net.debreczeni.model.Book;
import net.debreczeni.model.table.CartTableModel;
import net.debreczeni.model.table.ManageableBookTableModel;
import net.debreczeni.model.table.SearchableBookTableModel;
import net.debreczeni.service.BookService;

import java.util.List;

import static net.debreczeni.util.StringUtils.isBlank;

public class BookController {

    private final BookService bookService = BookService.getInstance();
    private SearchableBookTableModel searchableBookTableModel;
    private CartTableModel cartTableModel;
    private ManageableBookTableModel manageableBookTableModel;

    private BookController() {
    }

    public static BookController getInstance() {
        return Singleton.INSTANCE;
    }

    public List<Book> getAll() {
        return bookService.getAll();
    }

    public SearchableBookTableModel getSearchableBookTableModel() {
        return getSearchableBookTableModel(false);
    }

    public SearchableBookTableModel getSearchableBookTableModel(boolean reset) {
        if (searchableBookTableModel == null || reset) {
            this.searchableBookTableModel = new SearchableBookTableModel(this::getAll);
        }
        return searchableBookTableModel;
    }

    public ManageableBookTableModel getManageableBookTableModel() {
        if (manageableBookTableModel == null) {
            this.manageableBookTableModel = new ManageableBookTableModel(this::getAll);
        }
        return manageableBookTableModel;
    }

    public CartTableModel getCartTableModel() {
        return getCartTableModel(false);
    }

    public CartTableModel getCartTableModel(boolean reset) {
        if (cartTableModel == null || reset) {
            cartTableModel = new CartTableModel();
        }
        return cartTableModel;
    }

    public void addToCart(Integer index) throws OutOfStockException {
        Book book = searchableBookTableModel.getBook(index);
        if (book.getQuantity() == 0) {
            throw new OutOfStockException("Out of stock for book " + book.getTitle());
        }

        book.setQuantity(book.getQuantity() - 1);
        cartTableModel.addBook(book);
    }

    public void removeBook(Book book) {
        bookService.delete(book.getId());
    }

    public void removeFromCart(Integer index) {
        cartTableModel.removeBook(index);
    }

    public void modifyStockInCart(Book book, Long modifiedWith) throws OutOfStockException {
        searchableBookTableModel.modifyStock(book, modifiedWith);

        long newQuantity = book.getQuantity() + modifiedWith;
        if (newQuantity > 0) {
            book.setQuantity(newQuantity);
        } else {
            cartTableModel.removeBook(book);
        }
    }

    public Long finalizeOrder() throws InvalidOrderException {
        if (cartTableModel.size() == 0) {
            throw new InvalidOrderException("The cart is empty");
        }

        final Long total = cartTableModel.total();
        cartTableModel.refresh();
        searchableBookTableModel.persistData(false);
        return total;
    }

    public void persistTableData() {
        manageableBookTableModel.persistData(true);
    }

    public void addBookToTable() {
        manageableBookTableModel.newBook();
    }

    public void update(Book book) throws ValidationException {
        if (
                isBlank(book.getTitle()) ||
                        isBlank(book.getGenre()) ||
                        isBlank(book.getAuthor()) ||
                        book.getQuantity() == null ||
                        book.getQuantity() < 0 ||
                        book.getPrice() == null ||
                        book.getPrice() < 0
        ) {
            throw new ValidationException("Invalid fields for book");
        }

        bookService.update(book);
    }

    private static class Singleton {
        private static final BookController INSTANCE = new BookController();
    }
}
