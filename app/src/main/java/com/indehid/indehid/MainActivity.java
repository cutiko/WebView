package com.indehid.indehid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends AppCompatActivity {

    private WebView root;
    private ProgressDialog progressDialog;
    private static final String URL = "http://stackoverflow.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = (WebView) findViewById(R.id.rootWv);
        setInmersive();
        setDialog();
        setWebview();
    }

    private void setInmersive(){
        root.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void setDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Cargando");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    private void setWebview(){
        root.canGoBack();
        root.setWebViewClient(new CustomWebClient());
        root.setWebChromeClient(new CustomChromeClient());
        root.getSettings().setJavaScriptEnabled(true);
        root.loadUrl(URL);
    }

    @Override
    public void onBackPressed() {
        root.goBack();
    }

    private class CustomWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            progressDialog.dismiss();
        }
    }

    private class CustomChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressDialog.setProgress(newProgress);
        }
    }
}
