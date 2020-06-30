package ru.aold.game2069;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;

public class FlyingAlienView extends View {
    private int canvasHeight;
    private int canvasWidth;
    private Bitmap[] alien = new Bitmap[2];
    private int alienSpeed;
    private int alienX = 10;
    private int alienY;
    private Paint greenPaint = new Paint();
    private int greenSpeed = 29;
    private int greenX;
    private int greenY;
    private Bitmap[] life = new Bitmap[2];
    private int lifeCounterOfAlien;
    private Bitmap mBitmap;
    private Paint redPaint = new Paint();
    private int redSpeed = 32;
    private int redX;
    private int redY;
    private int score;
    private Paint scorePaint = new Paint();
    private boolean touch = false;
    private Paint yellowPaint = new Paint();
    private int yellowSpeed = 25;
    private int yellowX;
    private int yellowY;

    public FlyingAlienView(Context context) {
        super(context);
        this.alien[0] = BitmapFactory.decodeResource(getResources(), R.drawable.alien1);
        this.alien[1] = BitmapFactory.decodeResource(getResources(), R.drawable.alien2);
        this.mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        this.yellowPaint.setColor(InputDeviceCompat.SOURCE_ANY);
        this.yellowPaint.setAntiAlias(false);
        this.greenPaint.setColor(-16711936);
        this.greenPaint.setAntiAlias(false);
        this.redPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.redPaint.setAntiAlias(false);
        this.scorePaint.setColor(-1);
        this.scorePaint.setTextSize(70.0f);
        this.scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.scorePaint.setAntiAlias(true);
        this.life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        this.life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);
        this.alienY = 550;
        this.score = 0;
        this.lifeCounterOfAlien = 4;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, null);
        int height = this.alien[0].getHeight();
        int height2 = this.canvasHeight - (this.alien[0].getHeight() * 3);
        this.alienY += this.alienSpeed;
        if (this.alienY < height) {
            this.alienY = height;
        }
        if (this.alienY > height2) {
            this.alienY = height2;
        }
        this.alienSpeed += 2;
        if (this.touch) {
            canvas.drawBitmap(this.alien[1], (float) this.alienX, (float) this.alienY, null);
            this.touch = false;
        } else {
            canvas.drawBitmap(this.alien[0], (float) this.alienX, (float) this.alienY, null);
        }
        this.yellowX -= this.yellowSpeed;
        if (hitBallChecker(this.yellowX, this.yellowY)) {
            this.score += 1000;
            this.yellowX = -100;
        }
        if (this.yellowX < 0) {
            this.yellowX = this.canvasWidth + 21;
            double random = Math.random();
            double d = (double) (height2 - height);
            Double.isNaN(d);
            this.yellowY = ((int) Math.floor(random * d)) + height;
        }
        canvas.drawCircle((float) this.yellowX, (float) this.yellowY, 38.0f, this.yellowPaint);
        this.greenX -= this.greenSpeed;
        if (hitBallChecker(this.greenX, this.greenY)) {
            this.score += 2000;
            this.greenX = -100;
        }
        if (this.greenX < 0) {
            this.greenX = this.canvasWidth + 21;
            double random2 = Math.random();
            double d2 = (double) (height2 - height);
            Double.isNaN(d2);
            this.greenY = ((int) Math.floor(random2 * d2)) + height;
        }
        canvas.drawCircle((float) this.greenX, (float) this.greenY, 38.0f, this.greenPaint);
        this.redX -= this.redSpeed;
        if (hitBallChecker(this.redX, this.redY)) {
            this.score -= 5000;
            this.redX = -100;
            this.lifeCounterOfAlien--;
            if (this.lifeCounterOfAlien == 0) {
                Intent intent = new Intent(getContext(), GameOverActivity.class);
                intent.putExtra("SCORE", this.score);
                getContext().startActivity(intent);
            }
        }
        if (this.redX < 0) {
            this.redX = this.canvasWidth + 21;
            double random3 = Math.random();
            double d3 = (double) (height2 - height);
            Double.isNaN(d3);
            this.redY = ((int) Math.floor(random3 * d3)) + height;
        }
        canvas.drawCircle((float) this.redX, (float) this.redY, 42.0f, this.redPaint);
        StringBuilder sb = new StringBuilder();
        sb.append("Счёт : ");
        sb.append(this.score);
        canvas.drawText(sb.toString(), 20.0f, 60.0f, this.scorePaint);
        for (int i = 0; i < 4; i++) {
            double width = (double) this.life[0].getWidth();
            Double.isNaN(width);
            double d4 = width * 1.5d;
            double d5 = (double) i;
            Double.isNaN(d5);
            int i2 = (int) ((d4 * d5) + 580.0d);
            if (i < this.lifeCounterOfAlien) {
                canvas.drawBitmap(this.life[0], (float) i2, (float) 30, null);
            } else {
                canvas.drawBitmap(this.life[1], (float) i2, (float) 30, null);
            }
        }
    }

    public boolean hitBallChecker(int i, int i2) {
        int i3 = this.alienX;
        if (i3 < i && i < i3 + this.alien[0].getWidth()) {
            int i4 = this.alienY;
            if (i4 < i2 && i2 < i4 + this.alien[0].getHeight()) {
                return true;
            }
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        motionEvent.getAction();
        this.touch = true;
        this.alienSpeed = -30;
        return true;
    }
}