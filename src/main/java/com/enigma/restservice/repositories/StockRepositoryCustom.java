package com.enigma.restservice.repositories;

import com.enigma.restservice.models.stock.StockSummary;

import java.util.List;

public interface StockRepositoryCustom {
    public List<StockSummary> stockSummary();
}
