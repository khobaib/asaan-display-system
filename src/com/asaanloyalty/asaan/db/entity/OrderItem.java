package com.asaanloyalty.asaan.db.entity;

import lombok.Getter;
import lombok.Setter;

public class OrderItem {
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
    
    @Getter
    @Setter    
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

}
