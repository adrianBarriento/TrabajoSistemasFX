package views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Common;
import models.Productos;
import models.Proveedores;
import models.Ventas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloTablaProveedores {
    @FXML
    private TableColumn<Proveedores, String> columnaNombre= new TableColumn<>("Nombre");

    Common c =new Common();

    public void crearTablaProveedores(TableView<Proveedores> id_tablaProveedores){
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        id_tablaProveedores.getColumns().addAll(columnaNombre);

    }

    public void llenarTabla(TableView id_tablaProveedores){
        ObservableList<Proveedores> data = c.obtenerProveedores();
        id_tablaProveedores.setItems(data);
    }
    public void newProveedor(TableView id_tablaProveedor, TextField nombreProveedor){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO proveedores(Nombre) VALUES (?)");

            query.setString(1, nombreProveedor.getText());
            query.execute();
            ObservableList<Proveedores> data = c.obtenerProveedores();

            id_tablaProveedor.setItems(data);
            c.vtnMensajeExitoInsercion();
        } catch (SQLException e) {
            new Common().vtnAlertaError();
            e.printStackTrace();

        }
    }

    public void modificarProveedores(TableView<Proveedores> id_tablaProveedores, TextField nombreProveedor){

        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO proveedores(Nombre) VALUES (?)" );

            query.setString(1, nombreProveedor.getText());


            query.execute();
            ObservableList<Proveedores> data = c.obtenerProveedores();
            id_tablaProveedores.setItems(data);
            new Common().vtnMensajeExitoInsercion();
        } catch (SQLException e) {
            new Common().vtnAlertaError();
            e.printStackTrace();
        }
    }

    public void borrarProveedores (TableView<Proveedores> id_tablaProoveedores){
        Proveedores proveedorBorrar = id_tablaProoveedores.getSelectionModel().getSelectedItem();
        id_tablaProoveedores.getItems().remove(proveedorBorrar);

        Common commmon = new Common();
        Connection conex = commmon.getConexion();
        try {
            Statement consulta = conex.createStatement();
            int valor = consulta.executeUpdate("DELETE FROM proveedores WHERE Id_Proveedor= " + proveedorBorrar.getId());
            if(valor == 1){
                commmon.vtnMensajeExitoInsercion();
            } else {
                commmon.vtnAlertaError();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
