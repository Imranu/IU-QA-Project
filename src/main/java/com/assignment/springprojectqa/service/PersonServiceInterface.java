package com.assignment.springprojectqa.service;

import java.util.List;

public interface PersonServiceInterface<T> {

    T create(T t);

    List<T> getAll();

    T getById(Long id);

    T updateById(Long id, T t);

    T deleteById(Long id);

}
