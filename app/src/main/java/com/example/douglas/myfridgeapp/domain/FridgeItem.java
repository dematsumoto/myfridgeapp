package com.example.douglas.myfridgeapp.domain;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class FridgeItem implements Parent<ItemOptions>{

    private String Id;
    private String name;
    private String startDate;
    private String validUntilDate;
    private String isActive;
    private String status;

    private List<ItemOptions> mItemOptions;


    public FridgeItem(List<ItemOptions> itemOptions){
        mItemOptions = itemOptions;
    };

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

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public List<ItemOptions> getChildList() {
        return mItemOptions;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
