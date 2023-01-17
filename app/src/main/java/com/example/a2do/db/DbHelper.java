package com.example.a2do.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //Constructor
    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Crear tablas
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

       //Creación tabla de usuarios
        String queryUs = "create table usu_tb (ID integer primary key autoincrement," +
                "Nombre text, Contraseña text, Contraseña2 text);";
        sqLiteDatabase.execSQL(queryUs);

        //Creación tabla de categorías
        String queryCa = "create table cat_tb (ID integer primary key autoincrement," +
                "Nombre_Cat text, idUsuario integer );";
        sqLiteDatabase.execSQL(queryCa);

        //Creación de items
        String queryItems = "create table item_tb (ID integer primary key autoincrement, " +
                "Nombre_Item text, idCat integer);";
        sqLiteDatabase.execSQL(queryItems);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    //abrir base de datos
    public void abrir () {
        this.getWritableDatabase();
    }

    //cerrar base de datos
    public void cerrar() {
        this.close();
    }

    //USUARIOS
    //Insertar registros en la tabla de usuarios
    public void insertUsu (String nom, String cont, String cont2) {
        ContentValues valores =  new ContentValues();
        valores.put("Nombre", nom);
        valores.put("Contraseña", cont);
        valores.put("Contraseña2", cont2);
        this.getWritableDatabase().insert("usu_tb", null, valores);
    }

    //Validar que el usuario/a existe
    public Cursor ConsultarUsuCon (String usu, String con) throws SQLException {
        Cursor ucursor = null;
        ucursor = this.getReadableDatabase().query("usu_tb", new String []{"ID", "Nombre", "Contraseña", "Contraseña2"},
                "Nombre like '" + usu + "'"+ "and Contraseña like '" + con +"'", null,null,null,null);
        return ucursor;
    }

    //Eliminar cuenta de usuario
    public boolean borrarUsu (int id){
        this.getWritableDatabase().delete("usu_tb","id="+ id, null);
        return true;
    }

    //CATEGORÍAS
    //Insertar registros en la tabla de categorías
    public void insertCat (String nom_cat, int idUsuario) {
        ContentValues valores =  new ContentValues();
        valores.put("Nombre_Cat", nom_cat);
        valores.put("idUsuario", idUsuario);
        this.getWritableDatabase().insert("cat_tb", null, valores);
    }

    //Consultar categoría
    public Cursor consultarCat (int idUsuario) throws SQLException {
        Cursor catcursor = null;
        catcursor = this.getReadableDatabase().query("cat_tb", new String []{"ID", "Nombre_Cat"},
                "idUsuario like " + idUsuario , null,null,null,null);
        return catcursor;
    }

    //Eliminar categoría
    public boolean borrarCat (int id){
        this.getWritableDatabase().delete("cat_tb","id="+ id, null);
        return true;
    }

    //ITEMS
    //Insertar registros en la tabla de items
    public void insertItem (String nom_item, int idCat) {
        ContentValues valores =  new ContentValues();
        valores.put("Nombre_Item", nom_item);
        valores.put("idCat", idCat);

        this.getWritableDatabase().insert("item_tb", null, valores);
    }

    //Consultar ítem
    public Cursor consultarItem (int idCat) throws SQLException {
        Cursor itcursor = null;
        itcursor = this.getReadableDatabase().query("item_tb", new String []{"ID", "Nombre_Item"},
                "idCat like " + idCat , null,null,null,null);
        return itcursor;
    }

    //Actualizar ítem
    public void actualizarItem (String nom_item, int idItem) {
        ContentValues valores =  new ContentValues();
        valores.put("Nombre_Item", nom_item);
        this.getWritableDatabase().update("item_tb", valores, "id="+ idItem, null);
    }

    //Eliminar ítem
    public boolean borrarItem (int idItem){
        this.getWritableDatabase().delete("item_tb","id="+ idItem, null);
        return true;
    }

}

