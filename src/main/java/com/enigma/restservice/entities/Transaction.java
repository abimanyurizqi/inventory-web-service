package com.enigma.restservice.entities;

import javax.persistence.*;

@Table(name = "transaction")

@Entity
public class Transaction extends AbstractEntity{
    private Double amount;

    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    public Transaction(Double amount, String description, TypeTransaction type) {
        this.amount = amount;
        this.description = description;
        this.type = type;
    }

    public Transaction() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeTransaction getTypeTransaction() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id" + getId() +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }

    public void setTypeTransaction(TypeTransaction type) {

        this.type = type;
    }


}
