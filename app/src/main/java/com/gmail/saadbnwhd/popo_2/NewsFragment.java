package com.gmail.saadbnwhd.popo_2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



/**
 * Created by Musab on 12/6/2016.
 */
public class NewsFragment extends Fragment {
    public WebView mWebView;
    private static final String TAG = "Main";
    private ProgressDialog progressBar;
    public NewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view= inflater.inflate(R.layout.fragment_news, container, false);
        mWebView = (WebView) view.findViewById(R.id.webview);
        mWebView.loadUrl("https://www.facebook.com/popofootballclub");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());


        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient()
        );

        return view;
    }


}



