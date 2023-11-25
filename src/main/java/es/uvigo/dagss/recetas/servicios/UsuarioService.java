package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Usuario;
import es.uvigo.dagss.recetas.repositorios.AdministradorRepository;
import es.uvigo.dagss.recetas.repositorios.FarmaciaRepository;
import es.uvigo.dagss.recetas.repositorios.MedicoRepository;
import es.uvigo.dagss.recetas.repositorios.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    public Usuario login(String login, String password){
        Usuario user = medicoRepository.getByLoginAndPassword(login, password);
        if(user != null){
            return user;
        }

        user = pacienteRepository.getByLoginAndPassword(login, password);
        if(user != null){
            return user;
        }

        user = farmaciaRepository.getByLoginAndPassword(login, password);
        if(user != null){
            return user;
        }

        user = administradorRepository.getByLoginAndPassword(login, password);
        if(user != null){
            return user;
        }

        return null;
    }

    public Administrador insertAdministrador(Administrador admin){
        return administradorRepository.save(admin);
    }

    public void deleteAdministradorById(Long id){
        administradorRepository.deleteById(id);
    }


}
