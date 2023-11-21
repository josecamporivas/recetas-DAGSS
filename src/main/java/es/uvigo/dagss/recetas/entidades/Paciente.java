package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "PACIENTE")
public class Paciente extends Usuario {

	private Nombre nombreCompleto;

    @Column(unique = true)
    private String DNI;

    @Column(unique = true)
    private String numTarjetaSanitaria;

    @Column(unique = true)
    private String numSS;

    private Direccion direccion;

    private Integer telefono;

    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date fechanac;

    @ManyToOne
    private CentroSalud centroSaludAsignado;

    @ManyToOne
    private Medico medicoAsignado; //TODO: EL MEDICO TIENE QUE ESTAR ASIGNADO AL CENTRO DE SALUD DEL PACIENTE
                                   //QUIZAS EL TRUCO ES NO ALMACENAR EL CENTRO DE SALUD PQ YA LO SACAMOS CON LA CLAVE FORANEA DEL MEDICO
    
    public Paciente() {
        super(TipoUsuario.PACIENTE);        
    }

}
