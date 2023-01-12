package ru.netology.service;

import java.util.List;

public interface IService<T> {

    List<T> getAll();
    T getById(long id);
    T save(T t);
    String removeById(long id);
}
