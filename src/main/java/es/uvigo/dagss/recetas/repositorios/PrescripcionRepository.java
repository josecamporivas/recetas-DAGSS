package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescripcionRepository extends JpaRepository<Prescripcion, Long> {
}
