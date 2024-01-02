package es.uvigo.dagss.recetas.servicios;

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

    /*
     * Se mostrará una lista con los médicos actualmente registrados, indicando su
     * datos esenciales (nombre y apelidos, centro de salud, localidad, provincia,
     * activo [true|false]).
     */

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
    /*
     * La lista de médicos podrá filtrarse por nombre o por localidad, permitiéndose
     * en todos estos casos búsquedas aproximadas (tipo LIKE en SQL).
     * Esta lista también podrá filtrarse por centro de salud asignado,
     * seleccionando un centro de salud de una lista desplegable con todos los
     * centros registrados.
     * Para que puedan realizarse por centro de salud o no esto lo hago utilizando
     * el mismo método, siendo la misma llamada para ambas
     */

    public List<Medico> findAllByNombre(String nombre) {
        return medicoRepository.findByNombreCompletoAndActivo(nombre);
    }

    public List<Medico> findAllByCentroSalud(Long centroSalud) {
        return medicoRepository.findByCentroSalud_IdCentroAndEstadoTrue(centroSalud);
    }

    /*
     * NO LOGRO ENTENDER CÓMO REALIZAR LA BÚSQUEDA UTILIZANDO UN CAMPO DE UN OBJETO
     * AL QUE ESTÁ RELACIONADO WIP.
     */
    public List<Medico> findByDireccionLocalidad(String localidad) {
        List<CentroSalud> centroSaludList = centroSaludRepository.findAllByDireccionLocalidadContainingAndEstadoTrue(localidad);

        return medicoRepository.findAllByCentroSaludInAndEstadoTrue(centroSaludList);
    }

    /*
     * Se podrá seleccionar un médico de esa lista y mediante un botón Editar
     * acceder a la edición de los datos del médico seleccionado, incluyendo la
     * modificación del centro de salud al que está asignado. Una vez completada esa
     * edición se actualizará la lista de médicos.
     * Entiendo que la parte de modificar el centro de salud será de la parte visual
     * no del servicio
     */

    /*
     * un medico podrá modificar sus propios datos, menos las citas de su agenda y
     * su centro de salud [HU-M10]
     */

    public Medico update(Medico medico) {
        return medicoRepository.save(medico);
    }

    /*
     * Se podrá seleccionar un médico de esa lista y mediante un botón Borrar
     * realizar la eliminación lógica de ese usuario en la aplicación, establiendo
     * el valor de activo a false. Una vez completada esa edición se actualizará la
     * lista de médicos.
     */

    public void delete(Medico medico) {
        medico.desactivar();
        medicoRepository.save(medico);
    }

    /*
     * Mediante un botón Nuevo se accederá a la creación de un nuevo médico,
     * vinculándolo a un centro de salud y asignándosele como password inicial su
     * número de colegiado. Una vez completada esa edición se actualizará la lista
     * de médicos.
     */

    public Medico create(Medico medico) {
        medico.setPassword(medico.getNumColegiado());
        return medicoRepository.save(medico);
    }

    /* WIP: metodo con muchas probabilidades de no funcionar */
    public List<Time> findFreeSpaceSchedule(Long idMedico, Date day) {
        Optional<Medico> medico = medicoRepository.findById(idMedico);
        if(medico.isEmpty()){
            throw new RuntimeException("No existe el medico con id " + idMedico);
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
