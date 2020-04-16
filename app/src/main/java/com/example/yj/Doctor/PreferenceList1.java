package com.example.yj.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yj.doctorsearch.R;

import java.util.ArrayList;
import java.util.List;

public class PreferenceList1 extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> files = new ArrayList<>();
    private    String a="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference);
        listView=(ListView)findViewById(R.id.listview);
        final String ar[] = {"Cardiology","Urologist","Dentist","Psychology"};
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,ar);
        listView.setAdapter(arrayAdapter);
        final Intent intent1=  getIntent();
        final String id = intent1.getStringExtra("user_id");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(int j=0;j<ar.length;j++) {
                    if(listView.isItemChecked(j)) {
                        a = ar[j];
                    }
                }
              //  Toast.makeText(PreferenceList.this, id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PreferenceList1.this,DoctorDetailUpdate.class);
                intent.putExtra("dis",a);
                intent.putExtra("user_id",id);
                startActivity(intent);
                finish();
                //Toast.makeText(Preference.this, a, Toast.LENGTH_SHORT).show();

            }
        });}

}
