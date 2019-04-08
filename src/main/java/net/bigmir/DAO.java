package net.bigmir;

import java.util.List;

public interface DAO <T> {
    void initTable();
    void add(T t);
    void delete(T t);
    List<T> getList();
}
