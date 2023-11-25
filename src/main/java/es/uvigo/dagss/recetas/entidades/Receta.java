package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
    @Column(nullable = false)
    private Date fechaValidezFinal;

    @Column(columnDefinition = "varchar(32) DEFAULT 'PLANIFICADA'")
    private TipoEstadoReceta estado = TipoEstadoReceta.PLANIFICADA;

    @Column(nullable = false)
    private Integer numUnidades; //DUDA: no se si ponerle un rango. NO se como poner un rango

    @ManyToOne
    private Prescripcion prescripcion;

    @ManyToOne
    private Medicamento medicamento;

    @ManyToOne
    private Farmacia farmacia;
}
