package com.enigma.restservice.models.transactions;


import com.enigma.restservice.validation.annotations.MinLength;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransactionModel {

    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull(message = "{amount.notblank}")
    private Double amount;

    @MinLength(3)
    @NotBlank(message = "{description.notblank}")
    private String description;

    @NotBlank(message = "{typeTransaction.notblank}")
    private String typeTransaction;


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

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

}
