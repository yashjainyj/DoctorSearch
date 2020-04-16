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

import com.example.yj.doctorsearch.R;
import com.example.yj.myutility.MyUtility;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ForgetPassword extends AppCompatActivity {
    private TextView textView;
    private EditText editText,editText1;
    private Button button;
    private ProgressDialog progressDialog;
    private double aadhar1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        editText=(EditText)findViewById(R.id.password);
        editText1=(EditText)findViewById(R.id.aadhar);
        textView=(TextView)findViewById(R.id.textview);
        button=(Button)findViewById(R.id.forget);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Intent intent = getIntent();
        final String UserType = intent.getStringExtra("UserType");
        final String aadhar = intent.getStringExtra("aadhar");
        final String emailId = intent.getStringExtra("email");
        aadhar1= Double.parseDouble(aadhar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a1 = editText1.getText().toString();
                double a2 = Double.parseDouble(a1);

                if(aadhar1==a2)
                {
                    String pass = editText.getText().toString();
                    String urlString = MyUtility.MY_URL+"ForgetServelet?password="+pass+"&aadhar_number="+aadhar;
                    ForgetPasswordTask task = new ForgetPasswordTask();
                    task.execute(urlString);
                    Toast.makeText(ForgetPassword.this, "Password Forget Successfully ..Now Enter Your Id and Password for Login ", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(ForgetPassword.this,SignUpActivity.class);
                    startActivity(intent1);
                    finish();
                    textView.setText("");


                }
                else {
                    Toast.makeText(ForgetPassword.this, "Enter Correct Aadhar Number", Toast.LENGTH_SHORT).show();
                }
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
                textView.setText("In these you have to Forget your password with the registered  email ");
                break;
            case R.id.lcc :
                textView.setText("Here is my contact detail : \n Email : abcd@gmail.com \n M. No. : 9876543210");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class ForgetPasswordTask extends AsyncTask<String,Void,String> {
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
                        (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    InputStream is = httpURLConnection.getInputStream();
                    int ch = -1;
                    while ((ch=is.read())!=-1)
                    {
                        result +=(char)ch;
                    }
                    is.close();
                }

            } catch (MalformedURLException e) {

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
            Toast.makeText(ForgetPassword.this, s, Toast.LENGTH_SHORT).show();
        }
    }


}
