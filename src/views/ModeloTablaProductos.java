package views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloTablaProductos {
    @FXML
    private TableColumn<Productos, Boolean> columnaNuevo = new TableColumn<>("Nuevo");
    @FXML
    private TableColumn<Productos, String> columnaTipoProducto= new TableColumn<>("Tipo");
    @FXML
    private TableColumn<Productos, Integer> columnaStock = new TableColumn<>("Stock");
    @FXML
    private TableColumn<Productos, String> columnaMarca = new TableColumn<>("Marca");
    @FXML
    private TableColumn<Productos, String> columnaModelo = new TableColumn<>("Modelo");
    @FXML
    private TableColumn<Productos, Float> columnaPrecioCompra = new TableColumn<>("Precio de compra");
    @FXML
    private TableColumn<Productos, Float> columnaPrecioVenta = new TableColumn<>("Precio de venta");


    Common c =new Common();

    public void crearTablaProductos(TableView<Productos> id_tablaProductos){

        this.columnaNuevo.setCellValueFactory(new PropertyValueFactory<>("Nuevo"));
        this.columnaTipoProducto.setCellValueFactory(new PropertyValueFactory<>("TipoProducto"));
        this.columnaStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        this.columnaMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        this.columnaModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        this.columnaPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("PrecioCompra"));
        this.columnaPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("PrecioVenta"));

        id_tablaProductos.getColumns().addAll(columnaNuevo, columnaTipoProducto, columnaStock, columnaMarca, columnaModelo, columnaPrecioCompra, columnaPrecioVenta);

    }

    public void llenarTabla(TableView id_tablaProductos){
        ObservableList<Productos> data = c.obtenerProductos();
        id_tablaProductos.setItems(data);
    }

    public void borrarProductos (TableView<Productos> id_tablaProductos){
        Productos producoBorrar = id_tablaProductos.getSelectionModel().getSelectedItem();
        id_tablaProductos.getItems().remove(producoBorrar);

        Common commmon = new Common();
        Connection conex = commmon.getConexion();
        try {
            Statement consulta = conex.createStatement();
            int valor = consulta.executeUpdate("DELETE FROM productos WHERE Id_Producto= " + producoBorrar.getIdProducto());
            if(valor == 1){
                commmon.vtnMensajeExitoInsercion();
            } else {
                commmon.vtnAlertaError();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void newProducto(TableView<Productos> id_tablaProductos, Boolean nuevo, ComboBox tipoProducto, TextField stock, TextField marca, TextField modelo, TextField precioCompra, TextField precioVenta){

        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO productos(Nuevo, TipoProducto, Stock, Marca, Modelo, PrecioCompra, PrecioVenta) VALUES (?, ?, ?, ?, ?, ?, ?)" );

            query.setBoolean(1, nuevo);
            query.setString(2, String.valueOf(tipoProducto.getValue()));
            query.setInt(3, Integer.parseInt(stock.getText()));
            query.setString(4, marca.getText());
            query.setString(5, modelo.getText());
            query.setFloat(6, Float.parseFloat(precioCompra.getText()));
            query.setFloat(7, Float.parseFloat(precioVenta.getText()));

            query.execute();
            ObservableList<Productos> data = c.obtenerProductos();
            id_tablaProductos.setItems(data);
            new Common().vtnMensajeExitoInsercion();
        } catch (SQLException e) {
            new Common().vtnAlertaError();
        }
    }
    public void modificarProducto(TableView<Productos> id_tablaProductos, Boolean nuevo, ComboBox tipoProducto, TextField stock, TextField marca, TextField modelo, TextField precioCompra, TextField precioVenta){

        Productos productosModificar = id_tablaProductos.getSelectionModel().getSelectedItem();
        int id =0;
        if(productosModificar != null){
            Connection conexion=new Common().getConexion();
            PreparedStatement query;
            try {
                query = conexion.prepareStatement("UPDATE productos SET Nuevo = ?, TipoProducto = ?, Stock = ?, Marca = ?, Modelo = ?, PrecioCompra = ?, PrecioVenta = ? WHERE Id_Producto = " + productosModificar.getIdProducto());

                query.setBoolean(1, nuevo);
                query.setString(2, String.valueOf(tipoProducto.getValue()));
                query.setInt(3, Integer.parseInt(stock.getText()));
                query.setString(4, marca.getText());
                query.setString(5, modelo.getText());
                query.setFloat(6, Float.parseFloat(precioCompra.getText()));
                query.setFloat(7, Float.parseFloat(precioVenta.getText()));

                query.execute();
                ObservableList<Productos> data = c.obtenerProductos();
                id_tablaProductos.setItems(data);
                new Common().vtnMensajeExitoInsercion();
            } catch (SQLException e) {
                new Common().vtnAlertaError();
                e.printStackTrace();
            }
        } else {
            new Common().vtnAlertaError();
        }
    }
}
