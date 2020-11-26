package com.example.rest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    EditText txtID,txtNombre,txtApellido,txtPuesto,txtSalario;
    private Empleados empleado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtID=(EditText) findViewById(R.id.txtID);
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtApellido=(EditText)findViewById(R.id.txtApellido);
        txtPuesto=(EditText)findViewById(R.id.txtPuesto);
        txtSalario=(EditText)findViewById(R.id.txtSalario);
    }

    public void RegristarEmpleado(View view){
        empleado=new Empleados(Integer.parseInt(txtID.getText().toString()),txtApellido.getText().toString(),txtNombre.getText().toString(),txtPuesto.getText().toString(),Float.valueOf(txtSalario.getText().toString()));


        Call<String> call = ApiAdapter.getApiService().InsertEmpleado(empleado);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                txtID.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtPuesto.setText("");
                txtSalario.setText("");
                Toast.makeText(MainActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                txtID.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtPuesto.setText("");
                txtSalario.setText("");
                Toast.makeText(MainActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void ConsultarEmpleados(View view){
        Intent intent = new Intent(this, TerceraActivity.class);
        startActivity(intent);

    }
    public void BuscarEmpleado(View view){
        Intent intent = new Intent(this, SegundaActivity.class);
        startActivity(intent);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opcion1:
                Date fecha = new Date();

                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.text_informacion_progra))
                        .setMessage(getString(R.string.text_programadores)+ "fecha:"+fecha.toString())
                        .setPositiveButton(getString(R.string.text_Aceptar), null)
                        .setCancelable(false)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
