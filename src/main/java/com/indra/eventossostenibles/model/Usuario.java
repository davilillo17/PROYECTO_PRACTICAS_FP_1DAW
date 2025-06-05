package main.java.com.indra.eventossostenibles.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static int nextId = 1;
    private int id;
    private int idUsuario;
    private String nombreUsuario;
    private String emailUsuario;
    private String contraseñaUsuario;
    private List<Inscripcion> misInscripciones;

    public Usuario(String nombreUsuario, String emailUsuario, String contraseñaUsuario) {
        this.idUsuario = nextId++;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
        this.misInscripciones = new ArrayList<>();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public List<Inscripcion> getMisInscripciones() {
        return misInscripciones;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }

     public int getId() {
        return this.id;
    }



    public static void resetNextId() {
        nextId = 1;
    }


    public void añadirInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null && !this.misInscripciones.contains(inscripcion)) {
            this.misInscripciones.add(inscripcion);
        }
    }

    public void removerInscripcion(Inscripcion inscripcion) {
        this.misInscripciones.remove(inscripcion);
    }

    @Override
    public String toString() {
        return "Usuario [ID=" + idUsuario + ", Nombre=" + nombreUsuario + ", Email=" + emailUsuario + "]";
    }
}