package br.ufg.inf.esp.ria.login.loginpersistente;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.ufg.inf.esp.ria.modelo.ESite;

import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.lista_site)
public class ItemSiteView extends LinearLayout {

  @ViewById
  TextView nome;

  @ViewById
  TextView descricao;

  public ItemSiteView(Context context) {
    super(context);
  }

  public void bind(ESite site) {
    nome.setText(site.getNome());
    descricao.setText(site.getDescricao());
  }
}
