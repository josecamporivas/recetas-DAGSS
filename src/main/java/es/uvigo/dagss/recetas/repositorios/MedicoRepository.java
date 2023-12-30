package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    public Medico getByLoginAndPassword(String login, String password);
    public Medico findByNumColegiado(String numColegiado);
    public List<Medico> findByNombreCompleto(Nombre nombreCompleto);
    public List<Medico> findByNombreCompletoAndCentroSalud(Nombre nombreCompleto,CentroSalud centroSalud);

    public List<Medico> findAllByCentroSaludIn(List<CentroSalud> centroSaludList);
    public List<Medico> findAllByCentroSalud_IdCentroAndActivoTrue(Long centroSaludId);
}
