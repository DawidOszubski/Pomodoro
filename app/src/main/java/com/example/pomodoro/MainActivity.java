package com.example.pomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int time=0;
    private TextView timeCounter;
    private TextView boardText1;
    private TextView boardText2;
    private Button start;
    private Boolean isTimerActive = false;
    private CountDownTimer countDownTimer;
    private EditText inputTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardText1 = findViewById(R.id.textViewPodajCzas);
        boardText2 = findViewById(R.id.textViewLecimy);
        start = findViewById(R.id.button_start);
        final Button enter = findViewById(R.id.button_enter);
        inputTime = (EditText) findViewById(R.id.inputTime);
        final MyKeyboardNumbers keyboard = (MyKeyboardNumbers) findViewById(R.id.keyboard);
        timeCounter = findViewById(R.id.timeCounter);

        inputTime.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        inputTime.setTextIsSelectable(true);
        InputConnection ic = inputTime.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
        inputTime.setShowSoftInputOnFocus(false);

        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                inputTime.clearFocus();
            }
        });



        inputTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    keyboard.setVisibility(View.VISIBLE);
                    start.setVisibility(View.INVISIBLE);

                }else{
                    keyboard.setVisibility(View.INVISIBLE);
                    start.setVisibility(View.VISIBLE);
            }}
        });
    }

    public void TimeCounter(View view){
        if(!TextUtils.isEmpty((CharSequence) inputTime.getText().toString())) {
            time = Integer.parseInt(inputTime.getText().toString());
            if(isTimerActive){
                resetTimer();
            }else {
        countDownTimer = new CountDownTimer(time * 1000 * 60, 1000) {
            @Override
            public void onTick(long l) {

                UpadteTime((int) l / 1000 );
            }

        @Override
        public void onFinish() {
            if(isTimerActive) {
                resetTimer();
                Toast.makeText(MainActivity.this, "Koniec! " + time, Toast.LENGTH_SHORT).show();
            }
        }
    }.start();
    startTimer();

} }else{
            Toast.makeText(MainActivity.this, "Podaj czas nauki ", Toast.LENGTH_SHORT).show();
        }

    }

    public void UpadteTime(int time){

        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        time = time % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, time);
        timeCounter.setText(timeString);
    }

    private void startTimer(){
        isTimerActive=true;
        start.setText("Stop");
        timeCounter.setVisibility(View.VISIBLE);
        boardText1.setVisibility(View.INVISIBLE);
        boardText2.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        countDownTimer.cancel();
        start.setText("Start");
        isTimerActive=false;
        timeCounter.setVisibility(View.INVISIBLE);
        boardText1.setVisibility(View.VISIBLE);
        boardText2.setVisibility(View.INVISIBLE);
    }



}