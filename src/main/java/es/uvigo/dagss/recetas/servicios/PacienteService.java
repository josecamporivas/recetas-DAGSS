package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.tipos.Direccion;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;
import es.uvigo.dagss.recetas.repositorios.PacienteRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    /*
     * Se mostrará una lista con los pacientes actualmente registrados, indicando su
     * datos esenciales (nombre y apelidos, centro de salud, localidad, provincia,
     * activo [true|false]).
     */

     public List<Paciente> getAll(){
        return pacienteRepository.findAll();
     }

    /*
     * La lista de pacientes podrá filtrarse por nombre o por localidad,
     * permitiéndose en todos estos casos búsquedas aproximadas (tipo LIKE en SQL).
     */
    public List<Paciente> buscarPacientesNombre(Nombre nombre) {
        return pacienteRepository.findBynombreCompleto(nombre);
    }

    public List<Paciente> buscarPacientesLocalidad(String localidad) {
        Direccion direccion = new Direccion();
        direccion.setLocalidad(localidad);
        return pacienteRepository.findByDireccion(direccion);
    }

    /*
     * También podrá filtrarse la lista de pacientes por centro de salud asignado
     * (seleccionando un centro de salud de una lista desplegable con todos los
     * centros registrados) y por médico asignado (seleccionando un médico de una
     * lista desplegable con todos los médicos disponibles en el centro de salud
     * indicado).
     */
    /* Sé que se obtiene tras obtener el médico peeero ns cómo se hace eso xd WIP */
    public List<Paciente> buscarPacientesCentroSalud(CentroSalud centroSalud) {
        return new ArrayList<>();
    }

    public List<Paciente> buscarPacientesMedico(Medico medico) {
        return pacienteRepository.findByMedicoAsignado(medico);
    }

    /*
     * Se podrá seleccionar un paciente de esa lista y mediante un botón Editar
     * acceder a la edición de los datos del paciente seleccionado, incluyendo la
     * modificación del centro de salud al que está asignado y del médico que
     * corresponde a ese paciente. Una vez completada esa edición se actualizará la
     * lista de médicos.
     */

    public Paciente modificar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    /*
     * Se podrá seleccionar un paciente de esa lista y mediante un botón Borrar
     * realizar la eliminación lógica de ese usuario en la aplicación, establiendo
     * el valor de activo a false. Una vez completada esa edición se actualizará la
     * lista de pacientes.
     */
    public void eliminar(Paciente paciente) {
        paciente.desactivar();
        pacienteRepository.save(paciente);
    }

    /*
     * Mediante un botón Nuevo se accederá a la creación de un nuevo paciente, donde
     * además de introducir sus datos se le asignará un centro de salud y un médico
     * asignado a dicho centro de salud. Una vez completada y confirmada esa edición
     * se actualizará la lista de pacientes.
     */

    public Paciente crear(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
}
