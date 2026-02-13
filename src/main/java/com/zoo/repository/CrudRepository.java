package com.zoo.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    T save(T entity);
    List<T> findAll();
    Optional<T> findById(int id);
    T update(T entity);
    void delete(int id);

    // ✅ LANGUAGE FEATURE — default method
    default T existsOrThrow(int id) {
        return findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Entity with id " + id + " not found"));
    }
}
