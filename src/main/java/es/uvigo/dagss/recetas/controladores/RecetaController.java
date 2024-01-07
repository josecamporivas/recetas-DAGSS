package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.controladores.excepciones.ResourceNotFoundException;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.servicios.RecetaService;
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
@RequestMapping("/recetas")
public class RecetaController {
    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public ResponseEntity<List<Receta>> getAllRecetas() {
        return new ResponseEntity<>(recetaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable Long id) {
        Optional<Receta> receta = recetaService.findById(id);
        if(receta.isEmpty()){
            throw new ResourceNotFoundException("No existe la receta con id " + id);
        }
        return new ResponseEntity<>(receta.get(), HttpStatus.OK);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Receta>> getRecetasDisponiblesByPacienteId(@PathVariable Long id) {
        return new ResponseEntity<>(recetaService.findAllByPacienteAndEstadoPlanificada(id), HttpStatus.OK);
    }

    @GetMapping("/paciente/numTarjeta/{numTarjeta}")
    public ResponseEntity<List<Receta>> getRecetasDisponiblesByPacienteNumTarjeta(@PathVariable String numTarjeta) {
        return new ResponseEntity<>(recetaService.findAllByPacienteNumTarjeta(numTarjeta), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Receta> createReceta(@RequestBody Receta receta) {
        Receta newReceta = recetaService.create(receta);
        URI uri = createRecetaUri(newReceta);
        return ResponseEntity.created(uri).body(newReceta);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Receta> updateReceta(@PathVariable Long id, @RequestBody Receta receta) {
        Optional<Receta> recetaOptional = recetaService.findById(id);
        receta.setId(id);

        if (recetaOptional.isEmpty()) {
            throw new ResourceNotFoundException("No existe la receta con id " + id);
        }

        Receta updatedReceta = recetaService.update(receta);
        return new ResponseEntity<>(updatedReceta, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteReceta(@PathVariable Long id) {
        Optional<Receta> recetaOptional = recetaService.findById(id);

        if (recetaOptional.isEmpty()) {
            throw new ResourceNotFoundException("No existe la receta con id " + id);
        }

        recetaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI createRecetaUri(Receta receta) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(receta.getId())
                .toUri();
    }
}
