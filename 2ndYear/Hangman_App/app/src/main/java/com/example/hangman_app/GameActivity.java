package com.example.hangman_app;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.*;
import android.os.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.io.*;
import hangman_package.*;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Scoreboard scoreboard;
    private Player player;
    private HangmanGame game;

    private  GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViewById(R.id.btnScoreboard).setOnClickListener(this);
        findViewById(R.id.btnRules).setOnClickListener(this);
        findViewById(R.id.btnRing).setOnClickListener(this);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/RingbearerMedium-51mgZ.ttf");
        TextView label = findViewById(R.id.lblPlayer);
        label.setTypeface(type);
        TextView hint = findViewById(R.id.lblHint);
        hint.setTypeface(type);
        grabExtras();
        setupButtons();
        setupHearts();
        serializeBoard();
        setupGame();
    } // onCreate()
    public void grabExtras() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("Scoreboard") && intent.hasExtra("Player")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    scoreboard = (Scoreboard) intent.getSerializableExtra("Scoreboard", Scoreboard.class);
                    player = (Player) intent.getSerializableExtra("Player", Player.class);
                } // if
            } // if intent has the extras
        } // if intent != null
        else
            Toast.makeText(getApplicationContext(), "Can't start the game... Please restart the application.", Toast.LENGTH_SHORT).show();
    } // getExtras()

    public void serializeBoard() {
        try {
            java.io.File fileDir = getFilesDir();
            File f = new File(fileDir, "scoreboard.ser");
            FileOutputStream file = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(scoreboard);
            out.close();
            out.flush();
            file.flush();
            file.close();
        } catch (IOException ex) {
            Toast.makeText(getApplicationContext(), "ERROR: Couldn't save the scoreboard.", Toast.LENGTH_SHORT).show();
            System.exit(0);
        } // catch (IOException)
    } // serializeBoard

    public void noWordsLeftAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("No words left!");
        alertDialogBuilder.setMessage("You've guessed all the words and defeated the Nazgul! Would you like to play again?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            } // onClick
        }); // Positive
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    player.restartDictionary(GameActivity.this);
                    resetGame();
                } catch (IOException e) {
                    Toast.makeText(GameActivity.this,"ERROR: Couldn't restart the game. Please check the wordList.txt file.",Toast.LENGTH_SHORT).show();
                }
            } // onClick()
        });
        alertDialogBuilder.show();
    } // noWordsLeftAlert()
    public void setupGame() {
        try {
            game = new HangmanGame(player);
        } catch (NoWordsLeftException e) {
            noWordsLeftAlert();
        } // catch (NoWordsLeftException)
        TextView lblPlayer = findViewById(R.id.lblPlayer);
        lblPlayer.setText("Welcome back, " + game.getPlayer().getName());
        displayWord();
    } // setupGame()

    public void displayWord() {
        String label = "";
        for (int i = 0; i < game.displayWordState().length(); i++) {
            if (game.displayWordState().charAt(i) == '-')
                label += " __ ";
            else
                label += " " + game.displayWordState().charAt(i) + " ";
        } // for
        TextView lblWord = findViewById(R.id.lblWord);
        lblWord.setText(label);
    } // displayWord()

    public void setupButtons() {
        gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(6);
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.addView(gridLayout);
        for (int i = 0; i < 28; i++) {
            Button button = new Button(this);
            Character letter;
            if (i == 26)
                letter = (char) (24 + 'A');
            else if (i == 27)
                letter = (char) (25 + 'A');
            else
                letter = (char) (i + 'A');
            button.setText(letter.toString());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = 170;
            params.width = 170;
            button.setLayoutParams(params);
            button.setOnClickListener(this);
            if (i == 24 || i == 25)
                button.setVisibility(View.INVISIBLE);
            gridLayout.addView(button);
        } // for
    } // setupButtons()

    @Override
    public void onClick(View view) {
        int btnClick = view.getId();
        if (btnClick == R.id.btnScoreboard) {
            Intent sc = new Intent(this, ScoreboardActivity.class);
              sc.putExtra("Scoreboard", scoreboard);
            startActivity(sc);
        } // btnPlay
        else if (btnClick == R.id.btnRules) {
            Intent rules = new Intent(this, RulesActivity.class);
            startActivity(rules);
        } // btnRules
        else if (btnClick == R.id.btnRing) {
            if (!game.displayHint())
                Toast.makeText(GameActivity.this,"No more hints!",Toast.LENGTH_SHORT).show();
            else {
                displayWord();
                updateHearts();
                disableLetter(game.getHintChar());
                checkComplete();
            } // else
        } // btnRing
        else {
            String charClicked = ((Button) view).getText().toString();
            ((Button) view).setEnabled(false);
            if (game.guessLetter(charClicked.toLowerCase().charAt(0)))
                displayWord();
            else
                updateHearts();

            checkComplete();
        } // else alphabet buttons
    } // onClick()

    public void checkComplete() {
        if (game.isComplete()) {
            if (game.hasWon())
                displayWon();
            else
                displayLost();
        } // if game is complete
    } // checkComplete()

    public void disableLetter(char hint) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button btn = (Button) gridLayout.getChildAt(i);
            if (btn.getText().charAt(0) == Character.toUpperCase(hint))
                btn.setEnabled(false);
        } // for
    } // disableLetter()

    public void displayWon() {
        scoreboard.addGamePlayed(player, true);
        serializeBoard();
        Toast.makeText(getApplicationContext(), "You escaped the Nazgul! The word was \"" + game.getWord() + "\".", Toast.LENGTH_LONG).show();
        resetGame();
    } // displayWon()

    public void displayLost() {
        scoreboard.addGamePlayed(player, false);
        serializeBoard();
        Toast.makeText(getApplicationContext(), "Oh no! The Nazgul stole the Ring! The word was \"" + game.getWord() + "\".", Toast.LENGTH_LONG).show();
        resetGame();
    } // displayLost()

    public void resetGame() {
        try {
            game = new HangmanGame(player);
        } catch (NoWordsLeftException e) {
            noWordsLeftAlert();
        } // catch (NoWordsLeftException)
        resetHearts();
        resetButtons();
        displayWord();
    } // resetGame()

    public void resetButtons() {
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.removeViewAt(0);
        setupButtons();
    } // resetButtons()
    public void setupHearts() {
        LinearLayout hearts = findViewById(R.id.heartLayout);
        for (int i = 0; i < 6; ++i) {
            ImageView img = new ImageView(this);
            img.setImageResource(R.drawable.heart);
            img.setPadding(80, 0, 20, 0);
            hearts.addView(img);
        } // for
    } // setupHearts()
    public void updateHearts() {
        int numGuesses = game.getNumGuesses();
        LinearLayout hearts = findViewById(R.id.heartLayout);
        for (int i = hearts.getChildCount(); i > 0; --i) {
            if (numGuesses == i) {
                ImageView img = (ImageView) hearts.getChildAt(i);
                img.setImageResource(R.drawable.heart_black);
            } // if
        } // for
    } // updateImage()

    public void resetHearts() {
        LinearLayout hearts = findViewById(R.id.heartLayout);
        for (int i = hearts.getChildCount(); i > 0; --i) {
                ImageView img = (ImageView) hearts.getChildAt(i-1);
                img.setImageResource(R.drawable.heart);
        } // for
    } // resetHearts()

} // GameActivity class