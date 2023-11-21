package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Nombre {
    private String nombre;
    private String apellido1;
    private String apellido2;
}
