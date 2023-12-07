package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Medicamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    public List<Medicamento> findByNombre(String nombre);
    public List<Medicamento> findByPrincipioActivo(String principioActivo);
    public List<Medicamento> findByFabricante(String fabricante);
    public List<Medicamento> findByFamilia(String familia);
}
