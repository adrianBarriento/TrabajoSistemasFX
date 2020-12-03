package models;

public class Employe {
    private String nombre;
    private String apellido;
    private String numSegSocial;
    private int sueldo;
    private String dni;


    public Employe(String nombre, String apellido, String numSegSocial, int sueldo, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numSegSocial = numSegSocial;
        this.sueldo = sueldo;
        this.dni = dni;
    }

    public Employe() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumSegSocial() {
        return numSegSocial;
    }

    public void setNumSegSocial(String numSegSocial) {
        this.numSegSocial = numSegSocial;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
