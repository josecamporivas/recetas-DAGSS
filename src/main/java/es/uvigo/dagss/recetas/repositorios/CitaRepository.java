package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
}
