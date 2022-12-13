package com.example.a2do.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    //constructor
    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryUs = "create table usu_tb (ID integer primary key autoincrement," +
                "Nombre text, Contraseña txt, Contraseña2 txt);";
        sqLiteDatabase.execSQL(queryUs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //abrir base de datos
    public void abrir () {
        this.getWritableDatabase();
    }

    //cerrar base de datos
    public void cerrar() {
        this.close();
    }

    //insertar registros en la tabla de usuarios
    public void insertUsu (String nom, String cont, String cont2) {
        ContentValues valores =  new ContentValues();
        valores.put("Nombre", nom);
        valores.put("Contraseña", cont);
        valores.put("Contraseña2", cont2);
        this.getWritableDatabase().insert("usu_tb", null, valores);

    }

}

