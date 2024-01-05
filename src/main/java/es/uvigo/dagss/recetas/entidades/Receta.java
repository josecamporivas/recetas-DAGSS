package es.uvigo.dagss.recetas.entidades;

import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoReceta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


@Getter
@Setter
@Entity
public class Receta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaValidezInicial;

    @Temporal(TemporalType.DATE)
    private java.sql.Date fechaValidezFinal;

    private TipoEstadoReceta estado;

    private Integer numUnidades;

    @ManyToOne
    private Prescripcion prescripcion;

    @ManyToOne
    private Farmacia farmacia;

    public Receta() {
        this.estado = TipoEstadoReceta.PLANIFICADA;
        this.fechaValidezInicial = Calendar.getInstance().getTime();
    }

    public Receta(java.sql.Date fechaValidezFinal, Integer numUnidades) {
        this();
        this.fechaValidezFinal = fechaValidezFinal;
        this.numUnidades = numUnidades;
    }
}
