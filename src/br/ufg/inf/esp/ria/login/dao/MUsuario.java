package br.ufg.inf.esp.ria.login.dao;

import org.droidpersistence.dao.TableDefinition;

import br.ufg.inf.esp.ria.modelo.EUsuario;

public class MUsuario extends TableDefinition<EUsuario> {

  public MUsuario() {
    super(EUsuario.class);
  }
}
