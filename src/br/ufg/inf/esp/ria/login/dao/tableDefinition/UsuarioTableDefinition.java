package br.ufg.inf.esp.ria.login.dao.tableDefinition;

import org.droidpersistence.dao.TableDefinition;

import br.ufg.inf.esp.ria.modelo.EUsuario;

public class UsuarioTableDefinition extends TableDefinition<EUsuario> {

  public UsuarioTableDefinition() {
    super(EUsuario.class);
  }
}
