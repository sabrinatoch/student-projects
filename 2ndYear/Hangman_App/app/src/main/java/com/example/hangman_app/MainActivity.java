package com.example.hangman_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.w3c.dom.Text;

import java.io.*;

import hangman_package.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Scoreboard scoreboard = null;
    private int index = 0;
    private String items[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/RingbearerMedium-51mgZ.ttf");
        TextView lblLogin = findViewById(R.id.lblLogin);
        TextView lblEdition = findViewById(R.id.lblEdition);
        lblLogin.setTypeface(type);
        lblEdition.setTypeface(type);
        findViewById(R.id.btnPlay).setOnClickListener(this);
        findViewById(R.id.radioSelectPlayer).setOnClickListener(this);
        findViewById(R.id.radioNewPlayer).setOnClickListener(this);
        Spinner dropdown = findViewById(R.id.playerDropdown);
        dropdown.setOnItemSelectedListener(this);
        checkScoreboard();
        if (scoreboard == null) {
            getRadioNew().setChecked(true);
            enableNew();
            scoreboard = new Scoreboard();
        } // if scoreboard == null
        else {
            getRadioSelect().setChecked(true);
            enableSelect();
            setDropdown();
        } // else scoreboard exists
    } // onCreate()

    public void setDropdown() {
        Spinner dropdown = findViewById(R.id.playerDropdown);
        items = new String[scoreboard.getNumPlayers()];
        for (int i = 0; i < scoreboard.getNumPlayers(); i++) {
            items[i] = scoreboard.getPlayerAt(i).getName();
        } // for
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    } // setDropdown()

    @Override
    public void onClick(View view) {
        int btnClick = view.getId();
        if (btnClick == R.id.btnPlay) {
            try {
                handlePlay();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
            } // catch
        } // btnPlay
        else if (btnClick == R.id.radioSelectPlayer) {
            enableSelect();
        } // else if radioSelectPlayer
        else if (btnClick == R.id.radioNewPlayer) {
            enableNew();
        } // else if radioNewPlayer
    } // onClick()

    public void handlePlay() throws IOException {
        if (getRadioNew().isChecked()) {
            if (getName().length() == 0) {
                Toast.makeText(getApplicationContext(), "Please enter a name first.", Toast.LENGTH_SHORT).show();
            } // if empty
            else if (getName().length() > 12) {
                Toast.makeText(getApplicationContext(), "Please enter a name under 12 characters.", Toast.LENGTH_SHORT).show();
            } // else too long
            else {
                Player player = new Player(getName(), this);
                if (!scoreboard.playerExists(player)) {
                    scoreboard.addPlayer(player);
                    Intent game = new Intent(this, GameActivity.class);
                    game.putExtra("Player", player);
                    game.putExtra("Scoreboard", scoreboard);
                    EditText edtName =  findViewById(R.id.edtName);
                    startActivity(game);
                    edtName.setText("");
                } // if player doesn't exist
                else {
                    Toast.makeText(getApplicationContext(), "A player with that name already exists! Please pick another name.", Toast.LENGTH_SHORT).show();
                } // else player exists
            } // start game
        } // NEW PLAYER
        else if (getRadioSelect().isChecked()) {
            Player player = scoreboard.getPlayerAt(index);
            Intent game = new Intent(this, GameActivity.class);
            game.putExtra("Player", player);
            game.putExtra("Scoreboard", scoreboard);
            startActivity(game);
        } // RETURNING PLAYER
    } // handlePlay()

    public void checkScoreboard() {
        try {
            java.io.File fileDir = getFilesDir();
            File f = new File(fileDir, "scoreboard.ser");
            FileInputStream file = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(file);
            scoreboard = (Scoreboard) in.readObject();
            in.close();
            file.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Welcome to Hangman (LOTR Edition)!", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
        } // catch
    } // checkScoreboard()

    public String getName() {
        EditText edtName = findViewById(R.id.edtName);
        return edtName.getText().toString();
    } // getName()

    public RadioButton getRadioSelect() {
        return findViewById(R.id.radioSelectPlayer);
    } // getRadioSelect()

    public RadioButton getRadioNew() {
        return findViewById(R.id.radioNewPlayer);
    } // getRadioNew()

    public void enableSelect() {
        (findViewById(R.id.playerDropdown)).setEnabled(true);
        EditText txt = findViewById(R.id.edtName);
        txt.setText("");
        txt.setEnabled(false);
    } // enableSelect()

    public void enableNew() {
        (findViewById(R.id.playerDropdown)).setEnabled(false);
        findViewById(R.id.edtName).setEnabled(true);
    } //  enableNew()

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        index = pos;
    } // onItemSelected()

    public void onNothingSelected(AdapterView parent) {
        // Do nothing.
    } // onNothingSelected()

} // MainActivity class
