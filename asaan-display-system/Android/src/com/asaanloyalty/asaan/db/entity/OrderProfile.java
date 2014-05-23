package com.asaanloyalty.asaan.db.entity;

import lombok.Getter;
import lombok.Setter;

public class OrderProfile {
    @Getter
    @Setter
    long Id;

    @Getter
    @Setter
    long serverId;

    @Getter
    @Setter
    String serverName;

    @Getter
    @Setter
    String serverPeerId;

    @Getter
    @Setter
    String tableName;

    @Getter
    @Setter
    String POSTicket;

    @Getter
    @Setter
    OrderItem[] orderItems;

    @Getter
    @Setter
    int orderStatus = 2; // ORDER_STATUS_*, default is pending

    @Getter
    @Setter
    long createdDate;

    @Getter
    @Setter
    String allregiesItem;


    public OrderProfile() {
        // TODO Auto-generated constructor stub
    }


    public OrderProfile(long id, long serverId, String serverName, String serverPeerId, String tableName, String pOSTicket,
            OrderItem[] orderItems, int orderStatus, long createdDate, String allregiesItem) {
        Id = id;
        this.serverId = serverId;
        this.serverName = serverName;
        this.serverPeerId = serverPeerId;
        this.tableName = tableName;
        this.POSTicket = pOSTicket;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
        this.allregiesItem = allregiesItem;
    }   
}

