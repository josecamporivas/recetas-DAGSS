package es.uvigo.dagss.recetas.entidades.tipos;

public enum TipoEstadoReceta {
    PLANIFICADA ("PLANIFICADA"),
    ANULADA     ("ANULADA"),
    SERVIDA  ("SERVIDA");

    private final String etiqueta;

    private TipoEstadoReceta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
