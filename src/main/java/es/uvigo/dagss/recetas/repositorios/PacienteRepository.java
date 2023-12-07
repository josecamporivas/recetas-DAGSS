package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.tipos.Direccion;
import es.uvigo.dagss.recetas.entidades.tipos.Nombre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public Paciente getByLoginAndPassword(String login, String password);
    public List<Paciente> findBynombreCompleto(Nombre nombreCompleto);
    public List<Paciente> findByDireccion(Direccion direccion);
    public List<Paciente> findByMedicoAsignado(Medico medicoAsignado);
}
