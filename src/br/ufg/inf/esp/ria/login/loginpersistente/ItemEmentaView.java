package br.ufg.inf.esp.ria.login.loginpersistente;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.ufg.inf.esp.ria.modelo.EEmenta;

import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.lista_ementa)
public class ItemEmentaView extends LinearLayout {
  
  @ViewById
  TextView disciplina;

  @ViewById
  TextView ementa;
  
  public ItemEmentaView(Context context) {
    super(context);
  }
  
  public void bind(EEmenta ementa) {
    disciplina.setText(ementa.getDisciplina());
    this.ementa.setText(ementa.getEmenta());
  }
}
