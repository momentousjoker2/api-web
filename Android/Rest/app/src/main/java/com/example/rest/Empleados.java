package com.example.rest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Empleados {

    @SerializedName("Clave")
    @Expose
    private int Clave;

    @SerializedName("Apellidos")
    @Expose
    private String Apellidos;

    @SerializedName("Nombres")
    @Expose
    private String Nombres;

    @SerializedName("Puesto")
    @Expose
    private String Puesto;

    @SerializedName("Salario")
    @Expose
    private float Salario;

    public Empleados() {
    }

    public Empleados(int clave, String apellidos, String nombre, String puesto, float salario) {
        this.Clave = clave;
        this.Apellidos = apellidos;
        this.Nombres = nombre;
        this.Puesto = puesto;
        this.Salario = salario;
    }

    public int getClave() {
        return Clave;
    }

    public void setClave(int clave) {
        this.Clave = clave;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        this.Apellidos = apellidos;
    }

    public String getNombre() {
        return Nombres;
    }

    public void setNombre(String nombre) {
        this.Nombres = nombre;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        this.Puesto = puesto;
    }

    public float getSalario() {
        return Salario;
    }

    public void setSalario(float salario) {
        this.Salario = salario;
    }
}
