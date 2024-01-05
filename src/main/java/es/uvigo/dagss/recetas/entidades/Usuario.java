package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import es.uvigo.dagss.recetas.entidades.tipos.Nombre;
import es.uvigo.dagss.recetas.entidades.tipos.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  // Una tabla propia para cada subclase
@DiscriminatorColumn(name = "TIPO_USUARIO", discriminatorType = DiscriminatorType.STRING, length = 20)
public abstract class Usuario implements Serializable {

    @Id
    @TableGenerator(name = "USUARIO_GEN", table = "USUARIO_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)           
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USUARIO_GEN")
    private Long id;

	@Column(length = 9)
	protected String DNI;

	protected Nombre nombreCompleto;

	protected String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO", length = 20)
    protected TipoUsuario tipo;

    private String login;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcceso;

	private Boolean estado;
	
    public Usuario() {
        this.fechaAlta = Calendar.getInstance().getTime();
        this.ultimoAcceso = Calendar.getInstance().getTime();
		this.estado = true;
    }

    public Usuario(TipoUsuario tipo) {
    	this();
        this.tipo = tipo;
    }

    public Usuario(TipoUsuario tipo, String login, String password) {
    	this();
        this.tipo = tipo;
        this.login = login;
        this.password = password;
    }

    public void activar() {
        this.estado = true;
    }

    public void desactivar() {
        this.estado = false;
    }

	@Override
	public int hashCode() {
		if (this.id !=null)
			return Objects.hash(id);
		return Objects.hash(fechaAlta, login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (this.id !=null)
			return this.id.equals(other.getId());
		return Objects.equals(fechaAlta, other.fechaAlta)
				&& Objects.equals(login, other.login);
	}
}
