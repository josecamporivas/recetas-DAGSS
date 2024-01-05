package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
public class Prescripcion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrescripcion;

    private Double dosisDiaria;

    private String indicaciones;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    private java.sql.Date fechaFinal;

    @Value("true")
    private boolean estado;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Medicamento medicamento;

    public Prescripcion() {
        this.estado = true;
        this.fechaInicio = Calendar.getInstance().getTime();
    }

    public Prescripcion(Double dosisDiaria, String indicaciones, java.sql.Date fechaFinal) {
        this();
        this.dosisDiaria = dosisDiaria;
        this.indicaciones = indicaciones;
        this.fechaFinal = fechaFinal;
    }
}
