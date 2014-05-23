package com.asaanloyalty.asaan.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.asaanloyalty.asaan.R;
import com.asaanloyalty.asaan.adapter.DisplayTicketGridAdapter;
import com.asaanloyalty.asaan.adapter.FoodItemListAdapter;
import com.asaanloyalty.asaan.db.entity.OrderItem;
import com.asaanloyalty.asaan.db.entity.OrderProfile;
import com.asaanloyalty.asaan.network.ClientAPIWrapper;
import com.asaanloyalty.asaan.util.AsaanConstants;


public class DisplayTicketActivity extends Activity {
    
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
    
    
    
    GridView gvTicketSystem;
    
    static DisplayTicketGridAdapter displayTicketGridAdapter;   
    static List<OrderProfile> listProfile;
    public static int prevOrderState, newOrderState;
    
    int lastSelectedTicketIndex;
    
    ClientAPIWrapper workerThread;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_system_tickets);
        
        String stationName = getIntent().getExtras().getString("station_name");
        setTitle("Asaan Display System - " + stationName);
        
        workerThread = new ClientAPIWrapper(this);
        workerThread.start();
        
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        
//        int deviceHeight = metrics.heightPixels;
//        float deviceDensity = metrics.density;
//        
//        int statusBarHeight = getStatusBarHeight();
//        
//        Log.e(">>>>>>>>>>>>>", "device height & density dpi = " + deviceHeight + ", " + deviceDensity);
//        Log.e(">>>>>>>>>>>>>", "status bar height = " + statusBarHeight);
        
        gvTicketSystem = (GridView) findViewById(R.id.gv_ticket_system);
        
//        int rowHeight = (int) ((deviceHeight - 10 * 1 * deviceDensity - statusBarHeight) / 2);
//        Log.e(">>>>>>>>>>>>>", "ROW height =" + rowHeight);
//        gvTicketSystem.setRowHeight(rowHeight);
        
        listProfile = new ArrayList<OrderProfile>();
        
        /*
         * Hardcoded data section
         */
//        Random r = new Random();
//        for(int i = 1; i <= 30; i++){
//            List<OrderItem> orderItemList = new ArrayList<OrderItem>();
//            for(int j = 1; j <= (i%6) + 1 ; j++){
//                
//                int randomVal = r.nextInt(10);
//                String itemName = ITEM_NAME[randomVal];
//                String selectedOp = SELECTED_OPTIONS[randomVal];
//                String specialIns = SPECIAL_INSTRUCTIONS[randomVal];
//                
//                OrderItem thisItem = new OrderItem(j, i, j, itemName, "orderDesc", selectedOp, specialIns,
//                        j + 3, System.currentTimeMillis(), r.nextInt(3), System.currentTimeMillis());
//                orderItemList.add(thisItem);
//            }
//            
//            int posTicketBase = 545 + i;
//            String tableName = TABLE_NAME_PREFIX[i%3] + (r.nextInt(50) + 10); 
//            String serverPeerName = SERVER_PEER_NAME[r.nextInt(5)];
//            
//            OrderProfile thisProfile = new OrderProfile(i, i + 5, "Server: " + serverPeerName, "serverPeer", 
//                    "Table: " + tableName, "A-" + posTicketBase,
//                    orderItemList.toArray(new OrderItem[orderItemList.size()]), 1, System.currentTimeMillis(), "Peanuts, Soynuts");
//            listProfile.add(thisProfile);
//            
//        }
        
        displayTicketGridAdapter = new DisplayTicketGridAdapter(DisplayTicketActivity.this, listProfile);
        gvTicketSystem.setAdapter(displayTicketGridAdapter);
        gvTicketSystem.setOnItemClickListener(mItemClickListener);
    }
    
    
    public static void addNewOrder(OrderProfile orderProfile){
        listProfile.add(orderProfile);
        displayTicketGridAdapter.notifyDataSetChanged();
        
    }
    
    
//    public int getStatusBarHeight() {
//        int result = 0;
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = getResources().getDimensionPixelSize(resourceId);
//        }
//        return result;
//  }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(workerThread != null){
            ClientAPIWrapper newThread = workerThread;
            workerThread = null;
            newThread.interrupt();
        }

    }
    
    
    
    private OnItemClickListener mItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            OrderProfile selectedProfile =  (OrderProfile) parent.getItemAtPosition(position);
            lastSelectedTicketIndex = position;
//            Toast.makeText(DisplayTicketActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
            
            prevOrderState = getFoodItemStatus(selectedProfile);
            Log.e("", "prevOrderState = " + prevOrderState);
            newOrderState = prevOrderState;
            showOrderDetailsDialog(selectedProfile);
            
        }
    };
    
    private int getFoodItemStatus(OrderProfile orderProfile){
        List<OrderItem> orderItemList = orderProfile.getOrderItems();
        int orderState = 0;
        for(int i = 0; i < orderItemList.size(); i++){
            orderState = ( orderState | ((orderItemList.get(i).getDeliveryStatus() != 1)? 1 : 0) << i); 
        }
        return orderState;
    }
    
    
    
    private void showOrderDetailsDialog(OrderProfile orderProfile) {
        LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
        View textEntryView = inflater.inflate(R.layout.dialog_ticket, null);
        final AlertDialog alert = new AlertDialog.Builder(DisplayTicketActivity.this).create();
        alert.setCancelable(false);
        alert.setView(textEntryView);
        
        CheckBox cbItemStatus = (CheckBox)textEntryView.findViewById(R.id.cb_item_status);
        TextView tvTicketName = (TextView)textEntryView.findViewById(R.id.tv_ticket_name);
        TextView tvTableName = (TextView)textEntryView.findViewById(R.id.tv_table_name);
        TextView tvServertName = (TextView)textEntryView.findViewById(R.id.tv_server_name);
        TextView tvAllergyItems = (TextView)textEntryView.findViewById(R.id.tv_allergy_items);
        
        final ListView orderList =  (ListView) textEntryView.findViewById(R.id.lv_order_item_list);
        
        tvTicketName.setText(orderProfile.getPOSTicket());
        tvTableName.setText(orderProfile.getTableName());
        tvServertName.setText(orderProfile.getServerName());        
        
        Random r = new Random();
        String allergy1 = AsaanConstants.allergy_first_items[r.nextInt(5)]; 
        String allergy2 = AsaanConstants.allergy_second_items[r.nextInt(5)]; 
        tvAllergyItems.setText("Allergies: " + allergy1 + ", " + allergy2);
        
        List<OrderItem> orderItem = orderProfile.getOrderItems();
        FoodItemListAdapter foodItemListAdapter = new FoodItemListAdapter(DisplayTicketActivity.this, orderItem);
        orderList.setAdapter(foodItemListAdapter);
             
        
        
        Button OK = (Button) textEntryView.findViewById(R.id.b_ok);
        OK.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                alert.dismiss();  
                Log.e("", "optionSelected = " + newOrderState);
                
                int position = 0;
                while(newOrderState > 0){
                    if((prevOrderState % 2) != (newOrderState % 2)){
                        Log.e(">>>>>>>>", "Item status changed at position = " + position);
                        
                        // only order delivery state can be changed from Released to Complete,i.e. 1 to 2
                        listProfile.get(lastSelectedTicketIndex).getOrderItems().get(position).setDeliveryStatus(2);
                        
                    }
                    prevOrderState/= 2;
                    newOrderState/= 2;
                    position++;
                }
                
            }

        });
        
        Button Cancel = (Button) textEntryView.findViewById(R.id.b_cancel);
        Cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                alert.dismiss();    
            }

        });
        alert.show();
        
    }

}
