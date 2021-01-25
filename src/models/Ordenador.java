package models;

public class Ordenador {
    private String placaBase, procesador, memoriaRAM, ventilador, discoDuro;

    public Ordenador(String placaBase, String procesador, String memoriaRAM, String ventilador, String discoDuro) {
        this.placaBase = placaBase;
        this.procesador = procesador;
        this.memoriaRAM = memoriaRAM;
        this.ventilador = ventilador;
        this.discoDuro = discoDuro;
    }

    public Ordenador(){}

    public String getPlacaBase() {
        return placaBase;
    }

    public void setPlacaBase(String placaBase) {
        this.placaBase = placaBase;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getMemoriaRAM() {
        return memoriaRAM;
    }

    public void setMemoriaRAM(String memoriaRAM) {
        this.memoriaRAM = memoriaRAM;
    }

    public String getVentilador() {
        return ventilador;
    }

    public void setVentilador(String ventilador) {
        this.ventilador = ventilador;
    }

    public String getDiscoDuro() {
        return discoDuro;
    }

    public void setDiscoDuro(String discoDuro) {
        this.discoDuro = discoDuro;
    }
}
