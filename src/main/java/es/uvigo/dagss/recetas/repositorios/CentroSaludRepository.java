package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentroSaludRepository extends JpaRepository<CentroSalud, Long> {
}
