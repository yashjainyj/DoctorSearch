package com.example.yj.Patient;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yj.Doctor.HomeDoctor;
import com.example.yj.MainActivity;
import com.example.yj.doctorsearch.R;
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


public class SearchDoc extends AppCompatActivity {
    private TextView textView;
    private String id;
    private ProgressDialog progressDialog;
    private String user_id;
    private JSONArray jsonArray;
    private int f=0,id2,d_id,q;
    private  String name,pass,em,em1,ad,userq,n,id1,g,address,fee,special,patient_id;
    private Double aDouble;
    private File internalPath;
    private String spec1,spec2,spec3,spec4;
    private ImageButton imageButton,imageButton1,imageButton2,imageButton3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchdoc);
        imageButton=(ImageButton)findViewById(R.id.kidney);
        imageButton1=(ImageButton)findViewById(R.id.illness);
        imageButton2=(ImageButton)findViewById(R.id.dentist);
        imageButton3=(ImageButton)findViewById(R.id.heart);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Intent intent =getIntent();
        id=intent.getStringExtra("user_id");
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spec1="cardiology";
                String stringurl =  MyUtility.MY_URL+"DoctorLogin?";
               DocDetailFetch task1 = new DocDetailFetch();
                task1.execute(stringurl);

            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spec2="dentist";
                String stringurl =  MyUtility.MY_URL+"DoctorLogin?";
                DocDetailFetch task1 = new DocDetailFetch();
                task1.execute(stringurl);

            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spec3="psychology";
                String stringurl =  MyUtility.MY_URL+"DoctorLogin?";
                DocDetailFetch task1 = new DocDetailFetch();
                task1.execute(stringurl);

            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spec4="urologist";
                String stringurl =  MyUtility.MY_URL+"DoctorLogin?";
                DocDetailFetch task1 = new DocDetailFetch();
                task1.execute(stringurl);

            }
        });

        textView=(TextView)findViewById(R.id.textview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_patient_search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i)
        {
            case R.id.Help :
                textView.setText("");
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchDoc.this);
                builder.setMessage("Here You can search any doctor and book appoitment with them");
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
                builder = new AlertDialog.Builder(SearchDoc.this);
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
                builder = new AlertDialog.Builder(SearchDoc.this);
                builder.setMessage("This app is used to search a doctor and booked appointement");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
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
                        Toast.makeText(SearchDoc.this, "Thank you", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SearchDoc.this, "Thank you", Toast.LENGTH_SHORT).show();
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
                Intent intent1 = new Intent(SearchDoc.this, MainActivity.class);
                startActivity(intent1);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

     class DocDetailFetch extends AsyncTask<String, Void, String> {

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
                        if (user.getSpecial().equalsIgnoreCase(spec1)) {
                            f=1;
                        }
                        else if(user.getSpecial().equalsIgnoreCase(spec2))
                        {
                            f=2;
                        }
                        else if(user.getSpecial().equalsIgnoreCase(spec3))
                        {
                            f=3;
                        }
                        else if(user.getSpecial().equalsIgnoreCase(spec4))
                        {
                            f=4;
                        }
                    }
                    if(f==1) {
                        Intent intent = new Intent(SearchDoc.this, CardiologyDoc.class);
                        Toast.makeText(SearchDoc.this, id, Toast.LENGTH_SHORT).show();
                        intent.putExtra("user_id", id);
//                    intent.putExtra("name",name);
//                    intent.putExtra("address",address);
//                    intent.putExtra("gender",g);
//                    intent.putExtra("mobile",n);
//                    intent.putExtra("fee",fee);
//                    intent.putExtra("special",special);
                        startActivity(intent);
                        finish();
                    }
                    else if(f==2)
                    {
                        Intent intent = new Intent(SearchDoc.this, DentistDoc.class);
                        intent.putExtra("user_id", id);
//                    intent.putExtra("name",name);
//                    intent.putExtra("address",address);
//                    intent.putExtra("gender",g);
//                    intent.putExtra("mobile",n);
//                    intent.putExtra("fee",fee);
//                    intent.putExtra("special",special);
                        startActivity(intent);
                        finish();
                    }
                    else if (f==3)
                    {
                        Intent intent = new Intent(SearchDoc.this, PsychologyDoc.class);
                        intent.putExtra("user_id", id);
//                    intent.putExtra("name",name);
//                    intent.putExtra("address",address);
//                    intent.putExtra("gender",g);
//                    intent.putExtra("mobile",n);
//                    intent.putExtra("fee",fee);
//                    intent.putExtra("special",special);
                        startActivity(intent);
                        finish();
                    }
                    else if (f==4)
                    {
                        Intent intent = new Intent(SearchDoc.this, UrologistDoc.class);
                        intent.putExtra("user_id", id);
//                    intent.putExtra("name",name);
//                    intent.putExtra("address",address);
//                    intent.putExtra("gender",g);
//                    intent.putExtra("mobile",n);
//                    intent.putExtra("fee",fee);
//                    intent.putExtra("special",special);
                        startActivity(intent);
                        finish();
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
