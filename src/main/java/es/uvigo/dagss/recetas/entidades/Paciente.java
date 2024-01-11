package es.uvigo.dagss.recetas.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.uvigo.dagss.recetas.entidades.tipos.Direccion;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;
import es.uvigo.dagss.recetas.entidades.tipos.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Medico medicoAsignado; //El centro de salud asociado se obtiene a partir del médico

    public Paciente() {
        super(TipoUsuario.PACIENTE);
    }

    public Paciente(String numTarjetaSanitaria, String numSS, Direccion direccion, Integer telefono, Date fechaNac,
                    String DNI, Nombre nombreCompleto, String email, String login, String password) {
        super(TipoUsuario.PACIENTE, login, password);
        this.numTarjetaSanitaria = numTarjetaSanitaria;
        this.numSS = numSS;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.DNI = DNI;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
    }
}
