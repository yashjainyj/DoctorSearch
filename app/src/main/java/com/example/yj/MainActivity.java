package com.example.yj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yj.Doctor.HomeDoctor;
import com.example.yj.Doctor.Password;
import com.example.yj.Doctor.SignUpActivity;
import com.example.yj.Patient.HomePatient;
import com.example.yj.Patient.SignupActivity;
import com.example.yj.doctorsearch.R;
import com.example.yj.dto.User;
import com.example.yj.myutility.MyUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.yj.myutility.MyUtility.file;
import static com.example.yj.myutility.MyUtility.file1;
import static com.example.yj.myutility.MyUtility.temp;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;
    ImageButton imageButton1,imageButton2;
    private ProgressDialog progressDialog;
    private JSONArray jsonArray;
    private int f=0;
    private int id;
    private int p_id;
    private int q;
    private  String pass,em,em1,ad,userq,id1,user_type,user_type1,st;
    private Double aDouble;
    private File internalPath;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> files = new ArrayList<>();
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textview);
        imageView=(ImageView)findViewById(R.id.logo);
        imageButton1=(ImageButton)findViewById(R.id.docicon);
        imageButton2=(ImageButton)findViewById(R.id.paticon);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                    imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user_type = "Patient";
                    String stringurl =  MyUtility.MY_URL+"LoginServelet?";
                    LoginAsyncTask task = new LoginAsyncTask();
                    task.execute(stringurl);

                }
            });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_type1 = "Doctor";
//                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
//                    startActivity(intent);
//                    textView.setText("");
                String stringurl =  MyUtility.MY_URL+"LoginServelet?";
                LoginAsyncTask task = new LoginAsyncTask();
                task.execute(stringurl);

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
                textView.setText("In these you have to select the User Type . If you are a doctor then choose Doctor option otherwise choose Patient option. ");
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
                        if (user.getUserType().equalsIgnoreCase(user_type))
                        {
                            pass = user.getPassword();
                            em1=user.getEmail();
                            id = user.getUserId();
                            id1 = String.valueOf(id);
                            aDouble=user.getAadhar_number();
                            ad = aDouble.toString();
                            userq = user.getUserType();
                            f=1;
                        }
                        else if(user.getUserType().equalsIgnoreCase(user_type1))
                        {
                            pass = user.getPassword();
                            em1=user.getEmail();
                            id = user.getUserId();
                            id1 = String.valueOf(id);
                            aDouble=user.getAadhar_number();
                            ad = aDouble.toString();
                            userq = user.getUserType();
                            f=2;
                        }
                    }
                    if(f==1)
                    {

                        internalPath = getDir("my_dir",MODE_PRIVATE);
                        File myDir = new File(internalPath.getAbsolutePath()+"/"+"my_dir");
                        if(!myDir.exists()){
                            myDir.mkdir();
                        }
                        internalPath = myDir;

                    //    Toast.makeText(MainActivity.this, s1, Toast.LENGTH_SHORT).show();
                        File f = new File(internalPath,file);
                        if(f.exists()){
                          //  Toast.makeText(MainActivity.this, internalPath+"", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MainActivity.this, "File Exists", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,HomePatient.class);
                            intent.putExtra("user_id",id1);
                            startActivity(intent);
                            textView.setText("");

                        }else{
                            try {

                                boolean b = f.createNewFile();
                                if (b) {
                                    //Toast.makeText(MainActivity.this, "File created successfully", Toast.LENGTH_SHORT).show();
                                    PrintWriter fOut = new PrintWriter(internalPath.getAbsolutePath() + "/" + file);
                                    fOut.write(id1);
                                    fOut.close();
                                 //   Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                                    intent.putExtra("password",pass);
                                    intent.putExtra("user_id",id1);
                                    intent.putExtra("email",em1);
                                    intent.putExtra("aadhar",ad);
                                    intent.putExtra("UserType",userq);
                                    startActivity(intent);
                                    textView.setText("");

                                }


                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                            // Toast.makeText(getBaseContext(),temp,Toast.LENGTH_SHORT).show();
                        MyUtility.p_id1 = id1;
                        p_id = user.getUserId();
                        finish();
                    }

                    if(f==2)
                    {

                        internalPath = getDir("my_dir",MODE_PRIVATE);
                        File myDir = new File(internalPath.getAbsolutePath()+"/"+"my_dir");
                        if(!myDir.exists()){
                            myDir.mkdir();
                        }
                        internalPath = myDir;

                        File f = new File(internalPath,file1);
                        if(f.exists()){
                            //  Toast.makeText(MainActivity.this, internalPath+"", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MainActivity.this, "File Exists", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, HomeDoctor.class);
                            intent.putExtra("user_id",id1);
                            startActivity(intent);
                            textView.setText("");

                        }else{
                            try {

                                boolean b = f.createNewFile();
                                if (b) {
                                    //Toast.makeText(MainActivity.this, "File created successfully", Toast.LENGTH_SHORT).show();
                                    PrintWriter fOut = new PrintWriter(internalPath.getAbsolutePath() + "/" + file1);
                                    fOut.write(id1);
                                    fOut.write(em1);
                                    fOut.write(pass);
                                    fOut.write(userq);
                                    fOut.write(ad);
                                    fOut.close();
                                    //   Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                                    intent.putExtra("password",pass);
                                    intent.putExtra("user_id",id1);
                                    intent.putExtra("email",em1);
                                    intent.putExtra("aadhar",ad);
                                    intent.putExtra("UserType",userq);
                                    startActivity(intent);
                                    textView.setText("");

                                }


                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                        // Toast.makeText(getBaseContext(),temp,Toast.LENGTH_SHORT).show();
                        MyUtility.p_id1 = id1;
                        p_id = user.getUserId();
                        finish();
                    }

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
            else {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

        }

    }

}
