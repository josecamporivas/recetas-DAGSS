package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoReceta;
import es.uvigo.dagss.recetas.repositorios.PrescripcionRepository;
import es.uvigo.dagss.recetas.repositorios.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescripcionService {

    @Autowired
    private PrescripcionRepository prescripcionRepository;

    @Autowired
    private RecetaRepository recetaRepository;

    public Prescripcion create(Prescripcion prescripcion){
        return prescripcionRepository.save(prescripcion);
    }

    public void delete(Prescripcion prescripcion){
        prescripcionRepository.delete(prescripcion);
    }

    public List<Prescripcion> findAllByPacienteAndActiva(Paciente paciente){
        return prescripcionRepository.findAllByPacienteAndEnVigor(paciente);
    }

    public Prescripcion setAnulada(Prescripcion prescripcion){
        List<Receta> recetaList = recetaRepository.findAllByPrescripcion(prescripcion);
        for(Receta r: recetaList){
            r.setEstado(TipoEstadoReceta.ANULADA);
            recetaRepository.save(r);
        }

        prescripcion.setEstado(false);
        return prescripcionRepository.save(prescripcion);
    }
}
