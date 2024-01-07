package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.repositorios.MedicoRepository;
import es.uvigo.dagss.recetas.repositorios.PacienteRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;


     public List<Paciente> getAll(){
        return pacienteRepository.findAll().stream().filter(Paciente::getEstado).toList();
     }

     public Optional<Paciente> findById(Long id){
         Optional<Paciente> paciente = pacienteRepository.findById(id);

         if(paciente.isPresent() && !paciente.get().getEstado()){
             return Optional.empty();
         }

         return paciente;
     }

    public List<Paciente> findAllByNombreCompleto(String nombre) {
        return pacienteRepository.findByNombreCompletoAndActivo(nombre);
    }

    public List<Paciente> findAllByLocalidad(String localidad) {
        return pacienteRepository.findByDireccionLocalidadContainingAndEstadoTrue(localidad);
    }

    public List<Paciente> findAllByCentroSalud(Long centroSaludId) {
        List<Medico> medicoList = medicoRepository.findAllByActivoTrueAndCentroSalud_IdCentro(centroSaludId);

        return pacienteRepository.findAllByMedicoAsignadoInAndEstadoTrue(medicoList);
    }

    public List<Paciente> findAllByMedico(Long medicoId) {
        return pacienteRepository.findByMedicoAsignadoIdAndEstadoTrue(medicoId);
    }

    public Paciente update(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void delete(Paciente paciente) {
        paciente.desactivar();
        pacienteRepository.save(paciente);
    }

    public Paciente create(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
}
