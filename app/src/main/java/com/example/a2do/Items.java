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

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Items extends AppCompatActivity {

    Button buttonNuevoItem;
    Cursor items;

    Button ButAtras;
    List<EditText> txtItem;
    List<ImageButton> papeleras;
    List<ImageButton> discos;
    List<LinearLayout> layouts;

    int idCat;
    int idUsu;

    Cursor cursorItems;

    ConstraintLayout popUp;
    Button Pop_up_crear;
    Button Pop_up_atras;
    EditText nomnueitem;

    DbHelper helper = new DbHelper(this, "BD1", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);

        idCat = getIntent().getExtras().getInt("id_cat");
        idUsu = getIntent().getExtras().getInt("id_usu");

        txtItem = new ArrayList<>();
        txtItem.add(findViewById(R.id.eT1));
        txtItem.add(findViewById(R.id.eT2));
        txtItem.add(findViewById(R.id.eT3));
        txtItem.add(findViewById(R.id.eT4));
        txtItem.add(findViewById(R.id.eT5));
        txtItem.add(findViewById(R.id.eT6));
        txtItem.add(findViewById(R.id.eT7));
        txtItem.add(findViewById(R.id.eT8));
        txtItem.add(findViewById(R.id.eT9));
        txtItem.add( findViewById(R.id.eT10));

        papeleras = new ArrayList<>();
        papeleras.add(findViewById(R.id.bB1));
        papeleras.add(findViewById(R.id.bB2));
        papeleras.add(findViewById(R.id.bB3));
        papeleras.add(findViewById(R.id.bB4));
        papeleras.add(findViewById(R.id.bB5));
        papeleras.add(findViewById(R.id.bB6));
        papeleras.add(findViewById(R.id.bB7));
        papeleras.add(findViewById(R.id.bB8));
        papeleras.add(findViewById(R.id.bB9));
        papeleras.add(findViewById(R.id.bB10));

        discos = new ArrayList<>();
        discos.add(findViewById(R.id.bG1));
        discos.add(findViewById(R.id.bG2));
        discos.add(findViewById(R.id.bG3));
        discos.add(findViewById(R.id.bG4));
        discos.add(findViewById(R.id.bG5));
        discos.add(findViewById(R.id.bG6));
        discos.add(findViewById(R.id.bG7));
        discos.add(findViewById(R.id.bG8));
        discos.add(findViewById(R.id.bG9));
        discos.add(findViewById(R.id.bG10));

        layouts = new ArrayList<>();
        layouts.add(findViewById(R.id.linearLayout1));
        layouts.add(findViewById(R.id.linearLayout2));
        layouts.add(findViewById(R.id.linearLayout3));
        layouts.add(findViewById(R.id.linearLayout4));
        layouts.add(findViewById(R.id.linearLayout5));
        layouts.add(findViewById(R.id.linearLayout6));
        layouts.add(findViewById(R.id.linearLayout7));
        layouts.add(findViewById(R.id.linearLayout8));
        layouts.add(findViewById(R.id.linearLayout9));
        layouts.add(findViewById(R.id.linearLayout10));

        popUp= (ConstraintLayout) findViewById(R.id.POP_UP_ITEM);
        nomnueitem = (EditText) findViewById(R.id.nomnueitem);

        cargarEditTexts();

        buttonNuevoItem = (Button) findViewById(R.id.buttonNuevoItem);
        buttonNuevoItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.VISIBLE);
            }
        });

        // Volvemos a pantalla principal
        ButAtras= (Button) findViewById(R.id.ButAtras);
        ButAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentAtras = new Intent(Items.this, PantallaPrincipal.class);
                intentAtras.putExtra("id_usu", idUsu);
                startActivity(intentAtras);
            }
        });

        Pop_up_crear = (Button) findViewById(R.id.Pop_up_crear);
        Pop_up_crear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.GONE);
                if (items.getCount() == 10){
                    Toast.makeText(Items.this, "MÁXIMO 10 ITEMS", Toast.LENGTH_LONG).show();
                }else {
                    helper.abrir();
                    helper.insertItem(String.valueOf(nomnueitem.getText()),idCat);
                    helper.cerrar();

                    Toast.makeText(Items.this, "NUEVO ÍTEM CREADO", Toast.LENGTH_LONG).show();
                    nomnueitem.setText("");
                    cargarEditTexts();
                }

            }

        });

        Pop_up_atras = (Button) findViewById(R.id.Pop_up_atras);
        Pop_up_atras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.GONE);
            }
        });
        nomnueitem = (EditText) findViewById(R.id.nomnueitem);
    }

    //Visibiliza o invisibiliza los linear layouts de categorías
    public void cargarEditTexts () {
        items = helper.consultarItem(idCat);
        items.moveToFirst();
        for (int i=0; i<txtItem.size(); i++){
            if (i < items.getCount()){
                layouts.get(i).setVisibility(View.VISIBLE);
                txtItem.get(i).setText(items.getString(1));
                String nombreItem = String.valueOf(txtItem.get(i).getText());
                int id =items.getInt(0);
                discos.get(i).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                       helper.actualizarItem(nombreItem,id);
                       Toast.makeText(Items.this, "ÍTEM ACTUALIZADO",Toast.LENGTH_LONG).show();
                    }
                });
                papeleras.get(i).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        AlertDialog.Builder a = new AlertDialog.Builder(Items.this);
                        a.setMessage("¿Estás seguro de que quieres eliminar el ítem?");
                        a.setCancelable(false);
                        a.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(helper.borrarItem(id)){
                                    Toast.makeText(Items.this, "ÍTEM ELIMINADO",Toast.LENGTH_LONG).show();

                                }else {
                                    Toast.makeText(Items.this, "ERROR: ÍTEM NO ELIMINADO",Toast.LENGTH_LONG).show();
                                }

                                cargarEditTexts();
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
            items.moveToNext();

        }


    }

}
