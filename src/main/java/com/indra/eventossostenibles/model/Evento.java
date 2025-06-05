package main.java.com.indra.eventossostenibles.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private static int nextId = 1;
    private int id;
    private int idEvento;
    private String nombreEvento;
    private String descripcionEvento;
    private LocalDate fechaEvento;
    private double duracionEvento;
    private Categoria categoria; 
    private Ubicacion ubicacion;
    private Organizador organizador; 
    private List<Inscripcion> inscripciones;

    public Evento(String nombreEvento, String descripcionEvento, LocalDate fechaEvento, double duracionEvento,
                  Categoria categoria, Ubicacion ubicacion, Organizador organizador) {
        this.idEvento = nextId++; 
        this.nombreEvento = nombreEvento;
        this.descripcionEvento = descripcionEvento;
        this.fechaEvento = fechaEvento;
        this.duracionEvento = duracionEvento;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.organizador = organizador;
        this.inscripciones = new ArrayList<>();
    }


    public int getIdEvento() {
        return idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public double getDuracionEvento() {
        return duracionEvento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

     public int getId() {
        return this.id;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public void setDuracionEvento(double duracionEvento) {
        this.duracionEvento = duracionEvento;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public static void resetNextId() {
        nextId = 1;
    }

  
    public void añadirInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null && !this.inscripciones.contains(inscripcion)) {
            this.inscripciones.add(inscripcion);
            System.out.println("Inscripción añadida al evento " + this.nombreEvento + " para " + inscripcion.getUsuario().getNombreUsuario());
        }
    }

    public void removerInscripcion(Inscripcion inscripcion) {
        this.inscripciones.remove(inscripcion);
        System.out.println("Inscripción removida del evento " + this.nombreEvento + " para " + inscripcion.getUsuario().getNombreUsuario());
    }

   
    public void cancelarEvento() {
        System.out.println("El evento '" + this.nombreEvento + "' ha sido cancelado por el organizador " + this.organizador.getNombreOrganizador() + ".");
    }

    @Override
    public String toString() {
        return "Evento [ID=" + idEvento + ", Nombre=" + nombreEvento + ", Fecha=" + fechaEvento + ", Duración=" + duracionEvento +
               ", Categoría=" + (categoria != null ? categoria.getNombreCategoria() : "N/A") +
               ", Ubicación=" + (ubicacion != null ? ubicacion.getTipoUbicacion() : "N/A") +
               ", Organizador=" + (organizador != null ? organizador.getNombreOrganizador() : "N/A") +
               ", Inscritos=" + inscripciones.size() + "]";
    }
}