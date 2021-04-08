package net.debreczeni.service;

import net.debreczeni.model.User;

import java.util.List;

public interface Service<T> {
    void create(T obj);

    List<T> getAll();

    void update(T obj);

    void delete(int id);
}
