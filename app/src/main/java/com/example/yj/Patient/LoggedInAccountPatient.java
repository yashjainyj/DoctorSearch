package com.example.yj.Patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.yj.doctorsearch.R;
import com.example.yj.dto.User;
import com.example.yj.myutility.MyUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoggedInAccountPatient extends AppCompatActivity {
    private ImageView imageView;
    private EditText editText, editText1, editText2, logged;
    private Button button;
    private ProgressDialog progressDialog;
    public int p_id;
    private String name,age,g,n,id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_logged_in);
        imageView = (ImageView) findViewById(R.id.imageView2);
        editText = (EditText) findViewById(R.id.editText4);
        editText1 = (EditText) findViewById(R.id.editText2);
        editText2 = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button);
        logged = (EditText) findViewById(R.id.gender);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");
        p_id = Integer.parseInt(id);
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String gender = intent.getStringExtra("gender");
        String mobile = intent.getStringExtra("mobile");
        editText.setText(name);
        editText1.setText(age);
        editText2.setText(mobile);
        logged.setText(gender);
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
            case R.id.Edit : Intent intent = new Intent(LoggedInAccountPatient.this,PatientDetailUpdate.class);
                intent.putExtra("user_id",id);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
