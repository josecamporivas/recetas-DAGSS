package es.uvigo.dagss.recetas.entidades;

import es.uvigo.dagss.recetas.entidades.tipos.TipoUsuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario {
	
    public Administrador() {
        super(TipoUsuario.ADMINISTRADOR);
    }
}
