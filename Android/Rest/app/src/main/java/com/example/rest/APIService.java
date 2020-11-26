package com.example.rest;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface APIService {

    @Headers("Content-Type: application/json")
    @GET("/Empleados")
    Call<List<Empleados>> getEmpleados();

    @Headers("Content-Type: application/json")
    @GET("/Empleados/{id}")
    Call<List<Empleados>> getEmpleado(@Path("id") long id);

    @Headers("Content-Type: application/json")
    @POST("/Empleados")
    Call<String> InsertEmpleado(@Body Empleados empleados);

    @Headers("Content-Type: application/json")
    @PUT("/Empleados/{id}")
    Call<String> updateEmpleado(@Path("id")long id,@Body Empleados empleados);

    @Headers("Content-Type: application/json")
    @DELETE("/Empleados/{id}")
    Call<String>EliminarEmpleado(@Path("id")long id);
}
