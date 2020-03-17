package com.enigma.restservice.models.stock;
import com.enigma.restservice.models.item.ItemModel;
import com.enigma.restservice.models.unit.UnitModel;


public class StockModel {

    private Integer id;
    private ItemModel item;
    private Integer quantity;
    private UnitModel unit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public UnitModel getUnit() {
        return unit;
    }

    public void setUnit(UnitModel unit) {
        this.unit = unit;
    }
}
