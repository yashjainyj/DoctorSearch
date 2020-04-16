package com.example.yj.Doctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yj.myutility.MyUtility;
import com.example.yj.doctorsearch.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterDoc extends AppCompatActivity {
    private TextView textView;
    private Button register;
    private EditText email,password,aadhar;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_doc);
        textView=(TextView)findViewById(R.id.textview);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        register=(Button)findViewById(R.id.register1);
        aadhar=(EditText)findViewById(R.id.aadhar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Intent intent = getIntent();
        final String UserType = intent.getStringExtra("UserType");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = email.getText().toString();
                String pass = password.getText().toString();
                String aadhar1 = aadhar.getText().toString();

                if(emailId.equalsIgnoreCase("") || pass.equalsIgnoreCase("") || aadhar1.equalsIgnoreCase(""))
                {
                    Toast.makeText(RegisterDoc.this, "Field is empty ", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (isValidEmail(email.getText().toString())) {
                        String urlString = MyUtility.MY_URL + "Registration_servelet?email=" + emailId + "&password=" + pass + "&user_type=" + UserType + "&aadhar_number=" + aadhar1;
                        RegistrationAsyncTask task = new RegistrationAsyncTask();
                        task.execute(urlString);
                        // Toast.makeText(RegisterDoc.this, "Registered Successfully ..Now Login to your registered Id ", Toast.LENGTH_LONG).show();
                        finish();
                        textView.setText("");
                    }
                    else {
                        Toast.makeText(RegisterDoc.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                    }
                    }
            }
        });
    }
    public static boolean isValidEmail(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher((CharSequence) email).matches());
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
                textView.setText("Here you have to Register your account  ");
                break;
            case R.id.lcc :
                textView.setText("Here is my contact detail : \n Email : abcd@gmail.com \n M. No. : 9876543210");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    class RegistrationAsyncTask extends AsyncTask<String,Void,String>{
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
                result="URL DOSE";
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
            Toast.makeText(RegisterDoc.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
