package es.uvigo.dagss.recetas.entidades;

import es.uvigo.dagss.recetas.entidades.tipos.Direccion;
import es.uvigo.dagss.recetas.entidades.tipos.TipoUsuario;
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

    private String nombreFarmacia;

    private String numColegiadoFarmaceutico;

    private Direccion direccion;

    private Integer telefono;
	
    public Farmacia() {
        super(TipoUsuario.FARMACIA);
    }

    public Farmacia(String nombreFarmacia, String numColegiadoFarmaceutico, Direccion direccion, Integer telefono) {
        super(TipoUsuario.FARMACIA);
        this.nombreFarmacia = nombreFarmacia;
        this.numColegiadoFarmaceutico = numColegiadoFarmaceutico;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
}
