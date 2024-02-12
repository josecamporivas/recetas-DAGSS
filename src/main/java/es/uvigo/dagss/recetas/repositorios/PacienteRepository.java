package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public Optional<Paciente> findByNumTarjetaSanitaria(String numTarjetaSanitaria);
    public List<Paciente> findByDireccionLocalidadContainingAndEstadoTrue(String localidad);
    public List<Paciente> findByMedicoAsignadoIdAndEstadoTrue(Long medicoAsignadoId);
    public List<Paciente> findAllByMedicoAsignadoInAndEstadoTrue(List<Medico> medicos);

    @Query("SELECT p FROM Paciente p WHERE p.estado = true and p.nombreCompleto.nombre like concat('%', ?1, '%') or p.nombreCompleto.apellido1 like concat('%', ?1, '%') or p.nombreCompleto.apellido2 like concat('%', ?1, '%')")
    public List<Paciente> findByNombreCompletoAndActivo(String nombre);
}
