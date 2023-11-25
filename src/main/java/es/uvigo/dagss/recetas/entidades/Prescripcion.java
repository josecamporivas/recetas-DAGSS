package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
public class Prescripcion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrescripcion;

    @Column(nullable = false)
    private Double dosisDiaria;

    private String indicaciones;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @Value("true")
    private boolean estado;

    @ManyToOne
    @Column(nullable = false)
    private Paciente paciente;

    @ManyToOne
    @Column(nullable = false)
    private Medico medico;

    @ManyToOne
    @Column(nullable = false)
    private Medicamento medicamento;
}
