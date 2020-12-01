package models;

public class Proveedores {
    private String nombre;
    private String articulo;

    public Proveedores(String nombre, String articulo) {
        this.nombre = nombre;
        this.articulo = articulo;
    }

    public Proveedores(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }
}
