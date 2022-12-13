package com.example.a2do;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaPrincipal extends AppCompatActivity {
    ImageButton buttonConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        // Entramos en la pantalla de opciones/configuración
        buttonConf= (ImageButton) findViewById(R.id.buttonConf);
        buttonConf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentConf = new Intent(PantallaPrincipal.this, OpcionesConfiguracion.class);
                startActivity(intentConf);
            }
        });

    }
}
