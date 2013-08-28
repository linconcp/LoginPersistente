package br.ufg.inf.esp.ria.login.loginpersistente;

import java.util.List;

import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;
import br.ufg.inf.esp.ria.login.dao.DataManager;
import br.ufg.inf.esp.ria.login.fragments.ListaAdapter;
import br.ufg.inf.esp.ria.modelo.ESite;
import br.ufg.inf.esp.ria.negocio.NSite;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;

@Fullscreen
@NoTitle
@EActivity(R.layout.bemvindo)
public class Bemvindo extends Activity {

  @ViewById(R.id.tvBemvindo)
  TextView tvBemvindo;

  @ViewById(R.id.listaFavoritos)
  ListView listaFavoritos;

  @Extra("usuario")
  String usuario;

  NSite nSite = new NSite(DataManager.getDatabase(this));

  ListaAdapter adapter;

  @AfterViews
  protected void iniciar() {
    tvBemvindo.setText("Seja bem-vindo " + usuario + ",");
    List<ESite> listaSite = null;

    listaSite = nSite.listarTodos();
    
    adapter = new ListaAdapter(this, R.layout.linha_site, listaSite);
  }
}
