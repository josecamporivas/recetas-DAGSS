package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.repositorios.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

}
