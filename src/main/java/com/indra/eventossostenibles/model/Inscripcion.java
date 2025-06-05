package main.java.com.indra.eventossostenibles.model;

import java.time.LocalDate;

public class Inscripcion {

    private Usuario usuario; 
    private Evento evento;  
    private EstadoInscripcion estadoInscripcion; 
    private LocalDate fechaInscripcion; 

    public Inscripcion(Usuario usuario, Evento evento, LocalDate fechaInscripcion) {
      
        this.usuario = usuario;
        this.evento = evento;
        this.estadoInscripcion = EstadoInscripcion.ACTIVA;
        this.fechaInscripcion = fechaInscripcion;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public EstadoInscripcion getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setEstadoInscripcion(EstadoInscripcion estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    

    

    @Override
    public String toString() {
        return "Inscripcion [Usuario=" + usuario.getNombreUsuario() +
               ", Evento=" + evento.getNombreEvento() +
               ", Estado=" + estadoInscripcion +
               ", Fecha=" + fechaInscripcion + "]";
    }
}