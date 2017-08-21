package com.mycompany.app.dto;

public class LocalidadDTO {
    private int idLocalidad;
    private String nombre;

    public LocalidadDTO(int idLocalidad,String Nombre){
        this.nombre=Nombre;
        this.idLocalidad=idLocalidad;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalidadDTO that = (LocalidadDTO) o;

        if (idLocalidad != that.idLocalidad) return false;
        return nombre != null ? nombre.equals(that.nombre) : that.nombre == null;
    }

    @Override
    public int hashCode() {
        int result = idLocalidad;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
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
