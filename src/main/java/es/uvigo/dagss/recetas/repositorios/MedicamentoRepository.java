package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Medicamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    public List<Medicamento> findAllByNombreContainingAndEstadoTrue(String nombre);
    public List<Medicamento> findAllByPrincipioActivoContainingAndEstadoTrue(String principioActivo);
    public List<Medicamento> findAllByFabricanteContainingAndEstadoTrue(String fabricante);
    public List<Medicamento> findAllByFamiliaContainingAndEstadoTrue(String familia);
}
