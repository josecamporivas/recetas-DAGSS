package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoReceta;
import es.uvigo.dagss.recetas.repositorios.PacienteRepository;
import es.uvigo.dagss.recetas.repositorios.PrescripcionRepository;
import es.uvigo.dagss.recetas.repositorios.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescripcionService {

    @Autowired
    private PrescripcionRepository prescripcionRepository;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Prescripcion> getAll(){
        return prescripcionRepository.findAll().stream().filter(Prescripcion::isEstado).toList();
    }

    public Optional<Prescripcion> findById(Long id){
        Optional<Prescripcion> prescripcionOptional = prescripcionRepository.findById(id);
        if(prescripcionOptional.isPresent() && !prescripcionOptional.get().isEstado()){
            return Optional.empty();
        }
        return prescripcionOptional;
    }

    public Prescripcion create(Prescripcion prescripcion){
        return prescripcionRepository.save(prescripcion);
    }

    public Prescripcion update(Prescripcion prescripcion){
        return prescripcionRepository.save(prescripcion);
    }

    public List<Prescripcion> findAllByPacienteAndActiva(Long idPaciente){
        Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
        if(paciente.isEmpty()){
            throw new RuntimeException("No existe el paciente con id " + idPaciente);
        }
        return prescripcionRepository.findAllByPacienteAndEnVigor(idPaciente);
    }

    public void delete(Prescripcion prescripcion){
        List<Receta> recetaList = recetaRepository.findAllByPrescripcion_IdPrescripcion(prescripcion.getIdPrescripcion());
        for(Receta r: recetaList){
            r.setEstado(TipoEstadoReceta.ANULADA);
            recetaRepository.save(r);
        }

        prescripcion.setEstado(false);
        prescripcionRepository.save(prescripcion);
    }
}
