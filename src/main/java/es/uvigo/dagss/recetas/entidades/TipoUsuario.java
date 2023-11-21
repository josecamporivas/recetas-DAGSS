package es.uvigo.dagss.recetas.entidades;


public enum TipoUsuario {
    ADMINISTRADOR ("ADMINISTRADOR"), 
    PACIENTE      ("PACIENTE"), 
    MEDICO        ("MEDICO"), 
    FARMACIA      ("FARMACIA");    
    
    private final String etiqueta;

    private TipoUsuario(String etiqueta) {
        this.etiqueta = etiqueta;
    }
   
    public String getEtiqueta() {
        return etiqueta;
    }    
}
