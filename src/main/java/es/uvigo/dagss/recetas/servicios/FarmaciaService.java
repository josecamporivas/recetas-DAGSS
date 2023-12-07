package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.repositorios.FarmaciaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmaciaService {
    @Autowired
    private FarmaciaRepository farmaciaRepository;

    /*
     * Se mostrará una lista con las farmacias actualmente registradas, indicando su
     * datos esenciales (nombre establecimiento, localidad, provincia, activo
     * [true|false]).
     */

    public List<Farmacia> getAll() {
        return farmaciaRepository.findAll();
    }
    /*
     * La lista de farmacias podrá filtrarse por nombre de establecimiento o por
     * localidad, permitiéndose en todos estos casos búsquedas aproximadas (tipo
     * LIKE en SQL).
     */

    public List<Farmacia> buscarFarmaciasNombre(String nombreFarmacia) {
        return farmaciaRepository.findByNombreFarmacia(nombreFarmacia);
    }

    /*
     * Se podrá seleccionar una farmacia de esa lista y mediante un botón Editar
     * acceder a la edición de los datos de la farmacia seleccionada. Una vez
     * completada esa edición se actualizará la lista de farmacias.
     */

    public Farmacia editar(Farmacia farmacia) {
        return farmaciaRepository.save(farmacia);
    }

    /*
     * Se podrá seleccionar una farmacia de esa lista y mediante un botón Borrar
     * realizar la eliminación lógica de ese usuario en la aplicación, establiendo
     * el valor de activo a false. Una vez completada esa edición se actualizará la
     * lista de farmacias.
     */

     public void eliminar(Farmacia farmacia){
        farmacia.desactivar();
        farmaciaRepository.save(farmacia);
     }

     

}
