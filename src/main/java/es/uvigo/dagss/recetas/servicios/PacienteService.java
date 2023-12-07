package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.repositorios.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
}
