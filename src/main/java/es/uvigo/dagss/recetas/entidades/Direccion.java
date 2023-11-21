package es.uvigo.dagss.recetas.entidades;

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
}
