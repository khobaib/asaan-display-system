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
	
    public static final String[] TABLE_NAME_PREFIX = {"A", "P", "C"};
    public static final String[] SERVER_PEER_NAME = {"Chris Brown", "Tom Cook", "Noor Awan", "Uwe Hauldt", "Dan Boyle"};
    public static final String[] ITEM_NAME = {"Pepperoni Pizza", "Pasta Bolognese", "lasagne",
        "Pizza Milano", "Soya Milk", "Coconut Milk", "Fried Rice", "Chicken Fry", "Mashed Potato", "Frappe"};
    public static final String[] SELECTED_OPTIONS = {
        "Large, Cheesy Crust, Extra Toppings", "Sticky Cheese, half-baked pasta, 2 sauce",
        "Extra Cheese, eggplant, half-baked", "Medium, Sausage Crust, Extra Toppings",
        "Extra Soy, No Ice", "Concentrated Milk, Normal", "Egg, Chicken & Beef Mixed",
        "Extra Spicy, 2 Sauce", "Gravy & Hot", "No Ice, Medium Size"
    };
    public static final String[] SPECIAL_INSTRUCTIONS = {"Extra Pepperoni", "Sticky Cheese", "Extra Saucy",
        "Extra Cheese", "2 Straw", "Green Coconut", "No vege", "Breast Piece", "Extra Salad", "Extra Frappe"
    };
	
	public static String[] ALLERGY_FIRST_ITEMS = {"Peanuts", "gluten", "Cocoa", "egg", "Tree nuts"};
	public static String[] ALLERGY_SECOND_ITEMS = {"Soynuts", "crustacea", "Pepperoni", "wheat", "Coconuts"};

	public static int SERVER_CONNECTED = 0;
	public static int SERVER_DISCONNECTED = 1;
}
