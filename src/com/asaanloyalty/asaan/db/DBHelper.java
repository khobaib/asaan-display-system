package com.asaanloyalty.asaan.db;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.asaanloyalty.asaan.db.entity.KdsProfile;

public class DBHelper
{
	private static DBHelper dbHelper = null;
	private static final Logger logger = Logger.getLogger(DBHelper.class.getName());

	/**
	 * Creates or returns the only instance of DBHelper (lazy instantiation)
	 */
	public static DBHelper getInstance()
	{
		if (dbHelper == null)
		{
			logger.log(Level.SEVERE, "dbHelper is null");
			dbHelper = new DBHelper();
		}
		
		return dbHelper;
	}
	
	public List<KdsProfile> getAllKdsProfiles()
	{
		List<KdsProfile> pList = new ArrayList<KdsProfile>();
		KdsProfile p1 = new KdsProfile();
		p1.setId(1);
		p1.setName("Kitchen 1");
		pList.add(p1);

		KdsProfile p2 = new KdsProfile();
		p2.setId(1);
		p2.setName("Bar 1");
		pList.add(p2);
		
		KdsProfile p3 = new KdsProfile();
		p3.setId(1);
		p3.setName("Kitchen 2");
		pList.add(p3);
		
		KdsProfile p4 = new KdsProfile();
		p4.setId(1);
		p4.setName("Patio Bar");
		pList.add(p4);
		
		return pList;
	}

}
