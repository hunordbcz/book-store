package net.debreczeni.controller;

import net.debreczeni.exception.InvalidOrderException;
import net.debreczeni.exception.OutOfStockException;
import net.debreczeni.model.Book;
import net.debreczeni.model.table.CartTableModel;
import net.debreczeni.model.table.ManageableBookTableModel;
import net.debreczeni.model.table.SearchableBookTableModel;
import net.debreczeni.service.BookService;

import java.util.List;
import java.util.Map;

public class BookController {

    private SearchableBookTableModel searchableBookTableModel;
    private CartTableModel cartTableModel;

    private BookController() {
    }

    public static BookController getInstance() {
        return Singleton.INSTANCE;
    }

    public List<Book> getAll() {
        return BookService.getInstance().getAll();
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

    public ManageableBookTableModel getManageableBookTableModel(){
        return new ManageableBookTableModel(this::getAll);
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

    public void removeBook(Book book){
        BookService.getInstance().delete(book.getId());
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
        //todo generate invoice ?
        cartTableModel.refresh();
        return total;
    }

    private static class Singleton {
        private static final BookController INSTANCE = new BookController();
    }
}
