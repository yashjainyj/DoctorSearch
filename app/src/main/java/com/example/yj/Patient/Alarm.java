package com.example.yj.Patient;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.example.yj.doctorsearch.R;
import com.example.yj.myutility.MyUtility;

import java.util.regex.Pattern;


public class Alarm extends AppCompatActivity {
   private Button button;
   private  Ringtone r;
   @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_main);
        button=(Button)findViewById(R.id.stop);
       Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
       r = RingtoneManager.getRingtone(getApplicationContext(), notification);
       r.play();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             r.stop();
                Intent intent = new Intent(Alarm.this,HomePatient.class);
                intent.putExtra("user_id", MyUtility.user_id);
                startActivity(intent);
                finish();
            }
        });

    }
}
