package net.debreczeni.view.table;

import net.debreczeni.model.Book;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class CartTableModel extends BookTableModel {
    private Map<Book, Integer> cart;

    public CartTableModel() {
        super(Collections::emptyList);
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

    public void removeBook(int index){
        books.remove(index);
        fireTableDataChanged();
    }

}
