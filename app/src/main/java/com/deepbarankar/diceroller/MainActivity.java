package com.deepbarankar.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    boolean player1 = true;
    int player1Score = 0;
    int player2Score = 0;
    private final int MAX_SCORE = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button roll = (Button) findViewById(R.id.rollButton);
        final Button reset = (Button) findViewById(R.id.resetButton);

        roll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Log.d("Button Clicked!!!", "Success");
                rollClicked();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetClicked();
            }
        });
    }

    private void rollClicked() {
        if(player1Score >= MAX_SCORE || player2Score >= MAX_SCORE) return;

        int rand1 = 1 + (int) (Math.random() * 6);
        int rand2 = 1 + (int) (Math.random() * 6);
        String d1 = "dice1";
        String d2 = "dice1";
        if(rand1 == rand2 && rand1 == 6) {
            score = 0;

            /*
            Context context = getApplicationContext();
            CharSequence text = "Opps! Looks like you got two sixes!!!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
             */

            Toast.makeText(getApplicationContext(), "Oops! Looks like you got two sixes!!!", Toast.LENGTH_LONG).show();
            player1 = !player1;
        }

        else {
            score += rand1 + rand2;
            d1 = "dice" + rand1;
            d2 = "dice" + rand2;
        }

        if(player1) {
            TextView score1 = (TextView) findViewById(R.id.score1);
            player1Score += score;
            score1.setText(String.valueOf(player1Score));
        } else {
            TextView score1 = (TextView) findViewById(R.id.score2);
            player2Score += score;
            score1.setText(String.valueOf(player2Score));
        }

        if(player1Score >= MAX_SCORE || player2Score >= MAX_SCORE) {
            gameWon();
        }

        setDices(d1, d2);

        scoreUpdate();
    }

    private void resetClicked() {
        score = 0;
        player1Score = 0;
        player2Score = 0;

        Toast.makeText(getApplicationContext(), "Dices and scores are reset", Toast.LENGTH_LONG).show();

        player1 = true;

        TextView score1 = (TextView) findViewById(R.id.score1);
        score1.setText(String.valueOf(score));
        TextView score2 = (TextView) findViewById(R.id.score2);
        score2.setText(String.valueOf(score));

        setDices("dice1", "dice1");
        scoreUpdate();
    }

    private void scoreUpdate() {
        TextView scoreView = (TextView) findViewById(R.id.scoreCount);
        scoreView.setText(String.valueOf(score));
    }

    private void setDices(String d1, String d2) {
        int res1 = getResources().getIdentifier(d1, "drawable", this.getPackageName());
        ImageView dice_1 = (ImageView) findViewById(R.id.dice_1);
        dice_1.setImageResource(res1);

        int res2 = getResources().getIdentifier(d2, "drawable", this.getPackageName());
        ImageView dice_2 = (ImageView) findViewById(R.id.dice_2);
        dice_2.setImageResource(res2);
    }

    private void gameWon() {
        String p1 = "0";
        String p2 = "0";
        score = 0;
        if(player1) {
            p1 = "WINNER!!!";
        } else {
            p2 = "WINNER!!!";
        }

        TextView score1 = (TextView) findViewById(R.id.score1);
        score1.setText(p1);
        TextView score2 = (TextView) findViewById(R.id.score2);
        score2.setText(p2);
    }
}