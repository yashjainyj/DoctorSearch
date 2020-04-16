package com.example.yj.Patient;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.yj.doctorsearch.R;
import com.example.yj.myutility.MyUtility;

import java.util.Calendar;

public class Reminder extends AppCompatActivity {
    private EditText date, time;
    private Button button, button1, button2;
    private AlarmManager alarmManager;
    private DatePickerDialog dpd;
    private TimePickerDialog tpd;
    private int time1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("user_id");
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();
        dpd = new DatePickerDialog(this,  new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                date.setText(i2 + "-" + (i1 + 1) + "-" + i);


            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        tpd = new TimePickerDialog(Reminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                String t = i + ":" + i1;
                time.setText(t);

                time1 = (((i * 60) + i1) * 600);
            }
        }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpd.show();
            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tpd.show();
                Toast.makeText(Reminder.this, "" + time1, Toast.LENGTH_SHORT).show();

            }
        });


                   button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (date.getText().toString().equalsIgnoreCase("" ) || time.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(Reminder.this, "Please select date & time ", Toast.LENGTH_SHORT).show();
                    }
                    else {
                    MyUtility.user_id = id;
                    Intent intent = new Intent(Reminder.this, Alarm.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(Reminder.this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + time1, pendingIntent);
                    Toast.makeText(Reminder.this, "Alarm set Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                }

            }
        });
    }

}