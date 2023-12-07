package es.uvigo.dagss.recetas.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoCita;
import es.uvigo.dagss.recetas.repositorios.CitaRepository;

public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    /*
     * Se mostrará una lista ordenada por hora de inicio con las citas actualmente
     * registradas, indicando su datos esenciales (paciente, médico, centro de
     * salud, fecha y hora, estado).
     */

    public List<Cita> getAll() {
        return citaRepository.findAll();
    }

    /*
     * La lista de citas se deberá limitar por fecha (indicando el día sobre el que
     * se realizará la búsqueda) y podrá filtrarse por médico o por paciente
     * (seleccionándose ambos de una lista).
     * Entiendo que se refiere a que filtras x médico o paciente
     */

    public List<Cita> buscarCitaPaciente(Paciente paciente) {
        return citaRepository.findByPaciente(paciente);
    }

    public List<Cita> buscarCitaMedico(Medico medico) {
        return citaRepository.findByMedico(medico);
    }

    public Cita anular(Cita cita) {
        cita.setEstado(TipoEstadoCita.ANULADA);
        return citaRepository.save(cita);
    }

    /*
     * No se contempla, de momento, la creación manual de citas por parte de los
     * administradores.
     * No x los admins pero sí x otro así q lo creo ya
     */

    public Cita crear(Cita cita) {
        return citaRepository.save(cita);
    }
}
