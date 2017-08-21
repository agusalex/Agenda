package com.mycompany.app.dto;

public class LocalidadDTO {
    private int idLocalidad;
    private String nombre;

    public LocalidadDTO(int idLocalidad,String Nombre){
        this.nombre=Nombre;
        this.idLocalidad=idLocalidad;

    }
    public LocalidadDTO(String Nombre){
        this.nombre=Nombre;
        this.idLocalidad=0;

    }



    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString()
     {return this.nombre;}



}
