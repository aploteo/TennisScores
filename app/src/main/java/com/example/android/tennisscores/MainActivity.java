package com.example.android.tennisscores;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    public static final String SET1_PLAYER_1 = "set1Player1";
    public static final String SET1_PLAYER_2 = "set1Player2";
    public static final String SET2_PLAYER_1 = "set2Player1";
    public static final String SET2_PLAYER_2 = "set2Player2";
    public static final String SET3_PLAYER_1 = "set3Player1";
    public static final String SET3_PLAYER_2 = "set3Player2";
    public static final String POINTS_PLAYER_1 = "pointsPlayer1";
    public static final String POINTS_PLAYER_2 = "pointsPlayer2";
    public static final String INDEX_PLAYER_1 = "indexPlayer1";
    public static final String INDEX_PLAYER_2 = "indexPlayer2";
    public static final String GAME_PLAYER_1 = "gamePlayer1";
    public static final String GAME_PLAYER_2 = "gamePlayer2";
    public static final String SET_BEING_PLAYED = "setBeingPlayed";
    public static final String WON_SETS_PLAYER1 = "wonSetsPlayer1";
    public static final String WON_SETS_PLAYER2 = "wonSetsPlayer2";
    public static final String ACE_PLAYER1 = "acePlayer1";
    public static final String ACE_PLAYER2 = "acePlayer2";
    public static final String FAULT_PLAYER1 = "faultPlayer1";
    public static final String FAULT_PLAYER2 = "faultPlayer2";
    public static final String MESSAGE = "message";
    public static final String GAMETIE = "gameTie";
    public static final String TIEPLAYER1 = "tiePlayer1";
    public static final String TIEPLAYER2 = "tiePlayer2";
    public static final String CALLERMETHOD = "callerMethod";

    String message = "Beginning of the match";
    int gamePlayer1;
    int gamePlayer2;
    int setBeingPlayed = 1;
    int wonSetsPlayer1 = 0;
    int wonSetsPlayer2 = 0;
    int acePlayer1;
    int acePlayer2;
    int faultPlayer1;
    int faultPlayer2;

    TextView pointsPlayer1TextView;
    TextView pointsPlayer2TextView;
    TextView gamePlayer1TextView;
    TextView gamePlayer2TextView;
    TextView set1Player1TextView;
    TextView set2Player1TextView;
    TextView set3Player1TextView;
    TextView set1Player2TextView;
    TextView set2Player2TextView;
    TextView set3Player2TextView;
    TextView messageTextView;
    TextView acePlayer1TextView;
    TextView acePlayer2TextView;
    TextView faultPlayer1TextView;
    TextView faultPlayer2TextView;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    int indexPlayer1 = 0;
    int indexPlayer2 = 0;
    String[] playerPoints = {"0", "15", "30", "40"};
    boolean gameTie;
    int tiePlayer1 = 0;
    int tiePlayer2 = 0;
    int callerMethod = 0;
    int[][] setResults;

    {
        setResults = new int[2][3];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        pointsPlayer1TextView = findViewById(R.id.points_player1);
        pointsPlayer2TextView = findViewById(R.id.points_player2);
        gamePlayer1TextView = findViewById(R.id.game_player1);
        gamePlayer2TextView = findViewById(R.id.game_player2);
        set1Player1TextView = findViewById(R.id.set1_player1);
        set1Player2TextView = findViewById(R.id.set1_player2);
        set2Player1TextView = findViewById(R.id.set2_player1);
        set2Player2TextView = findViewById(R.id.set2_player2);
        set3Player1TextView = findViewById(R.id.set3_player1);
        set3Player2TextView = findViewById(R.id.set3_player2);
        messageTextView = findViewById(R.id.message);
        acePlayer1TextView = findViewById(R.id.aces_player1);
        acePlayer2TextView = findViewById(R.id.aces_player2);
        faultPlayer1TextView = findViewById(R.id.faults_player1);
        faultPlayer2TextView = findViewById(R.id.faults_player2);
    }

    /**
     * When button "player1" is pressed Player1 score increases 1 point.
     */
    public void pointForPlayer1(View view)
    {
        if (gameTie)
        {
            callerMethod = 1;
            untieGame();
            if (wonSetsPlayer1 == 2)
            {
                message = "Player1 won by " + wonSetsPlayer1 + " sets to " + wonSetsPlayer2;
                endOfMatch();
            }
            return;
        } else indexPlayer1++;

        if (indexPlayer1 == 3 && indexPlayer2 == 3)
        {
            updatePoints();
            gameTie = true;
            return;
        }

        if (indexPlayer1 == 4)
        {
            endOfGame(1);
        }

        updatePoints();

        if (wonSetsPlayer1 == 2)
        {
            message = "Player1 won by " + wonSetsPlayer1 + " sets to " + wonSetsPlayer2;
            endOfMatch();
        }
    }

    public void endOfGame(int callerMethod)
    {
        indexPlayer1 = 0;
        indexPlayer2 = 0;
        if (callerMethod == 1)
        {
            gamePlayer1TextView.setText(String.valueOf(++gamePlayer1));
            if (gamePlayer1 >= 6 && gamePlayer1 - gamePlayer2 >= 2)
            {
                setResults[0][setBeingPlayed - 1] = gamePlayer1;
                setResults[1][setBeingPlayed - 1] = gamePlayer2;
                updateGame();
                setBeingPlayed++;
                wonSetsPlayer1++;

                message = wonSetsPlayer1 + " - " + wonSetsPlayer2;
                updateMessage();
            }
        } else
        {
            gamePlayer2TextView.setText(String.valueOf(++gamePlayer2));
            if (gamePlayer2 >= 6 && gamePlayer2 - gamePlayer1 >= 2)
            {
                setResults[0][setBeingPlayed - 1] = gamePlayer1;
                setResults[1][setBeingPlayed - 1] = gamePlayer2;
                updateGame();
                setBeingPlayed++;
                wonSetsPlayer2++;
                message = wonSetsPlayer1 + " - " + wonSetsPlayer2;
                updateMessage();
            }
        }
    }

    private void untieGame()
    {
        if (callerMethod == 1)
        {
            tiePlayer1++;
        } else tiePlayer2++;

        if (tiePlayer1 - tiePlayer2 >= 2)
        {
            tiePlayer1 = 0;
            tiePlayer2 = 0;
            callerMethod = 0;
            gameTie = false;
            endOfGame(1);
            updatePoints();
            return;
        } else if (tiePlayer2 - tiePlayer1 >= 2)
        {
            tiePlayer1 = 0;
            tiePlayer2 = 0;
            callerMethod = 0;
            gameTie = false;
            endOfGame(2);
            updatePoints();
            return;
        }
        if (callerMethod == 1 && tiePlayer1 != tiePlayer2)
        {
            pointsPlayer1TextView.setText("Ad");
            pointsPlayer2TextView.setText("");
        } else if (callerMethod == 2 && tiePlayer1 != tiePlayer2)
        {
            pointsPlayer1TextView.setText("");
            pointsPlayer2TextView.setText("Ad");
        } else
        {
            tiePlayer1 = 0;
            tiePlayer2 = 0;
            pointsPlayer1TextView.setText("40");
            pointsPlayer2TextView.setText("40");
        }
        Log.v("MainActivity", "player 1: " + tiePlayer1);
        Log.v("MainActivity", "player 2: " + tiePlayer2);
    }

    /**
     * When button "player2" is pressed, Player2 score increases by 1 point.
     */
    public void pointForPlayer2(View view)
    {
        if (gameTie)
        {
            callerMethod = 2;
            untieGame();
            if (wonSetsPlayer2 == 2)
            {
                message = "Player2 won by " + wonSetsPlayer2 + " sets to " + wonSetsPlayer1;
                endOfMatch();
            }
            return;
        } else indexPlayer2++;

        if (indexPlayer1 == 3 && indexPlayer2 == 3)
        {
            updatePoints();
            gameTie = true;
            return;
        }

        if (indexPlayer2 == 4)
        {
            endOfGame(2);
        }

        updatePoints();

        if (wonSetsPlayer2 == 2)
        {
            message = "Player2 won by " + wonSetsPlayer2 + " sets to " + wonSetsPlayer1;
            endOfMatch();
        }

    }

    public void endOfMatch()
    {
        updateMessage();
        disableButtons();
    }

    /**
     * Update points column for both players.
     */
    public void updatePoints()
    {
        pointsPlayer1TextView.setText(playerPoints[indexPlayer1]);
        pointsPlayer2TextView.setText(playerPoints[indexPlayer2]);
    }

    /**
     * Update game and set columns for both players.
     */
    public void updateGame()
    {
        if (setBeingPlayed == 1)
        {
            set1Player1TextView.setText(String.valueOf(gamePlayer1));
            set1Player2TextView.setText(String.valueOf(gamePlayer2));
        } else if (setBeingPlayed == 2)
        {
            set2Player1TextView.setText(String.valueOf(gamePlayer1));
            set2Player2TextView.setText(String.valueOf(gamePlayer2));
        } else
        {
            set3Player1TextView.setText(String.valueOf(gamePlayer1));
            set3Player2TextView.setText(String.valueOf(gamePlayer2));
        }

        gamePlayer1 = 0;
        gamePlayer1TextView.setText(String.valueOf(gamePlayer1));
        gamePlayer2 = 0;
        gamePlayer2TextView.setText(String.valueOf(gamePlayer2));
    }

    /**
     * Reset scoreboard and all other counters.
     */
    public void resetScoreBoard(View view)
    {
        indexPlayer1 = 0;
        indexPlayer2 = 0;
        gamePlayer1 = 0;
        gamePlayer2 = 0;
        setBeingPlayed = 2;
        updateGame();
        setBeingPlayed = 3;
        updateGame();
        setBeingPlayed = 1;
        updateGame();
        updatePoints();
        enableButtons();
        wonSetsPlayer1 = 0;
        wonSetsPlayer2 = 0;
        message = "New match";
        updateMessage();
        acePlayer1 = 0;
        acePlayer2 = 0;
        updateAces();
        faultPlayer1 = 0;
        faultPlayer2 = 0;
        updateFaults();
    }

    public void disableButtons()
    {
        button1 = findViewById(R.id.button1);
        button1.setEnabled(false);
        button2 = findViewById(R.id.button2);
        button2.setEnabled(false);
        button3 = findViewById(R.id.button3);
        button3.setEnabled(false);
        button4 = findViewById(R.id.button4);
        button4.setEnabled(false);
        button5 = findViewById(R.id.button5);
        button5.setEnabled(false);
        button6 = findViewById(R.id.button6);
        button6.setEnabled(false);
    }

    public void enableButtons()
    {
        button1 = findViewById(R.id.button1);
        button1.setEnabled(true);
        button2 = findViewById(R.id.button2);
        button2.setEnabled(true);
        button3 = findViewById(R.id.button3);
        button3.setEnabled(true);
        button4 = findViewById(R.id.button4);
        button4.setEnabled(true);
        button5 = findViewById(R.id.button5);
        button5.setEnabled(true);
        button6 = findViewById(R.id.button6);
        button6.setEnabled(true);
    }

    /**
     * When button is pressed Player1's aces increase by 1.
     */
    public void aceForPlayer1(View view)
    {
        acePlayer1++;
        updateAces();
    }

    /**
     * When button is pressed Player2's aces increase by 1.
     */
    public void aceForPlayer2(View view)
    {
        acePlayer2++;
        updateAces();
    }

    /**
     * Updates aces scores for both players.
     */
    public void updateAces()
    {
        acePlayer1TextView.setText(String.valueOf(acePlayer1));
        acePlayer2TextView.setText(String.valueOf(acePlayer2));
    }

    /**
     * When button is pressed Player1's faults increase by 1.
     */
    public void faultForPlayer1(View view)
    {
        faultPlayer1++;
        updateFaults();
    }

    /**
     * When button is pressed Player1's faults increase by 1.
     */
    public void faultForPlayer2(View view)
    {
        faultPlayer2++;
        updateFaults();
    }

    /**
     * Updates fault scores for both players.
     */
    public void updateFaults()
    {
        faultPlayer1TextView.setText(String.valueOf(faultPlayer1));
        faultPlayer2TextView.setText(String.valueOf(faultPlayer2));
    }

    /**
     * Stores values for global variables between different Activities.
     */
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putInt(SET1_PLAYER_1, setResults[0][0]);
        outState.putInt(SET2_PLAYER_1, setResults[0][1]);
        outState.putInt(SET3_PLAYER_1, setResults[0][2]);
        outState.putInt(SET1_PLAYER_2, setResults[1][0]);
        outState.putInt(SET2_PLAYER_2, setResults[1][1]);
        outState.putInt(SET3_PLAYER_2, setResults[1][2]);
        outState.putString(POINTS_PLAYER_1, pointsPlayer1TextView.getText().toString());
        outState.putString(POINTS_PLAYER_2, pointsPlayer2TextView.getText().toString());
        outState.putInt(INDEX_PLAYER_1, indexPlayer1);
        outState.putInt(INDEX_PLAYER_2, indexPlayer2);
        outState.putInt(GAME_PLAYER_1, gamePlayer1);
        outState.putInt(GAME_PLAYER_2, gamePlayer2);
        outState.putInt(SET_BEING_PLAYED, setBeingPlayed);
        outState.putInt(WON_SETS_PLAYER1, wonSetsPlayer1);
        outState.putInt(WON_SETS_PLAYER2, wonSetsPlayer2);
        outState.putInt(ACE_PLAYER1, acePlayer1);
        outState.putInt(ACE_PLAYER2, acePlayer2);
        outState.putInt(FAULT_PLAYER1, faultPlayer1);
        outState.putInt(FAULT_PLAYER2, faultPlayer2);
        outState.putString(MESSAGE, message);
        outState.putBoolean(GAMETIE, gameTie);
        outState.putInt(TIEPLAYER1, tiePlayer1);
        outState.putInt(TIEPLAYER2, tiePlayer2);
        outState.putInt(CALLERMETHOD, callerMethod);

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    /**
     * Restores values for global variables between different Activities.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        setResults[0][0] = savedInstanceState.getInt(SET1_PLAYER_1);
        setResults[0][1] = savedInstanceState.getInt(SET2_PLAYER_1);
        setResults[0][2] = savedInstanceState.getInt(SET3_PLAYER_1);
        setResults[1][0] = savedInstanceState.getInt(SET1_PLAYER_2);
        setResults[1][1] = savedInstanceState.getInt(SET2_PLAYER_2);
        setResults[1][2] = savedInstanceState.getInt(SET3_PLAYER_2);

        set1Player1TextView.setText(String.valueOf(setResults[0][0]));
        set2Player1TextView.setText(String.valueOf(setResults[0][1]));
        set3Player1TextView.setText(String.valueOf(setResults[0][2]));

        set1Player2TextView.setText(String.valueOf(setResults[1][0]));
        set2Player2TextView.setText(String.valueOf(setResults[1][1]));
        set3Player2TextView.setText(String.valueOf(setResults[1][2]));

        pointsPlayer1TextView.setText(savedInstanceState.getString(POINTS_PLAYER_1));
        pointsPlayer2TextView.setText(savedInstanceState.getString(POINTS_PLAYER_2));

        indexPlayer1 = savedInstanceState.getInt(INDEX_PLAYER_1);
        indexPlayer2 = savedInstanceState.getInt(INDEX_PLAYER_2);

        gamePlayer1 = savedInstanceState.getInt(GAME_PLAYER_1);
        gamePlayer2 = savedInstanceState.getInt(GAME_PLAYER_2);
        gamePlayer1TextView.setText(String.valueOf(gamePlayer1));
        gamePlayer2TextView.setText(String.valueOf(gamePlayer2));

        setBeingPlayed = savedInstanceState.getInt(SET_BEING_PLAYED);
        wonSetsPlayer1 = savedInstanceState.getInt(WON_SETS_PLAYER1);
        wonSetsPlayer2 = savedInstanceState.getInt(WON_SETS_PLAYER2);

        acePlayer1 = savedInstanceState.getInt(ACE_PLAYER1);
        acePlayer2 = savedInstanceState.getInt(ACE_PLAYER2);
        updateAces();

        faultPlayer1 = savedInstanceState.getInt(FAULT_PLAYER1);
        faultPlayer2 = savedInstanceState.getInt(FAULT_PLAYER2);
        updateFaults();

        message = savedInstanceState.getString(MESSAGE);
        updateMessage();

        gameTie = savedInstanceState.getBoolean(GAMETIE);
        tiePlayer1 = savedInstanceState.getInt(TIEPLAYER1);
        tiePlayer2 = savedInstanceState.getInt(TIEPLAYER2);
        callerMethod = savedInstanceState.getInt(CALLERMETHOD);
    }

    /**
     * Updates the message that appears in message TextView.
     */
    public void updateMessage()
    {
        messageTextView.setText(message);
    }
}