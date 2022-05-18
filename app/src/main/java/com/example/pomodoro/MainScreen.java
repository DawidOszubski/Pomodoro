package com.example.pomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {
    Button learn;
    Button flashcards;
   /* Intent learnActivity = new Intent(MainScreen.this, MainActivity.class);
    Intent flashCardsActivity = new Intent(MainScreen.this, FlashCards.class);*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        learn = findViewById(R.id.learn);
        flashcards = findViewById(R.id.flashcards);

    }

    public void SelectedButton(final View view){
            view.animate().setDuration(700).scaleY(1.2f).scaleX(1.2f).withEndAction(new Runnable() {
                @Override
                public void run() {
                    switch (view.getId()) {
                        case R.id.learn:
                            Intent learnActivity = new Intent(MainScreen.this, MainActivity.class);
                            startActivity(learnActivity);
                            break;
                        case R.id.flashcards:
                            Intent flashCardsActivity = new Intent(MainScreen.this, FlashCardMainScreen.class);
                            startActivity(flashCardsActivity);
                            break;
                    }
                    view.animate().setDuration(500).scaleY(1).scaleX(1);
                }
            }).start();
    }
}