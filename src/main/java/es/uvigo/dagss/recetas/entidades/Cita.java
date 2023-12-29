package es.uvigo.dagss.recetas.entidades;

import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoCita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Entity
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32) DEFAULT 'PLANIFICADA'")
    private TipoEstadoCita estado = TipoEstadoCita.PLANIFICADA;

    private Integer duracion;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Temporal(TemporalType.TIME)
    private Time hora;

    @ManyToOne(fetch = FetchType.EAGER)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    private Medico medico;

    public Cita() {
        this.duracion = 15;
    }

    public Cita(Date fecha, Time hora, Paciente paciente, Medico medico) {
        this();
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.medico = medico;
    }

}