package com.asaanloyalty.asaan.db.entity;

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
}

