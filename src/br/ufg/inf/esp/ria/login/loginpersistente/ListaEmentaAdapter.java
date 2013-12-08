package br.ufg.inf.esp.ria.login.loginpersistente;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import br.ufg.inf.esp.ria.modelo.EEmenta;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;

@EBean
public class ListaEmentaAdapter extends BaseAdapter {
  
  List<EEmenta> ementas;
  
  @RootContext
  Context contexto;
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ItemEmentaView itemEmentaView;

    if (convertView == null) {
      itemEmentaView = ItemEmentaView_.build(contexto);
    } else {
      itemEmentaView = (ItemEmentaView) convertView;
    }

    itemEmentaView.bind((EEmenta) getItem(position));

    return itemEmentaView;
  
  }
  
  @Override
  public int getCount() {
    return ementas != null ? ementas.size() : 0;
  }

  @Override
  public Object getItem(int arg0) {
    return ementas != null ? ementas.get(arg0) : null;
  }

  @Override
  public long getItemId(int arg0) {
    return ementas != null ? ementas.get(arg0).getId() : null;
  }
}
