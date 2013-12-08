package br.ufg.inf.esp.ria.login.loginpersistente;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.LinearLayout;
import br.ufg.inf.esp.ria.modelo.ERequerimento;

import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.lista_requerimento)
public class ItemRequerimentoView extends LinearLayout {

  @ViewById
  EditText tipoRequerimento;

  @ViewById
  EditText dataCadastro;

  @ViewById
  EditText situacao;

  @ViewById
  EditText dataSituacao;

  public ItemRequerimentoView(Context context) {
    super(context);
  }

  public void bind(ERequerimento eRequerimento) {
    tipoRequerimento.setText(eRequerimento.getTipoRequerimento());
    dataCadastro.setText(DateFormat.format("dd/MM/yyyy", eRequerimento.getDataCadastro()));
    situacao.setText(eRequerimento.getSituacao());
    dataSituacao.setText(DateFormat.format("dd/MM/yyyy", eRequerimento.getDataSituacao()));
  }
}