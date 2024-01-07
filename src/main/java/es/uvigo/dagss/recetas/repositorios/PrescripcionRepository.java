package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescripcionRepository extends JpaRepository<Prescripcion, Long> {

    @Query("select p from Prescripcion p where p.paciente.id = :pacienteId and p.fechaFinal >= CURRENT_DATE and p.estado order by p.fechaInicio")
    public List<Prescripcion> findAllByPacienteAndEnVigor(@Param("pacienteId") Long pacienteId);
}
