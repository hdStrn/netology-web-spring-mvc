package ru.netology.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
public interface IController<T, S> {

    @GetMapping
    List<T> getAll();
    @GetMapping("/{id}")
    T getById(@PathVariable long id);
    @PostMapping
    T save(@RequestBody S s);
    @DeleteMapping("/{id}")
    String removeById(@PathVariable long id);
}
