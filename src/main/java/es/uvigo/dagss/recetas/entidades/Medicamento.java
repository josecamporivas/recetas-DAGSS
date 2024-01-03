package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Medicamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicamento;

    private String nombre;

    private String principioActivo;

    private String fabricante;

    private String familia;

    private Integer dosis;

    public Boolean estado;

    public Medicamento(){
        this.estado = true;
    }

    public Medicamento(String nombre, String principioActivo, String fabricante, String familia, Integer dosis) {
        this();
        this.nombre = nombre;
        this.principioActivo = principioActivo;
        this.fabricante = fabricante;
        this.familia = familia;
        this.dosis = dosis;
    }
}
