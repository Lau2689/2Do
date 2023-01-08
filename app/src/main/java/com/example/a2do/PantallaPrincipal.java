package com.example.a2do;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.a2do.db.DbHelper;

public class PantallaPrincipal extends AppCompatActivity {
    int idUsu;

    ImageButton buttonConf;
    Button buttonNuevaCat;
    Cursor categorias;
    Button cat1;
    Button cat2;
    Button cat3;
    Button cat4;
    Button cat5;
    Button cat6;
    Button cat7;
    Button cat8;
    Button cat9;
    Button cat10;

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

        cat1= (Button) findViewById(R.id.CAT1);
        cat2= (Button) findViewById(R.id.CAT2);
        cat3= (Button) findViewById(R.id.CAT3);
        cat4= (Button) findViewById(R.id.CAT4);
        cat5= (Button) findViewById(R.id.CAT5);
        cat6= (Button) findViewById(R.id.CAT6);
        cat7= (Button) findViewById(R.id.CAT7);
        cat8= (Button) findViewById(R.id.CAT8);
        cat9= (Button) findViewById(R.id.CAT9);
        cat10= (Button) findViewById(R.id.CAT10);
        popUp = (ConstraintLayout) findViewById(R.id.POP_UP_CREAR);

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
            }
        });

        BUTA = (Button) findViewById(R.id.BUTA);
        BUTA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.GONE);
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

    public void cargarButtons () {
        categorias = helper.ConsultarCat(idUsu);
        switch (categorias.getCount()) {
            case 10:
                cat10.setVisibility(View.VISIBLE);
            case 9:
                cat9.setVisibility(View.VISIBLE);
            case 8:
                cat8.setVisibility(View.VISIBLE);
            case 7:
                cat7.setVisibility(View.VISIBLE);
            case 6:
                cat6.setVisibility(View.VISIBLE);
            case 5:
                cat5.setVisibility(View.VISIBLE);
            case 4:
                cat4.setVisibility(View.VISIBLE);
            case 3:
                cat3.setVisibility(View.VISIBLE);
            case 2:
                cat2.setVisibility(View.VISIBLE);
            case 1:
                cat1.setVisibility(View.VISIBLE);

        }
    }
}


/*
package com.example.a2do;

        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.constraintlayout.widget.ConstraintLayout;
        import com.example.a2do.db.DbHelper;

public class PantallaPrincipal extends AppCompatActivity {

    int idUsu;

    ImageButton buttonConf;
    Button buttonNuevaCat;
    Cursor categorias;
    Button cat1;
    Button cat2;
    Button cat3;
    Button cat4;
    Button cat5;
    Button cat6;
    Button cat7;
    Button cat8;
    Button cat9;
    Button cat10;

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

        cat1= (Button) findViewById(R.id.CAT1);
        cat2= (Button) findViewById(R.id.CAT2);
        cat3= (Button) findViewById(R.id.CAT3);
        cat4= (Button) findViewById(R.id.CAT4);
        cat5= (Button) findViewById(R.id.CAT5);
        cat6= (Button) findViewById(R.id.CAT6);
        cat7= (Button) findViewById(R.id.CAT7);
        cat8= (Button) findViewById(R.id.CAT8);
        cat9= (Button) findViewById(R.id.CAT9);
        cat10= (Button) findViewById(R.id.CAT10);
        popUp = (ConstraintLayout) findViewById(R.id.POP_UP_CREAR);

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
            }
        });

        BUTA = (Button) findViewById(R.id.BUTA);
        BUTA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.GONE);
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
    }*/

    /*
    public void cargarButtons (){
        categorias = helper.ConsultarCat(idUsu);
        switch (categorias.getCount()){
            case 10:
                cat10.setVisibility(View.VISIBLE);
            case 9:
                cat9.setVisibility(View.VISIBLE);
            case 8:
                cat8.setVisibility(View.VISIBLE);
            case 7:
                cat7.setVisibility(View.VISIBLE);
            case 6:
                cat6.setVisibility(View.VISIBLE);
            case 5:
                cat5.setVisibility(View.VISIBLE);
            case 4:
                cat4.setVisibility(View.VISIBLE);
            case 3:
                cat3.setVisibility(View.VISIBLE);
            case 2:
                cat2.setVisibility(View.VISIBLE);
            case 1:
                cat1.setVisibility(View.VISIBLE);

        }

    }
}*/