package com.enigma.restservice.repositories.impl;
import com.enigma.restservice.entities.Transaction;
import com.enigma.restservice.repositories.TransactionRepositoryCustom;
import com.enigma.restservice.models.transactions.TransactionSummary;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<TransactionSummary> transactionSummary(LocalDate from, LocalDate to) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TransactionSummary> criteria = builder.createQuery(TransactionSummary.class);
        Root<Transaction> root = criteria.from(Transaction.class);
        criteria.multiselect(builder.count(root.get("id")), root.get("type"), builder.sum(root.get("amount")))
                .where(builder.between(builder.function("DATE", Date.class, root.get("createdDate")),
                                Date.valueOf(from),
                                Date.valueOf(to)
                        )
                )
                .groupBy(root.get("type"));
        return entityManager.createQuery(criteria).getResultList();
    }



}
