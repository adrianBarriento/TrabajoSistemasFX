package models;

public class Poblacion {
    private int cod_postal;
    private String poblacion, provincia;

    public Poblacion(int cod_postal, String poblacion, String provincia) {
        this.cod_postal = cod_postal;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    public Poblacion(){}

    public int getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(int cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
