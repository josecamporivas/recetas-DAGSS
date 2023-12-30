package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Farmacia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
    public Farmacia getByLoginAndPassword(String login, String password);
    public List<Farmacia> findAllByNombreFarmaciaContainingAndActivoTrue(String nombreFarmacia);
    public List<Farmacia> findAllByNumColegiadoFarmaceuticoContainingAndActivoTrue(String numColegiadosFarmaceuticos);
}
