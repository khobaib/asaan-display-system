package com.asaanloyalty.asaan.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.asaanloyalty.asaan.R;
import com.asaanloyalty.asaan.db.DBHelper;
import com.asaanloyalty.asaan.db.entity.KdsProfile;
import com.asaanloyalty.asaan.util.NothingSelectedSpinnerAdapter;

public class SelectStationActivity extends Activity {
    
    Spinner sDisplayList;
    
    List<KdsProfile> kdsProfile;
    int selectedProfileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_select_station);
        
        setTitle("Asaan Display System - Tickets");
        
        sDisplayList = (Spinner) findViewById(R.id.s_select_display_station);
        
        sDisplayList.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                selectedProfileId = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                
            }
        });
        
        kdsProfile = DBHelper.getInstance().getAllKdsProfiles();
        generateSpinner(sDisplayList, getProfileNames(kdsProfile));
        
        
    }
    
    
    public List<String> getProfileNames(List<KdsProfile> kdsProfile){
        
        if(kdsProfile == null){
            return new ArrayList<String>();
        }else{
            List<String> profileNameList = new ArrayList<String>();
            for(KdsProfile profile : kdsProfile){
                profileNameList.add(profile.getName());
            }
            return profileNameList;
        }
    }
    
    
    private void generateSpinner(Spinner spinner, List<String> arrayToSpinner) {
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_row_nothing_selected, arrayToSpinner);
        
        spinner.setAdapter(new NothingSelectedSpinnerAdapter(myAdapter, R.layout.spinner_row_nothing_selected, this));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    
    public void onClickContinue(View v){
        if(sDisplayList.getSelectedItemId() == -1){
            Toast.makeText(this, "Please select a Station first.", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "selected station = " + kdsProfile.get(selectedProfileId - 1).getName(),
                Toast.LENGTH_SHORT).show();
        
        Intent i = new Intent(SelectStationActivity.this, DisplayTicketActivity.class);
        i.putExtra("station_name", kdsProfile.get(selectedProfileId - 1).getName());
        startActivity(i);
    }


}
