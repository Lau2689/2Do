package com.example.a2do;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class OpcionesConfiguracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_configuracion);


        // Salimos de la aplicación y se vuelve a la pantalla de inicio de sesión
        Button buttonSalir= (Button) findViewById(R.id.buttonSalir);
        buttonSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentSalir = new Intent(OpcionesConfiguracion.this, MainActivity.class);
                startActivity(intentSalir);
            }
        });


        // Borramos cuenta, se pide permiso y vuelve a la página de inicio de sesión
        Button buttonBorrarUs= (Button) findViewById(R.id.buttonBorrarUs);
        buttonBorrarUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Añadir código para Toast de confirmación de borrado de cuenta y delete de cuenta

                Intent intentBorrarUs = new Intent(OpcionesConfiguracion.this, MainActivity.class);
                startActivity(intentBorrarUs);
            }
        });


        // Volvemos a pantalla principal
        Button buttonVolver= (Button) findViewById(R.id.buttonVolver);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentVolver = new Intent(OpcionesConfiguracion.this, PantallaPrincipal.class);
                startActivity(intentVolver);
            }
        });

    }
}
