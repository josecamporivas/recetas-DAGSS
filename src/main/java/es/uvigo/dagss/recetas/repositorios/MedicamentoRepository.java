package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Medicamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    public List<Medicamento> findAllByNombre(String nombre);
    public List<Medicamento> findAllByPrincipioActivo(String principioActivo);
    public List<Medicamento> findAllByFabricante(String fabricante);
    public List<Medicamento> findAllByFamilia(String familia);
}
