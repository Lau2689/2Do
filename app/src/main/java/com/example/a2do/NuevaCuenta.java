package com.example.a2do;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2do.db.DbHelper;

public class NuevaCuenta extends AppCompatActivity {
    Button buttonCrearNueva;
    EditText txtNomUsu, txtConUsu, txtConUsu2;

    DbHelper helper = new DbHelper(this, "BD1", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_cuenta);

        txtNomUsu = (EditText)findViewById(R.id.editTextTextPersonName);
        txtConUsu = (EditText)findViewById(R.id.editTextTextPassword);
        txtConUsu2 = (EditText) findViewById(R.id.editTextTextPassword2);
        buttonCrearNueva= (Button) findViewById(R.id.buttonCrearNueva);

        buttonCrearNueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String conUsu1 = String.valueOf(txtConUsu.getText());
                String conUsu2 = String.valueOf(txtConUsu2.getText());

                //Código de creación de cuenta.
                if (conUsu1.equals(conUsu2)) {

                    helper.abrir();
                    helper.insertUsu(String.valueOf(txtNomUsu.getText()),conUsu1,conUsu2);
                    helper.cerrar();

                    Toast.makeText(NuevaCuenta.this, "NUEVA CUENTA CREADA", Toast.LENGTH_LONG).show();

                    //Una vez creada vuelve a la pantalla de inicio de sesión para introducir credenciales
                    Intent intentCrearNueva = new Intent(NuevaCuenta.this, MainActivity.class);
                    startActivity(intentCrearNueva);
                }else {

                    Toast.makeText(NuevaCuenta.this, "LAS CONTRASEÑAS NO COINCIDEN, INTRODUZCALAS DE NUEVO", Toast.LENGTH_LONG).show();
                    txtConUsu.setText("");
                    txtConUsu2.setText("");
                }
            }
        });

        // Cancelamos creación de cuenta y se vuelve a pantalla de inicio de sesión
        Button buttonCancelar= (Button) findViewById(R.id.buttonCancelar);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentCancelar = new Intent(NuevaCuenta.this, MainActivity.class);
                startActivity(intentCancelar);
            }
        });

    }
}