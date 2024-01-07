package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    public List<Receta> findAllByPrescripcion_IdPrescripcion(Long idPrescripcion);

    @Query("select r from Receta r where r.prescripcion.idPrescripcion = ?1 and r.estado = ?2 and r.fechaValidezFinal >= CURRENT_DATE")
    public List<Receta> findAllByPrescripcion_IdPrescripcionAndEstado(Long idPrescripcion, TipoEstadoReceta estado);
}
