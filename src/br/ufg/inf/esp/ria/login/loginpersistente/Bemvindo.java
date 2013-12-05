package br.ufg.inf.esp.ria.login.loginpersistente;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import br.ufg.inf.esp.ria.modelo.ESite;
import br.ufg.inf.esp.ria.negocio.NSite;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.bemvindo)
@NoTitle
@Fullscreen
public class Bemvindo extends Activity {

  @ViewById(R.id.tvBemvindo)
  TextView tvBemvindo;

  @ViewById(R.id.listaFavoritos)
  ListView listaFavoritos;

  @Bean
  ListaSiteAdapter listaSiteAdapter;

  @Extra("usuario")
  String usuario;

  @Bean
  DownloadSites download;
  
  @AfterViews
  protected void iniciar() {
    tvBemvindo.setText("Seja bem-vindo " + usuario + ",");

    listaFavoritos.setAdapter(listaSiteAdapter);

    if (isOnline()) {
      download.execute(this);
    }
  }

  public void atualizaItens(ArrayList<ESite> meusSites) {
    NSite nSite = new NSite(this);

    for (ESite site : meusSites) {
      ESite eSiteAux = new ESite();
      eSiteAux.setUrl(site.getUrl());
      eSiteAux = nSite.consultarUrl(eSiteAux);

      if (eSiteAux == null) {
        nSite.incluir(site);
      }
    }

    this.listaSiteAdapter.listaSites.clear();
    this.listaSiteAdapter.listaSites.addAll(nSite.listarTodos());
    // this.listaSiteAdapter.listaSites.addAll(meusSites);
    Log.d("LIDO DO BANCO", this.listaSiteAdapter.listaSites.size() + " no banco de dados");
    listaFavoritos.invalidateViews();
    listaSiteAdapter.notifyDataSetChanged();
  }

  public boolean isOnline() {
    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();

    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
      return true;
    }
    return false;
  }
  
  @ItemClick(R.id.listaFavoritos)
  public void listaFavoritosItemCliked(ESite eSite) {
//    Intent i = new Intent(getApplicationContext(), MeuNavegador_.class);
//    i.putExtra("url", eSite.getUrl());
//    startActivity(i);
    MeuNavegador_.intent(this).url(eSite.getUrl()).start();
  }
}
