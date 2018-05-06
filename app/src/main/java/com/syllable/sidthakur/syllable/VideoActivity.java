package com.syllable.sidthakur.syllable;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VideoActivity extends FragmentActivity {

    private static final String TAG = "VideoActivity" ;
    public static final String USER_AGENT_FAKE = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


//        ActivityCompat.requestPermissions(this,
//                new String[]{Manifest.permission.CAMERA},1);


        webView = (WebView) findViewById(R.id.web_view_vid);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
              Log.d("WEBVIEW ","SSL error :" + error.toString());
               handler.proceed();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG,"URL1 : "+webView.getUrl());
                Log.d(TAG,"URL2 : "+url);
            }
        });


        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setUserAgentString(USER_AGENT_FAKE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            // Grant permissions for cam

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("WEBVIEW CONSOLE ",consoleMessage.message());
                return true;
            }

            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
                    }
                });
        webView.loadUrl("https://flashmeets.jagtechno.com:4200");
    //  webView.loadUrl("https://apprtc-m.appspot.com/room/91397880");

    }

}
