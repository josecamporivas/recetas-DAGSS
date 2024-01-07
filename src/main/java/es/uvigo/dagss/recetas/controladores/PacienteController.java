package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.controladores.excepciones.ResourceNotFoundException;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.servicios.PacienteService;
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
@RequestMapping(path = "/pacientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll(){
        return new ResponseEntity<>(pacienteService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable Long id){
        Optional<Paciente> paciente = pacienteService.findById(id);
        if (paciente.isEmpty()) {
            throw new ResourceNotFoundException("No existe el paciente con id " + id);
        }
        return new ResponseEntity<>(paciente.get(), HttpStatus.OK);
    }

    @GetMapping(path = "/nombre/{nombre}")
    public ResponseEntity<List<Paciente>> getAllByNombreCompleto(@PathVariable String nombre){
        return new ResponseEntity<>(pacienteService.findAllByNombreCompleto(nombre), HttpStatus.OK);
    }

    @GetMapping(path = "/localidad/{localidad}")
    public ResponseEntity<List<Paciente>> getAllByLocalidad(@PathVariable String localidad){
        return new ResponseEntity<>(pacienteService.findAllByLocalidad(localidad), HttpStatus.OK);
    }

    @GetMapping(path = "/centroSalud/{centroSalud}")
    public ResponseEntity<List<Paciente>> getAllByCentroSalud(@PathVariable Long centroSalud){
        return new ResponseEntity<>(pacienteService.findAllByCentroSalud(centroSalud), HttpStatus.OK);
    }

    @GetMapping(path = "/medico/{medico}")
    public ResponseEntity<List<Paciente>> getAllByMedico(@PathVariable Long medico){
        return new ResponseEntity<>(pacienteService.findAllByMedico(medico), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente){
        Paciente newPaciente = pacienteService.create(paciente);
        URI uri = createPacienteUri(newPaciente);

        return ResponseEntity.created(uri).body(newPaciente);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente paciente){
        Optional<Paciente> optionalPaciente = pacienteService.findById(id);
        paciente.setId(id);
        if (optionalPaciente.isEmpty()) {
            throw new ResourceNotFoundException("No existe el paciente con id " + id);
        }

        Paciente newPaciente = pacienteService.update(paciente);
        return new ResponseEntity<>(newPaciente, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Paciente> optionalPaciente = pacienteService.findById(id);
        if (optionalPaciente.isEmpty()) {
            throw new ResourceNotFoundException("No existe el paciente con id " + id);
        }

        pacienteService.delete(optionalPaciente.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI createPacienteUri(Paciente paciente) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(paciente.getId())
                .toUri();
    }
}
