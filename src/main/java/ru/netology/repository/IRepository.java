package ru.netology.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {

    List<T> getAll();
    Optional<T> getById(long id);
    Optional<T> save(T t);
    Optional<T> removeById(long id);
}
