package net.debreczeni.view;

import net.debreczeni.model.User;
import net.debreczeni.view.table.BookTableModel;
import net.debreczeni.view.table.SearchableBookTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookStore extends JFrame{
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
                return true;
            }
        });

        bookListModel.setAuthorFilter(new RowFilter<>() {
            public boolean include(Entry entry) {
                return true;
            }
        });

        bookListModel.setTitleFilter(new RowFilter<>() {
            public boolean include(Entry entry) {
                return true;
            }
        });


        genreSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                bookListModel.filter();
            }
        });

        authorSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                bookListModel.filter();
            }
        });

        titleSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                bookListModel.filter();
            }
        });
    }
}
