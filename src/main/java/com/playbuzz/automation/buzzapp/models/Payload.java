package com.playbuzz.automation.buzzapp.models;

import java.util.List;

public class Payload {

    private Integer currentItemCount;
    private Integer totalItems;
    private List<Article> items;

    public Payload() {}

    public Integer getCurrentItemCount() {
        return currentItemCount;
    }

    public void setCurrentItemCount(Integer currentItemCount) {
        this.currentItemCount = currentItemCount;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<Article> getItems() {
        return items;
    }

    public void setItems(List<Article> items) {
        this.items = items;
    }
}
