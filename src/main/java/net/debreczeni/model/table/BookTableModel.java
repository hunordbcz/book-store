package net.debreczeni.model.table;

import net.debreczeni.exception.OutOfStockException;
import net.debreczeni.model.Book;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class BookTableModel extends AbstractTableModel {

    public final static int TITLE = 0;
    public final static int AUTHOR = 1;
    public final static int GENRE = 2;
    public final static int QUANTITY = 3;
    public final static int PRICE = 4;

    private final Supplier<List<Book>> bookSupplier;
    protected List<Book> books;

    public BookTableModel(Supplier<List<Book>> bookSupplier) {
        this.bookSupplier = bookSupplier;
        this.books = new ArrayList<>();
    }

    public void refresh() {
        books = bookSupplier.get();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final Book book = books.get(rowIndex);
        switch (columnIndex) {
            case TITLE:
                return book.getTitle();
            case AUTHOR:
                return book.getAuthor();
            case GENRE:
                return book.getGenre();
            case QUANTITY:
                return book.getQuantity();
            case PRICE:
                return book.getPrice();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case TITLE:
                return "Title";
            case AUTHOR:
                return "Author";
            case GENRE:
                return "Genre";
            case QUANTITY:
                return "Quantity";
            case PRICE:
                return "Price";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case QUANTITY:
                return Long.class;
            case PRICE:
                return Double.class;
            case TITLE:
            case AUTHOR:
            case GENRE:
            default:
                return String.class;
        }
    }

    public Book getBook(Integer index) {
        return books.get(index);
    }

    public void modifyStock(Book book, long modifiedWith) throws OutOfStockException {
        final Optional<Book> optionalBook = books.stream().filter(o -> o.equals(book)).findAny();
        if (optionalBook.isEmpty()) {
            return;
        }

        final Book innerBook = optionalBook.get();
        if(innerBook.getQuantity() - modifiedWith < 0){
            throw new OutOfStockException("Not enough stock for book " + book.getTitle());
        }
        innerBook.setQuantity(innerBook.getQuantity() - modifiedWith);
        fireTableDataChanged();
    }
}
