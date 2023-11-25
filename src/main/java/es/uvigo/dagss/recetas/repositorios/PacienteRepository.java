package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public Paciente getByLoginAndPassword(String login, String password);
}
