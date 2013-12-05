package br.ufg.inf.esp.ria.login.dao.impl;

import org.droidpersistence.dao.DroidDao;

import br.ufg.inf.esp.ria.login.dao.tableDefinition.UsuarioTableDefinition;
import br.ufg.inf.esp.ria.modelo.EUsuario;

import android.database.sqlite.SQLiteDatabase;

public class UsuarioDao extends DroidDao<EUsuario, Long> {

  public UsuarioDao(SQLiteDatabase database) {
    super(EUsuario.class, new UsuarioTableDefinition(), database);
  }

}
