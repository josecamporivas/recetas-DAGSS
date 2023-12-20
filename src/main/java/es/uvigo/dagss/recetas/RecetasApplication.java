package es.uvigo.dagss.recetas;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.tipos.Direccion;
import es.uvigo.dagss.recetas.repositorios.AdministradorRepository;
import es.uvigo.dagss.recetas.repositorios.CentroSaludRepository;
import es.uvigo.dagss.recetas.repositorios.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class RecetasApplication implements CommandLineRunner {

	@Autowired
	private AdministradorRepository administradorRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private CentroSaludRepository centroSaludRepository;


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

		Medico medico1 = new Medico("123", 123, null);
		Medico medico2 = new Medico("1234", 1234, null);
		medicoRepository.save(medico1);
		medicoRepository.save(medico2);

		CentroSalud centroSalud1 = new CentroSalud("centroSalud1" , new Direccion(),
				"centrosalud1@gmail.com", true, Arrays.asList(medico1));
		CentroSalud centroSalud2 = new CentroSalud("centroSalud2" , new Direccion(),
				"centrosalud2@gmail.com", true, Arrays.asList(medico2));
		centroSaludRepository.save(centroSalud1);
		centroSaludRepository.save(centroSalud2);

		medico1.setCentroSalud(centroSalud1);
		medico2.setCentroSalud(centroSalud2);
		medicoRepository.save(medico1);
		medicoRepository.save(medico2);



	}
}
