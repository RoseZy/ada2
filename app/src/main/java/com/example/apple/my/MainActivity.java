package com.example.apple.my;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.string.ok;

public class MainActivity extends AppCompatActivity {
    private WebView contenWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contenWebView=(WebView)findViewById(R.id.webview);
        contenWebView.getSettings().setJavaScriptEnabled(true);
        contenWebView.loadUrl("file:///android_asset/web.html");
        contenWebView.addJavascriptInterface(MainActivity.this,"android");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenWebView.loadUrl("javascript:javacalljs()");
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenWebView.loadUrl("javascript:javacalljswith("+"'http://blog.csdn.net/Leejizhou'"+")");
            }
        });
    }
    @JavascriptInterface
    public void startFunction(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"show",Toast.LENGTH_LONG).show();
            }
        });
    }
    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this).setMessage(text).show();
            }
        });
    }
}

