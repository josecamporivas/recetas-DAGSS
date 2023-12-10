package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescripcionRepository extends JpaRepository<Prescripcion, Long> {

    @Query("select p from Prescripcion p where p.paciente = :paciente and p.fechaFinal >= CURRENT_DATE order by p.fechaInicio")
    public List<Prescripcion> findAllByPacienteAndEnVigor(@Param("paciente") Paciente paciente);

    public List<Prescripcion> findAllByPacienteAndEstado(Paciente paciente, boolean estado);
}
