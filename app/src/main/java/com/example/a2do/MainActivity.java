package com.example.a2do;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonCrearCuenta;
    Button buttonEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Entramos en la pantalla de crear nueva cuenta
        buttonCrearCuenta = (Button) findViewById(R.id.buttonCrearCuenta);
        buttonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentCrearCuenta = new Intent(MainActivity.this, NuevaCuenta.class);
                startActivity(intentCrearCuenta);
            }
        });

        // Entramos en la pantalla principal de la aplicación
        buttonEntrar = (Button) findViewById(R.id.buttonEntrar);
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentEntrar = new Intent(MainActivity.this, PantallaPrincipal.class);
                startActivity(intentEntrar);
            }
        });



    }
}