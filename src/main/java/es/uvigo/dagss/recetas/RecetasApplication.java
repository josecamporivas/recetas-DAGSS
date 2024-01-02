package es.uvigo.dagss.recetas;

import es.uvigo.dagss.recetas.entidades.*;
import es.uvigo.dagss.recetas.entidades.tipos.Direccion;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;
import es.uvigo.dagss.recetas.repositorios.*;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.List;

@SpringBootApplication
public class RecetasApplication implements CommandLineRunner {

	@Autowired
	private AdministradorRepository administradorRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private CentroSaludRepository centroSaludRepository;

	@Autowired
	private FarmaciaRepository farmaciaRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private CitaRepository citaRepository;

	public static void main(String[] args) {
		SpringApplication.run(RecetasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		crearDatosEjemplo();
	}

	private void crearDatosEjemplo() throws ParseException {

		//ADMINISTRADORES
		Administrador admin = new Administrador("admin1", "admin1");
		administradorRepository.save(admin);

		//MEDICOS
		Medico medico1 = new Medico("123", 123, null);
		medico1.setNombreCompleto(new Nombre("Carlos", "Fernandez", "Amil"));
		Medico medico2 = new Medico("1234", 1234, null);
		medico2.setNombreCompleto(new Nombre("Elena", "Vieitez", "Perez"));
		Medico medico3 = new Medico("12345", 12345, null);
		medico3.setNombreCompleto(new Nombre("Ramon", "Ramirez", "Ramirez"));

		medicoRepository.save(medico1);
		medicoRepository.save(medico2);
		medicoRepository.save(medico3);

		//CENTROS DE SALUD
		CentroSalud centroSalud1 = new CentroSalud("centroSalud1" , new Direccion("Avenida CentroSalud 1", "Ourense", 32004, "Ourense"),
				"centrosalud1@gmail.com", List.of(medico1));
		CentroSalud centroSalud2 = new CentroSalud("centroSalud2" , new Direccion("Avenida CentroSalud 2", "Ourense", 32004, "Ourense"),
				"centrosalud2@gmail.com", List.of(medico2));
		CentroSalud centroSalud3 = new CentroSalud("centroSalud3" , new Direccion("Avenida CentroSalud 3", "Pontevedra", 32004, "Pontevedra"),
				"centrosalud3@gmail.com", List.of(medico3));
		centroSaludRepository.save(centroSalud1);
		centroSaludRepository.save(centroSalud2);
		centroSaludRepository.save(centroSalud3);

		//ASIGNAR CENTROS DE SALUD A MEDICOS
		medico1.setCentroSalud(centroSalud1);
		medico2.setCentroSalud(centroSalud2);
		medico3.setCentroSalud(centroSalud3);
		medicoRepository.save(medico1);
		medicoRepository.save(medico2);
		medicoRepository.save(medico3);

		//PACIENTES
		Paciente paciente1 = new Paciente("1234567", "123", new Direccion("Avenida Paciente 1", "Ourense", 32004, "Ourense"), 986998877, Date.valueOf("2000-01-01"));
		Paciente paciente2 = new Paciente("7654321", "1234", new Direccion("Avenida Paciente 2", "Ourense", 32004, "Ourense"), 986998877, Date.valueOf("2000-02-02"));
		Paciente paciente3 = new Paciente("246810", "12345", new Direccion("Avenida Paciente 3", "Pontevedra", 36000, "Pontevedra"), 986998877, Date.valueOf("2002-02-02"));
		paciente1.setNombreCompleto(new Nombre("Antonio", "Gonzalez", "Alonso"));
		paciente2.setNombreCompleto(new Nombre("Juan", "Pepito", "Perez"));
		paciente3.setNombreCompleto(new Nombre("Maria", "Gonzalez", "Alonso"));
		paciente1.setMedicoAsignado(medico1);
		paciente2.setMedicoAsignado(medico2);
		paciente3.setMedicoAsignado(medico3);
		pacienteRepository.save(paciente1);
		pacienteRepository.save(paciente2);
		pacienteRepository.save(paciente3);

		//CITAS
		Cita cita1 = new Cita(Date.valueOf("2024-06-01"), Time.valueOf("10:00:00"), paciente1, medico1);
		Cita cita2 = new Cita(Date.valueOf("2024-06-02"), Time.valueOf("10:00:00"), paciente2, medico2);
		citaRepository.save(cita1);
		citaRepository.save(cita2);

		//FARMACIAS
		Farmacia farmacia1 = new Farmacia("farmacia1", "123", new Direccion("Avenida Farmacia 1", "Ourense", 32004, "Ourense"), 986998877);
		Farmacia farmacia2 = new Farmacia("farmacia2", "1234", new Direccion("Avenida Farmacia 2", "Ourense", 32004, "Ourense"), 986998877);
		farmaciaRepository.save(farmacia1);
		farmaciaRepository.save(farmacia2);
	}
}
