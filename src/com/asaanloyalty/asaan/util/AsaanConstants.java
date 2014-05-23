package com.asaanloyalty.asaan.util;

public class AsaanConstants
{
	public static int ORDER_STATUS_COMPLETED = 0;
	public static int ORDER_STATUS_CANCELLED = 1;
	public static int ORDER_STATUS_PENDING = 2;
	public static int ORDER_STATUS_REOPENED = 3;
	
	public static int ORDER_ITEM_STATUS_COMPLETED = 0;
	public static int ORDER_ITEM_STATUS_RELEASED = 1;
	public static int ORDER_ITEM_STATUS_PENDING = 2;
	
	public static String[] order_state = {"Completed", "Released", "Pending"};
	
	public static String[] allergy_first_items = {"Peanuts", "gluten", "Cocoa", "egg", "Tree nuts"};
	public static String[] allergy_second_items = {"Soynuts", "crustacea", "Pepperoni", "wheat", "Coconuts"};

	public static int SERVER_CONNECTED = 0;
	public static int SERVER_DISCONNECTED = 1;
}
