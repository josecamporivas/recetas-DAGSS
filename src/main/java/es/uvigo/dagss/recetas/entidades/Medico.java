package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario {

    // Anadir atributos propios
    
    public Medico() {
        super(TipoUsuario.MEDICO);
    }


}
