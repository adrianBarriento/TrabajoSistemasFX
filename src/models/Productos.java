package models;

public class Productos {
    private int idProducto;
    private boolean nuevo;
    private String tipoProducto;
    private int stock;
    private String marca;
    private String modelo;
    private float precioCompra;
    private float precioVenta;

    public Productos(int idProducto, boolean nuevo, String tipoProducto, int stock, String marca, String modelo, float precioCompra, float precioVenta) {
        this.idProducto = idProducto;
        this.nuevo = nuevo;
        this.tipoProducto = tipoProducto;
        this.stock = stock;
        this.marca = marca;
        this.modelo = modelo;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public Productos(){}

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
}
