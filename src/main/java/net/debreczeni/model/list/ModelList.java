package net.debreczeni.model.list;

import java.util.List;

public abstract class ModelList<T> {
    public abstract List<T> getList();

    public abstract void setList(List<T> list);
}
