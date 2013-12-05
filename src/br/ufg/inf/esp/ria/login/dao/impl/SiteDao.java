package br.ufg.inf.esp.ria.login.dao.impl;

import org.droidpersistence.dao.DroidDao;

import android.database.sqlite.SQLiteDatabase;
import br.ufg.inf.esp.ria.login.dao.tableDefinition.SiteTableDefinition;
import br.ufg.inf.esp.ria.modelo.ESite;

public class SiteDao extends DroidDao<ESite, Long> {

  public SiteDao(SQLiteDatabase database) {
    super(ESite.class, new SiteTableDefinition(), database);
  }
}
