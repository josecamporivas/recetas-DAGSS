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
    private Date hora;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Medico medico;

}