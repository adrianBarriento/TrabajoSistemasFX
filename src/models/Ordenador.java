package models;

public class Ordenador {
    private String nombre;
    private int idProducto, idEscandallo;

    public Ordenador(String nombre, int idProducto, int idEscandallo) {
        this.nombre = nombre;
        this.idProducto = idProducto;
        this.idEscandallo = idEscandallo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdEscandallo() {
        return idEscandallo;
    }

    public void setIdEscandallo(int idEscandallo) {
        this.idEscandallo = idEscandallo;
    }
}
