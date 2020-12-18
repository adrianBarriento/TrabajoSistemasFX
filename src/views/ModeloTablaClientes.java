package views;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Clientes;
import models.Common;
import models.Poblacion;

import java.sql.*;
import java.util.ArrayList;

public class ModeloTablaClientes {
    @FXML
    private TableColumn<Clientes, String> columnaNombre = new TableColumn<>("Nombre");
    @FXML
    private TableColumn<Clientes, String> columnaApellidos= new TableColumn<>("Apellidos");
    @FXML
    private TableColumn<Clientes, String> columnaEmail = new TableColumn<>("Email");
    @FXML
    private TableColumn<Clientes, Integer> columnaPoblacion = new TableColumn<>("Codigo Postal");

    Common c =new Common();

    public void crearTablaClientes(TableView<Clientes> id_tablaClientes){

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        this.columnaEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        this.columnaPoblacion.setCellValueFactory(new PropertyValueFactory<>("cod_postal"));

        id_tablaClientes.getColumns().addAll(columnaNombre, columnaApellidos, columnaEmail, columnaPoblacion);

    }

    public void llenarTabla(TableView id_tablaClientes){
        ObservableList<Clientes> data = c.obtenerClientes();
        id_tablaClientes.setItems(data);
    }

    public void borrarCliente (TableView<Clientes> id_tablaClientes){
        Clientes clienteBorrar = id_tablaClientes.getSelectionModel().getSelectedItem();
        id_tablaClientes.getItems().remove(clienteBorrar);

        Common commmon = new Common();
        Connection conex = commmon.getConexion();
        try {
            Statement consulta = conex.createStatement();
            int valor = consulta.executeUpdate("DELETE FROM clientes WHERE Email='" + clienteBorrar.getEmail() + "'");
            if(valor == 1){
                commmon.vtnMensajeExitoInsercion();
            } else {
                commmon.vtnAlertaError();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void modificarCliente(TableView<Clientes> id_tablaClientes, TextField nombreCliente, TextField apellidoCliente, ComboBox poblacionCliente, TextField emailCliente){
        Clientes clienteModificar = id_tablaClientes.getSelectionModel().getSelectedItem();
        int codigoPostal =0;
        String poblacion = String.valueOf(poblacionCliente.getValue());
        ObservableList<Poblacion> poblaciones = new Common().obtenerPoblaciones();
        for(Poblacion p:poblaciones){
            if(poblacion.equalsIgnoreCase(p.getPoblacion())){
                codigoPostal=p.getCod_postal();
            }
        }
        if(clienteModificar != null){
            Connection conexion=new Common().getConexion();
            PreparedStatement query;
            try {
                query = conexion.prepareStatement("UPDATE `clientes` SET `Nombre` = ?, `Apellidos` = ?, `Email` = ?, `cod_postal` = ? WHERE `clientes`.`Email` = '" + clienteModificar.getEmail() + "'");
                query.setString(1, nombreCliente.getText());
                query.setString(2, apellidoCliente.getText());
                query.setString(3, emailCliente.getText());
                query.setInt(4, codigoPostal);

                query.execute();
                ObservableList<Clientes> data = c.obtenerClientes();
                id_tablaClientes.setItems(data);
                new Common().vtnMensajeExitoInsercion();

            } catch (SQLException e) {
                new Common().vtnAlertaError();
                e.printStackTrace();
            }
        } else {
            new Common().vtnAlertaError();
        }
    }
    public void newCliente(TableView id_tablaClientes, TextField nombreCliente, TextField apellidoCliente, ComboBox poblacionCliente, TextField emailCliente){

        String poblacion = String.valueOf(poblacionCliente.getValue());
        int codigoPostal=0;
        ObservableList<Poblacion> poblaciones = new Common().obtenerPoblaciones();
        for(Poblacion p:poblaciones){
            if(poblacion.equalsIgnoreCase(p.getPoblacion())){
                codigoPostal=p.getCod_postal();
            }
        }
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO clientes(Nombre, Apellidos, Email, cod_postal) VALUES (?,?,?,?)");

            query.setString(1, nombreCliente.getText());
            query.setString(2, apellidoCliente.getText());
            query.setString(3, emailCliente.getText());
            query.setInt(4, codigoPostal);


            query.execute();

            ObservableList<Clientes> data = c.obtenerClientes();
            id_tablaClientes.setItems(data);
            new Common().vtnMensajeExitoInsercion();

        } catch (SQLException e) {
            new Common().vtnAlertaError();
            e.printStackTrace();
        }
    }

}
