package net.debreczeni.model.table;

import net.debreczeni.controller.BookController;
import net.debreczeni.model.Book;

import java.util.List;
import java.util.function.Supplier;

public class ManageableBookTableModel extends SearchableBookTableModel {
    public ManageableBookTableModel(Supplier<List<Book>> bookSupplier) {
        super(bookSupplier);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != ID;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        final Book book = books.get(rowIndex);
        switch (columnIndex) {
            case TITLE:
                book.setTitle((String) aValue);
                break;
            case AUTHOR:
                book.setAuthor((String) aValue);
                break;
            case GENRE:
                book.setGenre((String) aValue);
                break;
            case QUANTITY:
                book.setQuantity((Long) aValue);
                break;
            case PRICE:
                book.setPrice((Double) aValue);
            default:
                break;
        }
    }

    public void removeBook(int index) {
        final Book book = books.get(index);
        BookController.getInstance().removeBook(book);
        refresh();
    }
}
