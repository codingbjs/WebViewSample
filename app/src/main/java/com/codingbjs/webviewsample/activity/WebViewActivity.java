package com.codingbjs.webviewsample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.codingbjs.webviewsample.R;
import com.codingbjs.webviewsample.fragment.OnBackPressedListener;
import com.codingbjs.webviewsample.fragment.WebViewFragment;

public class WebViewActivity extends SingleFragmentActivity {

    OnBackPressedListener onBackPressedListener;

    @Override
    protected Fragment createFragment() {
        return new WebViewFragment();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if(onBackPressedListener != null){
            onBackPressedListener.onBackPressed();
        }else {
            super.onBackPressed();
        }

    }
}