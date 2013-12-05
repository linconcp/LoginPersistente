package br.ufg.inf.esp.ria.negocio;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.ufg.inf.esp.ria.login.dao.DataManager;
import br.ufg.inf.esp.ria.login.dao.impl.UsuarioDao;
import br.ufg.inf.esp.ria.modelo.EUsuario;

public class NUsuario {

  private SQLiteDatabase banco;

  private Context contexto;

  public NUsuario(Context contexto) {
    this.contexto = contexto;
  }

  public void incluir(EUsuario eUsuario) {
    try {
      this.banco = DataManager.getDatabase(contexto);
      UsuarioDao usuarioDao = new UsuarioDao(banco);
      usuarioDao.save(eUsuario);
      this.banco.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public EUsuario consultar(EUsuario eUsuario) {
    EUsuario eUsuarioAux = null;

    try {
      this.banco = DataManager.getDatabase(contexto);
      UsuarioDao usuarioDao = new UsuarioDao(banco);
      eUsuarioAux = usuarioDao.getByClause("identificacao = ?", new String[] { eUsuario.getIdentificacao() });
      this.banco.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return eUsuarioAux;
  }

  public boolean alterar(EUsuario eUsuario) {
    boolean retorno = false;

    try {
      this.banco = DataManager.getDatabase(contexto);
      UsuarioDao usuarioDao = new UsuarioDao(banco);
      usuarioDao.update(eUsuario, eUsuario.getId());
      this.banco.close();
      retorno = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retorno;
  }

  public boolean excluir(EUsuario eUsuario) {
    boolean retorno = false;

    try {
      this.banco = DataManager.getDatabase(contexto);
      UsuarioDao usuarioDao = new UsuarioDao(banco);
      usuarioDao.delete(eUsuario.getId());
      this.banco.close();
      retorno = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retorno;
  }

  public List<EUsuario> listar(EUsuario eUsuario) {
    List<EUsuario> listaAux = new ArrayList<EUsuario>();

    try {
      this.banco = DataManager.getDatabase(contexto);
      UsuarioDao usuarioDao = new UsuarioDao(banco);
      listaAux = usuarioDao.getAll();
      this.banco.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return listaAux;
  }

  public boolean validar(EUsuario eUsuario) {
    boolean retorno = false;
    EUsuario eUsuarioAux = new EUsuario();
    eUsuarioAux.setIdentificacao(eUsuario.getIdentificacao());

    try {
      this.banco = DataManager.getDatabase(contexto);
      UsuarioDao usuarioDao = new UsuarioDao(banco);
      eUsuarioAux = usuarioDao.getByClause("identificacao = ?", new String[] { eUsuario.getIdentificacao() });
      this.banco.close();
      if (eUsuarioAux != null) {
        if (eUsuario.getSenha().equals(eUsuarioAux.getSenha())) {
          retorno = true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retorno;
  }

}
