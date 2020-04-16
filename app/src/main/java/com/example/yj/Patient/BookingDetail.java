package com.example.yj.Patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yj.Doctor.HomeDoctor;
import com.example.yj.doctorsearch.R;
import com.example.yj.dto.User;
import com.example.yj.dto.UserAppointment;
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
import java.util.ArrayList;
import java.util.List;

public class BookingDetail extends AppCompatActivity {
    private JSONArray jsonArray;
    private ProgressDialog progressDialog;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> files = new ArrayList<>();
    private int f=0;
    private ImageButton button;
    private  String user_id,time[],date[],des[];
    public int p_id,p_id1;
    private long n1;
    private String name,age,g,n;
    private TextView textView,textView1,textView2,textView3;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_detail);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        relativeLayout=(RelativeLayout)findViewById(R.id.ss);
                String stringurl = MyUtility.MY_URL+"PatientDetailFetch?";
                PatientFetch task = new PatientFetch();
                task.execute(stringurl);

        textView=(TextView)findViewById(R.id.name6);
        textView1=(TextView)findViewById(R.id.age);
        textView2=(TextView)findViewById(R.id.time);
        textView3=(TextView)findViewById(R.id.date);
        button=(ImageButton) findViewById(R.id.phone12);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:"+n1));
                startActivity(intent2);
            }
        });


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
                        if (MyUtility.p_id[0]==user.getpId()) {
                            name = user.getName();
                            int a = user.getAge();
                            age = String.valueOf(a);
                            g=user.getGender();
                            MyUtility.patient_id =String.valueOf(user.getpId());
                             n1 = user.getMobile();
                            n=String.valueOf(n1);
                            f=1;
                        }
                    }
                    if(f==1)
                    {
                        textView.setText(name);
                        textView1.setText(age);
                        textView2.setText(MyUtility.des[0]);
                        textView3.setText(MyUtility.time[0]);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(BookingDetail.this, s, Toast.LENGTH_SHORT).show();
            }

        }

    }
}
