package com.example.yj.Patient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yj.doctorsearch.R;
import com.example.yj.myutility.MyUtility;

public class AskQuestion extends AppCompatActivity {
    private EditText editText,editText1,editText2;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_question);
        editText=(EditText)findViewById(R.id.prblm);
        editText1=(EditText)findViewById(R.id.title1);
        editText2=(EditText)findViewById(R.id.description);
        button=(Button)findViewById(R.id.submit);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AskQuestion.this,Preference.class);
                startActivity(intent);
                finish();
            }
        });
       String title =  editText1.getText().toString();
       String Descri = editText2.getText().toString();
       Intent intent = getIntent();
       String dis = intent.getStringExtra("dis");
       editText.setText(dis);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            Intent intent1 = new Intent(AskQuestion.this,AskThank.class);
            startActivity(intent1);
            finish();
           }
       });

    }
}
