package es.uvigo.dagss.recetas.entidades;

import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoCita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    private LocalDateTime fecha;

    @Temporal(TemporalType.TIME)
    private LocalDateTime hora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Medico medico;

}