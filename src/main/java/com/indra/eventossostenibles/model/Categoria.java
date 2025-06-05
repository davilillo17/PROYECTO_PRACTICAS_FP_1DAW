package main.java.com.indra.eventossostenibles.model;

public class Categoria {
    private static int nextId = 1;
    private int idCategoria;
    private String nombreCategoria;

    public Categoria(String nombreCategoria) {
        this.idCategoria = nextId++;
        this.nombreCategoria = nombreCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public static void resetNextId() {
        nextId = 1;
    }

    @Override
    public String toString() {
        return "Categoria [ID=" + idCategoria + ", Nombre=" + nombreCategoria + "]";
    }
}