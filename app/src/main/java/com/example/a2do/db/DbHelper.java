package com.example.a2do.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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

       //Creación tabla de usuarios
        String queryUs = "create table usu_tb (ID integer primary key autoincrement," +
                "Nombre text, Contraseña text, Contraseña2 text);";
        sqLiteDatabase.execSQL(queryUs);

        //Creación tabla de categorías
        String queryCa = "create table cat_tb (ID integer primary key autoincrement," +
                "Nombre_Cat text);";
        sqLiteDatabase.execSQL(queryCa);

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

    //USUARIOS

    //insertar registros en la tabla de usuarios
    public void insertUsu (String nom, String cont, String cont2) {
        ContentValues valores =  new ContentValues();
        valores.put("Nombre", nom);
        valores.put("Contraseña", cont);
        valores.put("Contraseña2", cont2);
        this.getWritableDatabase().insert("usu_tb", null, valores);
    }

    //valida que el usuario/a existe
    public Cursor ConsultarUsuCon (String usu, String con) throws SQLException {
        Cursor ucursor = null;
        ucursor = this.getReadableDatabase().query("usu_tb", new String []{"ID", "Nombre", "Contraseña", "Contraseña2"},
                "Nombre like '" + usu + "'"+ "and Contraseña like '" + con +"'", null,null,null,null);
        return ucursor;
    }

    //CATEGORÍAS

    //insertar registros en la tabla de categorías
    public void insertCat (String nom_cat) {
        ContentValues valores =  new ContentValues();
        valores.put("Nombre_Cat", nom_cat);
        this.getWritableDatabase().insert("cat_tb", null, valores);
    }

    //valida que el usuario/a existe
    public Cursor ConsultarCat (String cat) throws SQLException {
        Cursor catcursor = null;
        catcursor = this.getReadableDatabase().query("cat_tb", new String []{"ID", "Nombre_Cat"},
                "Nombre_Cat like '" + cat , null,null,null,null);
        return catcursor;
    }

    //ITEMS

}

