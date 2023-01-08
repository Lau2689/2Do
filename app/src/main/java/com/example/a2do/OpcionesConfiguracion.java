package com.example.a2do;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2do.db.DbHelper;

public class OpcionesConfiguracion extends AppCompatActivity {

    Button buttonSalir;
    Button buttonBorrarUs;
    Button buttonVolver;
    int idUsu;
    DbHelper helper = new DbHelper(this, "BD1", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_configuracion);
        idUsu = getIntent().getExtras().getInt("id_usu");

        // Salimos de la aplicación y se vuelve a la pantalla de inicio de sesión
        buttonSalir= (Button) findViewById(R.id.buttonSalir);
        buttonSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentSalir = new Intent(OpcionesConfiguracion.this, MainActivity.class);
                startActivity(intentSalir);
        }
        });

        // Borramos cuenta, se pide permiso y vuelve a la página de inicio de sesión
        buttonBorrarUs= (Button) findViewById(R.id.buttonBorrarUs);
        buttonBorrarUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Añadir código para Toast de confirmación de borrado de cuenta y delete de cuenta

                AlertDialog.Builder a = new AlertDialog.Builder(OpcionesConfiguracion.this);
                a.setMessage("¿Estás seguro de que quieres eliminar tu cuenta?");
                a.setCancelable(false);
                a.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(helper.borrarUsu(idUsu)){
                            Toast.makeText(OpcionesConfiguracion.this, "CUENTA ELIMINADA",Toast.LENGTH_LONG).show();
                            Intent intentBorrarUs = new Intent(OpcionesConfiguracion.this, MainActivity.class);
                            startActivity(intentBorrarUs);
                            finish();
                        }else {
                            Toast.makeText(OpcionesConfiguracion.this, "ERROR: CUENTA NO ELIMINADA",Toast.LENGTH_LONG).show();
                        }
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

        // Volvemos a pantalla principal
        buttonVolver= (Button) findViewById(R.id.buttonVolver);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentVolver = new Intent(OpcionesConfiguracion.this, PantallaPrincipal.class);
                intentVolver.putExtra("id_usu", idUsu);
                startActivity(intentVolver);
            }
        });

    }
}
