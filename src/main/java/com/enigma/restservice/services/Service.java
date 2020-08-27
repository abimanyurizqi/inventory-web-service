package com.enigma.restservice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;


public interface Service<T, ID> {

    public T save(T entity);

    public T removeById(ID id);

    public T findById(ID id);

    public Page<T> findAll(T entity, int page, int size, Sort.Direction sort);

}
