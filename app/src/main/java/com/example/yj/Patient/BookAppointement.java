package com.example.yj.Patient;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yj.doctorsearch.R;
import com.example.yj.myutility.MyUtility;

import java.util.Calendar;

public class BookAppointement extends AppCompatActivity {
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14,button15,button16;
    private DatePickerDialog dpd;
    private TextView textView,textView1,textView2,textView3,textView4;
    private EditText editText;
    private int f=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_appointement);
        editText=(EditText)findViewById(R.id.editText5);
        button1=(Button)findViewById(R.id.button7);
        button2=(Button)findViewById(R.id.button8);
        button3=(Button)findViewById(R.id.button9);
        button4=(Button)findViewById(R.id.button10);
        button5=(Button)findViewById(R.id.button11);
        button6=(Button)findViewById(R.id.button12);
        button7=(Button)findViewById(R.id.button14);
        button8=(Button)findViewById(R.id.button15);
        button9=(Button)findViewById(R.id.button16);
        button10=(Button)findViewById(R.id.button17);
        button11=(Button)findViewById(R.id.button18);
        button12=(Button)findViewById(R.id.button19);
        button13=(Button)findViewById(R.id.button22);
        button14=(Button)findViewById(R.id.button23);
        button15=(Button)findViewById(R.id.button24);
        button16 =(Button)findViewById(R.id.button21);
        textView=(TextView)findViewById(R.id.name5);
        textView1=(TextView)findViewById(R.id.spec1);
        textView2=(TextView)findViewById(R.id.addre);
        textView3=(TextView)findViewById(R.id.fee2);
        textView4=(TextView)findViewById(R.id.mobile);
        final Intent intent = getIntent();
        textView.setText(intent.getStringExtra("name"));
        textView1.setText(intent.getStringExtra("address"));
        textView2.setText(intent.getStringExtra("special"));
        textView3.setText("Fee : "+intent.getStringExtra("fee"));
        textView4.setText("Mobile no. :"+intent.getStringExtra("mobile"));
        final String user_id = intent.getStringExtra("user_id");
        final String d_id = intent.getStringExtra("d_id");
       final long mobile = Long.parseLong(intent.getStringExtra("mobile"));
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:"+mobile));
                startActivity(intent2);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(BookAppointement.this, user_id, Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button1.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button2.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button3.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button4.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button5.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button6.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button7.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button8.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button9.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button10.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button11.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button12.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button13.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button14.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();
                }
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(BookAppointement.this, "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(BookAppointement.this, BookingDescription.class);
                    intent1.putExtra("user_id", user_id);
                    intent1.putExtra("name", textView.getText().toString());
                    intent1.putExtra("special", textView1.getText().toString());
                    intent1.putExtra("address", textView2.getText().toString());
                    intent1.putExtra("date", editText.getText().toString());
                    intent1.putExtra("time", button15.getText().toString());
                    intent1.putExtra("d_id", d_id);
                    startActivity(intent1);
                    finish();

                }
            }
        });
        final Calendar calendar = Calendar.getInstance();
        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String date = i2+"-"+(i1+1)+"-"+i;
                editText.setText(date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpd.show();
            }
        });
    }
}
