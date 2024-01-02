package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CitaRepository extends JpaRepository<Cita, Long> {
   
    public List<Cita> findAllByPaciente(Paciente paciente);
    public List<Cita> findAllByMedico(Medico medico);

    /*  WIP: no se si estará bien */
    @Query("select c from Cita c where c.medico = :medico and c.fecha = CURRENT_DATE")
    public List<Cita> findAllByMedicoAndFechaToday(@Param("medico") Medico medico);

    public List<Cita> findAllByMedico_IdAndFechaAndHoraBetweenAndEstado(Long medico, Date fecha,
                                                                     Time horaInicio, Time horaFin,
                                                                     TipoEstadoCita estadoCita);
}
