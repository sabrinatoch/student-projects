package com.example.hangman_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.io.*;
import java.util.Scanner;

public class RulesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/RingbearerMedium-51mgZ.ttf");
        TextView label = findViewById(R.id.lblRules);
        label.setTypeface(type);
        findViewById(R.id.btnBackRules).setOnClickListener(this);
        readFile();
    } // onCreate()


    public void readFile() {
        Scanner input = null;
        LinearLayout layout = findViewById(R.id.rulesLayout);
        try {
            input = new Scanner(new InputStreamReader(getAssets().open("rules.txt")));
            while (input.hasNext()) {
                String line = input.nextLine();
                TextView textRule = new TextView(this);
                textRule.setText(line + "\n\n");
                textRule.setTextSize(18);
                textRule.setTextColor(Color.WHITE);
                layout.addView(textRule);
            } // while hasNext()
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Something went wrong...",Toast.LENGTH_LONG).show();
        } // catch IOException
    } // readFile()

    @Override
    public void onClick(View v) {
        int btnClick = v.getId();
        if (btnClick == R.id.btnBackRules) {
            finish();
        }
    } // onClick()
} // RulesActivity class