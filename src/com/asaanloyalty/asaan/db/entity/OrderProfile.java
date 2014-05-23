package com.asaanloyalty.asaan.db.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class OrderProfile
{
	@Getter
	@Setter
	long Id;
	
	@Getter
	@Setter
	long serverId;
	
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
	long createdDate;
	
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




    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }
       
    public String getServerName() {
        return serverName;
    }


    public void setServerName(String serverName) {
        this.serverName = serverName;
    }


    public String getServerPeerId() {
        return serverPeerId;
    }

    public void setServerPeerId(String serverPeerId) {
        this.serverPeerId = serverPeerId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPOSTicket() {
        return POSTicket;
    }

    public void setPOSTicket(String pOSTicket) {
        POSTicket = pOSTicket;
    }

    public List<OrderItem> getOrderItems() {
        
        if(orderItems == null){
            return new ArrayList<OrderItem>();
        } else{
            return Arrays.asList(orderItems);
        }
    }

    public void setOrderItems(OrderItem[] orderItems) {
        this.orderItems = orderItems;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }


    public String getAllregiesItem() {
        return allregiesItem;
    }


    public void setAllregiesItem(String allregiesItem) {
        this.allregiesItem = allregiesItem;
    }   
	
}

