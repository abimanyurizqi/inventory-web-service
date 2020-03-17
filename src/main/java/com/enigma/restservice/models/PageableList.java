package com.enigma.restservice.models;

import java.util.List;

public class PageableList<T> {
    private List<T> list;
    private Long total;
    private int size;

    public PageableList(List<T> list, Long total, int size, int page) {
        this.list = list;
        this.total = total;
        this.size = size;
        this.page = page;
    }

    private int page;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}

