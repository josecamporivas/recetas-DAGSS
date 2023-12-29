package es.uvigo.dagss.recetas.entidades;

import es.uvigo.dagss.recetas.entidades.tipos.Direccion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class CentroSalud implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCentro;

    private String nombre;

    private Direccion direccion;

    private String email;

    private Boolean estado;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Medico> medicos;

    public CentroSalud() {
        this.estado = true;
    }
    public CentroSalud(String nombre, Direccion direccion, String email, List<Medico> medicos) {
        this();
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.medicos = medicos;
    }
}
