package es.uvigo.dagss.recetas.entidades;

import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoCita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

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

    @Value("15")
    private Integer duracion;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Temporal(TemporalType.TIME)
    private Time hora;

    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    public Cita() {
    }

    public Cita(Date fecha, Time hora, Paciente paciente, Medico medico) {
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.medico = medico;
    }

}