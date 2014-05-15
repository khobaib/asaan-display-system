package com.asaanloyalty.asaan.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.asaanloyalty.asaan.R;
import com.asaanloyalty.asaan.activity.DisplayTicketActivity;
import com.asaanloyalty.asaan.db.entity.OrderItem;

public class FoodItemListAdapter extends BaseAdapter{
    
    private static final String TAG = FoodItemListAdapter.class.getSimpleName();

    private Context mContext;
    private List<OrderItem> mListItem;

    public FoodItemListAdapter(Context context, List<OrderItem> listItem) {
        mContext = context;
        mListItem = listItem;
    }

    @Override
    public int getCount() {
        return mListItem.size();
    }

    @Override
    public Object getItem(int position) {
        return mListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        if(mListItem.get(position).getDeliveryStatus() == 2){
            return false;
        }
        return true;
        // return false if position == position you want to disable
    }
    
    static class ViewHolder {
        CheckBox cbItemStatus;
        TextView tvStatus;
        TextView tvOptions;
        TextView tvInstructions;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        OrderItem entry = mListItem.get(position);
        ViewHolder holder = null;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.row_item_order, null);
            
            holder = new ViewHolder();
            holder.cbItemStatus = (CheckBox)convertView.findViewById(R.id.cb_item_status);
            holder.tvStatus = (TextView)convertView.findViewById(R.id.tv_status);
            holder.tvOptions = (TextView)convertView.findViewById(R.id.tv_selected_options);
            holder.tvInstructions = (TextView)convertView.findViewById(R.id.tv_special_instructions);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        
        if(entry.getDeliveryStatus() == 2){
            convertView.setBackgroundColor(Color.argb(0xff, 0xe1, 0xe1, 0xe1));
            holder.cbItemStatus.setChecked(true);
            holder.cbItemStatus.setEnabled(false);
        }
        else{
            convertView.setBackgroundColor(Color.WHITE);
        }
        
        holder.cbItemStatus.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e(">>>>>>>", "position = " + position + " and isChecked = " + isChecked);
                DisplayTicketActivity.newOrderState = (DisplayTicketActivity.newOrderState ^ (1 << position));
                
            }
        });

        holder.tvStatus.setText("" + entry.getDeliveryStatus());
        holder.tvOptions.setText(entry.getOrderDescription());
        holder.tvInstructions.setText(entry.getSpecialInstruction());

        return convertView;
    }

}
