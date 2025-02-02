package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.CentroSalud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CentroSaludRepository extends JpaRepository<CentroSalud, Long> {
    public List<CentroSalud> findAllByNombreContainingAndEstadoTrue(String nombre);

    public List<CentroSalud> findAllByDireccionLocalidadContainingAndEstadoTrue(String localidad);
}
