package com.enigma.restservice.repositories;
import com.enigma.restservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.time.Year;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>, TransactionRepositoryCustom {
}
