package models;

public class Proveedores {
    private int id;
    private String nombre;


    public Proveedores(int id, String nombre) {
        this.id=id;
        this.nombre=nombre;
    }

    public Proveedores(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
