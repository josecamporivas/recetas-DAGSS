package es.uvigo.dagss.recetas.entidades;

import es.uvigo.dagss.recetas.entidades.tipos.Nombre;
import es.uvigo.dagss.recetas.entidades.tipos.TipoUsuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario {
	
    public Administrador() {
        super(TipoUsuario.ADMINISTRADOR);
    }

    public Administrador(String login, String password){
        super(TipoUsuario.ADMINISTRADOR,login,password);
    }

    public Administrador(String login, String password, String DNI, Nombre nombreCompleto, String email){
        super(TipoUsuario.ADMINISTRADOR,login,password);
        this.DNI = DNI;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
    }


}
