package mfanyakazi.com.mobiwater;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import mfanyakazi.com.mobiwater.service.NetworkService;
import mfanyakazi.com.mobiwater.service.SendToken;
import mfanyakazi.com.mobiwater.utils.PrefUtils;


public class MainActivity extends AppCompatActivity {

    public WebView webview;
    public ProgressDialog progressDialog;
    String mWeburl = "http://mobiwaternet.co.ke/m";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initially checking if theres a network connection
        ConnectivityManager cm=(ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();

        Bundle extras = this.getIntent().getExtras();
        if(extras!=null) {
            mWeburl = getIntent().getStringExtra("url");
        }

        //End of checking if there's a network connection
        if(ni!=null && ni.isConnected())
        {

            WebView webview=(WebView)findViewById(R.id.webView);
            //Enabling JavaScript
            WebSettings webSettings= webview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            //End of enabling JavaScript
            //Enabling loading of new urls on the same page i.e without redirecting to default broswer
            webview.setWebViewClient(new WebViewClient() {


                                         //Checking if server response if code isn't 200 or 201 it redirects to the error page
                                         @Override
                                         public void onReceivedError(WebView view, int errorCode, String desc, String failingUrl) {
                                             if (errorCode != 200 || errorCode != 201) {
                                                 Intent intent = new Intent(getApplicationContext(), newpage.class);
                                                 startActivity(intent);
                                             }
                                         }
                                         @Override
                                         public void onLoadResource (WebView view, String url) {
                                             //show progress bar
                                             if (progressDialog == null) {
                                                 progressDialog = new ProgressDialog(MainActivity.this);
                                                 progressDialog.setMessage("Working...");
                                                 progressDialog.show();
                                                 progressDialog.setCanceledOnTouchOutside(false);
                                                 progressDialog.setCancelable(false);
                                             }
                                         }

                                         @Override
                                         public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                             url= Uri.parse(url).toString();

                                             if( url!="http://mobiwaternet.co.ke/m")
                                             {
                                                 Intent intent = new Intent(getApplicationContext(), More.class);
                                                 intent.putExtra(More.URL, url);
                                                 startActivity(intent);
                                             }

                                             return true;
                                         }

                                         @Override
                                         public void onPageFinished(WebView view, String url) {
                                             if (progressDialog.isShowing()) {
                                                 progressDialog.dismiss();
                                             }
                                         }


                                     }
            );
            //Loading index
            webview.loadUrl(mWeburl);


        }
        else
        {
            Intent intent= new Intent(getApplicationContext(),newpage.class);
            startActivity(intent);
        }


    }

}

