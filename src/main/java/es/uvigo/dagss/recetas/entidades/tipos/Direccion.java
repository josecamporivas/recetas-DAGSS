package es.uvigo.dagss.recetas.entidades.tipos;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class Direccion implements Serializable {
    private String domicilio;
    private String provincia;
    private Integer cp;
    private String localidad;

    public Direccion() {
    }

    public Direccion(String domicilio, String provincia, Integer cp, String localidad) {
        this.domicilio = domicilio;
        this.provincia = provincia;
        this.cp = cp;
        this.localidad = localidad;
    }
}
