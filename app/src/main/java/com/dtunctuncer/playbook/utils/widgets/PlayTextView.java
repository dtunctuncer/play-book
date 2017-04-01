package com.dtunctuncer.playbook.utils.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.dtunctuncer.playbook.utils.FontCache;

public class PlayTextView extends AppCompatTextView {
    public PlayTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public PlayTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public PlayTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("oldfashionscriptflourishes.ttf", context);
        setTypeface(customFont);
    }
}
