package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmaciaRepository extends JpaRepository<FarmaciaRepository, Long> {
    public Farmacia getByLoginAndPassword(String login, String password);
}
