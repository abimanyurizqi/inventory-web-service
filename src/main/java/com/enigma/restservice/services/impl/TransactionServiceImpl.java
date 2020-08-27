package com.enigma.restservice.services.impl;
import com.enigma.restservice.entities.Transaction;
import com.enigma.restservice.exceptions.EntityNotFoundException;
import com.enigma.restservice.repositories.TransactionRepository;
import com.enigma.restservice.models.transactions.TransactionSummary;
import com.enigma.restservice.services.Service;
import com.enigma.restservice.services.TransactionSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Transactional
@org.springframework.stereotype.Service
public class TransactionServiceImpl implements Service<Transaction, Integer>, TransactionSummaryService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction entity) {

        return transactionRepository.save(entity);
    }

    @Override
    public Transaction removeById(Integer id) {
        Transaction entity = findById(id);
        transactionRepository.delete(entity);
        return entity;
    }

    @Override
    public Transaction findById(Integer id) {
        return transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Page<Transaction> findAll(Transaction entity, int page, int size, Sort.Direction direction) {

        Sort sort = Sort.Direction.DESC.equals(direction) ? Sort.by("id").descending() : Sort.by("id");
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return transactionRepository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, sort));
    }


    @Override
    public List<TransactionSummary> transactionSummary(Year year, Month month, Integer date) {
        LocalDate from = LocalDate.of(year.getValue(), 1, 1);
        LocalDate to = LocalDate.of(year.getValue(), 12, 1);

        if (month != null) {
            from = from.withMonth(month.getValue());
            to = to.withMonth(month.getValue());
        }

        if (date != null) {
            from = from.withDayOfMonth(1);
            to = to.withDayOfMonth(date);
        } else {
            from = from.withDayOfMonth(1);
            to = to.with(TemporalAdjusters.lastDayOfMonth());
        }
        List<TransactionSummary> summary = transactionRepository.transactionSummary(from, to);
        return summary;
    }
}
