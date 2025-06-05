package main.java.com.indra.eventossostenibles.model;

public class Ubicacion {
    private static int nextId = 1;
    private int idUbicacion;
    private String tipoUbicacion;
    private String direccionUbicacion;
    private String enlaceUbicacion;

    public Ubicacion(String tipoUbicacion, String direccionUbicacion, String enlaceUbicacion) {
        this.idUbicacion = nextId++;
        this.tipoUbicacion = tipoUbicacion;
        this.direccionUbicacion = direccionUbicacion;
        this.enlaceUbicacion = enlaceUbicacion;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public String getTipoUbicacion() {
        return tipoUbicacion;
    }

    public String getDireccionUbicacion() {
        return direccionUbicacion;
    }

    public String getEnlaceUbicacion() {
        return enlaceUbicacion;
    }

    public void setTipoUbicacion(String tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }

    public void setDireccionUbicacion(String direccionUbicacion) {
        this.direccionUbicacion = direccionUbicacion;
    }

    public void setEnlaceUbicacion(String enlaceUbicacion) {
        this.enlaceUbicacion = enlaceUbicacion;
    }


    public static void resetNextId() {
        nextId = 1;
    }

    @Override
    public String toString() {
        if ("Online".equalsIgnoreCase(tipoUbicacion)) {
            return "Ubicacion [ID=" + idUbicacion + ", Tipo=" + tipoUbicacion + ", Enlace=" + enlaceUbicacion + "]";
        } else {
            return "Ubicacion [ID=" + idUbicacion + ", Tipo=" + tipoUbicacion + ", Dirección=" + direccionUbicacion + "]";
        }
    }
}