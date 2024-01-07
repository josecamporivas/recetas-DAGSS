package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.repositorios.AdministradorRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> getAll() {
        return administradorRepository.findAll().stream().filter(Administrador::getEstado).toList();
    }

    public Optional<Administrador> findById(Long id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);

        if(administrador.isPresent() && !administrador.get().getEstado()){
            return Optional.empty();
        }
        return administrador;
    }

    public Administrador create(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public Administrador update(Administrador administrador) {
        return administradorRepository.save(administrador);

    }

    public void delete(Administrador administrador) {
        administrador.desactivar();
        administradorRepository.save(administrador);
    }

}
