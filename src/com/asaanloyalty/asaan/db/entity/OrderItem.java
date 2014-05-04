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
}
