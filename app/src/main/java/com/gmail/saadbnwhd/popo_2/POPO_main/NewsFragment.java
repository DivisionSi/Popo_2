package com.gmail.saadbnwhd.popo_2.POPO_main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gmail.saadbnwhd.popo_2.R;


/**
 * Created by Musab on 12/6/2016.
 */
public class NewsFragment extends Fragment {
    public WebView mWebView;
    private static final String TAG = "Main";
   // private ProgressDialog progressBar;
    ProgressBar progressBar;
    ProgressDialog pd;
    public NewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:{
                    webViewGoBack();
                }break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view= inflater.inflate(R.layout.fragment_news, container, false);

        if (savedInstanceState != null)
            ((WebView)view.findViewById(R.id.webview)).restoreState(savedInstanceState);

        mWebView = (WebView) view.findViewById(R.id.webview);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.setWebViewClient(new WC());

        mWebView.loadUrl("https://www.facebook.com/popofootballclub");


        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        pd = new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());


        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && mWebView.canGoBack()) {
                    handler.sendEmptyMessage(1);
                    return true;
                }

                return false;
            }

        });


        return view;
    }

    private class WC extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if(!url.equals("https://www.facebook.com/popofootballclub" )){

                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

                return;
            }

        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view,url);

            if(!url.equals("https://www.facebook.com/popofootballclub" )){

                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

                return;
            }



        }
    }

    private void webViewGoBack(){
        mWebView.goBack();
    }

    public void onSaveInstanceState(Bundle outState) {
        mWebView.saveState(outState);
    }


}



