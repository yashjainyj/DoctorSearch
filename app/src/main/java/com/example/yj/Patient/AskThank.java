package com.example.yj.Patient;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.yj.doctorsearch.R;

public class AskThank extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanku);
        int FinishTime = 5;
        int countDownInterval = 1000;
        CountDownTimer counterTimer = new CountDownTimer(FinishTime * 1000, countDownInterval) {
            public void onFinish() {
               finish();
            }

            public void onTick(long millisUntilFinished) {

            }
        };
        counterTimer.start();
    }
}