package net.debreczeni.service;

import java.util.List;

public interface Service<T> {
    void create(T obj);

    List<T> getAll();

    void update(T obj);

    void delete(int id);
}
