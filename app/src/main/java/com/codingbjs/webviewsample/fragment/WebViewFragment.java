package com.codingbjs.webviewsample.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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

import java.util.Objects;

public class WebViewFragment extends Fragment implements OnBackPressedListener{

        FragmentWebViewBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWebViewBinding.inflate(inflater, container, false);

        binding.domainEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.d("onEditorAction", "onEditorAction() called with: textView = [" + textView + "], i = [" + i + "], keyEvent = [" + keyEvent + "]");
                if(i == EditorInfo.IME_ACTION_NEXT) {
                    String url = textView.getText().toString();
                    if(!url.contains("https://") && !url.contains("http://")) {
                        url = "https://" + url;
                    }
                    Log.d("url", url);
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
        });

        binding.webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                binding.progressBar.setVisibility(View.VISIBLE);
                if(newProgress == 100) {
                    binding.progressBar.setVisibility(View.GONE);
                }else {
                    binding.progressBar.setProgress(newProgress);
                }
            }
        });

        binding.webView.loadUrl("https://www.daum.net");

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
