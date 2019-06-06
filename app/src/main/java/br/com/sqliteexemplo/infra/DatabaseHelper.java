package br.com.sqliteexemplo.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "aula_persistencia.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE aluno (id INTEGER PRIMARY KEY autoincrement NOT NULL, nome varchar(60), email varchar(60))");
        db.execSQL("CREATE TABLE cliente (id INTEGER PRIMARY KEY autoincrement NOT NULL, nome varchar(60), email varchar(60))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE aluno");
      db.execSQL("DROP TABLE cliente");
      onCreate(db);
    }

    public SQLiteDatabase leitura() {
        return getReadableDatabase();
    }

    public SQLiteDatabase gravacao() {
        return getWritableDatabase();
    }
}
