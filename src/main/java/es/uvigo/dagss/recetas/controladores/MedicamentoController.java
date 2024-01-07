package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.controladores.excepciones.ResourceNotFoundException;
import es.uvigo.dagss.recetas.entidades.Medicamento;
import es.uvigo.dagss.recetas.servicios.MedicamentoService;
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
@RequestMapping(path = "/medicamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping
    public ResponseEntity<List<Medicamento>> getAll(){
        return new ResponseEntity<>(medicamentoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Medicamento> getById(@PathVariable Long id){
        Optional<Medicamento> medicamento = medicamentoService.findById(id);
        if (medicamento.isEmpty()) {
            throw new ResourceNotFoundException("No existe el medicamento con id " + id);
        }
        return new ResponseEntity<>(medicamento.get(), HttpStatus.OK);
    }

    @GetMapping(path = "/nombre/{nombre}")
    public ResponseEntity<List<Medicamento>> getByNombre(@PathVariable String nombre){
        return new ResponseEntity<>(medicamentoService.findAllByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping(path = "/principioActivo/{principio}")
    public ResponseEntity<List<Medicamento>> getByPrincipio(@PathVariable String principio){
        return new ResponseEntity<>(medicamentoService.findAllByPrincipioActivo(principio), HttpStatus.OK);
    }

    @GetMapping(path = "/fabricante/{fabricante}")
    public ResponseEntity<List<Medicamento>> getByFabricante(@PathVariable String fabricante){
        return new ResponseEntity<>(medicamentoService.findAllByFabricante(fabricante), HttpStatus.OK);
    }

    @GetMapping(path = "/familia/{familia}")
    public ResponseEntity<List<Medicamento>> getByFamilia(@PathVariable String familia){
        return new ResponseEntity<>(medicamentoService.findAllByFamilia(familia), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medicamento> create(@RequestBody Medicamento medicamento){
        Medicamento newMedicamento = medicamentoService.create(medicamento);
        URI uri = createCentroSaludUri(newMedicamento);

        return ResponseEntity.created(uri).body(newMedicamento);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Medicamento> update(@PathVariable Long id, @RequestBody Medicamento medicamento){
        Optional<Medicamento> medicamentoOptional = medicamentoService.findById(id);
        medicamento.setIdMedicamento(id);
        if (medicamentoOptional.isEmpty()) {
            throw new ResourceNotFoundException("No existe el medicamento con id " + id);
        }

        Medicamento newMedicamento = medicamentoService.update(medicamento);
        return new ResponseEntity<>(newMedicamento, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Medicamento> medicamento = medicamentoService.findById(id);
        if(medicamento.isEmpty()){
            throw new ResourceNotFoundException("No existe el medicamento con id " + id);
        }
        medicamentoService.delete(medicamento.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI createCentroSaludUri(Medicamento medicamento) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(medicamento.getIdMedicamento())
                .toUri();
    }
}
