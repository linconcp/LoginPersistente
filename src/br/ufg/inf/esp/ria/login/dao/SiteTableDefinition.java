package br.ufg.inf.esp.ria.login.dao;

import org.droidpersistence.dao.TableDefinition;

import br.ufg.inf.esp.ria.modelo.ESite;

public class SiteTableDefinition extends TableDefinition<ESite> {

  public SiteTableDefinition() {
    super(ESite.class);
  }
}
