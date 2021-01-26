package com.example.ap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Hud extends BaseObject {
    private final Game game;
    private final Scene scene;

    public Hud(Game game, Scene scene) {
        super(0, 0, scene.getWidth(), scene.getHeight(), Color.BLACK, 0.0);
        this.game = game;
        this.scene = scene;
    }

    public void update(Canvas canvas) {
        draw((int)this.getX(), (int)this.getY(), canvas);
    }

    private void draw(int x, int y, Canvas canvas) {
        drawTimer(canvas);
        drawPlayerScore(x, y, canvas);
    }

    private void drawPlayerScore(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        Player player = this.game.getPlayer();
        paint.setColor(player.getColor());
        paint.setTextSize(this.scene.getBaseBlockSize() * 2);
        canvas.drawText(player.getName() + ": " + player.getScore(), x + (scene.getBaseBlockSize() * 2), y + this.getHeight() - (scene.getBaseBlockSize() * 2), paint);
    }

    private void drawTimer(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.rgb(204, 204, 204));
        paint.setTextSize(20 * this.scene.getBaseBlockSize());

        int x = (this.getWidth() / 2);
        int y = (int) ((this.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        canvas.drawText(String.valueOf(this.game.getCountdown()), x, y, paint);
    }
}
