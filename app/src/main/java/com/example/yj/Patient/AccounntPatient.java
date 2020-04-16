package com.example.yj.Patient;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.yj.doctorsearch.R;
import com.example.yj.dto.User;
import com.example.yj.myutility.MyUtility;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class AccounntPatient extends AppCompatActivity {
    private ImageView imageView;
    private EditText editText, editText1, editText2;
    private RadioButton radioButton, radioButton1;
    private Button button;
    private ProgressDialog progressDialog;
    private String gender,id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_detail_input);
        imageView = (ImageView) findViewById(R.id.imageView2);
        editText = (EditText) findViewById(R.id.editText4);
        editText1 = (EditText) findViewById(R.id.editText2);
        editText2 = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton2);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Intent intent = getIntent();
       id = intent.getStringExtra("user_id");
        final int p_id = Integer.parseInt(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton.isChecked()) {
                    gender = "Male";
                }
                if (radioButton1.isChecked()) {
                    gender = "Female";
                }
                String name = editText.getText().toString();
                String age = editText1.getText().toString();
                String number = editText2.getText().toString();

                String urlString = MyUtility.MY_URL + "PatientDetail?user_id=" + p_id + "&name=" + name + "&age=" + age + "&gender=" + gender + "&mobile=" + number;
                PatientAsynctask task = new PatientAsynctask();
                task.execute(urlString);
                // Toast.makeText(RegisterDoc.this, "Registered Successfully ..Now Login to your registered Id ", Toast.LENGTH_LONG).show();
                finish();
                //textView.setText("");
            }
        });

    }


    class PatientAsynctask extends AsyncTask<String, Void, String> {
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
            Toast.makeText(AccounntPatient.this, s, Toast.LENGTH_SHORT).show();
        }
    }

}
