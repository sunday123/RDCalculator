package com.ij34.rdcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 *
 *
 */
public class AboutActivity extends AppCompatActivity {
    TextView versionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle(R.string.about_activity_title);
        versionTextView = findViewById(R.id.versionTextView);
        versionTextView.setText(this.getString(R.string.app_version) +" "+BuildConfig.VERSION_NAME);
    }


    public void contactMeAction(View view) {
        Intent data=new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse( "mailto:lyx668@outlook.com" ));
        data.putExtra(Intent.EXTRA_SUBJECT,  "RDCalculator(进制转换)");
        data.putExtra(Intent.EXTRA_TEXT,  "" );
        startActivity(data);

    }


    public void shareAction(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,this.getString(R.string.share_app_content));
        startActivity(Intent.createChooser(intent,this.getString(R.string.share_app_to_friend)));

    }

    public void privacyPolicyAction(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(this.getString(R.string.privacy_policy_url)));
        startActivity(intent);

    }
}