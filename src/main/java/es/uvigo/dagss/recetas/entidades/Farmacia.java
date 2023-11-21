package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario {

    // Anadir atributos propios
	
    public Farmacia() {
        super(TipoUsuario.FARMACIA);
    }
    
}
