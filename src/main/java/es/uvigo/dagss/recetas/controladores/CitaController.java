package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.controladores.excepciones.ResourceNotFoundException;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.servicios.CitaService;
import es.uvigo.dagss.recetas.servicios.MedicoService;

import es.uvigo.dagss.recetas.servicios.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/citas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CitaController {
    @Autowired
    private CitaService citaService;
    
    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;
    
    @GetMapping
    public ResponseEntity<List<Cita>> getAll() {
        List<Cita> result = citaService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cita> findById(@PathVariable Long id){
        Optional<Cita> optionalCita = citaService.findById(id);
        if(optionalCita.isEmpty()){
            throw new ResourceNotFoundException("No existe una cita con el id " + id);
        }

        return new ResponseEntity<>(optionalCita.get(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/medico/{medicoId}")
    public ResponseEntity<List<Cita>> findAllByMedico(@PathVariable Long medicoId) {
        Optional<Medico> medicoCita = medicoService.findById(medicoId);
        if(medicoCita.isEmpty()){
            throw new ResourceNotFoundException("No existe un medico con el id " + medicoId);
        }
        List<Cita> result = citaService.findAllByMedico(medicoCita.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/medico/{medicoId}/hoy")
    public ResponseEntity<List<Cita>> findAllByMedicoForToday(@PathVariable Long medicoId) {
        Optional<Medico> medico = medicoService.findById(medicoId);
        if(medico.isEmpty()){
            throw new ResourceNotFoundException("No existe el médico con id " + medicoId);
        }

        List<Cita> result = citaService.findAllByMedicoForToday(medico.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/paciente/{pacienteId}")
    public ResponseEntity<List<Cita>> findAllByPaciente(@PathVariable Long pacienteId) {
        Optional<Paciente> pacienteOptional = pacienteService.findById(pacienteId);
        if(pacienteOptional.isEmpty()){
            throw new ResourceNotFoundException("No existe el paciente con el id " + pacienteId);
        }
        List<Cita> result = citaService.findByPaciente(pacienteOptional.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cita> create(@RequestBody Cita cita) {
        Cita newCita = citaService.create(cita);
        URI uri = createCitaUri(newCita);

        return ResponseEntity.created(uri).body(newCita);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cita> setCompletada(@PathVariable Long id, @RequestBody Cita cita) {
        Optional<Cita> optionalCita = citaService.findById(id);
        cita.setIdCita(id);

        if (optionalCita.isEmpty()) {
            throw new ResourceNotFoundException("No existe la cita con id " + id);
        }

        Cita updatedCita = citaService.update(cita);
        return new ResponseEntity<>(updatedCita, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> setAnulada(@PathVariable Long id) {
        Optional<Cita> cita = citaService.findById(id);
        if (cita.isEmpty()) {
            throw new ResourceNotFoundException("No existe la cita con id " + id);
        }
        citaService.setAnulada(cita.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI createCitaUri(Cita cita) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cita.getIdCita())
                .toUri();
    }
}
