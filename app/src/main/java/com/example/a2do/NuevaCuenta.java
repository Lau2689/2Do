package com.example.a2do;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NuevaCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_cuenta);

        // Salimos de la aplicación y se vuelve a la pantalla de inicio de sesión
        Button buttonCrearNueva= (Button) findViewById(R.id.buttonCrearNueva);
        buttonCrearNueva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Código de creación de cuenta. Meter un Toast de cuenta creada y entra en su pantalla de inicio

                Intent intentCrearNueva= new Intent(NuevaCuenta.this, PantallaPrincipal.class);
                startActivity(intentCrearNueva);
            }
        });


        // Cancelamos creación de cuenta y se vuelve a pantalla de Inicio de Sesión
        Button buttonCancelar= (Button) findViewById(R.id.buttonCancelar);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentCancelar = new Intent(NuevaCuenta.this, MainActivity.class);
                startActivity(intentCancelar);
            }
        });

    }
}