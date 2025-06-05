package main.java.com.indra.eventossostenibles.service;

import main.java.com.indra.eventossostenibles.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PortalManager {
    private List<Usuario> usuarios;
    private List<Organizador> organizadores;
    private List<Categoria> categorias;
    private List<Ubicacion> ubicaciones;
    private List<Evento> eventos;
    private List<Inscripcion> inscripciones;

    public PortalManager() {
        this.usuarios = new ArrayList<>();
        this.organizadores = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.ubicaciones = new ArrayList<>();
        this.eventos = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
    }


     public List<Evento> listarEventos() {
        return new ArrayList<>(this.eventos);
    }


    public List<Inscripcion> listarInscripciones() {
        return new ArrayList<>(inscripciones);
    }

    public boolean cancelarInscripcion(int idUsuario, int idEvento) {

        Inscripcion inscripcionACancelar = null;
        for (Inscripcion i : inscripciones) {
            if (i.getUsuario().getId() == idUsuario && i.getEvento().getId() == idEvento) {
                inscripcionACancelar = i;
                break;
            }
        }
        if (inscripcionACancelar != null) {
            inscripciones.remove(inscripcionACancelar);
            return true;
        }
        return false;
    }




    public Usuario crearUsuario(String nombre, String email, String contraseña) {

        if (usuarios.stream().anyMatch(u -> u.getEmailUsuario().equalsIgnoreCase(email))) {
            System.out.println("Error: Ya existe un usuario con ese email.");
            return null;
        }
        Usuario nuevoUsuario = new Usuario(nombre, email, contraseña);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario registrado: " + nuevoUsuario.getNombreUsuario());
        return nuevoUsuario;
    }

   public Organizador crearOrganizador(String nombre, String email, String telefono, String contraseña) {

        if (organizadores.stream().anyMatch(o -> o.getEmailOrganizador().equalsIgnoreCase(email))) {
            System.out.println("Error: Ya existe un organizador con ese email.");
            return null;
        }
        Organizador nuevoOrganizador = new Organizador(nombre, email, telefono);
        organizadores.add(nuevoOrganizador);
        System.out.println("Organizador registrado: " + nuevoOrganizador.getNombreOrganizador());
        return nuevoOrganizador;
    }

    public Categoria crearCategoria(String nombre) {

        if (categorias.stream().anyMatch(c -> c.getNombreCategoria().equalsIgnoreCase(nombre))) {
            System.out.println("Error: La categoría '" + nombre + "' ya existe.");
            return null;
        }
        Categoria nuevaCategoria = new Categoria(nombre);
        categorias.add(nuevaCategoria);
        System.out.println("Categoría creada: " + nuevaCategoria.getNombreCategoria());
        return nuevaCategoria;
    }

    public Ubicacion crearUbicacion(String tipo, String direccion, String enlace) {
        Ubicacion nuevaUbicacion = new Ubicacion(tipo, direccion, enlace);
        ubicaciones.add(nuevaUbicacion);
        System.out.println("Ubicación creada: " + nuevaUbicacion.toString());
        return nuevaUbicacion;
    }


    public Evento crearEvento(Organizador organizador, String nombreEvento, String descripcionEvento,
                              LocalDate fechaEvento, double duracionEvento, Categoria categoria, Ubicacion ubicacion) {

        if (!organizadores.contains(organizador)) {
            System.out.println("Error: El organizador proporcionado no existe en el sistema.");
            return null;
        }
        if (!categorias.contains(categoria)) {
            System.out.println("Error: La categoría proporcionada no existe en el sistema.");
            return null;
        }
        if (!ubicaciones.contains(ubicacion)) {
            System.out.println("Error: La ubicación proporcionada no existe en el sistema.");
            return null;
        }
        if (fechaEvento.isBefore(LocalDate.now())) {
            System.out.println("Error: La fecha del evento no puede ser en el pasado.");
            return null;
        }

        Evento nuevoEvento = new Evento(nombreEvento, descripcionEvento, fechaEvento, duracionEvento, categoria, ubicacion, organizador);
        eventos.add(nuevoEvento);
        organizador.añadirEventoOrganizado(nuevoEvento);
        System.out.println("Evento creado: " + nuevoEvento.getNombreEvento() + " por " + organizador.getNombreOrganizador());
        return nuevoEvento;
    }


    public List<Evento> getTodosLosEventos() {
        return new ArrayList<>(eventos);
    }

    public Optional<Evento> buscarEventoPorId(int idEvento) {
        return eventos.stream().filter(e -> e.getIdEvento() == idEvento).findFirst();
    }

    public Optional<Usuario> buscarUsuarioPorId(int idUsuario) {
        return usuarios.stream().filter(u -> u.getIdUsuario() == idUsuario).findFirst();
    }

    public Optional<Organizador> buscarOrganizadorPorId(int idOrganizador) {
        return organizadores.stream().filter(o -> o.getIdOrganizador() == idOrganizador).findFirst();
    }

    public Optional<Categoria> buscarCategoriaPorNombre(String nombreCategoria) {
        return categorias.stream().filter(c -> c.getNombreCategoria().equalsIgnoreCase(nombreCategoria)).findFirst();
    }


    public List<Evento> buscarEventosPorCategoria(String nombreCategoria) {
        List<Evento> eventosFiltrados = new ArrayList<>();
        Optional<Categoria> categoriaBuscada = buscarCategoriaPorNombre(nombreCategoria);

        if (categoriaBuscada.isPresent()) {
            for (Evento evento : eventos) {
                if (evento.getCategoria() != null && evento.getCategoria().getIdCategoria() == categoriaBuscada.get().getIdCategoria()) {
                    eventosFiltrados.add(evento);
                }
            }
        } else {
            System.out.println("Categoría '" + nombreCategoria + "' no encontrada.");
        }
        return eventosFiltrados;
    }

   
    public boolean inscribirUsuarioAEvento(Usuario usuario, Evento evento) {
    if (!usuarios.contains(usuario) || !eventos.contains(evento)) {
        System.out.println("Error de inscripción: Usuario o Evento no válidos.");
        return false;
    }

    boolean yaInscritoActivo = inscripciones.stream()
        .anyMatch(i -> i.getUsuario().equals(usuario) && i.getEvento().equals(evento) && i.getEstadoInscripcion() == EstadoInscripcion.ACTIVA);

    if (yaInscritoActivo) {
        System.out.println(usuario.getNombreUsuario() + " ya está inscrito y activo en " + evento.getNombreEvento() + ".");
        return false;
    }

    Inscripcion nuevaInscripcion = new Inscripcion(usuario, evento, LocalDate.now());
    inscripciones.add(nuevaInscripcion);

    System.out.println(usuario.getNombreUsuario() + " se ha inscrito en el evento: " + evento.getNombreEvento());
    return true;
}

    public boolean cancelarInscripcion(Usuario usuario, Evento evento) {
        Optional<Inscripcion> inscripcionOpt = inscripciones.stream()
            .filter(i -> i.getUsuario().equals(usuario) && i.getEvento().equals(evento) && i.getEstadoInscripcion() == EstadoInscripcion.ACTIVA)
            .findFirst();

        if (inscripcionOpt.isPresent()) {
            Inscripcion inscripcionACancelar = inscripcionOpt.get();
            inscripcionACancelar.setEstadoInscripcion(EstadoInscripcion.CANCELADA);

            usuario.removerInscripcion(inscripcionACancelar);
            evento.removerInscripcion(inscripcionACancelar);
            System.out.println(usuario.getNombreUsuario() + " ha cancelado su inscripción a " + evento.getNombreEvento() + ".");
            return true;
        } else {
            System.out.println("No se encontró una inscripción activa para " + usuario.getNombreUsuario() + " en " + evento.getNombreEvento() + ".");
            return false;
        }
    }

   
    public boolean modificarEvento(Organizador organizador, Evento eventoAModificar,
                                   String nuevoNombre, String nuevaDescripcion, LocalDate nuevaFecha,
                                   Double nuevaDuracion, Categoria nuevaCategoria, Ubicacion nuevaUbicacion) {
        if (!eventos.contains(eventoAModificar) || !eventoAModificar.getOrganizador().equals(organizador)) {
            System.out.println("Error: El evento no existe o el organizador no tiene permisos para modificarlo.");
            return false;
        }

        if (nuevaFecha != null && nuevaFecha.isBefore(LocalDate.now())) {
            System.out.println("Error: La nueva fecha del evento no puede ser en el pasado.");
            return false;
        }
        if (nuevaCategoria != null && !categorias.contains(nuevaCategoria)) {
            System.out.println("Error: La nueva categoría no existe en el sistema.");
            return false;
        }
        if (nuevaUbicacion != null && !ubicaciones.contains(nuevaUbicacion)) {
            System.out.println("Error: La nueva ubicación no existe en el sistema.");
            return false;
        }

        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            eventoAModificar.setNombreEvento(nuevoNombre);
        }
        if (nuevaDescripcion != null && !nuevaDescripcion.isEmpty()) {
            eventoAModificar.setDescripcionEvento(nuevaDescripcion);
        }
        if (nuevaFecha != null) {
            eventoAModificar.setFechaEvento(nuevaFecha);
        }
        if (nuevaDuracion != null) {
            eventoAModificar.setDuracionEvento(nuevaDuracion);
        }
        if (nuevaCategoria != null) {
            eventoAModificar.setCategoria(nuevaCategoria);
        }
        if (nuevaUbicacion != null) {
            eventoAModificar.setUbicacion(nuevaUbicacion);
        }

        System.out.println("Evento '" + eventoAModificar.getNombreEvento() + "' modificado por " + organizador.getNombreOrganizador() + ".");
        return true;
    }

    public Optional<Usuario> loginUsuario(String email, String contraseña) {
        return usuarios.stream()
                       .filter(u -> u.getEmailUsuario().equalsIgnoreCase(email) && u.getContraseñaUsuario().equals(contraseña))
                       .findFirst();
    }

    public Optional<Organizador> loginOrganizador(String email, String contraseña) {
        return organizadores.stream()
                            .filter(o -> o.getEmailOrganizador().equalsIgnoreCase(email) && o.getContraseñaOrganizador().equals(contraseña))
                            .findFirst();
    }

    public List<Usuario> getUsuarios() { return new ArrayList<>(usuarios); }
    public List<Organizador> getOrganizadores() { return new ArrayList<>(organizadores); }
    public List<Categoria> getCategorias() { return new ArrayList<>(categorias); }
    public List<Ubicacion> getUbicaciones() { return new ArrayList<>(ubicaciones); }
    public List<Inscripcion> getInscripciones() { return new ArrayList<>(inscripciones); }

}
