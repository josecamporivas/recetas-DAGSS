package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    public Medico getByLoginAndPassword(String login, String password);
    public Medico findByNumColegiado(String numColegiado);
    public List<Medico> findByNombreCompleto(Nombre nombreCompleto);
    public List<Medico> findByNombreCompletoAndCentroSalud(Nombre nombreCompleto,CentroSalud centroSalud);

    public List<Medico> findAllByCentroSaludIn(List<CentroSalud> centroSaludList);

    @Query("SELECT m FROM Medico m WHERE m.estado = true AND m.centroSalud.idCentro = ?1")
    public List<Medico> findAllByActivoTrueAndCentroSalud_IdCentro(Long centroSaludId);
}
