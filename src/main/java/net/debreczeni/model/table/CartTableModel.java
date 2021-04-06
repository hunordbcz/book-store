package net.debreczeni.model.table;

import net.debreczeni.controller.BookController;
import net.debreczeni.exception.OutOfStockException;
import net.debreczeni.model.Book;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

public class CartTableModel extends BookTableModel {
    public CartTableModel() {
        super(ArrayList::new);
    }

    public void addBook(Book book) {
        final Optional<Book> any = books.stream().filter(o -> o.equals(book)).findAny();
        if (any.isPresent()) {
            Book cartBook = any.get();
            cartBook.setQuantity(cartBook.getQuantity() + 1);
        } else {
            books.add(new Book(
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    1L,
                    book.getPrice()
            ));
        }

        fireTableDataChanged();
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void removeBook(int index) {
        final Book book = books.remove(index);
        try {
            BookController.getInstance().modifyStockInCart(book, -book.getQuantity());
        } catch (OutOfStockException ignored) {
        }

        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == PRICE) {
            final Book book = books.get(rowIndex);
            return book.getQuantity() * book.getPrice();
        }

        return super.getValueAt(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == QUANTITY;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Long newStock = (Long) aValue;
        if (newStock <= 0L) {
            newStock = 0L;
        }

        final Book book = books.get(rowIndex);
        try {
            BookController.getInstance().modifyStockInCart(book, newStock - book.getQuantity());
        } catch (OutOfStockException e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Out of stock",
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            fireTableDataChanged();
        }
    }

    public int size() {
        return books.size();
    }

    public long total() {
        return books.stream().mapToLong(o -> (long) (o.getQuantity() * o.getPrice())).sum();
    }
}
