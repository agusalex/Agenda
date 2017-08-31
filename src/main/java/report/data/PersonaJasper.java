package report.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.dto.PersonaDTO;



public class PersonaJasper extends PersonaDTO implements Comparable<PersonaJasper>{

	
	public PersonaJasper() {
		super();
	}
	
	public PersonaJasper(PersonaDTO person) {
		 this.nombre = person.getNombre();
		 this.telefono = person.getTelefono();
		 this.calle = person.getCalle();
		 this.altura = person.getAltura();
		 this.piso = person.getPiso();
		 this.departamento = person.getDepartamento();
		 this.email = person.getEmail();
		 this.fechaNacimmiento = person.getFechaNacimmiento();
		 this.localidad = person.getLocalidad();
		 this.etiqueta = person.getEtiqueta();
		 if(this.fechaNacimmiento == null)
			 this.fechaNacimmiento = "";
		 if(this.email.equals(""))
			 this.mailServer = "Sin cuenta de correo electronico";
		 else
			 this.setMailServer(person);
			
	}
	
	
	private void setMailServer(PersonaDTO person) {
		this.mailServer = "";
		int i = this.email.indexOf('@');
		if (i != -1) {
		i++;
			for (int j = i; j < this.email.length(); j++) {
				this.mailServer += this.email.charAt(j);
			}
		}
	}
	
	
	@Override
	public int compareTo(PersonaJasper personJasper2) {
		return this.nombre.compareTo(personJasper2.nombre);
	}

	
	private static void borrarNulls(List<PersonaDTO> personas){

		for (PersonaDTO persona:personas) {

			if(persona.getLocalidad()==null)
				persona.setLocalidad(new LocalidadDTO(""));

			else if(persona.getLocalidad().getNombre()==null)
				persona.setLocalidad(new LocalidadDTO(""));

			else if(persona.getLocalidad().getNombre().equals("null"))
				persona.setLocalidad(new LocalidadDTO(""));

			if(persona.getEtiqueta()==null)
				persona.setEtiqueta(new EtiquetaDTO(""));

			else if(persona.getEtiqueta().getNombre()==null)
				persona.setEtiqueta(new EtiquetaDTO(""));

			else if(persona.getEtiqueta().getNombre().equals("null"))
				persona.setEtiqueta(new EtiquetaDTO(""));
		}
	}
			
	
	public static List<PersonaJasper> getPersonasJasper(List<PersonaDTO> personas){
		borrarNulls(personas);
		List<PersonaJasper> personasJasper = new ArrayList<PersonaJasper>();
		personas.forEach(p -> personasJasper.add(new PersonaJasper(p)));
		Collections.sort(personasJasper);
		return personasJasper;
	}
	
	private String mailServer;

	public String getMailServer() {
		return mailServer;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}
	
	
}
