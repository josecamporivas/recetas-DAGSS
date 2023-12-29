package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoCita;
import es.uvigo.dagss.recetas.repositorios.CentroSaludRepository;
import es.uvigo.dagss.recetas.repositorios.CitaRepository;
import es.uvigo.dagss.recetas.repositorios.MedicoRepository;

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
        return medicoRepository.findAll();

    }

    public Optional<Medico> findById(Long id) {
        return medicoRepository.findById(id);
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

    public List<Medico> findAllByNombre(Nombre nombre) {
        return medicoRepository.findByNombreCompleto(nombre);
    }

    public List<Medico> findByNombreCompletoAndCentroSalud(Nombre nombre, CentroSalud centroSalud) {
        return medicoRepository.findByNombreCompletoAndCentroSalud(nombre, centroSalud);
    }

    /*
     * NO LOGRO ENTENDER CÓMO REALIZAR LA BÚSQUEDA UTILIZANDO UN CAMPO DE UN OBJETO
     * AL QUE ESTÁ RELACIONADO WIP.
     */
    public List<Medico> findByDireccionLocalidad(String localidad) {
        List<CentroSalud> centroSaludList = centroSaludRepository.findAllByDireccionLocalidadContaining(localidad);

        return medicoRepository.findAllByCentroSaludIn(centroSaludList);
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
        return medicoRepository.save(medico);
    }

    /* WIP: metodo con muchas probabilidades de no funcionar */
    public List<LocalDateTime> findFreeSpaceSchedule(Medico medico, LocalDateTime day) {
        LocalDateTime inicio = day.withHour(8).withMinute(30).withSecond(0).withNano(0);
        LocalDateTime fin = day.withHour(15).withMinute(30).withSecond(0).withNano(0);

        List<Cita> citas = citaRepository.findAllByMedicoAndFechaAndHoraBetweenAndEstado(medico, day, inicio, fin,
                TipoEstadoCita.PLANIFICADA);

        List<LocalDateTime> huecosDisponibles = new ArrayList<>();

        LocalDateTime actual = inicio;
        while (actual.isBefore(fin)) {
            boolean hayCita = false;
            for (Cita cita : citas) {
                if (cita.getFecha().equals(actual)) {
                    hayCita = true;
                    break;
                }
            }

            if (!hayCita) {
                huecosDisponibles.add(actual);
            }

            actual = actual.plusMinutes(15);
        }

        return huecosDisponibles;
    }

}
