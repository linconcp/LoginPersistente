package br.ufg.inf.esp.ria.modelo;

import org.droidpersistence.dao.DroidDao;

import android.database.sqlite.SQLiteDatabase;
import br.ufg.inf.esp.ria.login.dao.SiteTableDefinition;

public class SiteDao extends DroidDao<ESite, Long> {

  public SiteDao(SQLiteDatabase database) {
    super(ESite.class, new SiteTableDefinition(), database);
  }
}
