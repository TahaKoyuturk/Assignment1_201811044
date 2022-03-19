package com.example.chessbutton;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton button1;
    private AppCompatButton button2;

    private ImageButton playerPause;
    private ImageButton playerReset;
    private ImageButton playerChangeClock;

    private boolean mTimeRunning = false;
    private boolean turnPlayer1 = false;
    private boolean turnPlayer2 = false;
    private boolean isStartedPlayer1 = false;
    private boolean isStartedPlayer2 = false;

    private int isPaused =-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hiding Navigation Bar
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        button1 = findViewById(R.id.player1);
        button2 = findViewById(R.id.player2);
        playerPause = findViewById(R.id.playerpause);
        playerReset = findViewById(R.id.playerreset);
        playerChangeClock = findViewById(R.id.playerchangeclock);

        Player p1 = new Player(button1);
        Player p2 = new Player(button2);

        button1.setText("Start");
        button2.setText("Start");


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimeRunning){
                    mTimeRunning=p1.pauseTimer();
                    mTimeRunning=p2.startTimer();
                    isStartedPlayer2=true;
                    turnPlayer2=true;
                    turnPlayer1=false;
                }else{
                    mTimeRunning=p2.startTimer();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimeRunning){
                    mTimeRunning= p2.pauseTimer();
                    mTimeRunning=p1.startTimer();
                    isStartedPlayer1=true;
                    turnPlayer1=true;
                    turnPlayer2=false;
                }else{
                    mTimeRunning=p1.startTimer();
                }
            }
        });

        playerPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPaused*=-1;
                if(!turnPlayer1){
                    if(isPaused==1){
                        p2.pauseTimer();
                        playerPause.setBackgroundResource(R.drawable.start);
                    }else{
                        p2.startTimer();
                        playerPause.setBackgroundResource(R.drawable.pause);
                    }
                }else if(!turnPlayer2){
                    if(isPaused==1){
                        p1.pauseTimer();
                        playerPause.setBackgroundResource(R.drawable.start);
                    }else{
                        p1.startTimer();
                        playerPause.setBackgroundResource(R.drawable.pause);
                    }
                }
            }
        });

        playerReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isStartedPlayer1 && isStartedPlayer2){
                   p1.resetTimer();
                   p2.resetTimer();
               }
            }
        });
        playerChangeClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turnPlayer1){
                    p1.pauseTimer();
                    p2.startTimer();
                    turnPlayer1=false;
                    turnPlayer2=true;
                }else{
                    p2.pauseTimer();
                    p1.startTimer();
                    turnPlayer2=false;
                    turnPlayer1=true;
                }
            }
        });
    }





}