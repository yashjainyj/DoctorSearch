package com.example.yj.Doctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.yj.Patient.PatientDetailUpdate;
import com.example.yj.doctorsearch.R;

public class LoggedDoc extends AppCompatActivity {
    private ImageView imageView;
    private EditText editText, editText1, editText2, logged,fee12,spec;
    private Button button;
    private ProgressDialog progressDialog;
    public int p_id;
    private String name,age,g,n,id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_logged);
        imageView = (ImageView) findViewById(R.id.imageView2);
        editText = (EditText) findViewById(R.id.editText4);
        editText1 = (EditText) findViewById(R.id.editText2);
        editText2 = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button);
        fee12 = (EditText)findViewById(R.id.fee1);
        spec=(EditText)findViewById(R.id.special1);
        logged = (EditText) findViewById(R.id.gender);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Intent intent = getIntent();
         id = intent.getStringExtra("user_id");
        p_id = Integer.parseInt(id);
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String gender = intent.getStringExtra("gender");
        String mobile = intent.getStringExtra("mobile");
        String fee = intent.getStringExtra("fee");
        String spec1 = intent.getStringExtra("special");
        editText.setText(name);
        editText1.setText(address);
        editText2.setText(mobile);
        logged.setText(gender);
        fee12.setText(fee);
        spec.setText(spec1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.update_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i)
        {
            case R.id.Edit : Intent intent = new Intent(LoggedDoc.this,DoctorDetailUpdate.class);
                intent.putExtra("user_id",id);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
