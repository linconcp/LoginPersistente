	package br.ufg.inf.esp.ria.login.loginpersistente;

	import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.util.Log;
import br.ufg.inf.esp.ria.modelo.EEmenta;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.UiThread;

	@EBean
	public class DownloadEmenta {

	  private ProgressDialog dialog;
	  private final String URL = "https://dl.dropbox.com/s/413niwowvlsvrrx/disciplinas.json";

	  @UiThread
	  public void execute(Bemvindo minhaLista) {
	    dialog = ProgressDialog.show(minhaLista, "Aguarde", "Baixando JSON, Por Favor Aguarde...");
	    JSONParser parser = new JSONParser();
	    JSONObject json = parser.getJSONFromUrl(URL);
	    
	    if (json != null) {
	      List<EEmenta> ementa = parse(json);
	      minhaLista.atualizaEmentas((ArrayList<EEmenta>) ementa);
	    }
	    
	    dialog.dismiss();

	  }

	  private List<EEmenta> parse(JSONObject json) {
	    List<EEmenta> array = new ArrayList<EEmenta>();
	    try {
	      JSONArray ementas = json.getJSONArray("disciplinas");
	      for (int i = 0; i < ementas.length(); i++) {
	        JSONObject ementaJson = new JSONObject(ementas.getString(i));
	        EEmenta eRequerimento = new EEmenta();
	        eRequerimento.setId(ementaJson.getLong("id"));
	        eRequerimento.setDisciplina(ementaJson.getString("nome"));
	        eRequerimento.setEmenta(ementaJson.getString("ementa"));

	        array.add(eRequerimento);
	      }
	    } catch (JSONException e) {
	      Log.d("ERRO", e.getMessage());
	    }

	    return array;
	  }
	}
