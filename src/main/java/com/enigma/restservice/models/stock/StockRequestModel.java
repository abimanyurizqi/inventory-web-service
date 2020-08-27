package com.enigma.restservice.models.stock;


import javax.validation.constraints.NotNull;

public class StockRequestModel {

    @NotNull(message = "{item.notblank}")
    private Integer itemId;
    @NotNull(message = "{unit.notblank}")
    private Integer unitId;
    @NotNull(message = "{quantity.notblank}")
    private Integer quantity;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
