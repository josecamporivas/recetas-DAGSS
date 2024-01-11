package es.uvigo.dagss.recetas;

import es.uvigo.dagss.recetas.entidades.*;
import es.uvigo.dagss.recetas.entidades.tipos.Direccion;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoReceta;
import es.uvigo.dagss.recetas.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.sql.Time;
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

	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@Autowired
	private PrescripcionRepository prescripcionRepository;

	@Autowired
	private RecetaRepository recetaRepository;


	public static void main(String[] args) {
		SpringApplication.run(RecetasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		crearDatosEjemplo();
	}

	private void crearDatosEjemplo() {

		//ADMINISTRADORES
		Administrador admin = new Administrador("admin1", "purple", "63922740J",
				new Nombre("Esteban Administrador", "Vazquez", "Veiga"), "admin@gmail.com");
		administradorRepository.save(admin);

		//MEDICOS
		Medico medico1 = new Medico("132064550", 764151631, null, "93541458K",
				new Nombre("Carlos", "Fernandez", "Amil"), "carlos@gmail.com", "carlos", "carlos");
		Medico medico2 = new Medico("543506769", 665241085, null, "52222238W",
				new Nombre("Elena", "Vieitez", "Perez"), "elena@gmail.com", "elena", "elena");
		Medico medico3 = new Medico("121121692", 687111852, null, "73245421N",
				new Nombre("Ramon", "Ramirez", "Ramirez"), "ramon@gmail.com", "ramon", "ramon");
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
		Paciente paciente1 = new Paciente("4564856001", "522-30-9316",
				new Direccion("Avenida Paciente 1", "Ourense", 32004, "Ourense"),
				646043360, Date.valueOf("2000-01-01"), "77753516S", new Nombre("Antonio", "Gonzalez", "Alonso"),
				"antonio@gmail.com", "antonio", "antonio");
		Paciente paciente2 = new Paciente("9011527506", "362-92-0949",
				new Direccion("Avenida Paciente 2", "Ourense", 32004, "Ourense"),
				623356150, Date.valueOf("2000-02-02"), "50146756V", new Nombre("Juan", "Pepito", "Perez"),
				"juan@gmail.com", "juan", "juan");
		Paciente paciente3 = new Paciente("6495080788", "318-24-3132",
				new Direccion("Avenida Paciente 3", "Pontevedra", 36000, "Pontevedra"),
				684205539, Date.valueOf("2002-02-02"), "24608716G", new Nombre("Maria", "Gonzalez", "Alonso"),
				"maria@gmail.com", "maria", "maria");
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
		Farmacia farmacia1 = new Farmacia("farmacia Carla", "364125564",
				new Direccion("Avenida Farmacia 1", "Ourense", 32004, "Ourense"),
				785044303, "29228938D", new Nombre("Carla", "Alcalde", "Piñeiro"),
				"carla@gmail.com", "carla", "carla");
		Farmacia farmacia2 = new Farmacia("farmacia Ivan", "807587068",
				new Direccion("Avenida Farmacia 2", "Ourense", 32004, "Ourense"),
				620864143, "62674636G", new Nombre("Ivan", "Esteban", "Dominguez"),
				"ivan@gmail.com", "ivan", "ivan");
		farmaciaRepository.save(farmacia1);
		farmaciaRepository.save(farmacia2);

		//MEDICAMENTOS
		Medicamento medicamento1 = new Medicamento("Paracetamol", "Paracetamol", "Galenicum", "Analgesicos", 20);
		Medicamento medicamento2 = new Medicamento("Tresiba", "Insulina humana", "Novo Nordisk", "Hormonas", 40);
		medicamentoRepository.save(medicamento1);
		medicamentoRepository.save(medicamento2);

		//PRESCRIPCIONES
		Prescripcion prescripcion1 = new Prescripcion(0.5, "Tomar despues de comer", Date.valueOf("2030-01-01"));
		prescripcion1.setMedico(medico1);
		prescripcion1.setPaciente(paciente1);
		prescripcion1.setMedicamento(medicamento1);
		Prescripcion prescripcion2 = new Prescripcion(3d, "No excederse de la cantidad sugerida", Date.valueOf("2030-01-01"));
		prescripcion2.setMedico(medico2);
		prescripcion2.setPaciente(paciente2);
		prescripcion2.setMedicamento(medicamento2);
		prescripcionRepository.save(prescripcion1);
		prescripcionRepository.save(prescripcion2);

		//RECETAS
		Receta receta1 = new Receta(Date.valueOf("2025-01-01"), Date.valueOf("2025-01-05"), 5);
		receta1.setPrescripcion(prescripcion1);
		Receta receta2 = new Receta(Date.valueOf("2024-01-01"), Date.valueOf("2024-01-05"), 3);
		receta2.setPrescripcion(prescripcion2);
		receta2.setEstado(TipoEstadoReceta.SERVIDA);
		receta2.setFarmacia(farmacia1);
		recetaRepository.save(receta1);
		recetaRepository.save(receta2);
	}
}
