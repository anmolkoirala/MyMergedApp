package com.example.mymergedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCrd, btnGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrd  = findViewById(R.id.buttonCrudop);
        btnGame = findViewById(R.id.buttonWordg);

        btnGame.setOnClickListener(this);
        btnCrd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.buttonCrudop)
        {
            Intent intent = new Intent(this, CRUDActivity.class);
            startActivity(intent);
        }

        if (view.getId()==R.id.buttonWordg)
        {
            Intent intent = new Intent(this,WordGameActivity.class);
            startActivity(intent);
        }
    }
}
