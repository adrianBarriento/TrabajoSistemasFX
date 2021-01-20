package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Compras {
    Common common = new Common();
    private int cantidad;
    private float precio;
    private float precioUnitario;
    private String productoString, proveedorString;

    public Compras(float precioUnitario, String productoString, String proveedorString, int producto, int cantidad) {
        this.precioUnitario = precioUnitario;
        this.productoString = productoString;
        this.proveedorString = proveedorString;
        this.cantidad = cantidad;
        precio = obtenerPrecio(producto, cantidad);
    }

    public Compras(){}

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getProductoString() {
        return productoString;
    }

    public void setProductoString(String productoString) {
        this.productoString = productoString;
    }

    public String getProveedorString() {
        return proveedorString;
    }

    public void setProveedorString(String proveedorString) {
        this.proveedorString = proveedorString;
    }

    private float obtenerPrecio(int producto, int cantidad){
        Common common = new Common();
        Connection connection = common.getConexion();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT PrecioCompra FROM productos WHERE Id_Producto = '" + producto + "'");
            while(resultSet.next()){
                precio = resultSet.getFloat("PrecioCompra");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return precio*cantidad;
    }


}
