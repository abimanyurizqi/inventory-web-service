package com.enigma.restservice.models.stock;

public class StockSummary {
    private String item;
    private Long quantity;
    private String unit;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public StockSummary(String item, Long quantity, String unit) {
        this.item = item;
        this.quantity = quantity;
        this.unit = unit;
    }
}
