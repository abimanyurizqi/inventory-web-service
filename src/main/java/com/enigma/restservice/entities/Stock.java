package com.enigma.restservice.entities;


import javax.persistence.*;

@Table(name = "stock")
@Entity
public class Stock extends AbstractEntity{


    @ManyToOne(targetEntity = Unit.class)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;


    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private Integer quantity;


    public Stock(Item item, Integer quantity, Unit unit) {
        this.unit = unit;
        this.item = item;
        this.quantity = quantity;
    }

    public Stock() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Stock{" +
                ", item" + item +
                ", quantity=" + quantity +
                ", unit" + unit +
                '}';
    }
}
