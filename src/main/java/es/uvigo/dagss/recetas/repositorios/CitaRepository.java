package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
   
    public List<Cita> findByPaciente(Paciente paciente);
    public List<Cita> findByMedico(Medico medico);

}
