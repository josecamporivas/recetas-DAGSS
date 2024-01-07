package es.uvigo.dagss.recetas.controladores.excepciones;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
