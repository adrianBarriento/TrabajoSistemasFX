package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ventas {
    Common common = new Common();
    private int cantidad;
    private float precio;
    private String productoString, clienteString, vendedorString;

    public Ventas(int producto, int cliente, int vendedor, int cantidad) {
        clienteString = obtenerCliente(cliente);
        productoString = obtenerProducto(producto);
        vendedorString = obtenerVendedor(vendedor);
        this.cantidad = cantidad;
        precio = obtenerPrecio(producto);
    }

    public Ventas(){}

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

    public String getClienteString() {
        return clienteString;
    }

    public void setClienteString(String clienteString) {
        this.clienteString = clienteString;
    }

    public String getVendedorString() {
        return vendedorString;
    }

    public void setVendedorString(String vendedorString) {
        this.vendedorString = vendedorString;
    }

    public float obtenerPrecio(int producto){
        Common common = new Common();
        Connection connection = common.getConexion();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Precio FROM productos WHERE Id_Producto = '" + producto + "'");
            while(resultSet.next()){
                precio = resultSet.getFloat("Precio");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return precio;
    }

    public String obtenerCliente(int cliente){
        String nombre = null, apellidos = null;
        Connection connection = common.getConexion();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Nombre, Apellidos FROM clientes WHERE Id_Cliente = '" + cliente + "'");
            while(resultSet.next()){
                nombre = resultSet.getString("Nombre");
                apellidos = resultSet.getString("Apellidos");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return nombre + " " + apellidos;
    }

    public String obtenerProducto(int producto){
        Connection connection = common.getConexion();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Modelo FROM productos WHERE Id_Producto = '" + producto + "'");
            while(resultSet.next()){
                productoString = resultSet.getString("Modelo");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productoString;
    }

    public String obtenerVendedor(int vendedor){
        String nombre = null, apellidos = null;
        Connection connection = common.getConexion();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Nombre, Apellidos FROM personal WHERE Id_Personal = '" + vendedor + "'");
            while(resultSet.next()){
                nombre = resultSet.getString("Nombre");
                apellidos = resultSet.getString("Apellidos");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return nombre + " " + apellidos;
    }
}
