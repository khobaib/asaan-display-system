package com.asaanloyalty.asaan.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.asaanloyalty.asaan.R;
import com.asaanloyalty.asaan.activity.DisplayTicketActivity;
import com.asaanloyalty.asaan.db.entity.OrderItem;

public class FoodItemListAdapter extends BaseAdapter{

    private static final String TAG = FoodItemListAdapter.class.getSimpleName();

    private Context mContext;
    private List<OrderItem> mListItem;
    private boolean[] checkBoxState;

    public FoodItemListAdapter(Context context, List<OrderItem> listItem) {
        mContext = context;
        mListItem = listItem;
        checkBoxState = new boolean[mListItem.size()];
        
        int orderState = DisplayTicketActivity.prevOrderState;
        int position = 0;
        while(orderState > 0){
            if((orderState % 2) == 1){
                Log.e(TAG, "Item status checked at position = " + position);
                checkBoxState[position] = true;
            }
            orderState/= 2;
            position++;
        }
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

    static class ViewHolder {
        CheckBox cbItemStatus;
        TextView tvStatus;
        TextView tvOptions;
        TextView tvInstructions;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
            //            convertView.setTag(R.id.cb_item_status, holder.cbItemStatus);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }



        OrderItem entry = mListItem.get(position);
        holder.cbItemStatus.setChecked(checkBoxState[position]);
        
        if(entry.getDeliveryStatus() != 1){
            convertView.setBackgroundColor(Color.argb(0xff, 0xe1, 0xe1, 0xe1));
            Log.e(TAG, "checkbox pre-checked for position - " + position);
            holder.cbItemStatus.setEnabled(false);
        }
        else{
            convertView.setBackgroundColor(Color.WHITE);
            holder.cbItemStatus.setEnabled(true);           
        }

        holder.cbItemStatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    checkBoxState[position] = true;
                } else {
                    checkBoxState[position] = false;
                }

                Log.e(TAG, "position = " + position + " and isChecked = " + ((CheckBox) v).isChecked());
                DisplayTicketActivity.newOrderState = (DisplayTicketActivity.newOrderState ^ (1 << position));
            }
        });

        holder.tvStatus.setText("" + entry.getDeliveryStatus());
        holder.tvOptions.setText(entry.getOrderDescription());
        holder.tvInstructions.setText(entry.getSpecialInstruction());

        return convertView;
    }

}
