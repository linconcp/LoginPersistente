package br.ufg.inf.esp.ria.login.loginpersistente;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.UiThread;

import br.ufg.inf.esp.ria.modelo.ESite;

import android.app.ProgressDialog;
import android.util.Log;

@EBean
public class DownloadSites {
	
	private ProgressDialog dialog;
	private final String URL = "https://dl.dropboxusercontent.com/u/1984820/sites.json";

	
	@UiThread
	public void execute(Bemvindo minhaLista) {
		dialog = ProgressDialog.show(minhaLista, "Aguarde", "Baixando JSON, Por Favor Aguarde...");
		JSONParser parser = new JSONParser();
		JSONObject json = parser.getJSONFromUrl(URL);
		List<ESite> sites = parse(json);
		dialog.dismiss();
		minhaLista.atualizaItens((ArrayList<ESite>) sites);
	}
	
	private List<ESite> parse(JSONObject json) {
		List<ESite> array = new ArrayList<ESite>();
		try {
			JSONArray sitesList = json.getJSONArray("sites");
			for (int i = 0; i < sitesList.length(); i++) {
				JSONObject siteEmJSON = new JSONObject(sitesList.getString(i));
				ESite site = new ESite();
				site.setNome(siteEmJSON.getString("nome"));
				site.setUrl(siteEmJSON.getString("url"));
				site.setDescricao(siteEmJSON.getString("descricao"));
				array.add(site);
			}
		} catch (JSONException e) {
			Log.d("ERRO", e.getMessage());
		}

		return array;
	}
}
