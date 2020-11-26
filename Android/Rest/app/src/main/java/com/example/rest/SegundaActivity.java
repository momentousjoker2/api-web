package com.example.rest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SegundaActivity extends AppCompatActivity {
    EditText txtIDM,txtNombreM,txtApellidoM,txtPuestoM,txtSalarioM;
    private Empleados empleado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        txtIDM=(EditText) findViewById(R.id.txtIDM);
        txtNombreM=(EditText)findViewById(R.id.txtNombreM);
        txtApellidoM=(EditText)findViewById(R.id.txtApellidoM);
        txtPuestoM=(EditText)findViewById(R.id.txtPuestoM);
        txtSalarioM=(EditText)findViewById(R.id.txtSalarioM);
    }

    public void ConsultarEmpleado(View view){
        Call<List<Empleados>> call = ApiAdapter.getApiService().getEmpleado(Integer.parseInt(txtIDM.getText().toString()));
        call.enqueue(new Callback<List<Empleados>>() {
            @Override
            public void onResponse(Call<List<Empleados>> call, Response<List<Empleados>> response) {
                empleado=new Empleados();
                if (!response.body().isEmpty()) {
                    empleado = response.body().get(0);

                    txtIDM.setText(String.valueOf(empleado.getClave()));
                    txtNombreM.setText(empleado.getNombre());
                    txtApellidoM.setText(empleado.getApellidos());
                    txtPuestoM.setText(empleado.getPuesto());
                    txtSalarioM.setText(String.valueOf(empleado.getSalario()));
                }else{
                    Toast.makeText(SegundaActivity.this,"Empleado no encontrado",Toast.LENGTH_LONG).show();
                    txtIDM.setText("");
                    txtNombreM.setText("");
                    txtApellidoM.setText("");
                    txtPuestoM.setText("");
                    txtSalarioM.setText("");

                }
            }

            @Override
            public void onFailure(Call<List<Empleados>> call, Throwable t) {
                Toast.makeText(SegundaActivity.this,"Error"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void UpdateEmpleado(View view){
        Empleados empleados=new Empleados(0,txtApellidoM.getText().toString(),txtNombreM.getText().toString(),txtPuestoM.getText().toString(),Float.valueOf(txtSalarioM.getText().toString()));
        AlertDialog.Builder builder = new AlertDialog.Builder(SegundaActivity.this);
        builder.setMessage(R.string.updateMensaje).setTitle(R.string.updateTitulo);
        builder.setPositiveButton(R.string.updateOk, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Call<String> call = ApiAdapter.getApiService().updateEmpleado(Integer.parseInt(txtIDM.getText().toString()),empleados);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.code()==200)
                            Toast.makeText(SegundaActivity.this,"Actualizado",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(SegundaActivity.this,"Error",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(SegundaActivity.this,"Error"+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void EliminarEmpleado(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(SegundaActivity.this);
        builder.setMessage(R.string.eliminarMensaje).setTitle(R.string.eliminarTitulo);
        builder.setPositiveButton(R.string.Eliminarok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Call<String> call = ApiAdapter.getApiService().EliminarEmpleado(Integer.parseInt(txtIDM.getText().toString()));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.code()==200)
                            Toast.makeText(SegundaActivity.this,"Eliminado",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(SegundaActivity.this,"Error",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(SegundaActivity.this,"Error"+t.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }




}