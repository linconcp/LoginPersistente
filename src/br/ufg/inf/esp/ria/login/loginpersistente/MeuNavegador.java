package br.ufg.inf.esp.ria.login.loginpersistente;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.navegador) 
@NoTitle
@Fullscreen
public class MeuNavegador extends Activity {

  @ViewById(R.id.webView)
  WebView telaBrowser;

  @Extra("url")
  String url;

  @AfterViews
  protected void iniciar() {
    telaBrowser.setWebViewClient(new CustomWebViewClient());
    telaBrowser.loadUrl(url);
  }
}

class CustomWebViewClient extends WebViewClient {
  @Override
  public boolean shouldOverrideUrlLoading(WebView view, String url) {
    return false;
  }
}