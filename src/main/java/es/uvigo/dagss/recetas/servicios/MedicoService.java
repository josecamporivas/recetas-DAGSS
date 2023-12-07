package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;
import es.uvigo.dagss.recetas.repositorios.MedicoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    /*
     * Se mostrará una lista con los médicos actualmente registrados, indicando su
     * datos esenciales (nombre y apelidos, centro de salud, localidad, provincia,
     * activo [true|false]).
     */

    public List<Medico> getAll() {
        return medicoRepository.findAll();

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

    public List<Medico> buscarMedicosNombre(Nombre nombre) {
        return medicoRepository.findByNombreCompleto(nombre);
    }

    public List<Medico> buscarMedicosNombre(Nombre nombre, CentroSalud centroSalud) {
        return medicoRepository.findByNombreCompletoAndCentroSalud(nombre, centroSalud);
    }
    /*
     * NO LOGRO ENTENDER CÓMO REALIZAR LA BÚSQUEDA UTILIZANDO UN CAMPO DE UN OBJETO
     * AL QUE ESTÁ RELACIONADO WIP.
     */
    /*
     * public List<Medico> buscarMedicosLocalidad(String nombre) {
     * return medicoRepository.findByNombreCompleto(nombre);
     * }
     * public List<Medico> buscarMedicosLocalidad(Nombre nombre,CentroSalud
     * centroSalud) {
     * return
     * medicoRepository.findByNombreCompletoAndCentroSalud(nombre,centroSalud);
     * }
     */

    /*
     * Se podrá seleccionar un médico de esa lista y mediante un botón Editar
     * acceder a la edición de los datos del médico seleccionado, incluyendo la
     * modificación del centro de salud al que está asignado. Una vez completada esa
     * edición se actualizará la lista de médicos.
     * Entiendo que la parte de modificar el centro de salud será de la parte visual
     * no del servicio
     */

    public Medico modificar(Medico medico) {
        return medicoRepository.save(medico);
    }

    /*
     * Se podrá seleccionar un médico de esa lista y mediante un botón Borrar
     * realizar la eliminación lógica de ese usuario en la aplicación, establiendo
     * el valor de activo a false. Una vez completada esa edición se actualizará la
     * lista de médicos.
     */

    public void eliminar(Medico medico) {
        medico.desactivar();
        medicoRepository.save(medico);
    }

    /*
     * Mediante un botón Nuevo se accederá a la creación de un nuevo médico,
     * vinculándolo a un centro de salud y asignándosele como password inicial su
     * número de colegiado. Una vez completada esa edición se actualizará la lista
     * de médicos.
     */

    public Medico crear(Medico medico) {
        return medicoRepository.save(medico);
    }
}
