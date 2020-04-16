package com.example.yj.Patient;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yj.doctorsearch.R;
import com.example.yj.myutility.MyUtility;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BookingDescription extends AppCompatActivity {
    private TextView textView,textView1,textView2,textView3,textView4;
    private Button button;
    private EditText editText;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_description);
        final Intent intent = getIntent();
        textView=(TextView)findViewById(R.id.name5);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading........");
        textView1=(TextView)findViewById(R.id.spec1);
        button=(Button)findViewById(R.id.b5);
        textView2=(TextView)findViewById(R.id.addre);
        textView3=(TextView)findViewById(R.id.date);
        textView4=(TextView)findViewById(R.id.time);
        editText=(EditText)findViewById(R.id.description1);
        textView.setText(intent.getStringExtra("name"));
        textView1.setText(intent.getStringExtra("address"));
        textView2.setText(intent.getStringExtra("special"));
        textView3.setText("Date : "+intent.getStringExtra("date"));
        final String date  = intent.getStringExtra("date");
        final String time = intent.getStringExtra("time");
        textView4.setText("Time  :"+intent.getStringExtra("time"));
        final String user_id = intent.getStringExtra("user_id");
        final String d_id = intent.getStringExtra("d_id");
       final int u = Integer.parseInt(user_id);
        final int d = Integer.parseInt(d_id);
        final int p = Integer.parseInt(MyUtility.patient_id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(BookingDescription.this, "Please enter your reason", Toast.LENGTH_SHORT).show();
                }
                else{
                   // Toast.makeText(BookingDescription.this, d+" "+u+" "+p+" "+time+" "+date+" "+editText.getText().toString(), Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(BookingDescription.this);
//                builder.setMessage("Thank you for booking your appointement with "+textView.getText().toString()+".Your Appointment will be confirmed after verification by the doctor's office at "+textView1.getText().toString()+".We will send you an email/SMS with the Appointment details");
//                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
                    String urlString = "http://192.168.43.66:8080/FindDoctorWebApp/AppointmentDetail?user_id="+u+"&p_id="+p+"&d_id="+d+"&time="+time+"&date="+date+"&description="+editText.getText().toString();
                        AppointmentTask task = new AppointmentTask();
                        task.execute(urlString);
                        finish();
                }
            }

        });

    }
    class AppointmentTask extends AsyncTask<String, Void, String> {
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
                        result +=(char)ch;
                    }
                    is.close();
                }

            }  catch (MalformedURLException e) {
                result=e.getMessage();
                e.printStackTrace();
            } catch (IOException e) {
                result=e.getMessage();
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.cancel();
            Toast.makeText(BookingDescription.this, s, Toast.LENGTH_SHORT).show();
        }
    }


}
