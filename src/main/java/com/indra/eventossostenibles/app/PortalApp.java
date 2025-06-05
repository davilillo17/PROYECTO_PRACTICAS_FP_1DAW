package main.java.com.indra.eventossostenibles.app;

import main.java.com.indra.eventossostenibles.model.*;
import main.java.com.indra.eventossostenibles.service.PortalManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PortalApp {

    private static PortalManager portalManager = new PortalManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Iniciando Portal de Eventos Sostenibles ---");
        cargarDatosIniciales();

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = obtenerOpcionMenu();

            switch (opcion) {
                case 1:
                    menuUsuarios();
                    break;
                case 2:
                    menuOrganizadores();
                    break;
                case 3:
                    menuEventos();
                    break;
                case 4:
                    menuInscripciones();
                    break;
                case 0:
                    System.out.println("Saliendo del portal. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
            System.out.println("\n");
        } while (opcion != 0);

        scanner.close();
    }

    private static void cargarDatosIniciales() {

        Categoria conf = portalManager.crearCategoria("Conferencia");
        Categoria taller = portalManager.crearCategoria("Taller");
        Categoria limpieza = portalManager.crearCategoria("Limpieza de Playas");
        Categoria feria = portalManager.crearCategoria("Feria");

        Ubicacion online = portalManager.crearUbicacion("Online", null, "https://zoom.link.com/eco");
        Ubicacion civicCenter = portalManager.crearUbicacion("Presencial", "Av. Principal 123, Madrid", null);
        Ubicacion playaSol = portalManager.crearUbicacion("Presencial", "Playa del Sol, Málaga", null);

        Organizador org1 = portalManager.crearOrganizador("EcoSolutions", "eco@solutions.com", "912345678", "passEco");
        Organizador org2 = portalManager.crearOrganizador("GreenFuture", "green@future.org", "600112233", "passGreen");

        Evento ev1 = portalManager.crearEvento(org1, "Cumbre de Sostenibilidad 2025", "La mayor cumbre anual sobre sostenibilidad.", LocalDate.of(2025, 7, 20), 4.5, conf, online);
        Evento ev2 = portalManager.crearEvento(org1, "Taller de Compostaje Urbano", "Aprende a hacer compost en casa.", LocalDate.of(2025, 8, 5), 2.0, taller, civicCenter);
        portalManager.crearEvento(org2, "Jornada Limpieza Playa Sol", "Voluntariado para limpiar nuestra costa.", LocalDate.of(2025, 6, 15), 3.0, limpieza, playaSol);
        portalManager.crearEvento(org2, "Feria de Productos Ecológicos", "Descubre lo último en productos sostenibles.", LocalDate.of(2025, 9, 1), 8.0, feria, civicCenter);


        Usuario user1 = portalManager.crearUsuario("Ana Lopez", "ana@mail.com", "passAna");
        Usuario user2 = portalManager.crearUsuario("Beto Sanchez", "beto@mail.com", "passBeto");

        if (user1 != null && ev1 != null) portalManager.inscribirUsuarioAEvento(user1, ev1);
        if (user2 != null && ev1 != null) portalManager.inscribirUsuarioAEvento(user2, ev1);
        if (user1 != null && ev2 != null) portalManager.inscribirUsuarioAEvento(user1, ev2);

        System.out.println("\n--- Datos iniciales cargados ---\n");
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("--- Menú Principal ---");
        System.out.println("1. Gestión de Usuarios");
        System.out.println("2. Gestión de Organizadores");
        System.out.println("3. Gestión de Eventos");
        System.out.println("4. Gestión de Inscripciones");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int obtenerOpcionMenu() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            scanner.next();
            System.out.print("Seleccione una opción: ");
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    private static void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n--- Menú Usuarios ---");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Ver todos los usuarios");
            System.out.println("3. Inscribir usuario a evento");
            System.out.println("4. Cancelar inscripción de usuario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = obtenerOpcionMenu();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de usuario: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Email de usuario: ");
                    String email = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = scanner.nextLine();
                    portalManager.crearUsuario(nombre, email, pass);
                    break;
                case 2:
                    System.out.println("\n--- Lista de Usuarios ---");
                    if (portalManager.getUsuarios().isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                    } else {
                        portalManager.getUsuarios().forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("ID del usuario: ");
                    int idUser = obtenerOpcionMenu();
                    System.out.print("ID del evento: ");
                    int idEv = obtenerOpcionMenu();

                    Optional<Usuario> userOpt = portalManager.buscarUsuarioPorId(idUser);
                    Optional<Evento> evOpt = portalManager.buscarEventoPorId(idEv);

                    if (userOpt.isPresent() && evOpt.isPresent()) {
                        portalManager.inscribirUsuarioAEvento(userOpt.get(), evOpt.get());
                    } else {
                        System.out.println("Usuario o Evento no encontrados.");
                    }
                    break;
                case 4:
                    System.out.print("ID del usuario: ");
                    int idUserCancel = obtenerOpcionMenu();
                    System.out.print("ID del evento a cancelar: ");
                    int idEvCancel = obtenerOpcionMenu();

                    Optional<Usuario> userCancelOpt = portalManager.buscarUsuarioPorId(idUserCancel);
                    Optional<Evento> evCancelOpt = portalManager.buscarEventoPorId(idEvCancel);

                    if (userCancelOpt.isPresent() && evCancelOpt.isPresent()) {
                        portalManager.cancelarInscripcion(userCancelOpt.get(), evCancelOpt.get());
                    } else {
                        System.out.println("Usuario o Evento no encontrados.");
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuOrganizadores() {
        int opcion;
        do {
            System.out.println("\n--- Menú Organizadores ---");
            System.out.println("1. Registrar nuevo organizador");
            System.out.println("2. Ver todos los organizadores");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = obtenerOpcionMenu();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de organizador: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Email de organizador: ");
                    String email = scanner.nextLine();
                    System.out.print("Teléfono de organizador: ");
                    String tel = scanner.nextLine();
                    System.out.print("Contraseña para el organizador: ");
                    String pass = scanner.nextLine();
                    portalManager.crearOrganizador(nombre, email, tel, pass);
                    break;
                case 2:
                    System.out.println("\n--- Lista de Organizadores ---");
                    if (portalManager.getOrganizadores().isEmpty()) {
                        System.out.println("No hay organizadores registrados.");
                    } else {
                        portalManager.getOrganizadores().forEach(System.out::println);
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuEventos() {
        int opcion;
        do {
            System.out.println("\n--- Menú Eventos ---");
            System.out.println("1. Crear nuevo evento");
            System.out.println("2. Ver todos los eventos");
            System.out.println("3. Buscar eventos por categoría");
            System.out.println("4. Modificar evento (solo organizadores)");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = obtenerOpcionMenu();

            switch (opcion) {
                case 1: // Crear nuevo evento
                    System.out.print("ID del organizador (debe estar registrado): ");
                    int idOrg = obtenerOpcionMenu();
                    Optional<Organizador> orgOpt = portalManager.buscarOrganizadorPorId(idOrg);

                    if (orgOpt.isPresent()) {
                        Organizador organizador = orgOpt.get();
                        System.out.print("Nombre del evento: ");
                        String nomEv = scanner.nextLine();
                        System.out.print("Descripción del evento: ");
                        String descEv = scanner.nextLine();
                        System.out.print("Fecha del evento (YYYY-MM-DD): ");
                        LocalDate fechaEv = LocalDate.parse(scanner.nextLine());
                        System.out.print("Duración del evento (horas, ej. 2.5): ");
                        double durEv = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Nombre de la categoría (ej. Conferencia, Taller): ");
                        String nomCat = scanner.nextLine();
                        Optional<Categoria> catOpt = portalManager.buscarCategoriaPorNombre(nomCat);
                        if (!catOpt.isPresent()) {
                            System.out.println("Categoría no encontrada. Creando nueva categoría...");
                            catOpt = Optional.of(portalManager.crearCategoria(nomCat));
                        }

                        System.out.print("Tipo de ubicación (Online/Presencial): ");
                        String tipoUbic = scanner.nextLine();
                        String dirUbic = null;
                        String enlaceUbic = null;
                        if ("Presencial".equalsIgnoreCase(tipoUbic)) {
                            System.out.print("Dirección de la ubicación: ");
                            dirUbic = scanner.nextLine();
                        } else if ("Online".equalsIgnoreCase(tipoUbic)) {
                            System.out.print("Enlace de la ubicación: ");
                            enlaceUbic = scanner.nextLine();
                        } else {
                            System.out.println("Tipo de ubicación no válido. Usando 'Presencial' sin dirección.");
                            tipoUbic = "Presencial";
                        }
                        Ubicacion ubicacion = portalManager.crearUbicacion(tipoUbic, dirUbic, enlaceUbic);

                        portalManager.crearEvento(organizador, nomEv, descEv, fechaEv, durEv, catOpt.get(), ubicacion);
                    } else {
                        System.out.println("Organizador no encontrado.");
                    }
                    break;
                case 2:
                    System.out.println("\n--- Lista de Eventos ---");
                    if (portalManager.getTodosLosEventos().isEmpty()) {
                        System.out.println("No hay eventos registrados.");
                    } else {
                        portalManager.getTodosLosEventos().forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("Nombre de la categoría a buscar: ");
                    String catBuscar = scanner.nextLine();
                    List<Evento> eventosEncontrados = portalManager.buscarEventosPorCategoria(catBuscar);
                    if (eventosEncontrados.isEmpty()) {
                        System.out.println("No se encontraron eventos en la categoría '" + catBuscar + "'.");
                    } else {
                        System.out.println("\n--- Eventos en la categoría '" + catBuscar + "' ---");
                        eventosEncontrados.forEach(System.out::println);
                    }
                    break;
              case 4:
                    System.out.print("ID del organizador (solo si es el creador del evento): ");
                    int idModOrg = obtenerOpcionMenu();
                    
                    Optional<Organizador> currentOrg = portalManager.buscarOrganizadorPorId(idModOrg);

                    if (currentOrg.isPresent()) {
                        System.out.print("ID del evento a modificar: ");
                        int idEvMod = obtenerOpcionMenu();
                        Optional<Evento> evModOpt = portalManager.buscarEventoPorId(idEvMod);

                        if (evModOpt.isPresent()) {
                            Evento eventoAModificar = evModOpt.get();
                            
             
                            if (!eventoAModificar.getOrganizador().equals(currentOrg.get())) {
                                System.out.println("Error: No tienes permisos para modificar este evento. Solo el organizador que lo creó puede hacerlo.");
                                break;
                            }

                            System.out.println("Modificando evento: " + eventoAModificar.getNombreEvento() + " (Organizador: " + currentOrg.get().getNombreOrganizador() + ")");
                            
                            System.out.print("Nuevo nombre (dejar vacío para no cambiar): ");
                            String nuevoNom = scanner.nextLine();
                            
                            System.out.print("Nueva descripción (dejar vacío para no cambiar): ");
                            String nuevaDesc = scanner.nextLine();

                            System.out.print("Nueva fecha (YYYY-MM-DD, dejar vacío para no cambiar): ");
                            String fechaStr = scanner.nextLine();
                            LocalDate nuevaFecha = fechaStr.isEmpty() ? null : LocalDate.parse(fechaStr);

                            System.out.print("Nueva duración (horas, ej. 2.5, 0 para no cambiar): ");
                            String duracionStr = scanner.nextLine();
                            Double nuevaDuracion = null;
                            if (!duracionStr.isEmpty() && Double.parseDouble(duracionStr) != 0) {
                                nuevaDuracion = Double.parseDouble(duracionStr);
                            }

                            Categoria nuevaCategoria = null;
                            System.out.print("Nueva categoría (dejar vacío para no cambiar, o el nombre de una existente/nueva): ");
                            String nomCat = scanner.nextLine();
                            if (!nomCat.isEmpty()) {
                                Optional<Categoria> catOpt = portalManager.buscarCategoriaPorNombre(nomCat);
                                if (catOpt.isPresent()) {
                                    nuevaCategoria = catOpt.get();
                                } else {
                                    System.out.println("Categoría '" + nomCat + "' no encontrada. Creando nueva categoría...");
                                    nuevaCategoria = portalManager.crearCategoria(nomCat);
                                }
                            }

                            Ubicacion nuevaUbicacion = null;
                            System.out.print("Nuevo tipo de ubicación (Online/Presencial, dejar vacío para no cambiar): ");
                            String tipoUbic = scanner.nextLine();
                            if (!tipoUbic.isEmpty()) {
                                String dirUbic = null;
                                String enlaceUbic = null;
                                if ("Presencial".equalsIgnoreCase(tipoUbic)) {
                                    System.out.print("Nueva dirección de la ubicación: ");
                                    dirUbic = scanner.nextLine();
                                } else if ("Online".equalsIgnoreCase(tipoUbic)) {
                                    System.out.print("Nuevo enlace de la ubicación: ");
                                    enlaceUbic = scanner.nextLine();
                                }
                                nuevaUbicacion = portalManager.crearUbicacion(tipoUbic, dirUbic, enlaceUbic);
                            }


                            portalManager.modificarEvento(currentOrg.get(), eventoAModificar,
                                nuevoNom.isEmpty() ? null : nuevoNom,
                                nuevaDesc.isEmpty() ? null : nuevaDesc,
                                nuevaFecha,
                                nuevaDuracion,
                                nuevaCategoria,
                                nuevaUbicacion);

                        } else {
                            System.out.println("Evento no encontrado.");
                        }
                    } else {
                        System.out.println("Organizador no encontrado o el ID no es válido.");
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuInscripciones() {
        int opcion;
        do {
            System.out.println("\n--- Menú Inscripciones ---");
            System.out.println("1. Ver todas las inscripciones");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = obtenerOpcionMenu();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Lista de Inscripciones ---");
                    if (portalManager.getInscripciones().isEmpty()) {
                        System.out.println("No hay inscripciones registradas.");
                    } else {
                        portalManager.getInscripciones().forEach(System.out::println);
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}