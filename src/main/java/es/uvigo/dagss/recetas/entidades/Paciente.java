package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "PACIENTE")
public class Paciente extends Usuario {

	// Anadir atributos propios
   

    public Paciente() {
        super(TipoUsuario.PACIENTE);        
    }

}
