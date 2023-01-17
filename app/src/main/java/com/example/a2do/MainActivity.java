package com.example.a2do;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.a2do.db.DbHelper;

public class MainActivity extends AppCompatActivity {
    Button buttonCrearCuenta;
    Button buttonEntrar;

    DbHelper helper = new DbHelper(this,"BD1",null, 1);

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

        // Entramos en la pantalla principal de la aplicación tras validar el usuario
        buttonEntrar = (Button) findViewById(R.id.buttonEntrar);
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText txtusu = (EditText) findViewById(R.id.editTxtUsu);
                EditText txtcont = (EditText) findViewById(R.id.editTxtCont);

                try {
                    Cursor cursor = helper.ConsultarUsuCon(txtusu.getText().toString(), txtcont.getText().toString());
                    if (cursor.getCount() > 0){
                        cursor.moveToFirst();
                        int idUsu= cursor.getInt(0);

                        Intent intentEntrar = new Intent(MainActivity.this, PantallaPrincipal.class);
                        intentEntrar.putExtra("id_usu", idUsu);
                        startActivity(intentEntrar);


                    }else {
                        Toast.makeText(MainActivity.this, "USUARIO Y/O CONTRASEÑA INCORRECTOS O NO EXISTE",Toast.LENGTH_LONG).show();
                    }

                    txtusu.setText("");
                    txtcont.setText("");
                    txtusu.findFocus();

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
    }
}