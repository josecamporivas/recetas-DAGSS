package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.repositorios.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmaciaService {
    @Autowired
    private FarmaciaRepository farmaciaRepository;
}
