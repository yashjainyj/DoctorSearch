package com.example.yj.Patient;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yj.Doctor.Password;
import com.example.yj.Doctor.RegisterDoc;
import com.example.yj.MainActivity;
import com.example.yj.doctorsearch.R;
import com.example.yj.dto.User;
import com.example.yj.myutility.MyUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.yj.myutility.MyUtility.file;
import static com.example.yj.myutility.MyUtility.temp;

public class SignupActivity extends AppCompatActivity {
    private TextView textView,register;
    private EditText editText;
    private Button button;
    private ProgressDialog progressDialog;
    private JSONArray jsonArray;
    private int f=0;
    private int id;
    private int p_id;
    private int q;
    private  String pass,em,em1,ad,userq,id1;
    private Double aDouble;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity_patient);
        textView=(TextView)findViewById(R.id.textview);
        register=(TextView)findViewById(R.id.register);
        editText=(EditText)findViewById(R.id.phone);
        button=(Button)findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        final Intent intent = getIntent();
        userq = intent.getStringExtra("UserType");
        pass= intent.getStringExtra("password");
        id1 = intent.getStringExtra("user_id");
       ad = intent.getStringExtra("aadhar");
        em1 = intent.getStringExtra("email");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidEmail(editText.getText().toString())) {
                    String stringurl = MyUtility.MY_URL + "LoginServelet?";
                    LoginAsyncTask task = new LoginAsyncTask();
                    task.execute(stringurl);
                }
                else {
                    Toast.makeText(SignupActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this,RegisterDoc.class);
                intent.putExtra("UserType","Patient");
                startActivity(intent);
                editText.setText("");
                textView.setText("");

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
                textView.setText("In these you have to select the Login/Signup option . If you have a already register to this application then choose login option otherwise choose signup option ");
                Toast.makeText(SignupActivity.this, ""+id, Toast.LENGTH_SHORT).show();
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
            String ar[] = s.split("=", 2);
            if (ar[0].equals("success")) {
                User user = new User();

                try {
                    jsonArray = new JSONArray(ar[1]);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        user.setUserId(jsonObject.getInt("userId"));
                        user.setEmail(jsonObject.getString("email"));
                        user.setPassword(jsonObject.getString("password"));
                        user.setUserType(jsonObject.getString("userType"));
                        user.setAadhar_number(jsonObject.getDouble("aadhar_number"));
                        em = user.getEmail();
                        if (editText.getText().toString().equalsIgnoreCase(em)) {
                            pass = user.getPassword();
                            em1 = user.getEmail();
                            id = user.getUserId();
                            id1 = String.valueOf(id);
                            aDouble = user.getAadhar_number();
                            ad = aDouble.toString();
                            userq = user.getUserType();
                            f = 1;
                        }
                    }
                    if (f == 1) {
                        Intent intent1 = new Intent(SignupActivity.this, Password.class);
                        intent1.putExtra("password", pass);
                        intent1.putExtra("user_id", id1);
                        intent1.putExtra("email", em1);
                        intent1.putExtra("aadhar", ad);
                        intent1.putExtra("UserType", userq);
                        startActivity(intent1);
                        finish();
                    }else {
                        Toast.makeText(SignupActivity.this, "Email not register", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Toast.makeText(getBaseContext(),temp,Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(SignupActivity.this, "Not registered user", Toast.LENGTH_SHORT).show();
            }


              }
    }


    }




