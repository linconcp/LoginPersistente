package br.ufg.inf.esp.ria.login.loginpersistente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.util.Log;
import br.ufg.inf.esp.ria.modelo.ERequerimento;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.UiThread;

@EBean
public class DownloadRequerimento {

  private ProgressDialog dialog;
  private final String URL = "https://dl.dropbox.com/s/kueyz2v7iga74m3/requerimentos.json";

  @UiThread
  public void execute(Bemvindo minhaTela) {
    dialog = ProgressDialog.show(minhaTela, "Aguarde", "Baixando JSON, Por Favor Aguarde...");
    JSONParser parser = new JSONParser();
    JSONObject json = parser.getJSONFromUrl(URL);
    
    if (json != null) {
      List<ERequerimento> requerimentos = parse(json);
      minhaTela.atualizaRequerimentos((ArrayList<ERequerimento>) requerimentos);
    }
    
    dialog.dismiss();

  }

  private List<ERequerimento> parse(JSONObject json) {
    List<ERequerimento> array = new ArrayList<ERequerimento>();
    try {
      JSONArray requerimentos = json.getJSONArray("requerimentos");
      for (int i = 0; i < requerimentos.length(); i++) {
        JSONObject requerimentoEmJSON = new JSONObject(requerimentos.getString(i));
        ERequerimento eRequerimento = new ERequerimento();
        eRequerimento.setId(requerimentoEmJSON.getLong("id"));
        eRequerimento.setDataCadastro(new Date(requerimentoEmJSON.getString("dataCadastro")));
        eRequerimento.setTipoRequerimento(requerimentoEmJSON.getString("tipoRequerimento"));
        eRequerimento.setSituacao(requerimentoEmJSON.getString("situacao"));
        eRequerimento.setDataSituacao(new Date(requerimentoEmJSON.getString("dataSituacao")));

        array.add(eRequerimento);
      }
    } catch (JSONException e) {
      Log.d("ERRO", e.getMessage());
    }

    return array;
  }
}
