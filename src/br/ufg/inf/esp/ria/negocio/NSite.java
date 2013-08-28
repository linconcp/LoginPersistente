package br.ufg.inf.esp.ria.negocio;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import br.ufg.inf.esp.ria.modelo.ESite;
import br.ufg.inf.esp.ria.modelo.SiteDao;

public class NSite {

  private SQLiteDatabase banco;

  public NSite(SQLiteDatabase banco) {
    this.banco = banco;
  }

  public void incluir(ESite eSite) {
    try {
      SiteDao SiteDao = new SiteDao(banco);

      SiteDao.save(eSite);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ESite consultar(ESite eSite) {
    SiteDao SiteDao = new SiteDao(banco);
    ESite eSiteAux = null;

    try {
      eSiteAux = SiteDao.get(eSite.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return eSiteAux;
  }

  public boolean alterar(ESite eSite) {
    boolean retorno = false;
    SiteDao SiteDao = new SiteDao(banco);

    try {
      SiteDao.update(eSite, eSite.getId());
      retorno = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retorno;
  }

  public boolean excluir(ESite eSite) {
    boolean retorno = false;
    SiteDao SiteDao = new SiteDao(banco);

    try {
      SiteDao.delete(eSite.getId());
      retorno = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retorno;
  }

  public List<ESite> listarTodos() {
    List<ESite> listaAux = new ArrayList<ESite>();

    SiteDao SiteDao = new SiteDao(banco);
    listaAux = SiteDao.getAll();

    return listaAux;
  }

}
