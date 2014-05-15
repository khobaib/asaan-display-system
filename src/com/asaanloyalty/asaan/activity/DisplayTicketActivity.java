package com.asaanloyalty.asaan.activity;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.Toast;

import com.asaanloyalty.asaan.R;
import com.asaanloyalty.asaan.adapter.DisplayTicketGridAdapter;
import com.asaanloyalty.asaan.adapter.FoodItemListAdapter;
import com.asaanloyalty.asaan.db.entity.OrderItem;
import com.asaanloyalty.asaan.db.entity.OrderProfile;
//import android.widget.AdapterView.OnItemClickListener;

public class DisplayTicketActivity extends Activity {
    
    GridView gvTicketSystem;
    
    List<OrderProfile> listProfile;
    public static int prevOrderState, newOrderState;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_system_tickets);
        
        String stationName = getIntent().getExtras().getString("station_name");
        setTitle("Asaan Display System - " + stationName);
        
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
        for(int i = 1; i <= 30; i++){
            List<OrderItem> orderItemList = new ArrayList<OrderItem>();
            for(int j = 1; j <= (i%6) + 1 ; j++){
                OrderItem thisItem = new OrderItem(j, i, j, "item name", "order Description", "special instruction",
                        j + 3, System.currentTimeMillis(), j % 3, System.currentTimeMillis());
                orderItemList.add(thisItem);
            }
            OrderProfile thisProfile = new OrderProfile(i, i + 5, "server peer", "table name", "pOS Ticket",
                    orderItemList.toArray(new OrderItem[orderItemList.size()]), 1, System.currentTimeMillis());
            listProfile.add(thisProfile);
            
        }
        
        
        gvTicketSystem.setAdapter(new DisplayTicketGridAdapter(DisplayTicketActivity.this, listProfile));
        gvTicketSystem.setOnItemClickListener(mItemClickListener);
    }
    
    
//    public int getStatusBarHeight() {
//        int result = 0;
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = getResources().getDimensionPixelSize(resourceId);
//        }
//        return result;
//  }
    
    
    private OnItemClickListener mItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            OrderProfile selectedProfile =  (OrderProfile) parent.getItemAtPosition(position);
            
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
            orderState = ( orderState | ((orderItemList.get(i).getDeliveryStatus() == 2)? 1 : 0) << i); 
        }
        return orderState;
    }
    
    
    
    private void showOrderDetailsDialog(OrderProfile orderProfile) {
        LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
        View textEntryView = inflater.inflate(R.layout.dialog_ticket, null);
        final AlertDialog alert = new AlertDialog.Builder(DisplayTicketActivity.this).create();
//        alert.setView(textEntryView, 0, 0, 0, 0);
        alert.setCancelable(false);
        alert.setView(textEntryView);
        
        CheckBox cbItemStatus = (CheckBox)textEntryView.findViewById(R.id.cb_item_status);
        TextView tvTicketName = (TextView)textEntryView.findViewById(R.id.tv_ticket_name);
        TextView tvTableName = (TextView)textEntryView.findViewById(R.id.tv_table_name);
        TextView tvServertName = (TextView)textEntryView.findViewById(R.id.tv_server_name);
        TextView tvAllergyItems = (TextView)textEntryView.findViewById(R.id.tv_allergy_items);
        
        final ListView orderList =  (ListView) textEntryView.findViewById(R.id.lv_order_item_list);
//        orderList.setEnabled(false);
        
        tvTicketName.setText(orderProfile.getPOSTicket());
        tvTableName.setText(orderProfile.getTableName());
        tvServertName.setText(orderProfile.getServerPeerId());
        
        List<OrderItem> orderItem = orderProfile.getOrderItems();
        FoodItemListAdapter foodItemListAdapter = new FoodItemListAdapter(DisplayTicketActivity.this, orderItem);
        orderList.setAdapter(foodItemListAdapter);

        

//        FileList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        orderList.setOnItemClickListener(new OnItemClickListener() {
            
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//              chosedFile = (String) parent.getItemAtPosition(position);
              Toast.makeText(DisplayTicketActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
        
        
        
        
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
