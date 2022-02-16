package com.codingbjs.webviewsample.webinterface;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {

    Context context;

    /** Instantiate the interface and set the context */
    public WebAppInterface(Context c) {
        context = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
