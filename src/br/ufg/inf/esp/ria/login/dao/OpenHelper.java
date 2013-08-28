package br.ufg.inf.esp.ria.login.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

  public OpenHelper(Context context, String name, CursorFactory factory, int version) {
    super(context, name, factory, version);
  }

  @Override
  public void onOpen(SQLiteDatabase db) {
    super.onOpen(db);
    if (!db.isReadOnly()) {
      db.execSQL("PRAGMA foreign_keys=ON;");
    }
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    try {
      UsuarioTableDefinition usuarioTableDefinition = new UsuarioTableDefinition();
      usuarioTableDefinition.onCreate(db);

      SiteTableDefinition siteTableDefinition = new SiteTableDefinition();
      siteTableDefinition.onCreate(db);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    try {
      UsuarioTableDefinition usuarioTableDefinition = new UsuarioTableDefinition();
      usuarioTableDefinition.onUpgrade(db, oldVersion, newVersion);

      SiteTableDefinition siteTableDefinition = new SiteTableDefinition();
      siteTableDefinition.onUpgrade(db, oldVersion, newVersion);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
