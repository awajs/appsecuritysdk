package com.scenera.nicesecurityapplib.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.scenera.nicesecurityapplib.R;


/**
 * Created by Ekta Bhatt on 21-11-2019.
 */

public class CustomTextViewBold extends AppCompatTextView {

    private Typeface typeface;

    public CustomTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextViewBold);
        String customFont = a.getString(R.styleable.CustomTextViewBold_customFontType);
        if(typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/avenirbold.otf");
            a.recycle();
            setTypeface(typeface);
        }
    }
}
