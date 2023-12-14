package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.servicios.AdministradorService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(path = "/administradores", produces = "application/json")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public ResponseEntity<List<Administrador>> getAll() {
        List<Administrador> result = new ArrayList<>();
        result = administradorService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Administrador> getById(@PathVariable("id") Long id) {
        Optional<Administrador> admin = administradorService.findById(id);
        if (admin.isEmpty()) {

            throw new RuntimeException("No existe el administrador con id " + id);
        } else {
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);

        }
    }

    //duda. aqui el profesor usa @valid, a mi me da error porque import jakarta.validation.Valid; tp va

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrador> create(@RequestBody Administrador administrador) {
        Administrador newAdministrador = administradorService.create(administrador);
		URI uri = createAdminstradorUri(newAdministrador);

		return ResponseEntity.created(uri).body(administrador);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrador> update(@PathVariable("id") Long id, @RequestBody Administrador administrador) {
        Optional<Administrador> optionalAdministrador = administradorService.findById(id);

		if (optionalAdministrador.isEmpty()) {
            throw new RuntimeException("No existe el administrador con id " + id);
		} else {
			Administrador newAdministrador = administradorService.update(administrador);
			return new ResponseEntity<>(newAdministrador, HttpStatus.OK);
		}
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        Optional<Administrador> admin = administradorService.findById(id);
        if (admin.isEmpty()) {

            throw new RuntimeException("No existe el administrador con id " + id);
        } else {
            administradorService.delete(admin.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }


    private URI createAdminstradorUri(Administrador administrador) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(administrador.getId())
				.toUri();
	}
}
