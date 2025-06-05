package com.indra.eventossostenibles;

import main.java.com.indra.eventossostenibles.model.Categoria;
import main.java.com.indra.eventossostenibles.model.Evento;
import main.java.com.indra.eventossostenibles.model.Organizador;
import main.java.com.indra.eventossostenibles.model.Ubicacion;
import main.java.com.indra.eventossostenibles.model.Usuario;
import main.java.com.indra.eventossostenibles.service.PortalManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PortalManagerTest {

    private PortalManager portalManager;

    @BeforeEach
    void setUp() {
        portalManager = new PortalManager();

        Usuario.resetNextId();
        Organizador.resetNextId();
        Evento.resetNextId();
        Categoria.resetNextId();
        Ubicacion.resetNextId();
    }

    @Test
    void testCrearUsuarioYLoginExitoso() {
        System.out.println("--- Ejecutando testCrearUsuarioYLoginExitoso ---");
        Usuario nuevoUsuario = portalManager.crearUsuario("Ana Prueba", "ana.prueba@mail.com", "passTest1");
        assertNotNull(nuevoUsuario, "El usuario no debería ser nulo al crearlo");
        assertEquals(1, nuevoUsuario.getIdUsuario(), "El ID del primer usuario debe ser 1");
        assertEquals("ana.prueba@mail.com", nuevoUsuario.getEmailUsuario(), "El email del usuario debe coincidir");
        System.out.println("Usuario creado: " + nuevoUsuario.getNombreUsuario() + " (ID: " + nuevoUsuario.getIdUsuario() + ")");

        Optional<Usuario> usuarioLogueado = portalManager.loginUsuario("ana.prueba@mail.com", "passTest1");
        assertTrue(usuarioLogueado.isPresent(), "El usuario debería poder iniciar sesión con credenciales correctas");
        assertEquals(nuevoUsuario.getIdUsuario(), usuarioLogueado.get().getIdUsuario(), "El ID del usuario logueado debe coincidir");
        System.out.println("Login exitoso para: " + usuarioLogueado.get().getNombreUsuario());
    }

    @Test
    void testCrearOrganizadorYEvento() {
        System.out.println("--- Ejecutando testCrearOrganizadorYEvento ---");
        Organizador organizador = portalManager.crearOrganizador("Org Eventos Eco", "eco@org.com", "987654321", "orgPass");
        assertNotNull(organizador, "El organizador no debería ser nulo");
        assertEquals(1, organizador.getIdOrganizador(), "El ID del primer organizador debe ser 1");
        System.out.println("Organizador creado: " + organizador.getNombreOrganizador() + " (ID: " + organizador.getIdOrganizador() + ")");

        Categoria categoria = portalManager.crearCategoria("Taller de Reciclaje");
        assertNotNull(categoria, "La categoría no debería ser nula");
        assertEquals("Taller de Reciclaje", categoria.getNombreCategoria());
        System.out.println("Categoría creada: " + categoria.getNombreCategoria());

        Ubicacion ubicacion = portalManager.crearUbicacion("Presencial", "Calle Falsa 123", null);
        assertNotNull(ubicacion, "La ubicación no debería ser nula");
        assertEquals("Presencial", ubicacion.getTipoUbicacion());
        System.out.println("Ubicación creada: " + ubicacion.getDireccionUbicacion());

        Evento evento = portalManager.crearEvento(
            organizador,
            "Taller de Compostaje Urbano",
            "Aprende a compostar en casa.",
            LocalDate.of(2025, 7, 15),
            3.0,
            categoria,
            ubicacion
        );
        assertNotNull(evento, "El evento no debería ser nulo");
        assertEquals(1, evento.getIdEvento(), "El ID del primer evento debe ser 1");
        assertEquals("Taller de Compostaje Urbano", evento.getNombreEvento());
        assertEquals(organizador, evento.getOrganizador(), "El organizador del evento debe coincidir");
        assertTrue(portalManager.listarEventos().contains(evento), "El evento debe estar en la lista de eventos");
        System.out.println("Evento creado: " + evento.getNombreEvento());
    }

    @Test
    void testInscripcionYCancelacion() {
        System.out.println("--- Ejecutando testInscripcionYCancelacion ---");
        Usuario user = portalManager.crearUsuario("Participante", "participante@mail.com", "pass");
        Organizador org = portalManager.crearOrganizador("OrgDemo", "demo@org.com", "111", "pass");
        Categoria cat = portalManager.crearCategoria("Conferencia Online");
        Ubicacion ubi = portalManager.crearUbicacion("Online", null, "http://zoom.link");
        Evento event = portalManager.crearEvento(org, "Webinar Sostenible", "Charla de expertos", LocalDate.of(2025, 9, 1), 1.5, cat, ubi);

        boolean inscrito = portalManager.inscribirUsuarioAEvento(user, event);
        assertTrue(inscrito, "La inscripción debería ser exitosa");
        assertEquals(1, portalManager.listarInscripciones().size(), "Debería haber una inscripción");
        System.out.println("Inscripción exitosa para " + user.getNombreUsuario() + " en " + event.getNombreEvento());

        boolean inscritoDuplicado = portalManager.inscribirUsuarioAEvento(user, event);
        assertFalse(inscritoDuplicado, "La inscripción duplicada no debería ser exitosa");
        assertEquals(1, portalManager.listarInscripciones().size(), "El número de inscripciones no debería cambiar");
        System.out.println("Intento de inscripción duplicada: " + (inscritoDuplicado ? "exitoso inesperado" : "fallido como se espera"));

        boolean cancelado = portalManager.cancelarInscripcion(user.getIdUsuario(), event.getIdEvento());
        assertTrue(cancelado, "La cancelación de la inscripción debería ser exitosa");
        assertEquals(0, portalManager.listarInscripciones().size(), "No debería haber inscripciones después de cancelar");
        System.out.println("Cancelación de inscripción exitosa.");
    }
}