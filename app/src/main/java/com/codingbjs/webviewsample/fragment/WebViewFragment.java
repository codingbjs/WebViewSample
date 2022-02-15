package com.codingbjs.webviewsample.fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codingbjs.webviewsample.activity.WebViewActivity;
import com.codingbjs.webviewsample.databinding.FragmentWebViewBinding;

public class WebViewFragment extends Fragment implements OnBackPressedListener{

        private static final String ASSETS_FILE = "file:///android_asset/www/index.html";
        private static final String DAUM = "http://daum.net";

        FragmentWebViewBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWebViewBinding.inflate(inflater, container, false);

        binding.domainEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_NEXT) {
                    String url = textView.getText().toString();
                    if(!url.contains("https://") && !url.contains("http://")) {
                        url = "https://" + url;
                    }
                    binding.webView.loadUrl(url);
                }
                return false;
            }
        });


        binding.webView.getSettings().setJavaScriptEnabled(true);

        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                binding.progressBar.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(binding.domainEditText.getWindowToken(), 0);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                binding.progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        binding.webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                binding.progressBar.setProgress(newProgress);
            }
        });

        binding.webView.loadUrl(ASSETS_FILE);
//
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        WebViewActivity webViewActivity = (WebViewActivity) getActivity();
        if (webViewActivity != null) {
            webViewActivity.setOnBackPressedListener(this);
        }
    }

    @Override
    public void onBackPressed() {
        if(binding.webView.canGoBack()){
            binding.webView.goBack();
        }else {
            requireActivity().finish();
        }
    }
}
