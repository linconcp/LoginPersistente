/**
 * @author Douglas Cavalheiro (doug.cav@ig.com.br)
 */

package org.droidpersistence.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.ForeignKey;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;
import org.droidpersistence.model.FieldModel;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public abstract class TableDefinition<T> {

  private String nomeTabela;
  private String chavePrimaria = "";
  private StringBuilder colunas;
  private String[] ARRAY_COLUMNS;
  private Field[] FIELD_DEFINITION;
  private StringBuilder sqlCriacaoTabela;
  private StringBuilder chaveEstrangeira;
  private final Class<T> classe;
  private List<FieldModel> listaCampoModelo;

  /** Creates a new instance setting a model class to build a definition table */
  public TableDefinition(Class<T> modelo) {
    classe = modelo;
    try {
      createTableDefinition();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** Build a DDL instruction for create table with fields */
  private void createTableDefinition() throws Exception {

    if (!classe.isAnnotationPresent(Table.class)) {
      sqlCriacaoTabela = null;
      throw new Exception("Annotation @Table not declared in class " + classe.getSimpleName());
    }

    Annotation annotation = classe.getAnnotation(Table.class);
    Method method = annotation.getClass().getMethod("name");
    Object object = method.invoke(annotation);

    nomeTabela = object.toString().toUpperCase(Locale.getDefault());

    sqlCriacaoTabela = new StringBuilder();
    chaveEstrangeira = new StringBuilder();
    colunas = new StringBuilder();
    listaCampoModelo = new ArrayList<FieldModel>();

    FIELD_DEFINITION = classe.getDeclaredFields();

    ARRAY_COLUMNS = new String[FIELD_DEFINITION.length];

    for (int i = 0; i < FIELD_DEFINITION.length; i++) {
      Field field = FIELD_DEFINITION[i];
      annotation = null;
      Method methodName = null;
      Object objectName = null;
      String type;
      String primaryKeyText = "";

      if (field.isAnnotationPresent(Column.class)) {
        annotation = field.getAnnotation(Column.class);
        methodName = annotation.getClass().getMethod("name");
        objectName = methodName.invoke(annotation);
        
        if (objectName == null || objectName.toString() == "") {
          objectName = field.getName();
        }
      } else {
        sqlCriacaoTabela = null;
        throw new Exception("Annotation @Column not declared in the field --> " + field.getName());
      }

      if (field.isAnnotationPresent(PrimaryKey.class)) {
        chavePrimaria = objectName.toString();

        Annotation pKey_annotation = field.getAnnotation(PrimaryKey.class);
        Method pkey_methodAutoIncrement = pKey_annotation.getClass().getMethod("autoIncrement");
        Object pkey_autoIncrement = pkey_methodAutoIncrement.invoke(pKey_annotation);

        primaryKeyText = " PRIMARY KEY ";

        if (Boolean.valueOf(pkey_autoIncrement.toString())) {
          primaryKeyText = primaryKeyText + " AUTOINCREMENT ";
        }

      }

      if (field.isAnnotationPresent(ForeignKey.class)) {
        Annotation fkey_annotation = field.getAnnotation(ForeignKey.class);
        Method fkey_methodTableReference = fkey_annotation.getClass().getMethod("tableReference");
        Object fkey_tableReferenceName = fkey_methodTableReference.invoke(fkey_annotation);

        Method fkey_methodOnUpCascade = fkey_annotation.getClass().getMethod("onUpdateCascade");
        Object fkey_OnUpCascadeValue = fkey_methodOnUpCascade.invoke(fkey_annotation);

        Method fkey_methodOnDelCascade = fkey_annotation.getClass().getMethod("onDeleteCascade");
        Object fkey_OnDelCascadeValue = fkey_methodOnDelCascade.invoke(fkey_annotation);

        Method fkey_methodColumnReference = fkey_annotation.getClass().getMethod("columnReference");
        Object fkey_columnReference = fkey_methodColumnReference.invoke(fkey_annotation);

        String columnReference = fkey_columnReference.toString();
        if (columnReference == "") {
          columnReference = "_id";
        }

        chaveEstrangeira.append(", FOREIGN KEY (" + objectName.toString() + ") REFERENCES "
            + fkey_tableReferenceName.toString().toUpperCase(Locale.getDefault()) + " (" + columnReference + ")");

        if (Boolean.valueOf(fkey_OnUpCascadeValue.toString())) {
          chaveEstrangeira.append(" ON UPDATE CASCADE ");
        }
        if (Boolean.valueOf(fkey_OnDelCascadeValue.toString())) {
          chaveEstrangeira.append(" ON DELETE CASCADE ");
        }

      }

      if (field.getType() == int.class || field.getType() == Integer.class || field.getType() == Long.class
          || field.getType() == long.class) {
        type = " INTEGER ";
      } else if (field.getType() == String.class || field.getType() == char.class) {
        type = " TEXT ";
      } else if (field.getType() == Double.class || field.getType() == Float.class || field.getType() == double.class) {
        type = " REAL ";
      } else if (field.getType() == BigDecimal.class) {
        type = " NUMERIC ";
      } else if (field.getType() == Date.class) {
        type = " TIMESTAMP ";
      } else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
        type = " BOOLEAN ";
      } else {
        type = " NONE ";
      }

      if (i == FIELD_DEFINITION.length - 1) {
        if (objectName != null) {
          sqlCriacaoTabela.append(objectName.toString() + " " + type + primaryKeyText);
          colunas.append(objectName.toString());
        } else {
          sqlCriacaoTabela = null;
          throw new Exception("Property 'name' not declared in the field --> " + field.getName());
        }
      } else {
        if (objectName != null) {
          sqlCriacaoTabela.append(objectName.toString() + " " + type + primaryKeyText + ", ");
          colunas.append(objectName.toString() + " , ");
        } else {
          sqlCriacaoTabela = null;
          throw new Exception("Property 'name' not declared in the field --> " + field.getName());
        }
      }
      ARRAY_COLUMNS[i] = objectName.toString();
      if (!primaryKeyText.contains("AUTOINCREMENT")) {
        FieldModel fieldModel = new FieldModel();
        fieldModel.setColumnName(objectName.toString());
        fieldModel.setFieldName(field.getName());
        listaCampoModelo.add(fieldModel);
      }
    }

    if (chaveEstrangeira.toString() != "") {
      sqlCriacaoTabela.append(chaveEstrangeira);
    }
    sqlCriacaoTabela.append(");");

    if (getPK() == "") {
      StringBuilder sb = new StringBuilder();
      sb.append("CREATE TABLE " + nomeTabela + " (");
      sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
      sb.append(sqlCriacaoTabela);

      String[] columns = new String[ARRAY_COLUMNS.length + 1];
      columns[0] = BaseColumns._ID;
      for (int i = 0; i < ARRAY_COLUMNS.length; i++) {
        columns[i + 1] = ARRAY_COLUMNS[i];
      }

      ARRAY_COLUMNS = columns;

      sqlCriacaoTabela = sb;
    } else {
      StringBuilder sb = new StringBuilder();
      sb.append("CREATE TABLE " + nomeTabela + " (");
      sb.append(sqlCriacaoTabela);

      sqlCriacaoTabela = sb;
    }
  }

  /** Uses the create DLL to create table */
  public void onCreate(SQLiteDatabase db) throws Exception {
    if (sqlCriacaoTabela != null) {
      db.execSQL(sqlCriacaoTabela.toString());
    } else {
      throw new Exception("Table not created, the Create DDL not found");
    }
  }

  /** Upgrade (if necessary) the table */
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    try {
      db.execSQL("DROP TABLE IF EXISTS " + this.nomeTabela);
      onCreate(db);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getTableName() {
    return nomeTabela;
  }

  public StringBuilder getColumns() {
    return colunas;
  }

  public void setColumns(StringBuilder columns) {
    this.colunas = columns;
  }

  public String[] getArrayColumns() {
    return ARRAY_COLUMNS;
  }

  public Field[] getFieldDefinition() {
    return FIELD_DEFINITION;
  }

  public String getPK() {
    return chavePrimaria;
  }

  public void setPK(String pk) {
    this.chavePrimaria = pk;
  }

  public List<FieldModel> getLIST_FIELD_MODEL() {
    return listaCampoModelo;
  }

  public void setLIST_FIELD_MODEL(List<FieldModel> lIST_FIELD_MODEL) {
    listaCampoModelo = lIST_FIELD_MODEL;
  }

}
