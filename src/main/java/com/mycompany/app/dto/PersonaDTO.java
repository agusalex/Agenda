package com.mycompany.app.dto;

import java.time.Instant;
import java.util.Date;

public class PersonaDTO {
	protected int idPersona;
	protected String nombre ;
	protected String telefono ;
	protected String calle ;
	protected String altura ;
	protected String piso ;
	protected String departamento ;
	protected String email ;
	protected String mailServer ;
	protected String fechaNacimmiento ;
	protected LocalidadDTO localidad;
	protected EtiquetaDTO etiqueta;



	public PersonaDTO()
	{
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


	public String getAltura() {

		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
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

	public String getFechaNacimmiento() {
		return fechaNacimmiento;
	}

	public void setFechaNacimmiento(String fechaNacimmiento) {
		this.fechaNacimmiento = fechaNacimmiento;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {

		this.localidad = localidad;
	}

	public EtiquetaDTO getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(EtiquetaDTO etiqueta) {
		this.etiqueta = etiqueta;
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

	/*
	public void setMailServer() {
		this.mailServer = "";
		int i = this.email.indexOf('@');
		if (i != -1) {
		i++;
			for (int j = i; j < this.email.length(); j++) {
				this.mailServer += this.email.charAt(j);
			}
		}
	}
*/
	public String getMailServer(){ return this.mailServer; }

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
		
	}
}
