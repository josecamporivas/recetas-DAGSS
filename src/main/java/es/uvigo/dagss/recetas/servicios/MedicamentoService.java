package es.uvigo.dagss.recetas.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.uvigo.dagss.recetas.entidades.Medicamento;
import es.uvigo.dagss.recetas.repositorios.MedicamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    /*
     * Se mostrará una lista con los medicamentos actualmente registrados, indicando
     * su datos esenciales (nombre comercial, principio activo, fabricante,
     * familia).
     */

    public List<Medicamento> getAll() {
        return medicamentoRepository.findAll();
    }

    /*
     * La lista de medicamentos podrá filtrarse por nombre comercial, principio
     * activo, fabricante o famila, permitiéndose en todos estos casos búsquedas
     * aproximadas (tipo LIKE en SQL).
     */

    public List<Medicamento> findAllByNombre(String nombre) {
        return medicamentoRepository.findAllByNombre(nombre);
    }

    public List<Medicamento> findAllByPrincipioActivo(String principioActivo) {
        return medicamentoRepository.findAllByPrincipioActivo(principioActivo);
    }

    public List<Medicamento> findAllByFabricante(String fabricante) {
        return medicamentoRepository.findAllByFabricante(fabricante);
    }

    public List<Medicamento> findAllByFamilia(String familia) {
        return medicamentoRepository.findAllByFamilia(familia);
    }

    /*
     * Se podrá seleccionar un medicamento de esa lista y mediante un botón Editar
     * acceder a la edición de los datos del medicamento seleccionado. Una vez
     * completada esa edición se actualizará la lista de medicamentos.
     */

    public Medicamento update(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    /*
     * Se podrá seleccionar un medicamento de esa lista y mediante un botón Borrar
     * realizar la eliminación lógica de ese medicamento en la aplicación,
     * establiendo el valor de activo a false. Una vez completada esa edición se
     * actualizará la lista de medicamentos.
     */

    public void delete(Medicamento medicamento) {
        medicamento.setEstado(false);
        medicamentoRepository.save(medicamento);
    }

    /*
     * Mediante un botón Nuevo se accederá a la creación de un nuevo medicamento.
     * Una vez completada esa edición se actualizará la lista de medicamentos.
     */

     public Medicamento create(Medicamento medicamento){
        return medicamentoRepository.save(medicamento);
     }
}
