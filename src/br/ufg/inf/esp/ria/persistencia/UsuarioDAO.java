package br.ufg.inf.esp.ria.persistencia;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.ufg.inf.esp.ria.modelo.EUsuario;

public class UsuarioDAO extends SQLiteOpenHelper {

  private static final int VERSAO_TABELA = 1;

  private static final String BANCO_DADOS = "teste";

  private static final String TABELA = "usuario";

  private static final String COLUNA_ID = "id";
  private static final String COLUNA_NOME = "nome";
  private static final String COLUNA_SENHA = "senha";

  public UsuarioDAO(Context contexto) {
    super(contexto, BANCO_DADOS, null, VERSAO_TABELA);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    final String CREATE_USUARIO = "CREATE TABLE " + TABELA + "(" + COLUNA_ID + " INTEGER PRIMARY KEY, " + COLUNA_NOME
        + " TEXT UNIQUE, " + COLUNA_SENHA + " TEXT)";
    db.execSQL(CREATE_USUARIO);
    EUsuario eUsuario = new EUsuario();
    eUsuario.setNome("admin");
    eUsuario.setSenha("admin");
    incluir(eUsuario);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABELA);
    onCreate(db);
  }

  public void incluir(EUsuario eUsuario) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();

    values.put(COLUNA_NOME, eUsuario.getNome());
    values.put(COLUNA_SENHA, eUsuario.getSenha());

    db.insert(TABELA, null, values);
    Log.d("ADICIONANDO", eUsuario.getNome());
    db.close();
  }

  public EUsuario consultar(EUsuario eUsuario) {
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.query(TABELA, null, COLUNA_ID + "= ?", new String[] { String.valueOf(eUsuario.getId()) }, null,
        null, null, null);

    if (cursor != null) {
      cursor.moveToFirst();

      EUsuario eUsuarioAux = new EUsuario();
      eUsuarioAux.setId(cursor.getLong(0));
      eUsuarioAux.setNome(cursor.getString(1));
      eUsuarioAux.setSenha(cursor.getString(2));

      return eUsuario;
    } else {
      return null;
    }

  }

  public ArrayList<EUsuario> listar(EUsuario eUsuario) {
    ArrayList<EUsuario> eUsuarioList = new ArrayList<EUsuario>();
    String selectQuery = "SELECT * FROM " + TABELA;

    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.rawQuery(selectQuery, null);

    if (cursor.moveToFirst()) {
      do {
        EUsuario eUsuarioAux = new EUsuario();
        eUsuarioAux.setId(cursor.getLong(0));
        eUsuarioAux.setNome(cursor.getString(1));
        eUsuarioAux.setSenha(cursor.getString(2));

        eUsuarioList.add(eUsuario);
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();

    return eUsuarioList;
  }

  public int alterar(EUsuario eUsuario) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(COLUNA_NOME, eUsuario.getNome());
    values.put(COLUNA_SENHA, eUsuario.getSenha());

    return db.update(TABELA, values, COLUNA_ID + " = ?", new String[] { String.valueOf(eUsuario.getId()) });
  }

  public void excluir(EUsuario eUsuario) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABELA, COLUNA_ID + " = ?", new String[] { String.valueOf(eUsuario.getId()) });
    db.close();
  }
}