package models;

public class Clientes {
    private int idCliente;
    private String nombre;
    private String apellidos;
    private int cod_postal;
    private String email;

    public Clientes(int idCliente, String nombre, String apellidos, int cod_postal, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cod_postal = cod_postal;
        this.email = email;
    }

    public Clientes(){}

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(int cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {this.email = email; }
}
