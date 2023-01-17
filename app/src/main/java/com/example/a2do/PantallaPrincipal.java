package com.example.a2do;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.a2do.db.DbHelper;
import java.util.ArrayList;
import java.util.List;

public class PantallaPrincipal extends AppCompatActivity {
    int idUsu;

    ImageButton buttonConf;
    Button buttonNuevaCat;
    Cursor categorias;
    List<LinearLayout> layouts;
    List<Button> botonescats;
    List<ImageButton> botonespape;

    ConstraintLayout popUp;
    Button BUTC;
    Button BUTA;
    EditText nomnuecat;

    DbHelper helper = new DbHelper(this, "BD1", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        idUsu = getIntent().getExtras().getInt("id_usu");

        //El LinearLayout completo
        layouts = new ArrayList<>();
        layouts.add(findViewById(R.id.Ll1));
        layouts.add(findViewById(R.id.Ll2));
        layouts.add(findViewById(R.id.Ll3));
        layouts.add(findViewById(R.id.Ll4));
        layouts.add(findViewById(R.id.Ll5));
        layouts.add(findViewById(R.id.Ll6));
        layouts.add(findViewById(R.id.Ll7));
        layouts.add(findViewById(R.id.Ll8));
        layouts.add(findViewById(R.id.Ll9));
        layouts.add(findViewById(R.id.Ll10));

        //Botones de las categorías
        botonescats = new ArrayList<>();
        botonescats.add(findViewById(R.id.CAT1));
        botonescats.add(findViewById(R.id.CAT2));
        botonescats.add(findViewById(R.id.CAT3));
        botonescats.add(findViewById(R.id.CAT4));
        botonescats.add(findViewById(R.id.CAT5));
        botonescats.add(findViewById(R.id.CAT6));
        botonescats.add(findViewById(R.id.CAT7));
        botonescats.add(findViewById(R.id.CAT8));
        botonescats.add(findViewById(R.id.CAT9));
        botonescats.add(findViewById(R.id.CAT10));

        //ImageButton para borrar
        botonespape = new ArrayList<>();
        botonespape.add(findViewById(R.id.butB1));
        botonespape.add(findViewById(R.id.butB2));
        botonespape.add(findViewById(R.id.butB3));
        botonespape.add(findViewById(R.id.butB4));
        botonespape.add(findViewById(R.id.butB5));
        botonespape.add(findViewById(R.id.butB6));
        botonespape.add(findViewById(R.id.butB7));
        botonespape.add(findViewById(R.id.butB8));
        botonespape.add(findViewById(R.id.butB9));
        botonespape.add(findViewById(R.id.butB10));

        popUp = (ConstraintLayout) findViewById(R.id.POP_UP_CREAR);
        nomnuecat = (EditText) findViewById(R.id.nomnuecat);

        cargarButtons();

        buttonNuevaCat = (Button) findViewById(R.id.buttonNuevaCat);
        buttonNuevaCat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.VISIBLE);
            }
        });

        BUTC = (Button) findViewById(R.id.BUTC);
        BUTC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.GONE);
                if (categorias.getCount() == 10){
                    Toast.makeText(PantallaPrincipal.this, "MÁXIMO 10 CATEGORÍAS", Toast.LENGTH_LONG).show();
                }else {
                    helper.abrir();
                    helper.insertCat(String.valueOf(nomnuecat.getText()),idUsu);
                    helper.cerrar();

                    Toast.makeText(PantallaPrincipal.this, "NUEVA CATEGORIA CREADA", Toast.LENGTH_LONG).show();
                    nomnuecat.setText("");
                    cargarButtons();
                }
            }

        });

        BUTA = (Button) findViewById(R.id.BUTA);
        BUTA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.GONE);
                nomnuecat.setText("");
            }
        });
        nomnuecat = (EditText) findViewById(R.id.nomnuecat);

        // Entramos en la pantalla de opciones/configuración
        buttonConf= (ImageButton) findViewById(R.id.buttonConf);
        buttonConf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentConf = new Intent(PantallaPrincipal.this, OpcionesConfiguracion.class);
                intentConf.putExtra("id_usu", idUsu);
                startActivity(intentConf);
            }
        });
    }

    //Visibiliza o invisibiliza los linear layouts de categorías
    public void cargarButtons () {
        categorias = helper.consultarCat(idUsu);
        categorias.moveToFirst();
        for (int i=0; i<layouts.size(); i++){
            if (i < categorias.getCount()){
                layouts.get(i).setVisibility(View.VISIBLE);
                botonescats.get(i).setText(categorias.getString(1));
                int id =categorias.getInt(0);
                botonescats.get(i).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intentItems = new Intent(PantallaPrincipal.this, Items.class);
                        intentItems.putExtra("id_cat", id);
                        intentItems.putExtra("id_usu", idUsu);
                        startActivity(intentItems);
                    }
                });
                botonespape.get(i).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        AlertDialog.Builder a = new AlertDialog.Builder(PantallaPrincipal.this);
                        a.setMessage("¿Estás seguro de que quieres eliminar la categoría?");
                        a.setCancelable(false);
                        a.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(helper.borrarCat(id)){
                                    Toast.makeText(PantallaPrincipal.this, "CATEGORÍA ELIMINADA",Toast.LENGTH_LONG).show();

                                }else {
                                    Toast.makeText(PantallaPrincipal.this, "ERROR: CATEGORÍA NO ELIMINADA",Toast.LENGTH_LONG).show();
                                }

                                cargarButtons();
                            }
                        });
                        a.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        a.show();
                    }
                });

            } else {
                layouts.get(i).setVisibility(View.INVISIBLE);
            }
            categorias.moveToNext();

        }
    }
}

