package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // O : 0 , 1 : X , 2 : empty

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;
    boolean gameActive = true;
    int draw=0;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {      //check if box is empty and game is active

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);

            draw+=1;
            if (draw==9){
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setVisibility(View.VISIBLE);
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(view.VISIBLE);
                textView.setText("Draw");
                draw=0;
            }

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotationBy(900).setDuration(300);

            for (int[] winningPosition : winningPositions) {    // win condition true
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winMessage;
                    if (activePlayer == 1) {
                        winMessage = "O has Won!";
                    } else {
                        winMessage = "X has Won!";
                    }
                    gameActive = false;
                    //Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();  //toast winner
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(winMessage);
                    textView.setVisibility(View.VISIBLE);
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
            }
        for (int j=0; j<gameState.length; j++){
            gameState[j]=2;
        }
        activePlayer = 0;
        gameActive = true;
        draw=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
