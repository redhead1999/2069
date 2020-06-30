package ru.aold.game2069;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private FlyingAlienView gameView;
    private Handler handler = new Handler();
    private static final long Interval = 30;
    MediaPlayer mysong1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.gameView = new FlyingAlienView(this);
        setContentView((View) this.gameView);
        this.mysong1 = MediaPlayer.create(this, R.raw.bg);
        this.mysong1.start();
        this.mysong1.setLooping(true);
        new Timer().schedule(new TimerTask() {
            public void run() {
                MainActivity.this.handler.post(new Runnable() {
                    public void run() {
                        MainActivity.this.gameView.invalidate();
                    }
                });
            }
        }, 0, Interval);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mysong1.release();
        finish();
    }
}