package com.example.yj.Doctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.yj.doctorsearch.R;
import com.example.yj.myutility.MyUtility;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DoctorDetailUpdate extends AppCompatActivity {
    private EditText name,special,number,address,fee;
    private ImageView imageView;
    private RadioButton radioButton,radioButton1;
    private Button button;
    private ProgressDialog progressDialog;
    private  String gender,id1;
    private int id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_detail);
        name=(EditText)findViewById(R.id.editText4);
        address=(EditText)findViewById(R.id.address);
        number=(EditText)findViewById(R.id.editText3);
        fee=(EditText)findViewById(R.id.fee);
        special=(EditText)findViewById(R.id.special);
        radioButton=(RadioButton)findViewById(R.id.radioButton);
        radioButton1=(RadioButton)findViewById(R.id.radioButton2);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Intent intent = getIntent();
        id1 = intent.getStringExtra("user_id");
        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorDetailUpdate.this,PreferenceList1.class);
                intent.putExtra("user_id",id1);
                startActivity(intent);
                finish();
            }
        });
        Intent intent1 = getIntent();
        final String spec = intent1.getStringExtra("dis");
        id1 = intent1.getStringExtra("user_id");
        special.setText(spec);
        id=Integer.parseInt(id1);
        button=(Button)findViewById(R.id.button) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButton.isChecked())
                {
                    gender = "Male";
                }
                if (radioButton1.isChecked())
                {
                    gender="Female";
                }
                String nameq = name.getText().toString();
                String address1 = address.getText().toString();
                String number12 = number.getText().toString();
                long number1 = Long.parseLong(number12);
                String fee12 = fee.getText().toString();
                int fee1 = Integer.parseInt(fee12);
                String urlString = MyUtility.MY_URL+"/DoctorDetailUpdate?name="+nameq+"&gender="+gender+"&mobile="+number1+"&address="+address1+"&fee="+fee1+"&special="+spec+"&user_id="+id;
                DocDetailUpdate task = new DocDetailUpdate();
                task.execute(urlString);
                // Toast.makeText(RegisterDoc.this, "Registered Successfully ..Now Login to your registered Id ", Toast.LENGTH_LONG).show();
                finish();
                //textView.setText("");
            }
        });


    }

    class DocDetailUpdate extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
            Toast.makeText(DoctorDetailUpdate.this, s, Toast.LENGTH_SHORT).show();
        }
    }

}
