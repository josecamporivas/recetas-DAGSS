package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Farmacia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {

    public List<Farmacia> findAllByNombreFarmaciaContainingAndEstadoTrue(String nombreFarmacia);
    public List<Farmacia> findAllByNumColegiadoFarmaceuticoContainingAndEstadoTrue(String numColegiadosFarmaceuticos);
}
