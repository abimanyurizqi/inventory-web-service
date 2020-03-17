package com.enigma.restservice.services;

import com.enigma.restservice.models.transactions.TransactionSummary;

import java.time.Month;
import java.time.Year;
import java.util.List;

public interface TransactionSummaryService {
    public List<TransactionSummary> transactionSummary(Year year, Month month, Integer date);
}
