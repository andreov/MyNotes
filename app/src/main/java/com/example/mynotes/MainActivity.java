package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mInNote;
    private Button mSave;
    private SharedPreferences myNoteSharedPref;
    private static String NOTE_TEXT = "note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getDateFromSharedPref();
        clickSave();

    }
    private void initView(){
        mInNote=findViewById(R.id.inputNote);
        mSave=findViewById(R.id.bSave);
        myNoteSharedPref = getSharedPreferences("MyNote",MODE_PRIVATE);
    }

    private void clickSave(){
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myNoteSharedPref = getSharedPreferences("MyNote",MODE_PRIVATE);
                SharedPreferences.Editor myEditor = myNoteSharedPref.edit();
                if(mInNote.getText().length()>0) {
                    String noteTxt = mInNote.getText().toString();
                    myEditor.putString(NOTE_TEXT, noteTxt);
                    myEditor.apply();
                    Toast.makeText(MainActivity.this, "Данные сохранены", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(MainActivity.this, "Введите текст для заметки", Toast.LENGTH_LONG).show();
            }
        });

    }
    private void getDateFromSharedPref(){
        String noteTxt = myNoteSharedPref.getString(NOTE_TEXT, "");
        if(noteTxt.equals(""))
            Toast.makeText(MainActivity.this, "Создайте заметку", Toast.LENGTH_LONG).show();
        else mInNote.setText(noteTxt);
    }
}