package net.debreczeni.view;

import net.debreczeni.controller.BookController;
import net.debreczeni.exception.InvalidOrderException;
import net.debreczeni.exception.OutOfStockException;
import net.debreczeni.model.table.BookTableModel;
import net.debreczeni.model.table.SearchableBookTableModel;
import net.debreczeni.util.FilterOnChangeDocumentListener;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class BookStore extends JFrame {
    private final BookController bookController;
    private JPanel mainPanel;
    private JButton addToOrderButton;
    private JTable bookTable;
    private JButton finalizeButton;
    private JTable cartTable;
    private JButton removeBookButton;
    private JTextField genreSearchField;
    private JTextField titleSearchField;
    private JTextField authorSearchField;

    public BookStore(SearchableBookTableModel bookListModel, BookTableModel orderModel) throws HeadlessException {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.bookController = BookController.getInstance();

        bookTable.setModel(bookListModel);
        cartTable.setModel(orderModel);

        bookListModel.refresh();
        orderModel.refresh();

        bookListModel.setGenreFilter(new RowFilter<>() {
            public boolean include(Entry entry) {
                final String genre = entry.getStringValue(BookTableModel.GENRE);
                return genre.toLowerCase().contains(genreSearchField.getText().trim().toLowerCase());
            }
        });

        bookListModel.setAuthorFilter(new RowFilter<>() {
            public boolean include(Entry entry) {
                final String author = entry.getStringValue(BookTableModel.AUTHOR);
                return author.toLowerCase().contains(authorSearchField.getText().trim().toLowerCase());
            }
        });

        bookListModel.setTitleFilter(new RowFilter<>() {
            public boolean include(Entry entry) {
                final String title = entry.getStringValue(BookTableModel.TITLE);
                return title.toLowerCase().contains(titleSearchField.getText().trim().toLowerCase());
            }
        });
        bookTable.setRowSorter(bookListModel.getRowSorter());

        final FilterOnChangeDocumentListener filterListener = new FilterOnChangeDocumentListener(bookListModel.getRowSorter());
        genreSearchField.getDocument().addDocumentListener(filterListener);
        authorSearchField.getDocument().addDocumentListener(filterListener);
        titleSearchField.getDocument().addDocumentListener(filterListener);


        final ListSelectionModel bookSelectionModel = bookTable.getSelectionModel();
        bookSelectionModel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            addToOrderButton.setEnabled(true);
        });
        addToOrderButton.addActionListener(e -> {
            Arrays.stream(bookTable.getSelectedRows())
                    .map(row -> bookTable.getRowSorter().convertRowIndexToModel(row))
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .forEach(index -> {
                        try {
                            bookController.addToCart(index);
                        } catch (OutOfStockException ex) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    ex.getMessage(),
                                    "Out of stock",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    });

            bookSelectionModel.clearSelection();
            addToOrderButton.setEnabled(false);
        });

        final ListSelectionModel cartSelectionModel = cartTable.getSelectionModel();
        cartSelectionModel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            removeBookButton.setEnabled(true);
        });
        removeBookButton.addActionListener(e -> {
            Arrays.stream(cartTable.getSelectedRows())
                    .map(row -> cartTable.getRowSorter().convertRowIndexToModel(row))
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .forEach(bookController::removeFromCart);

            cartSelectionModel.clearSelection();
            removeBookButton.setEnabled(false);
        });

        finalizeButton.addActionListener(e -> {
            try {
                Long total = bookController.finalizeOrder();

                JOptionPane.showMessageDialog(
                        this,
                        "Order processed.\nTotal: " + total,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (InvalidOrderException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
