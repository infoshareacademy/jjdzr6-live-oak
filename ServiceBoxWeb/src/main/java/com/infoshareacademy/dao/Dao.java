package com.infoshareacademy.dao;

import java.util.List;

public interface Dao<T> {
    T findById(Long id);

    List<T> findAll();

    void save(T t);

    T update(T t);

    void delete(T t);
}
