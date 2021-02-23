package com.neoris.tcl.dao;

import java.util.Collection;
import java.util.Optional;

public interface IMasterDao<T> {
    Optional<T> get(Long id);
    Collection<T> getAll();
    int save(T t);
    void update(T t);
    void delete(T t);
}
