package com.minimax.tvwebview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
    
    private WebView webView;
    private ProgressBar progressBar;
    private static final String TARGET_URL = "http://118.31.72.29:8100/#/de-link/BdGpvdNr";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 设置全屏模式
        setupFullScreen();
        
        // 设置布局
        setContentView(R.layout.activity_main);
        
        // 初始化WebView
        initWebView();
        
        // 加载目标网页
        webView.loadUrl(TARGET_URL);
    }
    
    /**
     * 设置全屏模式
     */
    private void setupFullScreen() {
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // 设置全屏
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        
        // 隐藏导航栏（适用于更高版本Android）
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }
    
    /**
     * 初始化WebView设置
     */
    private void initWebView() {
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progress_bar);
        
        // WebView基础设置
        WebSettings webSettings = webView.getSettings();
        
        // 启用JavaScript
        webSettings.setJavaScriptEnabled(true);
        
        // 设置User Agent（适配电视浏览器）
        webSettings.setUserAgentString(webSettings.getUserAgentString() + " TV-WebView/1.0");
        
        // 支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        
        // 适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        
        // 缓存设置
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        
        // 混合内容支持（对于HTTPS中的HTTP内容）
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        
        // 设置WebViewClient处理页面导航
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 在当前WebView中加载所有链接
                view.loadUrl(url);
                return true;
            }
            
            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // 显示加载进度条
                progressBar.setVisibility(View.VISIBLE);
            }
            
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 隐藏加载进度条
                progressBar.setVisibility(View.GONE);
            }
        });
        
        // 设置WebChromeClient处理进度和标题
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });
    }
    
    @Override
    public void onBackPressed() {
        // 如果WebView可以后退，则后退
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            // 否则退出应用
            super.onBackPressed();
        }
    }
    
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) {
            webView.onPause();
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) {
            webView.onResume();
        }
    }
}
