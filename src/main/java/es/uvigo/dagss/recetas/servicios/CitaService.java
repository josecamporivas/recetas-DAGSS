package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import es.uvigo.dagss.recetas.repositorios.MedicoRepository;
import es.uvigo.dagss.recetas.repositorios.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoCita;
import es.uvigo.dagss.recetas.repositorios.CitaRepository;
import org.springframework.stereotype.Service;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getAll() {
        return citaRepository.findAll();
    }

    public Optional<Cita> findById(Long id) {
        return citaRepository.findById(id);
    }

    public List<Cita> findByPaciente(Paciente paciente) {
        return citaRepository.findAllByPaciente(paciente);
    }

    public List<Cita> findAllByMedico(Medico medico) {
        return citaRepository.findAllByMedico(medico);
    }

    public void setAnulada(Cita cita) {
        cita.setEstado(TipoEstadoCita.ANULADA);
        citaRepository.save(cita);
    }

    public Cita create(Cita cita) {
        return citaRepository.save(cita);
    }

    public List<Cita> findAllByMedicoForToday(Medico medico){
        return citaRepository.findAllByMedicoAndFechaToday(medico);
    }

    public Cita update(Cita cita){
        return citaRepository.save(cita);
    }

}
