package com.example.mymergedapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class WordGameActivity extends AppCompatActivity implements View.OnClickListener {


    private String[] words = {"Anmol", "Koirala", "Word", "Game", "Text", "demo","tryout"};
    private EditText dispLvl;
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    TextView textView;
    private int level = 0;
    Button BtnChck, BtnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);

        textView      = findViewById(R.id.leveltext);
        BtnChck       = findViewById(R.id.buttonCheck);
        BtnClear      = findViewById(R.id.buttonClear);
        dispLvl       = findViewById(R.id.dispLevelText);
        recyclerView  = findViewById(R.id.recyclerView);

        SharedPreferences savedata = getSharedPreferences("Game", Context.MODE_PRIVATE);
        if (savedata.getInt("Level",0) == 0) {
            showWord(level);
        }
        else {
            level=savedata.getInt("Level",0);
            showWord(level);
        }

        BtnChck.setOnClickListener(this);
        BtnClear.setOnClickListener(this);
    }


    private Character[] shuffleWords(int level){
        char[] word = words[level].toCharArray();
        ArrayList<Character> chars = new ArrayList<>(word.length);
        for(char c: word){
            chars.add(c);
        }
        Collections.shuffle(chars);
        Character[] shuffledWord = new Character[chars.size()];

        for(int i=0; i<shuffledWord.length; i++ ){
            shuffledWord[i] = chars.get(i);
        }
        return shuffledWord;
    }

    private void showWord(int i){
        WordGameCharacterAdapter charactersAdapter = new WordGameCharacterAdapter(WordGameActivity.this, shuffleWords(i), dispLvl);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setAdapter(charactersAdapter);
        recyclerView.setLayoutManager(layoutManager);
        textView.setText("LEVEL : " + (i+1));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonCheck ){
                String usr_word = dispLvl.getText().toString();
                if (level < words.length) {
                    if (usr_word.equals(words[level])) {
                        level++;
                        showWord(level);
                        sharedPreferences = getSharedPreferences("Game", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("Level", level);
                        editor.apply();
                        dispLvl.setText("");
                        Toast.makeText(WordGameActivity.this, "Proceed to Next Level", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(WordGameActivity.this, "Wrong Word. Try Again", Toast.LENGTH_SHORT).show();
                        dispLvl.setText("");
                        showWord(level);
                    }
                } else {
                    level = 0;
                    Toast.makeText(WordGameActivity.this, "You Win.Game Over", Toast.LENGTH_SHORT).show();
                }

        }

        if(view.getId() ==  R.id.buttonClear) {
            dispLvl.getText().clear();
            showWord(level);
        }
    }
}
