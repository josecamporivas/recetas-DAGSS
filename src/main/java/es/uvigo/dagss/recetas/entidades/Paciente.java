package es.uvigo.dagss.recetas.entidades;

import java.util.Date;

import es.uvigo.dagss.recetas.entidades.tipos.Direccion;
import es.uvigo.dagss.recetas.entidades.tipos.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "PACIENTE")
public class Paciente extends Usuario {

    @Column(unique = true)
    private String numTarjetaSanitaria;

    @Column(unique = true)
    private String numSS;

    private Direccion direccion;

    private Integer telefono;

    @Temporal(TemporalType.DATE)
    private Date fechaNac;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Medico medicoAsignado; //El centro de salud asociado se obtiene a partir del médico

    public Paciente() {
        super(TipoUsuario.PACIENTE);
    }

}
