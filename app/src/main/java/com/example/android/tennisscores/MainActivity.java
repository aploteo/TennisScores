package com.example.android.tennisscores;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    int gamePlayer1;
    int gamePlayer2;
    int setBeingPlayed = 1;
    int wonSetsPlayer1 = 0;
    int wonSetsPlayer2 = 0;
    int acePlayer1;
    int acePlayer2;

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

    Button button1;
    Button button2;

    int indexPlayer1 = 0;
    int indexPlayer2 = 0;

    String[] playerPoints = {"0", "15", "30", "40"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    /**
     * When button "point_player1" is pressed Player1 score increases 1 point.
     */
    public void pointForPlayer1(View view)
    {
        indexPlayer1++;
        if (indexPlayer1 == 4)
        {
            indexPlayer1 = 0;
            indexPlayer2 = 0;
            gamePlayer1TextView = findViewById(R.id.game_player1);
            gamePlayer1TextView.setText(String.valueOf(++gamePlayer1));
            if (gamePlayer1 >= 6 && gamePlayer1 - gamePlayer2 >= 2)
            {
                updateGame();
                setBeingPlayed++;
                wonSetsPlayer1++;
                messageTextView = findViewById(R.id.message);
                messageTextView.setText(wonSetsPlayer1 + " - " + wonSetsPlayer2);
            }
        }
        updatePoints();
        if (wonSetsPlayer1 == 2)
        {
            messageTextView = findViewById(R.id.message);
            messageTextView.setText("Player1 won by " + wonSetsPlayer1 + " sets to " + wonSetsPlayer2);
            disableButtons();
        }
    }

    /**
     * When button "point_player2" is pressed, Player2 score increases by 1 point.
     */
    public void pointForPlayer2(View view)
    {
        indexPlayer2++;
        if (indexPlayer2 == 4)
        {
            indexPlayer1 = 0;
            indexPlayer2 = 0;
            gamePlayer2TextView = findViewById(R.id.game_player2);
            gamePlayer2TextView.setText(String.valueOf(++gamePlayer2));
            if (gamePlayer2 >= 6 && gamePlayer2 - gamePlayer1 >= 2)
            {
                updateGame();
                setBeingPlayed++;
                wonSetsPlayer2++;
                messageTextView = findViewById(R.id.message);
                messageTextView.setText(wonSetsPlayer1 + " - " + wonSetsPlayer2);
            }
        }
        updatePoints();
        if (wonSetsPlayer2 == 2)
        {
            messageTextView = findViewById(R.id.message);
            messageTextView.setText("Player2 won by " + wonSetsPlayer2 + " sets to " + wonSetsPlayer1);
            disableButtons();
        }
    }

    /**
     * Update points column for both players.
     */
    public void updatePoints()
    {
        pointsPlayer1TextView = findViewById(R.id.points_player1);
        pointsPlayer1TextView.setText(playerPoints[indexPlayer1]);
        pointsPlayer2TextView = findViewById(R.id.points_player2);
        pointsPlayer2TextView.setText(playerPoints[indexPlayer2]);
    }

    /**
     * Update game and set columns for both players.
     */
    public void updateGame()
    {
        if (setBeingPlayed == 1)
        {
            set1Player1TextView = findViewById(R.id.set1_player1);
            set1Player1TextView.setText(String.valueOf(gamePlayer1));
            set1Player2TextView = findViewById(R.id.set1_player2);
            set1Player2TextView.setText(String.valueOf(gamePlayer2));
        } else if (setBeingPlayed == 2)
        {
            set2Player1TextView = findViewById(R.id.set2_player1);
            set2Player1TextView.setText(String.valueOf(gamePlayer1));
            set2Player2TextView = findViewById(R.id.set2_player2);
            set2Player2TextView.setText(String.valueOf(gamePlayer2));
        } else
        {
            set3Player1TextView = findViewById(R.id.set3_player1);
            set3Player1TextView.setText(String.valueOf(gamePlayer1));
            set3Player2TextView = findViewById(R.id.set3_player2);
            set3Player2TextView.setText(String.valueOf(gamePlayer2));
        }

        gamePlayer1 = 0;
        gamePlayer1TextView = findViewById(R.id.game_player1);
        gamePlayer1TextView.setText(String.valueOf(gamePlayer1));
        gamePlayer2 = 0;
        gamePlayer2TextView = findViewById(R.id.game_player2);
        gamePlayer2TextView.setText(String.valueOf(gamePlayer2));
    }

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
        messageTextView = findViewById(R.id.message);
        messageTextView.setText("New match");
    }

    public void disableButtons()
    {
        button1 = findViewById(R.id.button1);
        button1.setEnabled(false);
        button2 = findViewById(R.id.button2);
        button2.setEnabled(false);
    }

    public void enableButtons()
    {
        button1 = findViewById(R.id.button1);
        button1.setEnabled(true);
        button2 = findViewById(R.id.button2);
        button2.setEnabled(true);
    }
}