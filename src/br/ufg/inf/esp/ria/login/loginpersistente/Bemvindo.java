package br.ufg.inf.esp.ria.login.loginpersistente;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import br.ufg.inf.esp.ria.modelo.EEmenta;
import br.ufg.inf.esp.ria.modelo.ERequerimento;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.bemvindo)
@NoTitle
@Fullscreen
public class Bemvindo extends Activity {

  @ViewById(R.id.tvBemvindo)
  TextView tvBemvindo;

  @Extra("usuario")
  String usuario;

  @ViewById(R.id.listaFavoritos)
  ListView listaFavoritos;

  @Bean
  ListaRequerimentoAdapter listaRequerimentoAdapter;

  @Bean
  DownloadRequerimento downloadRequerimento;

  @Bean
  ListaEmentaAdapter listaEmentaAdapter;

  @Bean
  DownloadEmenta downloadEmenta;

  @AfterViews
  protected void iniciar() {
    tvBemvindo.setText("Seja bem-vindo " + usuario + ",");
  }

  @Click(R.id.consultarSolicitacao)
  public void entrar(View v) {
    listaFavoritos.setAdapter(listaRequerimentoAdapter);

    if (isOnline()) {
      downloadRequerimento.execute(this);
    }

  }

  public void atualizaRequerimentos(ArrayList<ERequerimento> meusRequerimentos) {
    this.listaRequerimentoAdapter.listaRequerimentos.clear();
    this.listaRequerimentoAdapter.listaRequerimentos.addAll(meusRequerimentos);
    Log.d("LIDO DO BANCO", this.listaRequerimentoAdapter.listaRequerimentos.size() + " no banco de dados");
    listaFavoritos.invalidateViews();
    listaRequerimentoAdapter.notifyDataSetChanged();
  }

  @Click(R.id.consultarEmenta)
  public void consultarEmenta(View v) {
    listaFavoritos.setAdapter(listaEmentaAdapter);

    if (isOnline()) {
      downloadEmenta.execute(this);
    }

  }

  public void atualizaEmentas(ArrayList<EEmenta> ementas) {
    if (this.listaEmentaAdapter.ementas != null)
      this.listaEmentaAdapter.ementas.clear();
    else
      this.listaEmentaAdapter.ementas = new ArrayList<EEmenta>();
    this.listaEmentaAdapter.ementas.addAll(ementas);
    listaFavoritos.invalidateViews();
    listaEmentaAdapter.notifyDataSetChanged();
  }

  public boolean isOnline() {
    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();

    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
      return true;
    }
    return false;
  }
}