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

    @Column(unique = true)
    private String email;

    private Boolean estado;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Medico> medicos;

    public CentroSalud() {
    }
    public CentroSalud(String nombre, Direccion direccion, String email, Boolean estado, List<Medico> medicos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.estado = estado;
        this.medicos = medicos;
    }
}
