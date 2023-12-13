package es.uvigo.dagss.recetas;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.repositorios.AdministradorRepository;
import es.uvigo.dagss.recetas.servicios.AdministradorService;
import es.uvigo.dagss.recetas.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecetasApplication implements CommandLineRunner {

	@Autowired
	private AdministradorRepository administradorRepository;

	public static void main(String[] args) {
		SpringApplication.run(RecetasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		crearDatosEjemplo();
	}

	private void crearDatosEjemplo(){
		Administrador admin = new Administrador("admin1", "admin1");
		administradorRepository.save(admin);

	}
}
