package net.debreczeni.model.table;

import net.debreczeni.model.Book;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchableBookTableModel extends BookTableModel {
    private final TableRowSorter<BookTableModel> filter = new TableRowSorter<>(this);
    private RowFilter<BookTableModel, Integer> genreFilter;
    private RowFilter<BookTableModel, Integer> titleFilter;
    private RowFilter<BookTableModel, Integer> authorFilter;

    public SearchableBookTableModel(Supplier<List<Book>> bookSupplier) {
        super(bookSupplier);
    }

    public void setGenreFilter(RowFilter<BookTableModel, Integer> genreFilter) {
        this.genreFilter = genreFilter;
        filter.setRowFilter(RowFilter.andFilter(
                Stream.of(genreFilter, titleFilter, authorFilter).filter(Objects::nonNull).collect(Collectors.toList())
        ));
    }

    public void setTitleFilter(RowFilter<BookTableModel, Integer> titleFilter) {
        this.titleFilter = titleFilter;
        filter.setRowFilter(RowFilter.andFilter(
                Stream.of(genreFilter, titleFilter, authorFilter).filter(Objects::nonNull).collect(Collectors.toList())
        ));
    }

    public void setAuthorFilter(RowFilter<BookTableModel, Integer> authorFilter) {
        this.authorFilter = authorFilter;
        filter.setRowFilter(RowFilter.andFilter(
                Stream.of(genreFilter, titleFilter, authorFilter).filter(Objects::nonNull).collect(Collectors.toList())
        ));
    }

    public void filter() {
        filter.sort();
    }

    public TableRowSorter<? extends TableModel> getRowSorter() {
        return filter;
    }
}
