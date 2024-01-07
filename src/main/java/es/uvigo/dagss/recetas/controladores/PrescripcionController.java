package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.controladores.excepciones.ResourceNotFoundException;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.repositorios.PacienteRepository;
import es.uvigo.dagss.recetas.servicios.PacienteService;
import es.uvigo.dagss.recetas.servicios.PrescripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/prescripciones", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrescripcionController {
    @Autowired
    private PrescripcionService prescripcionService;

    @GetMapping
    public ResponseEntity<List<Prescripcion>> getAll(){
        return new ResponseEntity<>(prescripcionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescripcion> getById(@PathVariable Long id){
        Optional<Prescripcion> prescripcion = prescripcionService.findById(id);
        if (prescripcion.isEmpty()) {
            throw new ResourceNotFoundException("No existe la prescripcion con id " + id);
        }
        return new ResponseEntity<>(prescripcion.get(), HttpStatus.OK);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Prescripcion>> getAllByPaciente(@PathVariable Long id){
        return new ResponseEntity<>(prescripcionService.findAllByPacienteAndActiva(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Prescripcion> create(@RequestBody Prescripcion prescripcion){
        Prescripcion newPrescripcion = prescripcionService.create(prescripcion);
        URI uri = createPrescripcionUri(newPrescripcion);
        return ResponseEntity.created(uri).body(newPrescripcion);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Prescripcion> setAnulada(@PathVariable Long id, @RequestBody Prescripcion prescripcion){
        Optional<Prescripcion> prescripcionOptional = prescripcionService.findById(id);
        prescripcion.setIdPrescripcion(id);

        if (prescripcionOptional.isEmpty()) {
            throw new ResourceNotFoundException("No existe la prescripcion con id " + id);
        }

        return new ResponseEntity<>(prescripcionService.update(prescripcion), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Prescripcion> prescripcionOptional = prescripcionService.findById(id);

        if (prescripcionOptional.isEmpty()) {
            throw new ResourceNotFoundException("No existe la prescripcion con id " + id);
        }
        prescripcionService.delete(prescripcionOptional.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI createPrescripcionUri(Prescripcion prescripcion) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(prescripcion.getIdPrescripcion())
                .toUri();
    }

}
