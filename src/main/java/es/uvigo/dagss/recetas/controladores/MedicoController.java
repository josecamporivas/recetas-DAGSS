package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.servicios.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> getAll(){
        return new ResponseEntity<>(medicoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Medico> getById(@PathVariable Long id){
        Optional<Medico> medico = medicoService.findById(id);
        if (medico.isEmpty()) {
            throw new RuntimeException("No existe el medico con id " + id);
        }
        return new ResponseEntity<>(medico.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}/huecosLibres")
    public ResponseEntity<List<Time>> getHuecosLibres(@PathVariable Long id, @RequestParam("fecha") Date fecha){
        Optional<Medico> medico = medicoService.findById(id);
        if (medico.isEmpty()) {
            throw new RuntimeException("No existe el medico con id " + id);
        }
        return new ResponseEntity<>(medicoService.findFreeSpaceSchedule(medico.get().getId(), fecha), HttpStatus.OK);
    }

    @GetMapping(path = "/nombre/{nombre}")
    public ResponseEntity<List<Medico>> getAllByNombreCompleto(@PathVariable String nombre){
        return new ResponseEntity<>(medicoService.findAllByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping(path = "/localidad/{localidad}")
    public ResponseEntity<List<Medico>> getAllByLocalidad(@PathVariable String localidad){
        return new ResponseEntity<>(medicoService.findByDireccionLocalidad(localidad), HttpStatus.OK);
    }

    @GetMapping(path = "/centroSalud/{centroSalud}")
    public ResponseEntity<List<Medico>> getAllByCentroSalud(@PathVariable Long centroSalud){
        return new ResponseEntity<>(medicoService.findAllByCentroSalud(centroSalud), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Medico> create(@RequestBody Medico medico){
        Medico newMedico = medicoService.create(medico);
        URI uri = createPacienteUri(newMedico);

        return ResponseEntity.created(uri).body(newMedico);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> update(@PathVariable Long id, @RequestBody Medico medico){
        Optional<Medico> optionalMedico = medicoService.findById(id);
        medico.setId(id);
        if (optionalMedico.isEmpty()) {
            throw new RuntimeException("No existe el medico con id " + id);
        } else {
            Medico newMedico = medicoService.update(medico);
            return new ResponseEntity<>(newMedico, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Medico> optionalMedico = medicoService.findById(id);
        if (optionalMedico.isEmpty()) {
            throw new RuntimeException("No existe el medico con id " + id);
        } else {
            medicoService.delete(optionalMedico.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private URI createPacienteUri(Medico newMedico) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newMedico.getId())
                .toUri();
    }
}
