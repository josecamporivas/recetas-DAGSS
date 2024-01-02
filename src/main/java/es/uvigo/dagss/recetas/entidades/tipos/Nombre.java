package es.uvigo.dagss.recetas.entidades.tipos;

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

    public Nombre() {
    }

    public Nombre(String nombre, String apellido1, String apellido2) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }
}
