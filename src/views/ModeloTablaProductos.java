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

        System.out.println("productos:  "+id_tablaProductos.getWidth());
        this.columnaTipoProducto.setCellValueFactory(new PropertyValueFactory<>("TipoProducto"));
        this.columnaTipoProducto.setPrefWidth(92.56);
        this.columnaStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        this.columnaStock.setPrefWidth(62.00);
        this.columnaMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        this.columnaMarca.setPrefWidth(92.56);
        this.columnaModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        this.columnaModelo.setPrefWidth(92.56);
        this.columnaPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("PrecioCompra"));
        this.columnaPrecioCompra.setPrefWidth(112.56);
        this.columnaPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("PrecioVenta"));
        this.columnaPrecioVenta.setPrefWidth(102.56);

        id_tablaProductos.getColumns().addAll(columnaTipoProducto, columnaStock, columnaMarca, columnaModelo, columnaPrecioCompra, columnaPrecioVenta);

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

    public void newProducto(TableView<Productos> id_tablaProductos, ComboBox tipoProducto, TextField marca, TextField modelo){

        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO productos(TipoProducto, Marca, Modelo) VALUES ( ?, ?, ?)" );


            query.setString(1, String.valueOf(tipoProducto.getValue()));
            query.setString(2, marca.getText());
            query.setString(3, modelo.getText());

            query.execute();
            ObservableList<Productos> data = c.obtenerProductos();
            id_tablaProductos.setItems(data);
            new Common().vtnMensajeExitoInsercion();
        } catch (SQLException e) {
            new Common().vtnAlertaError();
        }
    }
    public void modificarProducto(TableView<Productos> id_tablaProductos, ComboBox tipoProducto, TextField marca, TextField modelo){

        Productos productosModificar = id_tablaProductos.getSelectionModel().getSelectedItem();
        int id =0;
        if(productosModificar != null){
            Connection conexion=new Common().getConexion();
            PreparedStatement query;
            try {
                query = conexion.prepareStatement("UPDATE productos SET TipoProducto = ?, Marca = ?, Modelo = ? WHERE Id_Producto = " + productosModificar.getIdProducto());
                query.setString(1, String.valueOf(tipoProducto.getValue()));
                query.setString(2, marca.getText());
                query.setString(3, modelo.getText());

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
