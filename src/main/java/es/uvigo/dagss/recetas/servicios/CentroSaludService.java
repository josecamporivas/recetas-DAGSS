package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.repositorios.AdministradorRepository;
import es.uvigo.dagss.recetas.repositorios.CentroSaludRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentroSaludService {

    @Autowired
    private CentroSaludRepository centroSaludRepository;

    public CentroSalud crear(CentroSalud centroSalud) {
        return centroSaludRepository.save(centroSalud);
    }

    public CentroSalud modificar(CentroSalud centroSalud) {
        return centroSaludRepository.save(centroSalud);

    }

    /*
     * La lista de centros de salud podrá filtrarse por nombre o por localidad,
     * permitiéndose en todos estos casos búsquedas aproximadas (tipo LIKE en SQL)
     */
    /* no sé si buscarByName utiliza un like */
    public List<CentroSalud> buscarCentrosSaludNombre(String nombre) {
        return centroSaludRepository.findAllByNombre(nombre);

    }

    public List<CentroSalud> buscarCentrosSaludDireccion(String direccion) {
        return centroSaludRepository.findAllByDireccion(direccion);

    }
}
