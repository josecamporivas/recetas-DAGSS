package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.servicios.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<Administrador> getAll(){
        return administradorService.getAll();
    }

    @PostMapping
    public Administrador create(Administrador administrador){
        return administradorService.create(administrador);
    }

    @PutMapping
    public Administrador update(Administrador administrador){
        return administradorService.update(administrador);
    }

    @DeleteMapping
    public void delete(Administrador administrador){
        administradorService.delete(administrador);
    }
}
