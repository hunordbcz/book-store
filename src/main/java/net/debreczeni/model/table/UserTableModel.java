package net.debreczeni.model.table;

import net.debreczeni.controller.UserController;
import net.debreczeni.exception.ValidationException;
import net.debreczeni.model.AccessType;
import net.debreczeni.model.User;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class UserTableModel extends AbstractTableModel {
    public final static int ID = 0;
    public final static int USERNAME = 1;
    public final static int PASSWORD = 2;
    public final static int IS_MANAGER = 3;

    private final static UserController userController = UserController.getInstance();
    private final Supplier<List<User>> userSupplier;
    private List<User> users = new ArrayList<>();

    public UserTableModel(Supplier<List<User>> userSupplier) {
        this.userSupplier = userSupplier;
    }

    public void refresh() {
        this.users = userSupplier.get();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final User user = users.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return user.getId();
            case USERNAME:
                return user.getUsername();
            case PASSWORD:
                return "";
            case IS_MANAGER:
                return user.getAccessType() == AccessType.MANAGER;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case ID:
                return "ID";
            case USERNAME:
                return "Username";
            case PASSWORD:
                return "Password(Hidden)";
            case IS_MANAGER:
                return "Is Manager";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Integer.class;
            case USERNAME:
            case PASSWORD:
                return String.class;
            case IS_MANAGER:
                return Boolean.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != ID;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        final User user = users.get(rowIndex);
        switch (columnIndex) {
            case USERNAME:
                user.setUsername((String) aValue);
                break;
            case PASSWORD:
                user.setPassword((String) aValue);
                break;
            case IS_MANAGER:
                final AccessType accessType = (Boolean) aValue ? AccessType.MANAGER : AccessType.SELLER;
                user.setAccessType(accessType);
                break;
        }
    }

    public void persistData() {
        try {
            for (User user : users) {
                userController.update(user);
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Users successfully updated",
                    "Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Invalid values",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        refresh();
    }


    public void removeUser(int index) {
        final User user = users.get(index);
        if (user.getId() == null) {
            users.remove(index);
            fireTableDataChanged();
        } else {
            UserController.getInstance().removeUser(user);
            refresh();
        }
    }

    public void newUser() {
        users.add(new User());
        fireTableDataChanged();
    }
}
