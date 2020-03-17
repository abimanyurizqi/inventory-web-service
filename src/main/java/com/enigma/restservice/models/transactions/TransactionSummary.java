package com.enigma.restservice.models.transactions;
import com.enigma.restservice.entities.TypeTransaction;

public class TransactionSummary {
    private Long id;
    private TypeTransaction type;
    private double amount;

    public TransactionSummary(Long id, TypeTransaction type, double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public Long getJumlah() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
