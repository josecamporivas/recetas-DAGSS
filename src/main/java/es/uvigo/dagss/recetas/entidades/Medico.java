package es.uvigo.dagss.recetas.entidades;

import es.uvigo.dagss.recetas.entidades.tipos.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario {

    @Column(unique = true, nullable = false)
    private String numColegiado;

    @Column(unique = true)
    private Integer telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    private CentroSalud centroSalud;

    public Medico() {
        super(TipoUsuario.MEDICO);
    }
}
