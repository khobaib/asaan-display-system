package com.asaanloyalty.asaan.ui;

import com.asaanloyalty.asaan.db.entity.OrderItem;
import com.asaanloyalty.asaan.db.entity.OrderProfile;
import com.asaanloyalty.asaan.db.entity.ServerWrapper;
import com.asaanloyalty.asaan.util.AsaanConstants;

import android.os.Handler;
import android.os.Message;

public class UIP2PHandler extends Handler
{
	public static final int NEW_ORDER_PLACED = 1201;
	public static final int ORDER_STATUS_CHANGED = 1202;
	public static final int ORDER_ITEMS_CHANGED = 1203;
	public static final int SERVER_CHANGED = 1204;
	public static final int SERVER_CONNECTED = 1205;
	public static final int SERVER_DISCONNECTED = 1206;

	@Override
	public void handleMessage(Message msg)
	{
		switch (msg.what)
		{
			case NEW_ORDER_PLACED :
			{
				doNewOrderPlaced((OrderProfile)msg.obj);
				break;
			}
			case ORDER_STATUS_CHANGED :
			{
				doOrderStatusChanged((OrderProfile)msg.obj);
				break;
			}
			case ORDER_ITEMS_CHANGED :
			{
				doOrderItemsChanged((OrderItem[])msg.obj);
				break;
			}
			case SERVER_CHANGED :
			{
				doServerChanged((ServerWrapper)msg.obj);
				break;
			}
			case SERVER_CONNECTED :
			{
				doServerStatusChanged((Long)msg.obj, AsaanConstants.SERVER_CONNECTED);
				break;
			}
			case SERVER_DISCONNECTED :
			{
				doServerStatusChanged((Long)msg.obj, AsaanConstants.SERVER_DISCONNECTED);
				break;
			}
			default :
				break;
		}
	}

	private void doServerStatusChanged(Long obj, int status)
	{
		// TODO Auto-generated method stub
		
	}

	private void doServerChanged(ServerWrapper serverWrapper)
	{
		// TODO Auto-generated method stub
		
	}

	private void doOrderItemsChanged(OrderItem[] items)
	{
		// TODO Auto-generated method stub
		
	}

	private void doOrderStatusChanged(OrderProfile obj)
	{
		// TODO Auto-generated method stub
		
	}

	private void doNewOrderPlaced(OrderProfile obj)
	{
		// TODO Auto-generated method stub
		
	}
}
