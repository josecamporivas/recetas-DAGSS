package es.uvigo.dagss.recetas.entidades;

import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoReceta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;


@Getter
@Setter
@Entity
public class Receta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaValidezInicial;

    @Temporal(TemporalType.DATE)
    private Date fechaValidezFinal;

    private TipoEstadoReceta estado;

    private Integer numUnidades;

    @ManyToOne
    private Prescripcion prescripcion;

    @ManyToOne
    private Farmacia farmacia;

    public Receta() {
        this.estado = TipoEstadoReceta.PLANIFICADA;
    }

    public Receta(Date fechaValidezInicial, Date fechaValidezFinal, Integer numUnidades) {
        this();
        this.fechaValidezInicial = fechaValidezInicial;
        this.fechaValidezFinal = fechaValidezFinal;
        this.numUnidades = numUnidades;
    }
}
