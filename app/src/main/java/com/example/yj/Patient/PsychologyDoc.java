package com.example.yj.Patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yj.doctorsearch.R;
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

public class PsychologyDoc extends AppCompatActivity {
    private TextView textView,textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11;
    private Button button1,button2,button3;
    private ImageButton imageButton1,imageButton2,imageButton3;
    private ProgressDialog progressDialog;
    private JSONArray jsonArray;
    private int f=0;
    private String name[],address[],spec[];
    private int fee[],d_id[];
    private long phonenumber[];
    private String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dentist_main);
        Intent intent =getIntent();
        id = intent.getStringExtra("user_id");
        textView=(TextView)findViewById(R.id.name4);
        textView1=(TextView)findViewById(R.id.spec4);
        textView2=(TextView)findViewById(R.id.address4);
        textView3=(TextView)findViewById(R.id.cash4);
        textView4=(TextView)findViewById(R.id.name2);
        textView5=(TextView)findViewById(R.id.spec2);
        textView6=(TextView)findViewById(R.id.address2);
        textView7=(TextView)findViewById(R.id.cash2);
        textView8=(TextView)findViewById(R.id.name3);
        textView9=(TextView)findViewById(R.id.spec3);
        textView10=(TextView)findViewById(R.id.address3);
        textView11=(TextView)findViewById(R.id.cash3);
        button1=(Button)findViewById(R.id.b4);
        button2=(Button)findViewById(R.id.b2);
        button3=(Button)findViewById(R.id.b3);
        imageButton1=(ImageButton)findViewById(R.id.phone4);
        imageButton2=(ImageButton)findViewById(R.id.phone2);
        imageButton3=(ImageButton)findViewById(R.id.phone3);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        String stringurl =  MyUtility.MY_URL+"DoctorLogin?";
        DocDetailFetch task1 = new DocDetailFetch();
        task1.execute(stringurl);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PsychologyDoc.this,BookAppointement.class);
                intent1.putExtra("user_id",id);
                intent1.putExtra("name","Dr."+textView.getText().toString());
                intent1.putExtra("special",textView1.getText().toString());
                intent1.putExtra("address",textView2.getText().toString());
                intent1.putExtra("fee",textView3.getText().toString());
                intent1.putExtra("mobile",String.valueOf(phonenumber[0]));
                intent1.putExtra("d_id",String.valueOf(d_id[0]));
                startActivity(intent1);
            }
        });
        button2 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PsychologyDoc.this,BookAppointement.class);
                intent1.putExtra("user_id",id);
                intent1.putExtra("name","Dr."+textView4.getText().toString());
                intent1.putExtra("special",textView5.getText().toString());
                intent1.putExtra("address",textView6.getText().toString());
                intent1.putExtra("fee",textView7.getText().toString());
                intent1.putExtra("mobile",String.valueOf(phonenumber[1]));
                intent1.putExtra("d_id",String.valueOf(d_id[1]));
                startActivity(intent1);
            }
        });
        button3 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PsychologyDoc.this,BookAppointement.class);
                intent1.putExtra("user_id",id);
                intent1.putExtra("name","Dr."+textView8.getText().toString());
                intent1.putExtra("special",textView9.getText().toString());
                intent1.putExtra("address",textView10.getText().toString());
                intent1.putExtra("fee",textView11.getText().toString());
                intent1.putExtra("mobile",String.valueOf(phonenumber[2]));
                intent1.putExtra("d_id",String.valueOf(d_id[2]));
                startActivity(intent1);
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_DIAL);

                intent2.setData(Uri.parse("tel:"+phonenumber[0]));
                startActivity(intent2);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_DIAL);

                intent2.setData(Uri.parse("tel:"+phonenumber[1]));
                startActivity(intent2);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:"+phonenumber[2]));
                startActivity(intent2);
            }
        });

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
                    name = new String[jsonArray.length()];
                    spec = new String[jsonArray.length()];
                    d_id = new int[jsonArray.length()];
                    phonenumber = new long[jsonArray.length()];
                    address = new String[jsonArray.length()];
                    fee = new int[jsonArray.length()];
                    int j=0;
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
                        if (user.getSpecial().equalsIgnoreCase("Psychology")) {
                            f=1;
                            name[j] = user.getName();
                            spec[j] = user.getSpecial();
                            address[j] = user.getAddress();
                            fee[j]=user.getFee();
                            d_id[j]=user.getD_id();
                            phonenumber[j]=user.getMobile();
                            j++;

                        }
                    }
                    if(f==1)
                    {

//                        Intent intent = new Intent(SearchDoc.this,CardiologyDoc.class);
//                        intent.putExtra("user_id",id);
                        textView.setText(name[0]);
                        textView1.setText(spec[0]);
                        textView2.setText(address[0]);
                        textView3.setText(String.valueOf(fee[0]));
                        textView4.setText(name[1]);
                        textView5.setText(spec[1]);
                        textView6.setText(address[1]);
                        textView7.setText(String.valueOf(fee[1]));
                        textView8.setText(name[2]);
                        textView9.setText(spec[2]);
                        textView10.setText(address[2]);
                        textView11.setText(String.valueOf(fee[2]));
//                        startActivity(intent);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(PsychologyDoc.this, s, Toast.LENGTH_SHORT).show();
            }

        }
    }

}
