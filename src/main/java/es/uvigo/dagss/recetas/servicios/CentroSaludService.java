package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.repositorios.CentroSaludRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

@Service
public class CentroSaludService {

    @Autowired
    private CentroSaludRepository centroSaludRepository;

    public CentroSalud create(CentroSalud centroSalud) {
        return centroSaludRepository.save(centroSalud);
    }

    public List<CentroSalud> getAll() {
        return centroSaludRepository.findAll().stream().filter(CentroSalud::getEstado).toList();
    }

     public Optional<CentroSalud> findById(Long id) {
        Optional<CentroSalud> centroSalud = centroSaludRepository.findById(id);

        if(centroSalud.isPresent() && !centroSalud.get().getEstado()){
            return Optional.empty();
        }
        return centroSalud;
    }

    public List<CentroSalud> findAllByNombre(String nombre) {
        return centroSaludRepository.findAllByNombreContainingAndEstadoTrue(nombre);

    }

    public List<CentroSalud> findAllByLocalidad(String localidad) {
        return centroSaludRepository.findAllByDireccionLocalidadContainingAndEstadoTrue(localidad);
    }

    public CentroSalud update(CentroSalud centroSalud) {
        return centroSaludRepository.save(centroSalud);
    }

     public void delete(CentroSalud centroSalud) {
        centroSalud.setEstado(false);
        centroSaludRepository.save(centroSalud);
    }
}
