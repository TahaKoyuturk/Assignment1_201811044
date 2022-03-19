package com.example.chessbutton;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import android.os.CountDownTimer;
import java.util.concurrent.TimeUnit;
public class Player extends AppCompatActivity {

    private static long START_TIME_IN_MILLIS = 600000;
    private long mTimeLeftMinites=START_TIME_IN_MILLIS;
    private boolean mTimeRunning = true;

    public CountDownTimer cdt;
    private AppCompatButton button;

    public Player(AppCompatButton button){
        this.button=button;
    }

    public boolean pauseTimer(){
        cdt.cancel();
        mTimeRunning = false;
        updateCountDownText();
        return false;
    }

    public void resetTimer(){
        mTimeLeftMinites=START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    public void updateCountDownText(){
        int minutes = (int)(mTimeLeftMinites/1000)/60;
        int seconds = (int)(mTimeLeftMinites/1000)%60;
        String leftTimeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        button.setText(leftTimeFormatted);
    }

    public boolean startTimer(){
        cdt = new CountDownTimer(mTimeLeftMinites,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftMinites = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimeRunning=false;
            }
        }.start();
        mTimeRunning=true;
        return true;
    }
}