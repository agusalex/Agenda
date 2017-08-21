package com.mycompany.app.dto;

import java.util.Map;

public class EtiquetaDTO {
    private int idEtiqueta;
    private String nombre;


    public EtiquetaDTO(String Nombre){
        this.nombre=Nombre;
        this.idEtiqueta=0;

    }


    public EtiquetaDTO(int idEtiqueta,String Nombre){
        this.nombre=Nombre;
        this.idEtiqueta=idEtiqueta;

    }



    public int getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(int idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
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
