package models;

public class Employe {
    private String nombre;
    private String apellido;
    private long numSegSocial;
    private int sueldo;
    private String dni;


    public Employe(String nombre, String apellido, long numSegSocial, int sueldo, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numSegSocial = numSegSocial;
        this.sueldo = sueldo;
        this.dni = dni;
    }

    public Employe() {
        this.nombre = null;
        this.apellido = null;
        this.numSegSocial = 0;
        this.sueldo = 0;
        this.dni = null;
    }

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

    public long getNumSegSocial() {
        return numSegSocial;
    }

    public void setNumSegSocial(long numSegSocial) {
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
