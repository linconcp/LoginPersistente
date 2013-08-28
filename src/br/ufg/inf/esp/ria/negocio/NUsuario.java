package br.ufg.inf.esp.ria.negocio;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import br.ufg.inf.esp.ria.modelo.EUsuario;
import br.ufg.inf.esp.ria.modelo.UsuarioDao;

public class NUsuario {

  private SQLiteDatabase banco;

  public NUsuario(SQLiteDatabase banco) {
    this.banco = banco;
  }

  public void incluir(EUsuario eUsuario) {
    try {
      UsuarioDao usuarioDao = new UsuarioDao(banco);

      usuarioDao.save(eUsuario);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public EUsuario consultar(EUsuario eUsuario) {
    UsuarioDao usuarioDao = new UsuarioDao(banco);
    EUsuario eUsuarioAux = null;

    try {
      eUsuarioAux = usuarioDao.getByClause("identificacao = ?", new String[] { eUsuario.getIdentificacao() });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return eUsuarioAux;
  }

  public boolean alterar(EUsuario eUsuario) {
    boolean retorno = false;
    UsuarioDao usuarioDao = new UsuarioDao(banco);

    try {
      usuarioDao.update(eUsuario, eUsuario.getId());
      retorno = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retorno;
  }

  public boolean excluir(EUsuario eUsuario) {
    boolean retorno = false;
    UsuarioDao usuarioDao = new UsuarioDao(banco);

    try {
      usuarioDao.delete(eUsuario.getId());
      retorno = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retorno;
  }

  public List<EUsuario> listar(EUsuario eUsuario) {
    List<EUsuario> listaAux = new ArrayList<EUsuario>();

    UsuarioDao usuarioDao = new UsuarioDao(banco);
    listaAux = usuarioDao.getAll();

    return listaAux;
  }

  public boolean validar(EUsuario eUsuario) {
    boolean retorno = false;
    EUsuario eUsuarioAux = new EUsuario();
    eUsuarioAux.setIdentificacao(eUsuario.getIdentificacao());

    UsuarioDao usuarioDao = new UsuarioDao(banco);

    eUsuarioAux = usuarioDao.getByClause("identificacao = ?", new String[] { eUsuario.getIdentificacao() });

    if (eUsuarioAux != null) {
      if (eUsuario.getSenha().equals(eUsuarioAux.getSenha())) {
        retorno = true;
      }
    }

    return retorno;
  }

}
