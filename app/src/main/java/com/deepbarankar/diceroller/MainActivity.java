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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button roll = (Button) findViewById(R.id.rollButton);
        roll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Log.d("Button Clicked!!!", "Success");
                rollClicked();
            }
        });

        Button reset = (Button) findViewById(R.id.resetButton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;

                Toast.makeText(getApplicationContext(), "Dices and score is reset", Toast.LENGTH_LONG).show();

                setDices("dice1", "dice1");
                scoreUpdate();
            }
        });
    }

    private void rollClicked() {
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

            Toast.makeText(getApplicationContext(), "Opps! Looks like you got two sixes!!!", Toast.LENGTH_LONG).show();
        }

        else {
            score += rand1 + rand2;
            d1 = "dice" + rand1;
            d2 = "dice" + rand2;
        }

        setDices(d1, d2);

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
}