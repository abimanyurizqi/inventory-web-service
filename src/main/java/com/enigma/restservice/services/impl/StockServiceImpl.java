package com.enigma.restservice.services.impl;

import com.enigma.restservice.entities.Stock;
import com.enigma.restservice.exceptions.EntityNotFoundException;
import com.enigma.restservice.repositories.StockRepository;
import com.enigma.restservice.models.stock.StockSummary;
import com.enigma.restservice.services.Service;
import com.enigma.restservice.services.StockSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class StockServiceImpl implements Service<Stock>, StockSummaryService {

    @Autowired
    private StockRepository stockRepository;

    @Transactional
    @Override
    public Stock save(Stock entity) {
        return stockRepository.save(entity);
    }

    @Transactional
    @Override
    public Stock removeById(Integer id) {
        Stock entity = findById(id);
        stockRepository.delete(entity);
        return entity;
    }

    @Override
    public Stock findById(Integer id) {
        return stockRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException();
        });
    }

    @Override
    public Page<Stock> findAll(Stock entity, int page, int size, Sort.Direction direction) {
        Sort sort = Sort.Direction.DESC.equals(direction) ? Sort.by("id").descending() : Sort.by("id");
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return stockRepository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, sort));
    }

    @Override
    public List<StockSummary> stockSummary() {
        return stockRepository.stockSummary();
    }
}
