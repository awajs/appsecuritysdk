package com.scenera.nicesecurityapp.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.scenera.nicesecurityapp.R;


/**
 * Created by Ekta Bhatt on 21-11-2019.
 */


public class CustomTextViewRegular extends AppCompatTextView {

    private Typeface typeface;

    public CustomTextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextViewBold);
        String customFont = a.getString(R.styleable.CustomTextViewBold_customFontType);
        if(typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/avenirregular.otf");
            a.recycle();
            setTypeface(typeface);
        }
    }
}
