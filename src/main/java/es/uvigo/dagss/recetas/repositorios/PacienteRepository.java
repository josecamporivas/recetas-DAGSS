package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public Paciente getByLoginAndPassword(String login, String password);
    public List<Paciente> findByDireccionLocalidad(String localidad);
    public List<Paciente> findByMedicoAsignadoId(Long medicoAsignadoId);
    public List<Paciente> findAllByMedicoAsignadoIn(List<Medico> medicos);

    //TODO: no se si funcionará
    @Query("SELECT p FROM Paciente p WHERE p.nombreCompleto.nombre like ?1 or p.nombreCompleto.apellido1 like ?1 or p.nombreCompleto.apellido2 like ?1")
    public List<Paciente> findByNombreCompleto(String nombre);
}
