package net.debreczeni.util;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FilterOnChangeDocumentListener implements DocumentListener {
    private final TableRowSorter<? extends TableModel> rowSorter;

    public FilterOnChangeDocumentListener(TableRowSorter<? extends TableModel> rowSorter) {
        this.rowSorter = rowSorter;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        rowSorter.sort();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        rowSorter.sort();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        rowSorter.sort();
    }
}
