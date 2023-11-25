package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario {

    @Column(nullable = false)
    private String nombreFarmacia;

    @Column(nullable = false)
    private String numColegiadoFarmaceutico;

    private Direccion direccion;

    private Integer telefono;
	
    public Farmacia() {
        super(TipoUsuario.FARMACIA);
    }
    
}
