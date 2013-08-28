package br.ufg.inf.esp.ria.modelo;

import org.droidpersistence.dao.DroidDao;

import br.ufg.inf.esp.ria.login.dao.UsuarioTableDefinition;

import android.database.sqlite.SQLiteDatabase;

public class UsuarioDao extends DroidDao<EUsuario, Long> {

  public UsuarioDao(SQLiteDatabase database) {
    super(EUsuario.class, new UsuarioTableDefinition(), database);
  }

}
