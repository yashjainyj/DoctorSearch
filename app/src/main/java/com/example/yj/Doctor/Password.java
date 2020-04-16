package com.example.yj.Doctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.yj.Patient.HomePatient;
import com.example.yj.Patient.LoggedInAccountPatient;
import com.example.yj.Patient.SignupActivity;
import com.example.yj.doctorsearch.R;
import com.example.yj.dto.User;
import com.example.yj.dto.UserDoc;
import com.example.yj.myutility.MyUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Password extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private TextView textView,click;
    private ProgressDialog progressDialog;
    private JSONArray jsonArray;
    private int f=0,id2,p_id,q;
    private  String pass,em,em1,ad,userq,id1,user_id,d_id;
    private Double aDouble;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_match);
        textView=(TextView)findViewById(R.id.textview);
        click=(TextView)findViewById(R.id.click);
        editText=(EditText)findViewById(R.id.password);
        button=(Button)findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        final Intent intent = getIntent();
        final String user1 = intent.getStringExtra("UserType");
        final String pass= intent.getStringExtra("password");
        final String id = intent.getStringExtra("user_id");
        user_id = id;
        final String aadhar = intent.getStringExtra("aadhar");
        final String emailId = intent.getStringExtra("email");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p1=editText.getText().toString();
                //Toast.makeText(Password.this, user1, Toast.LENGTH_SHO
                // RT).show();
                if(p1.equals(pass))
                {
                    if(user1.equalsIgnoreCase("Doctor"))
                    {
                        String stringurl =  MyUtility.MY_URL+"DoctorLogin?";
                        DoctorFetch task1 = new DoctorFetch();
                        task1.execute(stringurl);
                        Intent intent2 = new Intent(Password.this,HomeDoctor.class);
                        intent2.putExtra("user_id",id);
                        startActivity(intent2);
                        finish();
                    }
                    else if(user1.equalsIgnoreCase("Patient"))
                        {
                            String stringurl =  MyUtility.MY_URL+"PatientDetailFetch?";
                            PatientFetch task = new PatientFetch();
                            task.execute(stringurl);
                            Intent intent1 = new Intent(Password.this,HomePatient.class);
                            intent1.putExtra("user_id",id);
                            startActivity(intent1);
                            finish();
                        }
                }
                else {
                    Toast.makeText(Password.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Password.this,ForgetPassword.class);
                intent.putExtra("UserType","Doctor");
                intent.putExtra("aadhar",aadhar);
                intent.putExtra("email",emailId);

                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i)
        {
            case R.id.Help :
                textView.setText("In these you have to insert your password with the registered  email ");
                Toast.makeText(Password.this, id1, Toast.LENGTH_SHORT).show();

                break;
            case R.id.lcc :
                textView.setText("Here is my contact detail : \n Email : abcd@gmail.com \n M. No. : 9876543210");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    class DoctorFetch extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String urlString = strings[0];
            String result = "";
            try {
                URL url = new URL(urlString.toString());
                HttpURLConnection httpURLConnection =
                        (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = httpURLConnection.getInputStream();
                    int ch = -1;
                    while ((ch = is.read()) != -1) {
                        result += (char) ch;
                    }
                    is.close();
                }

            } catch (MalformedURLException e) {
                result = "URL DOSE";
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.cancel();
            String ar[] = s.split("=", 2);
            if (ar[0].equals("success")) {
                UserDoc user = new UserDoc();
                try {
                    jsonArray = new JSONArray(ar[1]);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        user.setD_id(jsonObject.getInt("d_id"));
                        user.setName(jsonObject.getString("name"));
                        user.setGender(jsonObject.getString("gender"));
                        user.setMobile(jsonObject.getLong("mobile"));
                        user.setAddress(jsonObject.getString("address"));
                        user.setFee(jsonObject.getInt("fee"));
                        user.setSpecial(jsonObject.getString("special"));
                        user.setUser_id(jsonObject.getInt("user_id"));
                        int id = user.getUser_id();
                        if (Integer.parseInt(user_id)==id) {
                            //Toast.makeText(Password.this, "123", Toast.LENGTH_SHORT).show();
                            int d_id1 = user.getD_id();
                            MyUtility.doctor_id=String.valueOf(d_id1);
                            f=1;
                        }
                        }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(Password.this, s, Toast.LENGTH_SHORT).show();
            }

        }

    }





    class PatientFetch extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            String urlString = strings[0];
            String result = "";
            try {
                URL url = new URL(urlString.toString());
                HttpURLConnection httpURLConnection =
                        (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = httpURLConnection.getInputStream();
                    int ch = -1;
                    while ((ch = is.read()) != -1) {
                        result += (char) ch;
                    }
                    is.close();
                }

            } catch (MalformedURLException e) {
                result = "URL DOSE";
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.cancel();
            String ar[] = s.split("=", 2);
            if (ar[0].equals("success")) {
                User user = new User();
                try {
                    jsonArray = new JSONArray(ar[1]);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        user.setpId(jsonObject.getInt("pId"));
                        user.setName(jsonObject.getString("name"));
                        user.setAge(jsonObject.getInt("age"));
                        user.setGender(jsonObject.getString("gender"));
                        user.setMobile(jsonObject.getLong("mobile"));
                        user.setUserId(jsonObject.getInt("userId"));
                        int id = user.getUserId();
                        if (Integer.parseInt(user_id)==id) {
                           MyUtility.patient_id=String.valueOf(user.getpId());
                           MyUtility.name=user.getName();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(Password.this, s, Toast.LENGTH_SHORT).show();
            }

        }

    }


}
