package com.example.gamerpal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;

public class ShaderSpan extends ReplacementSpan {
    private final Shader shader;

    public ShaderSpan(Shader shader) {
        this.shader = shader;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return (int) paint.measureText(text, start, end);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end,
                     float x, int top, int y, int bottom, Paint paint) {
        TextPaint textPaint = new TextPaint(paint);
        textPaint.setShader(shader);
        canvas.drawText(text, start, end, x, y, textPaint);
    }
    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setShader(shader);
    }
}
