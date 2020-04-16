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

import com.example.yj.doctorsearch.R;
import com.example.yj.dto.User;
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

public class SignUpActivity extends AppCompatActivity {
    private TextView textView,register;
    private EditText editText;
    private Button button;
    private ProgressDialog progressDialog;
    private JSONArray jsonArray;
    private int f=0,id1;
    private  String pass,em,em1,ad,userq,id,id11;
    private File internalPath;
    private Double aDouble;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        textView=(TextView)findViewById(R.id.textview);
        register=(TextView)findViewById(R.id.register);
        editText=(EditText)findViewById(R.id.email);
        button=(Button)findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        final Intent intent = getIntent();
        userq = intent.getStringExtra("UserType");
        pass= intent.getStringExtra("password");
        id11 = intent.getStringExtra("user_id");
        ad = intent.getStringExtra("aadhar");
        em1 = intent.getStringExtra("email");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,RegisterDoc.class);
                intent.putExtra("UserType","Doctor");
                startActivity(intent);
                editText.setText("");
                textView.setText("");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidEmail(editText.getText().toString())) {
                    String email = editText.getText().toString();
                    String stringurl = MyUtility.MY_URL + "LoginServelet?email=" + email;
                    LoginAsyncTask task = new LoginAsyncTask();
                    task.execute(stringurl);
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public static boolean isValidEmail(CharSequence editText) {
        return (!TextUtils.isEmpty(editText) && Patterns.EMAIL_ADDRESS.matcher((CharSequence) editText).matches());
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
                textView.setText("In these you have to select the Login/Signup option . If you have a already register to this application then choose login option otherwise choose signup option ");
                break;
            case R.id.lcc :
                textView.setText("Here is my contact detail : \n Email : abcd@gmail.com \n M. No. : 9876543210");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    class LoginAsyncTask extends AsyncTask<String,Void,String>
    {
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
            String ar[]=s.split("=",2);
            if (ar[0].equals("success")){
                User user =new User();
            String ed = editText.getText().toString();
                try {
                    jsonArray = new JSONArray(ar[1]);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject =jsonArray.getJSONObject(i);
                        user.setUserId(jsonObject.getInt("userId"));
                        user.setEmail(jsonObject.getString("email"));
                        user.setPassword(jsonObject.getString("password"));
                        user.setUserType(jsonObject.getString("userType"));
                        user.setAadhar_number(jsonObject.getDouble("aadhar_number"));
                        em = user.getEmail();
                        if (ed.equalsIgnoreCase(em)&&user.getUserType().equalsIgnoreCase("Doctor"))
                        {
                           pass = user.getPassword();
                           em1=user.getEmail();
                           id1=user.getUserId();
                           id= String.valueOf(id1);
                            aDouble=user.getAadhar_number();
                            ad = aDouble.toString();
                            MyUtility.user_id1=user.getUserId();
                            userq = user.getUserType();
                            f=1;
                        }

                    }
                    if(f==1)
                    {
                        Intent intent1 = new Intent(SignUpActivity.this,Password.class);
                        intent1.putExtra("password",pass);
                        intent1.putExtra("email",em1);
                        intent1.putExtra("user_id",id);
                        intent1.putExtra("aadhar",ad);
                        intent1.putExtra("UserType",userq);
                        startActivity(intent1);
                        finish();
                    }else {
                        Toast.makeText(SignUpActivity.this, "Not Registered User", Toast.LENGTH_SHORT).show();
                    }


                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
    }
    else {
                Toast.makeText(SignUpActivity.this, s, Toast.LENGTH_SHORT).show();
           }

}

    }


}
