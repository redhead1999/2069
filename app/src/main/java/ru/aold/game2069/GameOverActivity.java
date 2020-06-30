package ru.aold.game2069;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {
    private TextView DisplayScore;
    private Button StartGameAgain;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_gameover);
        TextView highscoreLabel = (TextView) findViewById(R.id.highScoreLabel);
        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        int score = getIntent().getIntExtra("SCORE", 0);
        StringBuilder sb = new StringBuilder();
        sb.append(score);
        sb.append(BuildConfig.FLAVOR);
        scoreLabel.setText(sb.toString());
        SharedPreferences settings = getSharedPreferences("GAME_DATA", 0);
        String str = "HIGH_SCORE";
        int highScore = settings.getInt(str, 0);
        if (score > highScore) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Рекорд : ");
            sb2.append(score);
            highscoreLabel.setText(sb2.toString());
            Editor editor = settings.edit();
            editor.putInt(str, score);
            editor.commit();
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Рекорд: ");
            sb3.append(highScore);
            highscoreLabel.setText(sb3.toString());
        }
        this.StartGameAgain = (Button) findViewById(R.id.play_again_btn);
        this.DisplayScore = (TextView) findViewById(R.id.scoreLabel);
        this.StartGameAgain.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                GameOverActivity.this.startActivity(new Intent(GameOverActivity.this, MainActivity.class));
                GameOverActivity.this.finish();
            }
        });
        TextView textView = this.DisplayScore;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Счёт : ");
        sb4.append(score);
        textView.setText(sb4.toString());
    }
}