package com.asaanloyalty.asaan.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.asaanloyalty.asaan.R;
import com.asaanloyalty.asaan.db.entity.OrderItem;
import com.asaanloyalty.asaan.db.entity.OrderProfile;

public class DisplayTicketGridAdapter extends BaseAdapter {

    private static final String TAG = DisplayTicketGridAdapter.class.getSimpleName();

    private Context mContext;
    private List<OrderProfile> mListProfile;
    Bitmap bMap;

    public DisplayTicketGridAdapter(Context context, List<OrderProfile> listProfile) {
        mContext = context;
        mListProfile = listProfile;
    }

    @Override
    public int getCount() {
        return mListProfile.size();
    }

    @Override
    public Object getItem(int position) {
        return mListProfile.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView tvTicketName;
        TextView tvTableName;
        TextView tvServertName;
        TextView tvAllergyItems;
        CheckBox cbItemStatus;
        TextView tvStatus;
        TextView tvOptions;
        TextView tvInstructions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderProfile entry = mListProfile.get(position);
        ViewHolder holder = null;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.grid_item_ticket, null);

            holder = new ViewHolder();
            holder.tvTicketName = (TextView)convertView.findViewById(R.id.tv_ticket_name);
            holder.tvTableName = (TextView)convertView.findViewById(R.id.tv_table_name);
            holder.tvServertName = (TextView)convertView.findViewById(R.id.tv_server_name);
            holder.tvAllergyItems = (TextView)convertView.findViewById(R.id.tv_allergy_items);
            holder.cbItemStatus = (CheckBox)convertView.findViewById(R.id.cb_item_status);
            holder.tvStatus = (TextView)convertView.findViewById(R.id.tv_status);
            holder.tvOptions = (TextView)convertView.findViewById(R.id.tv_selected_options);
            holder.tvInstructions = (TextView)convertView.findViewById(R.id.tv_special_instructions);


            //            holder.lvOrderItems.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.cbItemStatus.setEnabled(false);       

        holder.tvTicketName.setText(entry.getPOSTicket());
        holder.tvTableName.setText(entry.getTableName());
        holder.tvServertName.setText(entry.getServerPeerId());

        List<OrderItem> orderItemList = entry.getOrderItems();
        if(orderItemList.size() >= 1){

            holder.tvStatus.setText("" + orderItemList.get(0).getDeliveryStatus());
            holder.tvOptions.setText(orderItemList.get(0).getOrderDescription());
            holder.tvInstructions.setText(orderItemList.get(0).getSpecialInstruction());
        }


        //        Log.e("order item list size", "position = " + position + " size of list = " + entry.getOrderItems().size());

        return convertView;
    }

}
