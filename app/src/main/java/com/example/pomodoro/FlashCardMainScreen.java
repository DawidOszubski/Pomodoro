package com.example.pomodoro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FlashCardMainScreen extends AppCompatActivity {

    String tableName;
    ListView listView;
    ArrayList<String> flashcardTitles, flashcardReadFromTable;
    ArrayAdapter<String> adapter;
    Button addFlashcard, createFlashcard, button_enter, button_delete,addNewFlashcardLayoutBtn,acceptNewFlashcardBtn,cancelNewFlashcardBtn;
    EditText editTextTitle, editTextSearchBar, editTextAddQuestion, editTextAddAnswer;
    ConstraintLayout flashcardMainLayout,addFlashcardLayout,flashcardWholeLayout,flashcard_model_layout,addNewFlashcardLayout,flashcardFlipLayout,deleteNoteLayout;
    Boolean isCreateFlashcardLayoutVisible = true, isFrontFlashcard = true,backClicked = false;;
    MyKeyboardAllButtons keyboard;
    Cursor c;
    SQLiteDatabase myDatabase;
    InputConnection ic;
    AnimatorSet flashcardFrontAnimator, flashcardBackAnimator;
    TextView flashcardFrontBtn, flashcardBackBtn;
    @Override
    public void onBackPressed() {
        if(flashcard_model_layout.getVisibility() == View.VISIBLE && !backClicked && addNewFlashcardLayout.getVisibility() == View.INVISIBLE){
            backClicked=true;
            flashcardWholeLayout.animate().translationXBy(1400).setDuration(1000);
            flashcard_model_layout.animate().translationXBy(1400).setDuration(1000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    flashcard_model_layout.setVisibility(View.INVISIBLE);
                    backClicked=false;
                }
            }).start();

        }else  if(flashcardFlipLayout.getVisibility() == View.INVISIBLE && addNewFlashcardLayout.getVisibility() == View.VISIBLE && keyboard.getVisibility() == View.INVISIBLE){
            addNewFlashcardLayout.setVisibility(View.INVISIBLE);
            flashcardFlipLayout.setVisibility(View.VISIBLE);
            editTextAddQuestion.setText("");
            editTextAddAnswer.setText("");

        }else if(keyboard.getVisibility() == View.VISIBLE){
            keyboard.setVisibility(View.INVISIBLE);
            getCurrentFocus().clearFocus();
        }else if(addFlashcardLayout.getVisibility() == View.VISIBLE){
            addFlashcardLayout.setVisibility(View.INVISIBLE);
            flashcardMainLayout.setVisibility(View.VISIBLE);
            isCreateFlashcardLayoutVisible=true;
        editTextTitle.setText("");
    }
        else
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

         myDatabase = this.openOrCreateDatabase("Flashcard", MODE_PRIVATE, null);

        ////////////////////
        flashcardTitles = new ArrayList<>();
        flashcardReadFromTable = new ArrayList<>();
        deleteNoteLayout = findViewById(R.id.deleteNoteLayout);
        acceptNewFlashcardBtn = findViewById(R.id.acceptNewFlashcard);
        cancelNewFlashcardBtn = findViewById(R.id.cancelNewFlashcard);
        editTextAddAnswer = findViewById(R.id.editTextAddAnswer);
        editTextAddQuestion = findViewById(R.id.editTextAddQuestion);
        flashcardFlipLayout = findViewById(R.id.flashcardFlipLayout);
        addNewFlashcardLayout = findViewById(R.id.addNewFlashcardLayout);
        addNewFlashcardLayoutBtn = findViewById(R.id.add_new_flashcard);
        flashcard_model_layout = findViewById(R.id.flashcard_model_layout);
        flashcard_model_layout.setX(1400);
        flashcardMainLayout  = findViewById(R.id.flashCard_MainLayout);
        flashcardWholeLayout = findViewById(R.id.flashCard_wholeLayout);
        addFlashcardLayout = findViewById(R.id.addFlashcard_layout);
        createFlashcard = findViewById(R.id.add_flashcardTitle);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSearchBar = findViewById(R.id.search_bar);
        listView = (ListView) findViewById(R.id.listViewFlashCard);
        addFlashcard = findViewById(R.id.add_flashcard);
        button_enter = findViewById(R.id.button_enter1);
        button_delete = findViewById(R.id.button_delete1);
        keyboard = (MyKeyboardAllButtons) findViewById(R.id.keyboardAllButtons);
        flashcardFrontBtn = findViewById(R.id.flashcard_model_question);
        flashcardBackBtn = findViewById(R.id.flashcard_model_answer);
        Button flashcardFlip = findViewById(R.id.flashcard_model_flip);
        ////////////////////////////////////////////////////////////////////////
        Float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        flashcardFrontBtn.setCameraDistance(8000*scale);
        flashcardBackBtn.setCameraDistance(8000*scale);
        flashcardFrontAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.flashcard_front_animator);
        flashcardBackAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.flashcard_back_animator);

        flashcardFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFrontFlashcard) {
                    flashcardFrontAnimator.setTarget(flashcardFrontBtn);
                    flashcardBackAnimator.setTarget(flashcardBackBtn);
                    flashcardFrontAnimator.start();
                    flashcardBackAnimator.start();
                    isFrontFlashcard=false;
                }else{
                    flashcardFrontAnimator.setTarget(flashcardBackBtn);
                    flashcardBackAnimator.setTarget(flashcardFrontBtn);
                    flashcardBackAnimator.start();
                    flashcardFrontAnimator.start();
                    isFrontFlashcard=true;
                }
            }
        });



        /////////////////////////////////////////////////////////
        editTextSearchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    keyboard.setVisibility(View.VISIBLE);
                    addFlashcard.setVisibility(View.INVISIBLE);
                    keyboardFocusOnEditText(editTextSearchBar);

                }else{
                    keyboard.setVisibility(View.INVISIBLE);
                    addFlashcard.setVisibility(View.VISIBLE);
                }}
        });

        editTextTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    keyboard.setVisibility(View.VISIBLE);
                    keyboardFocusOnEditText(editTextTitle);
                }else{
                    keyboard.setVisibility(View.INVISIBLE);
                }}
        });

        editTextAddQuestion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    keyboard.setVisibility(View.VISIBLE);
                    keyboardFocusOnEditText(editTextAddQuestion);
                    acceptNewFlashcardBtn.setVisibility(View.INVISIBLE);
                    cancelNewFlashcardBtn.setVisibility(View.INVISIBLE);
                }else {
                    keyboard.setVisibility(View.INVISIBLE);
                    acceptNewFlashcardBtn.setVisibility(View.VISIBLE);
                    cancelNewFlashcardBtn.setVisibility(View.VISIBLE);
                }}
        });

        editTextAddAnswer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    keyboard.setVisibility(View.VISIBLE);
                    keyboardFocusOnEditText(editTextAddAnswer);
                    acceptNewFlashcardBtn.setVisibility(View.INVISIBLE);
                    cancelNewFlashcardBtn.setVisibility(View.INVISIBLE);
                }else {
                    keyboard.setVisibility(View.INVISIBLE);
                    acceptNewFlashcardBtn.setVisibility(View.VISIBLE);
                    cancelNewFlashcardBtn.setVisibility(View.VISIBLE);
                }}
        });


        button_enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(editTextSearchBar.hasFocus())
                editTextSearchBar.clearFocus();
                if(editTextTitle.hasFocus())
                editTextTitle.clearFocus();
                if(editTextAddAnswer.hasFocus())
                    editTextAddAnswer.clearFocus();
                if(editTextAddQuestion.hasFocus())
                    editTextAddQuestion.clearFocus();
            }
        });

        addFlashcard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addFlashcardLayout.setVisibility(View.VISIBLE);
                editTextSearchBar.setText("");
            }
        });

        button_delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editTextSearchBar.setText("");
                editTextTitle.setText("");
                editTextAddAnswer.setText("");
                editTextAddQuestion.setText("");
                return true;
            }
        });


        showFlashcardTables();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, flashcardTitles);
        listView.setAdapter(adapter);



        createFlashcard.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(!editTextTitle.getText().toString().isEmpty() && !flashcardTitles.contains(editTextTitle.getText().toString())) {

                    myDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + editTextTitle.getText().toString() + " (question VARCHAR, answer VARCHAR)");
                    adapter.notifyDataSetChanged();
                    addFlashcardLayout.setVisibility(View.INVISIBLE);
                    flashcardMainLayout.setVisibility(View.VISIBLE);
                    editTextTitle.setText("");
                    refreshActivity();
                }else if(flashcardTitles.contains(editTextTitle.getText().toString())){
                }else{
                    Toast.makeText(FlashCardMainScreen.this, "Podaj nazwe fiszek", Toast.LENGTH_SHORT).show();
                    Toast.makeText(FlashCardMainScreen.this, "Podana nazwa fiszek już istnieje", Toast.LENGTH_SHORT).show();
                }
            }
        });


        editTextSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (FlashCardMainScreen.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(deleteNoteLayout.getVisibility() == View.INVISIBLE){
                    tableName = flashcardTitles.get(i);
                    backClicked=true;
                    flashcardWholeLayout.animate().translationXBy(-1400).setDuration(1000);
                    flashcard_model_layout.setVisibility(View.VISIBLE);
                    flashcard_model_layout.animate().translationXBy(-1400).setDuration(1000).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            backClicked=false;
                        }
                    }).start();
                    editTextSearchBar.setText("");}
                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    tableName = flashcardTitles.get(i);
                       deleteNoteShow();
                    return false;
                }
            });

        addNewFlashcardLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewFlashcardLayout.setVisibility(View.VISIBLE);
                flashcardFlipLayout.setVisibility(View.INVISIBLE);
            }
        });


    }

        private void showFlashcardTables(){
            c = myDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
            if (c.moveToPosition(1)) {
                while ( !c.isAfterLast() ) {
                    flashcardTitles.add( c.getString( c.getColumnIndex("name")) );
                    c.moveToNext();
                }
            }
        }


    private void keyboardFocusOnEditText(EditText editText){
            editText.setRawInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setTextIsSelectable(true);
            ic = editText.onCreateInputConnection(new EditorInfo());
            editText.setShowSoftInputOnFocus(false);
        keyboard.setInputConnection(ic);
    }



        private void refreshActivity(){
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }

        private void deleteNoteShow(){
        deleteNoteLayout.setVisibility(View.VISIBLE);
        }

        public void deleteFlashcardTitle(View view){
            myDatabase.execSQL("DROP TABLE " + tableName);
            adapter.notifyDataSetChanged();
            refreshActivity();
        }

        public void cancelDeleteFlashcardTitle(View view){
            deleteNoteLayout.setVisibility(View.INVISIBLE);
        }

    }