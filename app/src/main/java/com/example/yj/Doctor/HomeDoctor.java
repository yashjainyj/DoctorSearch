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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.yj.MainActivity;
import com.example.yj.Patient.BookingDetail;
import com.example.yj.Patient.HomePatient;
import com.example.yj.Patient.LoggedInAccountPatient;
import com.example.yj.doctorsearch.R;
import com.example.yj.dto.User;
import com.example.yj.dto.UserAppointment;
import com.example.yj.dto.UserDoc;
import com.example.yj.myutility.MyUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.yj.myutility.MyUtility.file;
import static com.example.yj.myutility.MyUtility.file1;

public class HomeDoctor extends AppCompatActivity {
private TextView textView;
    private File internalPath;
private String user_id;
    private ProgressDialog progressDialog;
    private EditText editText;
    private JSONArray jsonArray;
    private int f=0,id2,d_id,q,f2=0;
    private  String name,g,address,fee,doctor_id,n,special,time[],date[],des[];
    private Double aDouble;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_doc);
        Intent intent = getIntent();
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        user_id = intent.getStringExtra("user_id");
        d_id = Integer.parseInt(user_id);
        textView=(TextView)findViewById(R.id.textview);
        editText=(EditText)findViewById(R.id.o2);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringurl = MyUtility.MY_URL+"Appointment?";
                AppointmentDetail task = new AppointmentDetail();
                task.execute(stringurl);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_patient,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i)
        {
            case R.id.Help :
                textView.setText("Home Page of Doctor Profile");
                break;
            case R.id.lcc :
                textView.setText("Here is my contact detail : \n Email : abcd@gmail.com \n M. No. : 9876543210");
                break;
            case R.id.about :
                Toast.makeText(HomeDoctor.this, user_id + " " + MyUtility.doctor_id, Toast.LENGTH_SHORT).show();
                break;
            case  R.id.account :
                String stringurl =  MyUtility.MY_URL+"DoctorLogin?";
                DoctorFetch task1 = new DoctorFetch();
                task1.execute(stringurl);
                break;
            case R.id.Feedback :
                break;
            case R.id.rate:
                break;
            case R.id.logout :
                internalPath = getDir("my_dir",MODE_PRIVATE);
                File myDir = new File(internalPath.getAbsolutePath()+"/"+"my_dir");
                internalPath=myDir;
                File f = new File(internalPath,file1);
                f.delete();
                Intent intent1 = new Intent(HomeDoctor.this, MainActivity.class);
                startActivity(intent1);
                finish();
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
                        if (d_id==id) {
                            doctor_id=String.valueOf(user.getD_id());
                            name = user.getName();
                            g=user.getGender();
                            long n1 = user.getMobile();
                            n=String.valueOf(n1);
                            address = user.getAddress();
                            int f1=user.getFee();
                            fee =String.valueOf(f1);
                            special=user.getSpecial();
                            f=1;
                        }
                    }
                    if(f==1)
                    {
                        Intent intent = new Intent(HomeDoctor.this, LoggedDoc.class);
                        intent.putExtra("user_id",user_id);
                        intent.putExtra("name",name);
                        intent.putExtra("address",address);
                        intent.putExtra("gender",g);
                        intent.putExtra("mobile",n);
                        intent.putExtra("fee",fee);
                        intent.putExtra("special",special);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(HomeDoctor.this, DoctorDetail.class);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(HomeDoctor.this, s, Toast.LENGTH_SHORT).show();
            }

        }

    }
    class AppointmentDetail extends AsyncTask<String, Void, String> {
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
                UserAppointment user = new UserAppointment();
                try {
                    jsonArray = new JSONArray(ar[1]);
                    int j=0;
                    MyUtility.p_id = new int[jsonArray.length()];
                    MyUtility.time = new String[jsonArray.length()];
                    MyUtility.des =new String[jsonArray.length()];
                    date = new String[jsonArray.length()];
                    des = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        user.setUser_id(jsonObject.getInt("user_id"));
                        user.setP_id(jsonObject.getInt("p_id"));
                        user.setD_id(jsonObject.getInt("d_id"));
                        user.setTime(jsonObject.getString("time"));
                        user.setDate(jsonObject.getString("date"));
                        user.setDescription(jsonObject.getString("description"));
                        String d = String.valueOf(user.getD_id());
                        if (MyUtility.doctor_id.equalsIgnoreCase(d)) {
                            f=2;
                            MyUtility.p_id[j] = user.getP_id();
                            MyUtility.time[j] = user.getTime()+" "+user.getDate();
                            date[j] = user.getDate();
                            MyUtility.des[j]= user.getDescription();
                            j++;
                        }
                    }
                    if(f==2)
                    {
                        Intent intent =new Intent(HomeDoctor.this, BookingDetail.class);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                    }
                    else                     {
                        Toast.makeText(HomeDoctor.this, "No Record Found", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // Toast.makeText(DocDetailFetch.this, s, Toast.LENGTH_SHORT).show();
            }

        }
    }



}
