package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.uvigo.dagss.recetas.entidades.Medicamento;
import es.uvigo.dagss.recetas.repositorios.MedicamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public List<Medicamento> getAll() {
        return medicamentoRepository.findAll().stream().filter(Medicamento::getEstado).toList();
    }

    public Optional<Medicamento> findById(Long id){
        Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
        if(medicamento.isPresent() && !medicamento.get().getEstado()){
            return Optional.empty();
        }
        return medicamento;
    }

    public List<Medicamento> findAllByNombre(String nombre) {
        return medicamentoRepository.findAllByNombreContainingAndEstadoTrue(nombre);
    }

    public List<Medicamento> findAllByPrincipioActivo(String principioActivo) {
        return medicamentoRepository.findAllByPrincipioActivoContainingAndEstadoTrue(principioActivo);
    }

    public List<Medicamento> findAllByFabricante(String fabricante) {
        return medicamentoRepository.findAllByFabricanteContainingAndEstadoTrue(fabricante);
    }

    public List<Medicamento> findAllByFamilia(String familia) {
        return medicamentoRepository.findAllByFamiliaContainingAndEstadoTrue(familia);
    }

    public Medicamento update(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public void delete(Medicamento medicamento) {
        medicamento.setEstado(false);
        medicamentoRepository.save(medicamento);
    }

     public Medicamento create(Medicamento medicamento){
        return medicamentoRepository.save(medicamento);
     }
}
