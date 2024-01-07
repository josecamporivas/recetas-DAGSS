package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.controladores.excepciones.ResourceNotFoundException;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoReceta;
import es.uvigo.dagss.recetas.repositorios.PacienteRepository;
import es.uvigo.dagss.recetas.repositorios.PrescripcionRepository;
import es.uvigo.dagss.recetas.repositorios.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private PrescripcionRepository prescripcionRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Receta> getAll(){
        return recetaRepository.findAll().stream().filter(r -> r.getEstado() == TipoEstadoReceta.PLANIFICADA || r.getEstado() == TipoEstadoReceta.SERVIDA).toList();
    }

    public Optional<Receta> findById(Long id){
        Optional<Receta> receta = recetaRepository.findById(id);
        if(receta.isPresent() && receta.get().getEstado() == TipoEstadoReceta.ANULADA){
            return Optional.empty();
        }
        return receta;
    }

    public List<Receta> findAllByPacienteAndEstadoPlanificada(Long idPaciente){
        Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
        if(paciente.isEmpty()){
            throw new ResourceNotFoundException("No existe el paciente con id " + idPaciente);
        }

        return getRecetasDisponiblesByPacienteId(idPaciente);
    }

    public List<Receta> findAllByPacienteNumTarjeta(String numTarjeta){
        Optional<Paciente> paciente = pacienteRepository.findByNumTarjetaSanitaria(numTarjeta);
        if(paciente.isEmpty()){
            throw new ResourceNotFoundException("No existe el paciente con numTarjeta " + numTarjeta);
        }
        return getRecetasDisponiblesByPacienteId(paciente.get().getId());
    }

    private List<Receta> getRecetasDisponiblesByPacienteId(Long pacienteId){
        List<Prescripcion> prescripcionList = prescripcionRepository.findAllByPacienteAndEnVigor(pacienteId);

        List<Receta> recetaPlanificadaList = new ArrayList<>();

        for(Prescripcion prescripcion: prescripcionList){
            List<Receta> recetasPrescripcionPaciente = recetaRepository.findAllByPrescripcion_IdPrescripcionAndEstado(prescripcion.getIdPrescripcion(), TipoEstadoReceta.PLANIFICADA);
            recetaPlanificadaList.addAll(recetasPrescripcionPaciente);
        }

        return recetaPlanificadaList.stream().sorted(Comparator.comparing(Receta::getFechaValidezInicial)).toList();
    }

    public Receta create(Receta receta){
        return recetaRepository.save(receta);
    }

    public Receta update(Receta receta){
        return recetaRepository.save(receta);
    }

    public void delete(Long id){
        recetaRepository.deleteById(id);
    }
}
