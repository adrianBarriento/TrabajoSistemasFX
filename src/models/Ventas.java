package models;

import java.sql.*;

public class Ventas {
    Common common = new Common();
    private int cantidad;



    private int factura;
    private int idCliente;
    private float precio;
    private float precioUnitario;
    private String productoString, clienteString, vendedorString;
    private Date fecha;

    public Ventas(float precioUnitario, String productoString, String clienteString, String vendedorString, int producto, int cantidad, int factura) {
        this.precioUnitario = precioUnitario;
        this.productoString = productoString;
        this.clienteString = clienteString;
        this.vendedorString = vendedorString;
        this.cantidad = cantidad;
        this.factura = factura;
        precio = obtenerPrecio(producto, cantidad);
    }
    public Ventas(int idCliente, Date fecha, int factura) {
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.factura = factura;
    }


    public Ventas(){}

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

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    private float obtenerPrecio(int producto, int cantidad){
        Common common = new Common();
        Connection connection = common.getConexion();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT PrecioVenta FROM productos WHERE Id_Producto = '" + producto + "'");
            while(resultSet.next()){
                precio = resultSet.getFloat("PrecioVenta");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return precio*cantidad;
    }

    private String obtenerCliente(int cliente){
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

    private String obtenerProducto(int producto){
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

    private String obtenerVendedor(int vendedor){
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
