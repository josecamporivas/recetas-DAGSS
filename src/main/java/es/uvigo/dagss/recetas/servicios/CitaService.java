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

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;
    /*
     * Se mostrará una lista ordenada por hora de inicio con las citas actualmente
     * registradas, indicando su datos esenciales (paciente, médico, centro de
     * salud, fecha y hora, estado).
     */
    public List<Cita> getAll() {
        return citaRepository.findAll();
    }

     public Optional<Cita> findById(Long id) {
        return citaRepository.findById(id);
    }

    /*
     * La lista de citas se deberá limitar por fecha (indicando el día sobre el que
     * se realizará la búsqueda) y podrá filtrarse por médico o por paciente
     * (seleccionándose ambos de una lista).
     * Entiendo que se refiere a que filtras x médico o paciente
     */
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

    public void setCompletada(Cita cita){
        cita.setEstado(TipoEstadoCita.COMPLETADA);
        citaRepository.save(cita);
    }

    /*
     * No se contempla, de momento, la creación manual de citas por parte de los
     * administradores.
     * No x los admins pero sí x otro así q lo creo ya
     *
     * Los pacientes son los crean las citas, las citas tienen que tener asociadas el medico del paciente
     */
    public Cita create(Cita cita) {
        return citaRepository.save(cita);
    }

    /* WIP: no estoy seguro de como buscar por fecha de hoy  */
    public List<Cita> findAllByMedicoForToday(Medico medico){
        return citaRepository.findAllByMedicoAndFechaToday(medico);
    }

    public Cita update(Cita cita){
        return citaRepository.save(cita);
    }

}
