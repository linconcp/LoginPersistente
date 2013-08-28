package br.ufg.inf.esp.ria.login.fragments;

import java.util.List;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.ufg.inf.esp.ria.modelo.ESite;

public class ListaAdapter extends ArrayAdapter<ESite> {

  private Context context;
  private int layoutResourceId;
  private List<ESite> listaSites = null;

  TextView nome;

  TextView descricao;

  public ListaAdapter(Context context, int layout, List<ESite> ESites) {
    super(context, layout, ESites);
    this.context = context;
    this.layoutResourceId = layout;
    this.listaSites = ESites;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    View linha = convertView;

    // CARREGA O LAYOUT
    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
    linha = inflater.inflate(layoutResourceId, parent, false);

    // PERSONALIZA O LAYOUT
    nome = (TextView) linha.findViewById(R.id.titulo);
    nome.setText(listaSites.get(position).getNome());

    descricao = (TextView) linha.findViewById(R.id.descricao);
    descricao.setText(listaSites.get(position).getDescricao());

    // RETORNO A LINHA PRONTA
    return linha;
  }

  public int getCount() {
    if (listaSites != null) {
      return listaSites.size();
    } else {
      Log.d("RESULT", "Não possui registros!");
      return 0;
    }
  }
}
