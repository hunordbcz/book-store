package net.debreczeni.model.table;

import net.debreczeni.controller.BookController;
import net.debreczeni.controller.UserController;
import net.debreczeni.model.AccessType;
import net.debreczeni.model.Book;
import net.debreczeni.model.User;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class UserTableModel extends AbstractTableModel {
    public final static int USERNAME = 0;
    public final static int IS_MANAGER = 1;
    private final Supplier<List<User>> userSupplier;

    private List<User> users = new ArrayList<>();

    public UserTableModel(Supplier<List<User>> userSupplier) {
        this.userSupplier = userSupplier;
    }

    public void refresh(){
        this.users = userSupplier.get();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final User user = users.get(rowIndex);
        switch (columnIndex){
            case USERNAME:
                return user.getUsername();
            case IS_MANAGER:
                return user.getAccessType() == AccessType.MANAGER;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case USERNAME:
                return "Username";
            case IS_MANAGER:
                return "Is Manager";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case USERNAME:
                return String.class;
            case IS_MANAGER:
                return Boolean.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == IS_MANAGER;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        final User user = users.get(rowIndex);
        switch (columnIndex){
            case USERNAME:
                user.setUsername((String) aValue);
                break;
            case IS_MANAGER:
                final AccessType accessType = (Boolean) aValue ? AccessType.MANAGER : AccessType.SELLER;
                user.setAccessType(accessType);
                break;
        }
    }

    public void removeUser(int index) {
        final User user = users.get(index);
        UserController.getInstance().removeUser(user);
        refresh();
    }
}
