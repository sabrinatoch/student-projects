package com.example.hangman_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import hangman_package.Player;
import hangman_package.Scoreboard;

public class ScoreboardActivity extends AppCompatActivity implements View.OnClickListener {

    private Scoreboard score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/RingbearerMedium-51mgZ.ttf");
        Typeface typeC = Typeface.createFromAsset(getAssets(), "fonts/CourierPrime-Regular.ttf");
        TextView label = findViewById(R.id.lblScoreboard);
        label.setTypeface(type);
        TextView headerName = findViewById(R.id.headerName);
        TextView headerPlayed = findViewById(R.id.headerPlayed);
        TextView headerWon = findViewById(R.id.headerWon);
        TextView display = findViewById(R.id.areaDisplay);
        display.setTypeface(typeC);
        headerName.setTypeface(type);
        headerPlayed.setTypeface(type);
        headerWon.setTypeface(type);
        findViewById(R.id.btnBackScoreboard).setOnClickListener(this);
        setupScoreboard();
        score.sortPlayers();
        for (int i = 0; i < score.getNumPlayers(); ++i) {
            displayLine(score.getPlayerAt(i));
        } // for
    } // onCreate()

    public void setupScoreboard() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("Scoreboard")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    score = (Scoreboard) intent.getSerializableExtra("Scoreboard", Scoreboard.class);
                } // if API level is right
            } // Scoreboard
        } // if intent != null
        else
            score = new Scoreboard();
    } // setupScoreboard()

    public void displayLine(Player pl) {
        TextView areaDisplay = findViewById(R.id.areaDisplay);
        areaDisplay.append(String.format("%-18s%-10d%-10d\n\n", pl.getName(), pl.getNumberGamesPlayed(), pl.getNumberGamesWon()));
    } // displayLine(Player)

    @Override
    public void onClick(View v) {
        int btnClick = v.getId();
        if (btnClick == R.id.btnBackScoreboard)
            finish();
    } // onClick()
} // ScoreboardActivity class