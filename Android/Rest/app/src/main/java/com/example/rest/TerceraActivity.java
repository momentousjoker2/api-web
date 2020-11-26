package com.example.rest;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TerceraActivity extends AppCompatActivity implements Callback<List<Empleados>> {
    ListView lvEmpelados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);
        lvEmpelados=(ListView)findViewById(R.id.lvEmpleados);
        Call<List<Empleados>> call = ApiAdapter.getApiService().getEmpleados();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Empleados>> call, Response<List<Empleados>> response) {
        if (response.isSuccessful()){

            String[] datos = new String[response.body().size()];

            int i=0;
            for(Empleados list : response.body()) {
                datos[i] = list.getNombre()+" "+list.getApellidos();
                i=i+1;
            }

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(TerceraActivity.this,android.R.layout.simple_list_item_1,datos);
            lvEmpelados.setAdapter(adaptador);

        }
    }

    @Override
    public void onFailure(Call<List<Empleados>> call, Throwable t) {
        Toast.makeText(TerceraActivity.this,"Error"+t.getMessage(),Toast.LENGTH_LONG).show();

    }
}