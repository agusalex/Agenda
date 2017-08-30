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
		 //if(!this.email.equals(""))
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
		person.setMailServer(this.mailServer);
	}
	
	
	@Override
	public int compareTo(PersonaJasper personJasper2) {
		return this.nombre.compareTo(personJasper2.nombre);
	}

	
	public static List<PersonaJasper> getPersonasJasper(List<PersonaDTO> personas){
		List<PersonaJasper> personasJasper = new ArrayList<PersonaJasper>();
		personas.forEach(p -> personasJasper.add(new PersonaJasper(p)));
		Collections.sort(personasJasper);
		return personasJasper;
	}
}
