package com.example.yj.Patient;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yj.Doctor.HomeDoctor;
import com.example.yj.Doctor.Password;
import com.example.yj.MainActivity;
import com.example.yj.doctorsearch.R;
import com.example.yj.dto.User;
import com.example.yj.myutility.MyUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.yj.myutility.MyUtility.file;

public class HomePatient extends AppCompatActivity {
    private TextView textView;
private EditText editText,editText1,editText2,editText3,article;
private String id;
private Button button;
    private ProgressDialog progressDialog;
    String gender;
    private JSONArray jsonArray;
    private int f = 0;
    public int p_id,p_id1;
    private String name,age,g,n;
    private File internalPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.home_patient);
        textView=(TextView)findViewById(R.id.textview);

        button=(Button)findViewById(R.id.article);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.nytimes.com/2018/06/07/well/pediatric-neurologist-stroke.html"));
                startActivity(intent);
            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        String stringurl =  MyUtility.MY_URL+"PatientDetailFetch?";
        PatientFetch12 task = new PatientFetch12();
        task.execute(stringurl);
        Intent intent = getIntent();
         id = intent.getStringExtra("user_id");
         p_id = Integer.parseInt(id);
//         User user = new User();
//         if(MyUtility.name.equalsIgnoreCase(user.getName()))
//         {
//             Intent intent1 = new Intent(HomePatient.this,);
//             startActivity(intent1);
//         }
        editText=(EditText) findViewById(R.id.o1);
       // frameLayout=(FrameLayout)findViewById(R.id.r);
        editText1=(EditText) findViewById(R.id.o2);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomePatient.this,SearchDoc.class);
                intent.putExtra("user_id",id);
                String stringurl =  MyUtility.MY_URL+"PatientDetailFetch?";
                PatientFetch12 task = new PatientFetch12();
                task.execute(stringurl);
                startActivity(intent);
            }
        });

        editText2 = (EditText) findViewById(R.id.o3);
        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(HomePatient.this,SearchDoc.class);
                String stringurl =  MyUtility.MY_URL+"PatientDetailFetch?";
                intent1.putExtra("user_id",id);
                PatientFetch12 task = new PatientFetch12();
                task.execute(stringurl);
                startActivity(intent1);

            }
        });
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePatient.this,Reminder.class);
                intent.putExtra("user_id",id);
                startActivity(intent);
            }
        });

        editText3=(EditText)findViewById(R.id.o4);
        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomePatient.this,AskQuestion.class);
                startActivity(intent);
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
                textView.setText("");
                AlertDialog.Builder builder = new AlertDialog.Builder(HomePatient.this);
                builder.setMessage("Home Page of Patient Profile");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                break;
            case R.id.lcc :
                textView.setText("");
                builder = new AlertDialog.Builder(HomePatient.this);
                builder.setMessage("Here is my contact detail : \n Email : abcd@gmail.com \n M. No. : 9876543210");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                 alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.about :
                builder = new AlertDialog.Builder(HomePatient.this);
                builder.setMessage("This app is used to search a doctor and booked appointement");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                 alertDialog = builder.create();
                alertDialog.show();
                break;
            case  R.id.account :
                String stringurl =  MyUtility.MY_URL+"PatientDetailFetch?";
                PatientFetch task = new PatientFetch();
                task.execute(stringurl);
                break;
            case R.id.Feedback :
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Give Us Feedback");
                final EditText editText1 = new EditText(this);
                editText1.setHint("");
                builder1.setView(editText1);
                builder1.setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(HomePatient.this, "Thank you", Toast.LENGTH_SHORT).show();
                    }

                });
                builder1.setNegativeButton("cancel",null);
                AlertDialog alertDialog1 = builder1.create();
                alertDialog1.show();
                break;
            case R.id.rate:
                 builder = new AlertDialog.Builder(this);
                builder.setTitle("Rate Us");
                final EditText editText = new EditText(this);
                editText.setHint("1-5");
                builder.setView(editText);
                builder.setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(HomePatient.this, "Thank you", Toast.LENGTH_SHORT).show();
                    }

                    });
                builder.setNegativeButton("cancel",null);
                 alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.logout :
                internalPath = getDir("my_dir",MODE_PRIVATE);
                File myDir = new File(internalPath.getAbsolutePath()+"/"+"my_dir");
                internalPath=myDir;
                File f = new File(internalPath,file);
                f.delete();
               Intent intent1 = new Intent(HomePatient.this, MainActivity.class);
               startActivity(intent1);
               finish();
        }
        return super.onOptionsItemSelected(item);
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
                        if (p_id==id) {
                            name = user.getName();
                            int a = user.getAge();
                            age = String.valueOf(a);
                            g=user.getGender();
                            MyUtility.patient_id =String.valueOf(user.getpId());
                            long n1 = user.getMobile();
                            n=String.valueOf(n1);
                            f=1;
                        }
                    }
                    if(f==1)
                    {
                        Intent intent = new Intent(HomePatient.this, LoggedInAccountPatient.class);
                        intent.putExtra("user_id",id);
                        intent.putExtra("name",name);
                        intent.putExtra("age",age);
                        intent.putExtra("gender",g);
                        intent.putExtra("mobile",n);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(HomePatient.this, AccounntPatient.class);
                        intent.putExtra("user_id",id);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(HomePatient.this, s, Toast.LENGTH_SHORT).show();
            }

        }

    }
    class PatientFetch12 extends AsyncTask<String, Void, String> {
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
                        if (p_id==id) {
                            name = user.getName();
                            int a = user.getAge();
                            MyUtility.patient_id = String.valueOf(user.getpId());
                            age = String.valueOf(a);
                            g=user.getGender();
                            long n1 = user.getMobile();
                            n=String.valueOf(n1);
                            f=1;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(HomePatient.this, s, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

//
//
//<EditText
//        android:layout_width="300dp"
//                android:layout_height="20dp"
//                android:background="@drawable/edit_text"
//                android:layout_centerHorizontal="true"
//                android:layout_marginTop="10dp"
//                android:id="@+id/link"
//                android:text="Why "
//                android:textColor="@color/colorPrimary"
//                android:textSize="15dp"/>
//<EditText
//        android:id="@+id/article"
//                android:layout_width="300dp"
//                android:layout_height="20dp"
//                android:layout_below="@+id/weq"
//                android:layout_centerHorizontal="true"
//                android:text="Why you feel tired all the time...."
//                android:textAlignment="center"
//                android:textColor="@color/colorPrimary"
//                android:focusable="false"
//                android:background="@drawable/edit_text"
//                android:textSize="15dp"
//                android:textStyle="bold" />
