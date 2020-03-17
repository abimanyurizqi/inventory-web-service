package com.enigma.restservice.repositories;

import com.enigma.restservice.models.transactions.TransactionSummary;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepositoryCustom {
    public List<TransactionSummary> transactionSummary(LocalDate from, LocalDate to);


}
