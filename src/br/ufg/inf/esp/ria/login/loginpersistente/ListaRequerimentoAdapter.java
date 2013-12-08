package br.ufg.inf.esp.ria.login.loginpersistente;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import br.ufg.inf.esp.ria.modelo.ERequerimento;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;

@EBean
public class ListaRequerimentoAdapter extends BaseAdapter {

  List<ERequerimento> listaRequerimentos;

  @RootContext
  Context contexto;

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    ItemRequerimentoView itemRequerimentoView;

    if (convertView == null) {
      itemRequerimentoView = ItemRequerimentoView_.build(contexto);
    } else {
      itemRequerimentoView = (ItemRequerimentoView) convertView;
    }

    itemRequerimentoView.bind(getItem(position));

    return itemRequerimentoView;
  }

  @AfterInject
  void initAdapter() {
    // NRequerimento nRequerimento = new NRequerimento(contexto);
    //
    // listaRequerimentos = nRequerimento.listarTodos();
    listaRequerimentos = new ArrayList<ERequerimento>();
  }

  @Override
  public int getCount() {
    return listaRequerimentos.size();
  }

  @Override
  public ERequerimento getItem(int position) {
    return listaRequerimentos.get(position);
  }

  @Override
  public long getItemId(int position) {
    return listaRequerimentos.get(position).getId();
  }
}
