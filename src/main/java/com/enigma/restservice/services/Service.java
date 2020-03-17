package com.enigma.restservice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface Service<E> {

    public E save(E entity);

    public E removeById(Integer id);

    public E findById(Integer id);

    public Page<E> findAll(E entity, int page, int size, Sort.Direction sort);

}
