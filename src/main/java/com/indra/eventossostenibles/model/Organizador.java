package main.java.com.indra.eventossostenibles.model;

import java.util.ArrayList;
import java.util.List;

public class Organizador {
    private static int nextId = 1;
    private int idOrganizador;
    private String nombreOrganizador;
    private String emailOrganizador;
    private String telefonoOrganizador;
    private String contraseñaOrganizador;
    private List<Evento> eventosOrganizados;

    public Organizador(String nombreOrganizador, String emailOrganizador, String telefonoOrganizador) {
        this.idOrganizador = nextId++;
        this.nombreOrganizador = nombreOrganizador;
        this.emailOrganizador = emailOrganizador;
        this.telefonoOrganizador = telefonoOrganizador;
        this.eventosOrganizados = new ArrayList<>();
    }

    public int getIdOrganizador() {
        return idOrganizador;
    }

    public String getNombreOrganizador() {
        return nombreOrganizador;
    }

    public String getEmailOrganizador() {
        return emailOrganizador;
    }

    public String getTelefonoOrganizador() {
        return telefonoOrganizador;
    }

    public String getContraseñaOrganizador() {
        return contraseñaOrganizador;
    }

    public List<Evento> getEventosOrganizados() {
        return eventosOrganizados;
    }

    public void setNombreOrganizador(String nombreOrganizador) {
        this.nombreOrganizador = nombreOrganizador;
    }

    public void setEmailOrganizador(String emailOrganizador) {
        this.emailOrganizador = emailOrganizador;
    }

    public void setTelefonoOrganizador(String telefonoOrganizador) {
        this.telefonoOrganizador = telefonoOrganizador;
    }

    public void setContraseñaOrganizador(String contraseñaOrganizador) {
        this.contraseñaOrganizador = contraseñaOrganizador;
    }

    public static void resetNextId() {
        nextId = 1;
    }

    public void añadirEventoOrganizado(Evento evento) {
        if (evento != null && !this.eventosOrganizados.contains(evento)) {
            this.eventosOrganizados.add(evento);
        }
    }

    public void removerEventoOrganizado(Evento evento) {
        this.eventosOrganizados.remove(evento);
    }

    @Override
    public String toString() {
        return "Organizador [ID=" + idOrganizador + ", Nombre=" + nombreOrganizador + ", Email=" + emailOrganizador + "]";
    }
}