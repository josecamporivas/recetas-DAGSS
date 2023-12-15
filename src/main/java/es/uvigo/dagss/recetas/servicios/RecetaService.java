package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.entidades.tipos.TipoEstadoReceta;
import es.uvigo.dagss.recetas.repositorios.PrescripcionRepository;
import es.uvigo.dagss.recetas.repositorios.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private PrescripcionRepository prescripcionRepository;

    /*  Esto corresponde a HU-P4, falta ordenar por fecha   */
    public List<Receta> findAllByPacienteAndEstadoPlanificada(Paciente paciente){
        List<Prescripcion> prescripcionList = prescripcionRepository.findAllByPacienteAndEstado(paciente, true);

        List<Receta> recetaPlanificadaList = new ArrayList<>();

        for(Prescripcion prescripcion: prescripcionList){
            List<Receta> recetasPrescripcionPaciente = recetaRepository.findAllByPrescripcion(prescripcion);
            recetaPlanificadaList.addAll(recetasPrescripcionPaciente);
        }

        return recetaPlanificadaList;
    }

    public List<Receta> findAllByPacienteNumTarjeta(String numTarjeta){
        List<Receta> allRecetasPaciente = recetaRepository.findAllByPrescripcionPacienteNumTarjetaSanitaria(numTarjeta);
        List<Receta> recetasDisponibles = new ArrayList<>();

        for(Receta r: allRecetasPaciente){
            if(r.getEstado().equals(TipoEstadoReceta.PLANIFICADA)){
                recetasDisponibles.add(r);
            }
        }
        return recetasDisponibles;
    }

    public Receta setServida(Receta receta, Farmacia farmacia){

        Date now = Date.from(Instant.now());

        if(!receta.getEstado().equals(TipoEstadoReceta.PLANIFICADA) ||
                !receta.getFechaValidezInicial().after(now) ||
                !receta.getFechaValidezFinal().before(now)){
            //WIP: lanzar excepcion la receta no se puede modificar porque no está disponible
        }

        receta.setFarmacia(farmacia);
        receta.setEstado(TipoEstadoReceta.COMPLETADA);
        recetaRepository.save(receta);

        return receta;
    }
}
