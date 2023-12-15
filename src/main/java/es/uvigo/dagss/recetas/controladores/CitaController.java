package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.servicios.CitaService;
import es.uvigo.dagss.recetas.servicios.MedicoService;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/citas", produces = "application/json")
public class CitaController {
    @Autowired
    private CitaService citaService;
    
    @Autowired
    private MedicoService medicoService;
    
    @GetMapping
    public ResponseEntity<List<Cita>> getAll() {
        List<Cita> result = new ArrayList<>();
        result = citaService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(params = "medicoId", method = RequestMethod.GET)
    public ResponseEntity<List<Cita>> findAllByMedico(@RequestParam(name = "medicoId", required = true) Long medicoId) {
        List<Cita> result = new ArrayList<>();
        result = citaService.findAllByMedico(medicoService.findById(medicoId).get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    

    //de otra forma petaba al haber dos métodos con el mismo path. Para ello, como son citas para médicos de hoy, creo el nuevo path /today

    @RequestMapping(path="/today" ,params = "medicoId", method = RequestMethod.GET)
    public ResponseEntity<List<Cita>> findAllByMedicoForToday(@RequestParam(name = "medicoId", required = true) Long medicoId) {
        List<Cita> result = new ArrayList<>();
        Medico medico = medicoService.findById(medicoId).get();
        result = citaService.findAllByMedicoForToday(medico);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(path = "/deactivate/{id}")
    public ResponseEntity<HttpStatus> setAnulada(@PathVariable("id") Long id) {
        Optional<Cita> cita = citaService.findById(id);
        if (cita.isEmpty()) {

            throw new RuntimeException("No existe la cita con id " + id);
        } else {
            citaService.setAnulada(cita.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cita> setCompletada(@PathVariable("id") Long id, @RequestBody Cita cita) {
        Optional<Cita> optionalCita = citaService.findById(id);

        if (optionalCita.isEmpty()) {
            throw new RuntimeException("No existe la cita con id " + id);
        } else {
            Cita newCita = citaService.setCompletada(cita);
            return new ResponseEntity<>(newCita, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cita> create(@RequestBody Cita cita) {
        Cita newCita = citaService.create(cita);
        URI uri = createCitaUri(newCita);

        return ResponseEntity.created(uri).body(cita);
    }

    private URI createCitaUri(Cita cita) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cita.getIdCita())
                .toUri();
    }
    
    
}
