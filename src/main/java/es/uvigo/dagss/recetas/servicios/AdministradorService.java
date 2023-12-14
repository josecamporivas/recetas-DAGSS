package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.repositorios.AdministradorRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    /*
     * Se mostrará una lista con los administradores actualmente registrados,
     * indicando su datos esenciales (login, nombre del usuario, email, fecha de
     * registro/creación, fecha de último acceso, activo [true|false])
     */
    public List<Administrador> getAll() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> findById(Long id) {
        return administradorRepository.findById(id);
    }

    /*
     * Mediante un botón Nuevo se accederá a la creación de un nuevo usuario
     * administrador, asignándosele manualmente un password inicial. Una vez
     * completada esa edición se actualizará la lista de usuarios.
     */
    public Administrador create(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    /*
     * Se podrá seleccionar un administrador de esa lista y mediante un botón Editar
     * acceder a la edición de los
     * datos del usuario seleccionado. Una vez completada esa edición se actualizará
     * la lista de usuarios.
     */
    public Administrador update(Administrador administrador) {
        return administradorRepository.save(administrador);

    }

    /*
     * Se podrá seleccionar un usuario de esa lista y mediante un botón Borrar
     * realizar la eliminación lógica de ese usuario en la aplicación, establiendo
     * el valor de activo a false.
     * Una vez completada esa edición se actualizará la lista de usuarios.
     */
    /* ?????????? Entiendo q su eliminación se haría mediante un edit */
    public void delete(Administrador administrador) {
        administrador.desactivar();
        administradorRepository.save(administrador);
    }

}
