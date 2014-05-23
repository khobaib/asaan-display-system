package com.asaanloyalty.asaan.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Message;

import com.asaanloyalty.asaan.activity.DisplayTicketActivity;
import com.asaanloyalty.asaan.db.entity.OrderItem;
import com.asaanloyalty.asaan.db.entity.OrderProfile;
import com.asaanloyalty.asaan.ui.UIP2PHandler;

public class ClientAPIWrapper extends Thread {

    Context mContext;
    Random r = new Random();
    int orderProfileIdCounter = 1;

    public ClientAPIWrapper(Context context) {
        mContext = context;
    }

    @Override
    public void run() {
        super.run();

        while(!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(10000);
                if(Thread.currentThread().isInterrupted())
                    return;
                ((Activity)mContext).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        UIP2PHandler handler = new UIP2PHandler(mContext);                                                                   

                        int orderProfileId = orderProfileIdCounter++;
                        int orderItemCount = r.nextInt(6);
                        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
                        
                        for(int i = 1; i <= orderItemCount; i++){                                                    
                            
                            int randomVal = r.nextInt(10);
                            String itemName = DisplayTicketActivity.ITEM_NAME[randomVal];
                            String selectedOp = DisplayTicketActivity.SELECTED_OPTIONS[randomVal];
                            String specialIns = DisplayTicketActivity.SPECIAL_INSTRUCTIONS[randomVal];
                            int qty = r.nextInt(5) + 1; 
                            
                            OrderItem thisItem = new OrderItem(i, orderProfileId, i, itemName, "orderDesc", selectedOp, specialIns,
                                    qty, System.currentTimeMillis(), r.nextInt(3), System.currentTimeMillis());
                            orderItemList.add(thisItem);
                        }
                        
                        
                        int posTicketBase = 545 + orderProfileId;
                        String tableName = DisplayTicketActivity.TABLE_NAME_PREFIX[orderProfileId%3] + (r.nextInt(50) + 10); 
                        String serverPeerName = DisplayTicketActivity.SERVER_PEER_NAME[r.nextInt(5)];
                        
                        OrderProfile orderProfile = new OrderProfile(orderProfileId, orderProfileId + 5, "Server: " + serverPeerName, "serverPeer", 
                                "Table: " + tableName, "A-" + posTicketBase,
                                orderItemList.toArray(new OrderItem[orderItemList.size()]), 1, System.currentTimeMillis(), "Peanuts, Soynuts");
                        
                        Message msg = Message.obtain();
                        msg.what = UIP2PHandler.NEW_ORDER_PLACED;
                        msg.obj = orderProfile;                        
                        
                        handler.sendMessage(msg);
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

}
