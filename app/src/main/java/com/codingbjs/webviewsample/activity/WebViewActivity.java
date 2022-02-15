package com.codingbjs.webviewsample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.codingbjs.webviewsample.R;
import com.codingbjs.webviewsample.fragment.WebViewFragment;

public class WebViewActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new WebViewFragment();
    }

}