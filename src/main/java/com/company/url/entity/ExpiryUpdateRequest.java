package com.company.url.entity;

public class ExpiryUpdateRequest {
    private String shortUrl;
    private int daysToAdd;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getDaysToAdd() {
        return daysToAdd;
    }

    public void setDaysToAdd(int daysToAdd) {
        this.daysToAdd = daysToAdd;
    }
}
