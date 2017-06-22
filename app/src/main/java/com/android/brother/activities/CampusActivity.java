package com.android.brother.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.brother.R;
import com.android.brother.entities.RushEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 22-06-2017.
 */

public class CampusActivity extends BaseActivity {

    @BindView(R.id.campus_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.campus_webView)
    WebView webView;
    @BindView(R.id.map_rush_name)
    TextView rushName;
    @BindView(R.id.map_rush_time)
    TextView rushTime;
    @BindView(R.id.map_rush_date)
    TextView rushDate;
    @BindView(R.id.map_rush_info_layout)
    LinearLayout linearLayout;
    @BindView(R.id.map_description)
    TextView rushDescription;
    @BindView(R.id.map_description1)
    TextView rushDescription1;

    public static final String RUSH_EVENT_EXTRA = "RUSH_EVENT_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);
        ButterKnife.bind(this);

        RushEvent rushEvent = getIntent().getParcelableExtra(RUSH_EVENT_EXTRA);
        rushName.setText(rushEvent.getEventName());
        rushDate.setText(rushEvent.getEventDate());
        rushTime.setText(rushEvent.getEventTime());
        rushDescription.setText(rushEvent.getEventLocation());
        rushDescription1.setText(rushEvent.getEventDescription());

        String googleDocs = "http://docs.google.com/gview?embedded=true&url=";

        progressBar.setMax(100);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        webView.loadUrl(googleDocs + "http://www.asu.edu/map/pdf/asu_map_tempe_2015.pdf");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static Intent newIntent(Context context, RushEvent rushEvent){
        Intent intent = new Intent(context, CampusActivity.class);
        intent.putExtra(RUSH_EVENT_EXTRA, rushEvent);
        return intent;
    }
}
