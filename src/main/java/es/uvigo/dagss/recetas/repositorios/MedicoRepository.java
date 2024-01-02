package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    public List<Medico> findByCentroSalud_IdCentroAndEstadoTrue(Long idCentro);
    public List<Medico> findAllByCentroSaludInAndEstadoTrue(List<CentroSalud> centroSaludList);

    @Query("SELECT m FROM Medico m WHERE m.estado = true AND m.centroSalud.idCentro = ?1")
    public List<Medico> findAllByActivoTrueAndCentroSalud_IdCentro(Long centroSaludId);

    @Query("SELECT m FROM Medico m WHERE m.estado = true and m.nombreCompleto.nombre like concat('%', ?1, '%') or m.nombreCompleto.apellido1 like concat('%', ?1, '%') or m.nombreCompleto.apellido2 like concat('%', ?1, '%')")
    public List<Medico> findByNombreCompletoAndActivo(String nombre);
}
