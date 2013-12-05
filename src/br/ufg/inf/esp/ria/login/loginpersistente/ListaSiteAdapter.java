package br.ufg.inf.esp.ria.login.loginpersistente;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import br.ufg.inf.esp.ria.modelo.ESite;
import br.ufg.inf.esp.ria.negocio.NSite;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;

@EBean
public class ListaSiteAdapter extends BaseAdapter {

  List<ESite> listaSites;

  @RootContext
  Context contexto;

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    ItemSiteView itemSiteView;

    if (convertView == null) {
      itemSiteView = ItemSiteView_.build(contexto);
    } else {
      itemSiteView = (ItemSiteView) convertView;
    }

    itemSiteView.bind(getItem(position));

    return itemSiteView;
  }

  @AfterInject
  void initAdapter() {
    NSite nSite = new NSite(contexto);

    listaSites = nSite.listarTodos();
  }

  @Override
  public int getCount() {
    return listaSites.size();
  }

  @Override
  public ESite getItem(int position) {
    return listaSites.get(position);
  }

  @Override
  public long getItemId(int position) {
    return listaSites.get(position).getId();
  }
}
