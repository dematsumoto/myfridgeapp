package com.example.douglas.myfridgeapp.domain;

public class FridgeItem {

    private String Id;
    private String name;
    private String startDate;
    private String validUntilDate;
    private String active;
    private String status;

    public FridgeItem(String name, String startDate, String validUntilDate, String active) {
        this.name = name;
        this.startDate = startDate;
        this.validUntilDate = validUntilDate;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getValidUntilDate() {
        return validUntilDate;
    }

    public void setValidUntilDate(String validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
