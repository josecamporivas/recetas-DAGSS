package es.uvigo.dagss.recetas.entidades.tipos;


public enum TipoEstadoCita {
    PLANIFICADA ("PLANIFICADA"),
    ANULADA     ("ANULADA"),
    COMPLETADA  ("COMPLETADA"),
    AUSENTE     ("AUSENTE");

    private final String etiqueta;

    private TipoEstadoCita(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
