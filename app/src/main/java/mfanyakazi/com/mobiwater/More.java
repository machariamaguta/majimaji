package mfanyakazi.com.mobiwater;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mfanyakazi.com.mobiwater.utils.PrefUtils;


public class More extends AppCompatActivity {
    public Bundle extras;
    public String url;
    public static final String URL="";
    public WebView WEBVIEW;
    public ProgressDialog progressDialog;
    private PrefUtils prefUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        extras=getIntent().getExtras();
        prefUtils = new PrefUtils(this);
        if(extras!=null)
        {
            url=extras.getString(URL);
            WebView WEBVIEW=(WebView)findViewById(R.id.WEBVIEW);
            WEBVIEW.getSettings().setJavaScriptEnabled(true);
            WEBVIEW.loadUrl(url);
            WEBVIEW.setWebViewClient(new WebViewClient()
            {
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                    if(errorCode!=200 || errorCode!=201)
                    {
                        Intent intent=new Intent(getApplicationContext(),newpage.class);
                        startActivity(intent);
                    }
                    super.onReceivedError(view, errorCode, description, failingUrl);
                }

                @Override
                public void onLoadResource (WebView view, String url) {
                    //show progress bar
                    if (progressDialog == null) {
                        progressDialog = new ProgressDialog(More.this);
                        progressDialog.setMessage("Working...");
                        progressDialog.show();
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.setCancelable(false);
                    }
                }

                @Override
                public void onPageFinished(WebView view,String url)
                {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    if(!prefUtils.getHasSetPhoneNumber()){
                       showPhoneNumberDialogoe();
                    }

                }
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    url= Uri.parse(url).toString();

                    Intent intent=new Intent(getApplicationContext(),More.class);
                    intent.putExtra(More.URL, url);
                    startActivity(intent);
                    return true;
                }

            });

        }


    }


    public PrefUtils getPrefUtils() {
        return prefUtils;
    }

    public void showPhoneNumberDialogoe(){
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev  = getSupportFragmentManager().findFragmentByTag("dialog");

        if(prev!=null) {ft.remove(prev);}

        DialogFragment newFragment = PhoneNumberDialogue.getInstance();

        newFragment.show(ft, "dialog");



    }


}

