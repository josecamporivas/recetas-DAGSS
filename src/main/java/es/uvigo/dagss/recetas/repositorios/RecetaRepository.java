package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoReceta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    public List<Receta> findAllByPrescripcion(Prescripcion prescripcion);
    public List<Receta> findAllByPrescripcionAndEstado(Prescripcion prescripcion, TipoEstadoReceta estadoReceta);
}
