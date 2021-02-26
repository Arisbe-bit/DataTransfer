package com.neoris.tcl.dao;

import java.util.List;
import java.util.Optional;

public interface IMasterDao<T> {
    Optional<T> find(String id);
    List<T> findAll();
    T save(T entity);
    List<T> saveAll(List<T> entityList);
    void delete(T entity);
}
