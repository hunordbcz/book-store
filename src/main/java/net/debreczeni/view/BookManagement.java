package net.debreczeni.view;

import net.debreczeni.controller.PageController;
import net.debreczeni.controller.UserController;
import net.debreczeni.exception.OutOfStockException;
import net.debreczeni.model.User;
import net.debreczeni.model.table.BookTableModel;
import net.debreczeni.model.table.ManageableBookTableModel;
import net.debreczeni.model.table.SearchableBookTableModel;
import net.debreczeni.model.table.UserTableModel;
import net.debreczeni.util.FilterOnChangeDocumentListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

public class BookManagement extends JFrame {
    private static final UserController userController = UserController.getInstance();
    private static final PageController pageController = PageController.getInstance();
    private final User user;
    private JPanel mainPanel;
    private JTable userTable;
    private JTable bookTable;
    private JButton logOutButton;
    private JTextField titleSearchField;
    private JTextField genreSearchField;
    private JTextField authorSearchField;
    private JButton removeBookButton;
    private JButton newBookButton;
    private JButton newUserButton;
    private JButton removeUserButton;
    private JButton reportsButton;
    private JLabel nameField;

    public BookManagement(User user, ManageableBookTableModel bookListModel, UserTableModel userTableModel) throws HeadlessException {
        this.user = user;
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        bookTable.setModel(bookListModel);
        userTable.setModel(userTableModel);

        bookListModel.refresh();
        userTableModel.refresh();

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



        logOutButton.addActionListener(e -> {
            this.dispose();
            pageController.showLogin();
        });

        removeBookButton.addActionListener(e -> {
            Arrays.stream(bookTable.getSelectedRows())
                    .map(row -> bookTable.getRowSorter().convertRowIndexToModel(row))
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .forEach(bookListModel::removeBook);
        });

        removeUserButton.addActionListener(e -> {
            Arrays.stream(userTable.getSelectedRows())
                    .map(row -> userTable.getRowSorter().convertRowIndexToModel(row))
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .forEach(userTableModel::removeUser);
        });
    }
}
