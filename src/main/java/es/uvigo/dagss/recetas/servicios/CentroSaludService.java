package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.repositorios.CentroSaludRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

@Service
public class CentroSaludService {

    @Autowired
    private CentroSaludRepository centroSaludRepository;

    public CentroSalud create(CentroSalud centroSalud) {
        return centroSaludRepository.save(centroSalud);
    }

    /*
     * Se mostrará una lista con los centros de salud actualmente registrados,
     * indicando su datos esenciales (nombre, localidad, provincia, activo
     * [true|false]).
     */

    public List<CentroSalud> getAll() {
        return centroSaludRepository.findAll().stream().filter(CentroSalud::getEstado).toList();
    }

     public Optional<CentroSalud> findById(Long id) {
        Optional<CentroSalud> centroSalud = centroSaludRepository.findById(id);

        if(centroSalud.isPresent() && !centroSalud.get().getEstado()){
            return Optional.empty();
        }
        return centroSalud;
    }

    /*
     * La lista de centros de salud podrá filtrarse por nombre o por localidad,
     * permitiéndose en todos estos casos búsquedas aproximadas (tipo LIKE en SQL)
     */
    /* no sé si buscarByName utiliza un like */
    public List<CentroSalud> findAllByNombre(String nombre) {
        return centroSaludRepository.findAllByNombreContainingAndEstadoTrue(nombre);

    }

    /*tengo serias dudas sobre esto la verdad WIP*/
    public List<CentroSalud> findAllByLocalidad(String localidad) {
        return centroSaludRepository.findAllByDireccionLocalidadContainingAndEstadoTrue(localidad);
    }

    /*
     * Se podrá seleccionar un centro de esa lista y mediante un botón Editar
     * acceder a la edición de los datos del centro de salud seleccionado. Una vez
     * completada esa edición se actualizará la lista de centros de salud.
     */

    public CentroSalud update(CentroSalud centroSalud) {
        return centroSaludRepository.save(centroSalud);
    }

    /*
     * Se podrá seleccionar un centro de esa lista y mediante un botón Borrar
     * realizar la eliminación lógica de ese centro de salud en la aplicación,
     * establiendo el valor de activo a false. Una vez completada esa edición se
     * actualizará la lista de centros de salud.
     */

     public void delete(CentroSalud centroSalud) {
        centroSalud.setEstado(false);
        centroSaludRepository.save(centroSalud);
    }
}
