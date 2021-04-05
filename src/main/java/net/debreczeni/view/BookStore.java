package net.debreczeni.view;

import net.debreczeni.util.FilterOnChangeDocumentListener;
import net.debreczeni.view.table.BookTableModel;
import net.debreczeni.view.table.SearchableBookTableModel;

import javax.swing.*;
import java.awt.*;

public class BookStore extends JFrame {
    private JPanel mainPanel;
    private JButton addToOrderButton;
    private JTable bookTable;
    private JButton finalizeButton;
    private JTable orderTable;
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

        bookTable.setModel(bookListModel);
        orderTable.setModel(orderModel);

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
    }
}
