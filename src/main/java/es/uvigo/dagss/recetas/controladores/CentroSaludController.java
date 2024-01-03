package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.servicios.CentroSaludService;
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
@RequestMapping(path = "/centrosSalud", produces = "application/json")
public class CentroSaludController {

    @Autowired
    private CentroSaludService centroSaludService;

    @GetMapping(params = "nombre")
    public ResponseEntity<List<CentroSalud>> findAllByNombre(@RequestParam(name = "nombre", required = true) String nombre) {
        List<CentroSalud> centrosSalud = centroSaludService.findAllByNombre(nombre);

        return new ResponseEntity<>(centrosSalud, HttpStatus.OK);
    }

    @GetMapping(params = "localidad")
    public ResponseEntity<List<CentroSalud>> findAllByLocalidad(@RequestParam(name = "localidad", required = true) String localidad) {
        List<CentroSalud> centrosSalud = centroSaludService.findAllByLocalidad(localidad);

        return new ResponseEntity<>(centrosSalud, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CentroSalud>> getAll() {
        List<CentroSalud> result = centroSaludService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CentroSalud> getById(@PathVariable("id") Long id) {
        Optional<CentroSalud> centroSalud = centroSaludService.findById(id);
        if (centroSalud.isEmpty()) {
            throw new RuntimeException("No existe el centro de salud con id " + id);
        } else {

            return new ResponseEntity<>(centroSalud.get(), HttpStatus.OK);
        }
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CentroSalud> create(@RequestBody CentroSalud centroSalud) {
        CentroSalud newCentroSalud = centroSaludService.create(centroSalud);
        URI uri = createCentroSaludUri(newCentroSalud);

        return ResponseEntity.created(uri).body(centroSalud);
    }
    
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CentroSalud> update(@PathVariable("id") Long id, @RequestBody CentroSalud centroSalud) {
        Optional<CentroSalud> optionalCentroSalud = centroSaludService.findById(id);
        centroSalud.setIdCentro(id);
		if (optionalCentroSalud.isEmpty()) {
            throw new RuntimeException("No existe el centro de salud con id " + id);
		} else {
			CentroSalud newCentroSalud = centroSaludService.update(centroSalud);
			return new ResponseEntity<>(newCentroSalud, HttpStatus.OK);
		}
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        Optional<CentroSalud> centroSalud = centroSaludService.findById(id);
        if (centroSalud.isEmpty()) {
            throw new RuntimeException("No existe el administrador con id " + id); //TODO: handle exceptions
        } else {
            centroSaludService.delete(centroSalud.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    private URI createCentroSaludUri(CentroSalud centroSalud) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(centroSalud.getIdCentro())
				.toUri();
	}
}
