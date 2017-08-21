package com.mycompany.app.dto;

import java.time.Instant;
import java.util.Date;

public class PersonaDTO
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String calle;
	private int altura;
	private int piso;
	private String departamento;
	private String email;
	private Date fechaNacimmiento;
	private int idLocalidad;
	private int idEtiqueta;

	public PersonaDTO(int idPersona, String nombre, String telefono)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	public PersonaDTO()
	{
		this.idPersona = 0;
		this.nombre = "Ejemplo";
		this.telefono = "13245678";
		this.calle = "Calle";
		this.altura =1234;
		this.piso=1;
		this.departamento="A";
		this.email = "email@ejemplo.org";
		this.fechaNacimmiento =  Date.from(Instant.now());
		this.idEtiqueta=1;
		this.idLocalidad=1;
	}
	
	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimmiento() {
		return fechaNacimmiento;
	}

	public void setFechaNacimmiento(Date fechaNacimmiento) {
		this.fechaNacimmiento = fechaNacimmiento;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public int getIdEtiqueta() {
		return idEtiqueta;
	}

	public void setIdEtiqueta(int idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	public void setNombre(String nombre)

	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}
}
