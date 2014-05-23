package com.asaanloyalty.asaan.db.entity;

import lombok.Getter;
import lombok.Setter;

public class OrderItem
{
    @Getter
    @Setter
    long Id;

    @Getter
    @Setter
    long orderProfileId;

    @Getter
    @Setter
    long menuItemId;

    @Getter
    @Setter
    String menuItemName;

    @Getter
    @Setter
    String orderDescription;
    
    String selectedOptions;

    @Getter
    @Setter
    String specialInstruction;

    @Getter
    @Setter
    int quantity;

    @Getter
    long createdDate;

    @Getter
    @Setter
    int deliveryStatus; // ORDER_ITEM_STATUS_*

    @Getter
    long deliveredDate;

    public OrderItem() {
        // TODO Auto-generated constructor stub
    }



    public OrderItem(long id, long orderProfileId, long menuItemId, String menuItemName,
            String orderDescription, String selectedOptions, String specialInstruction,
            int quantity, long createdDate, int deliveryStatus, long deliveredDate) {
        Id = id;
        this.orderProfileId = orderProfileId;
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.orderDescription = orderDescription;
        this.selectedOptions = selectedOptions;
        this.specialInstruction = specialInstruction;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.deliveryStatus = deliveryStatus;
        this.deliveredDate = deliveredDate;
    }



    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getOrderProfileId() {
        return orderProfileId;
    }

    public void setOrderProfileId(long orderProfileId) {
        this.orderProfileId = orderProfileId;
    }

    public long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
    
    public String getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(String selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public int getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(int deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public long getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(long deliveredDate) {
        this.deliveredDate = deliveredDate;
    }



}
