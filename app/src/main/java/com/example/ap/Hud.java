package com.example.ap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Hud extends BaseObject {
    private Game game;

    public Hud(Game game) {
        super(0, 0, 0, 0, Color.BLACK, 0.0);
        this.game = game;
    }

    public void update(Canvas canvas) {
        draw(this.getX(), this.getY(), canvas);
    }

    private void draw(int x, int y, Canvas canvas) {
        drawTimer(x, y, canvas);
        drawPlayerScore(x, y, canvas);
    }

    private void drawPlayerScore(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        Player player = this.game.getPlayer();
        paint.setColor(player.getColor());
        paint.setTextSize(40);
        canvas.drawText("Player " + player.getName() + ": " + player.getScore(), x + 40, y + 715, paint);
    }

    private void drawTimer(int x, int y, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(204, 204, 204));
        paint.setTextSize(430);
        canvas.drawText(String.valueOf(this.game.getCountdown()), x + 150, y + 460, paint);
    }
}
