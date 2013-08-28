package br.ufg.inf.esp.ria.login.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DataManager {
  private static final String BANCO_DADOS = "teste.db";
  private static final Integer BANCO_VERSAO = 1;

  public static final SQLiteDatabase getDatabase(Context contexto) {
    SQLiteOpenHelper openHelper = new OpenHelper(contexto, BANCO_DADOS, null, BANCO_VERSAO);
    return openHelper.getWritableDatabase();
  }

}
