package models;

import java.util.Date;

public class Stocks {
    private String marca;
    private String modelo;
    private Date fecha;
    private int movimientos;

    public Stocks(){ }

    public Stocks(String marca, String modelo, Date fecha, int movimientos) {
        this.marca = marca;
        this.modelo = modelo;
        this.fecha = fecha;
        this.movimientos = movimientos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }
}
