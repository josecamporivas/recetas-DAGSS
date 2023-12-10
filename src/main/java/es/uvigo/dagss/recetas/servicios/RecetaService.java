package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.repositorios.PrescripcionRepository;
import es.uvigo.dagss.recetas.repositorios.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
