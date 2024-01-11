package es.uvigo.dagss.recetas.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;
import es.uvigo.dagss.recetas.entidades.tipos.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario {

    @Column(unique = true)
    private String numColegiado;

    @Column(unique = true)
    private Integer telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private CentroSalud centroSalud;

    public Medico() {
        super(TipoUsuario.MEDICO);
    }

    public Medico(String numColegiado, Integer telefono, CentroSalud centroSalud, String DNI,
                  Nombre nombreCompleto, String email, String login, String password) {
        super(TipoUsuario.MEDICO, login, password);
        this.numColegiado = numColegiado;
        this.telefono = telefono;
        this.centroSalud = centroSalud;
        this.DNI = DNI;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
    }
}
