package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.repositorios.FarmaciaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmaciaService {
    @Autowired
    private FarmaciaRepository farmaciaRepository;

    public List<Farmacia> getAll() {
        return farmaciaRepository.findAll().stream().filter(Farmacia::getEstado).toList();
    }

    public Optional<Farmacia> findById(Long id){
        Optional<Farmacia> farmacia = farmaciaRepository.findById(id);
        if(farmacia.isPresent() && !farmacia.get().getEstado()){
            return Optional.empty();
        }
        return farmacia;
    }

    public List<Farmacia> findAllByNombreFarmacia(String nombreFarmacia) {
        return farmaciaRepository.findAllByNombreFarmaciaContainingAndEstadoTrue(nombreFarmacia);
    }

    public List<Farmacia> findAllByNumColegiado(String numColegiado) {
        return farmaciaRepository.findAllByNumColegiadoFarmaceuticoContainingAndEstadoTrue(numColegiado);
    }

    public Farmacia update(Farmacia farmacia) {
        return farmaciaRepository.save(farmacia);
    }

     public void delete(Farmacia farmacia){
         farmacia.desactivar();
         farmaciaRepository.save(farmacia);
     }


    public Farmacia create(Farmacia farmacia) {
         farmacia.setPassword(farmacia.getNumColegiadoFarmaceutico());
         return farmaciaRepository.save(farmacia);
    }
}
