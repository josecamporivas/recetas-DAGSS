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

    @Column(nullable = false)
    private String nombre;

    private String principioActivo;

    private String fabricante;

    private String familia;

    @Column(nullable = false)
    private Integer dosis;

    public Boolean estado;
}
