package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.controladores.excepciones.ResourceNotFoundException;
import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoCita;
import es.uvigo.dagss.recetas.repositorios.CentroSaludRepository;
import es.uvigo.dagss.recetas.repositorios.CitaRepository;
import es.uvigo.dagss.recetas.repositorios.MedicoRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private CentroSaludRepository centroSaludRepository;

    @Autowired
    private CitaRepository citaRepository;

    public List<Medico> getAll() {
        return medicoRepository.findAll().stream().filter(Medico::getEstado).toList();
    }

    public Optional<Medico> findById(Long id) {
        Optional<Medico> medico = medicoRepository.findById(id);

        if(medico.isPresent() && !medico.get().getEstado()){
            return Optional.empty();
        }

        return medico;
    }

    public List<Medico> findAllByNombre(String nombre) {
        return medicoRepository.findByNombreCompletoAndActivo(nombre);
    }

    public List<Medico> findAllByCentroSalud(Long centroSalud) {
        return medicoRepository.findByCentroSalud_IdCentroAndEstadoTrue(centroSalud);
    }

    public List<Medico> findByDireccionLocalidad(String localidad) {
        List<CentroSalud> centroSaludList = centroSaludRepository.findAllByDireccionLocalidadContainingAndEstadoTrue(localidad);

        return medicoRepository.findAllByCentroSaludInAndEstadoTrue(centroSaludList);
    }

    public Medico update(Medico medico) {
        return medicoRepository.save(medico);
    }

    public void delete(Medico medico) {
        medico.desactivar();
        medicoRepository.save(medico);
    }

    public Medico create(Medico medico) {
        medico.setPassword(medico.getNumColegiado());
        return medicoRepository.save(medico);
    }

    public List<Time> findFreeSpaceSchedule(Long idMedico, Date day) {
        Optional<Medico> medico = medicoRepository.findById(idMedico);
        if(medico.isEmpty()){
            throw new ResourceNotFoundException("No existe el medico con id " + idMedico);
        }
        LocalDateTime inicio = day.toLocalDate().atTime(8,30);
        LocalDateTime fin = day.toLocalDate().atTime(15,30);

        List<Cita> citas = citaRepository.findAllByMedico_IdAndFechaAndHoraBetweenAndEstado(idMedico, day,
                            Time.valueOf("8:30:00"), Time.valueOf("15:30:00"), TipoEstadoCita.PLANIFICADA);

        return getFreeSpaces(inicio, fin, citas);
    }

    private static List<Time> getFreeSpaces(LocalDateTime inicio, LocalDateTime fin, List<Cita> citas) {
        List<Time> huecosDisponibles = new ArrayList<>();

        LocalDateTime actual = inicio;
        while (actual.isBefore(fin)) {
            boolean hayCita = false;
            for (Cita cita : citas) {
                if (cita.getHora().toLocalTime().equals(actual.toLocalTime())) {
                    hayCita = true;
                    break;
                }
            }

            if (!hayCita) {
                huecosDisponibles.add(Time.valueOf(actual.toLocalTime()));
            }

            actual = actual.plusMinutes(15);
        }
        return huecosDisponibles;
    }

}
