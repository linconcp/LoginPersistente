package br.ufg.inf.esp.ria.negocio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.ufg.inf.esp.ria.login.dao.DataManager;
import br.ufg.inf.esp.ria.login.dao.impl.SiteDao;
import br.ufg.inf.esp.ria.modelo.ESite;

public class NSite {

  private SQLiteDatabase banco;
  private Context contexto;

  public NSite(Context contexto) {
    this.contexto = contexto;
  }

  public void incluir(ESite eSite) {
    try {
      this.banco = DataManager.getDatabase(contexto);
      SiteDao SiteDao = new SiteDao(banco);

      SiteDao.save(eSite);
      banco.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ESite consultar(ESite eSite) {
    ESite eSiteAux = null;

    try {
      this.banco = DataManager.getDatabase(contexto);
      SiteDao SiteDao = new SiteDao(banco);

      eSiteAux = SiteDao.get(eSite.getId());
      banco.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return eSiteAux;
  }

  public boolean alterar(ESite eSite) {
    boolean retorno = false;

    try {
      this.banco = DataManager.getDatabase(contexto);
      SiteDao SiteDao = new SiteDao(banco);
      SiteDao.update(eSite, eSite.getId());
      retorno = true;
      banco.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retorno;
  }

  public boolean excluir(ESite eSite) {
    boolean retorno = false;

    try {
      this.banco = DataManager.getDatabase(contexto);
      SiteDao SiteDao = new SiteDao(banco);
      SiteDao.delete(eSite.getId());
      retorno = true;
      banco.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retorno;
  }

  public List<ESite> listarTodos() {
    List<ESite> listaAux = new ArrayList<ESite>();

    try {
      this.banco = DataManager.getDatabase(contexto);
      SiteDao SiteDao = new SiteDao(banco);
      listaAux = SiteDao.getAll();
      banco.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return listaAux;
  }

  public ESite consultarUrl(ESite eSite) {
    ESite eSiteAux = null;

    try {
      this.banco = DataManager.getDatabase(contexto);
      SiteDao SiteDao = new SiteDao(banco);
      eSiteAux = SiteDao.getByClause("url = ? ", new String[] { eSite.getUrl() });
      banco.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return eSiteAux;
  }
}