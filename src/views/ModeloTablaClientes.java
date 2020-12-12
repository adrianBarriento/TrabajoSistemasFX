package views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Clientes;
import models.Common;
import models.Employe;

import java.sql.*;

public class ModeloTablaClientes {
    @FXML
    private TableColumn<Clientes, String> columnaNombre = new TableColumn<>("Nombre");
    @FXML
    private TableColumn<Clientes, String> columnaApellidos= new TableColumn<>("Apellidos");
    @FXML
    private TableColumn<Clientes, String> columnaDireccion = new TableColumn<>("Direccion");
    @FXML
    private TableColumn<Clientes, String> columnaPoblacion = new TableColumn<>("Poblacion");
    @FXML
    private TableColumn<Clientes, String> columnaEmail = new TableColumn<>("Email");

    public void crearTablaClientes(TableView id_tablaClientes){

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        this.columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        this.columnaPoblacion.setCellValueFactory(new PropertyValueFactory<>("Poblacion"));
        this.columnaEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

        Common c =new Common();
        ObservableList<Clientes> data = c.obtenerClientes();

        id_tablaClientes.setItems(data);
        id_tablaClientes.getColumns().addAll(columnaNombre, columnaApellidos, columnaDireccion, columnaPoblacion, columnaEmail);

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

    public void modificarCliente(TableView<Clientes> id_tablaClientes, TextField nombreCliente, TextField apellidoCliente, TextField direccionCliente, TextField poblacionCliente, TextField emailCliente){
        Clientes clienteModificar = id_tablaClientes.getSelectionModel().getSelectedItem();

        if(clienteModificar != null){
            Connection conexion=new Common().getConexion();
            PreparedStatement query;
            try {
                query = conexion.prepareStatement("UPDATE cliente SET Nombre = ?, Apellidos = ?, Direccion = ?, Poblacion = ?, Email = ? WHERE Email ='" + clienteModificar.getEmail() + "'");

                query.setString(1, nombreCliente.getText());
                query.setString(2, apellidoCliente.getText());
                query.setString(3, direccionCliente.getText());
                query.setString(4, poblacionCliente.getText());
                query.setString(5, emailCliente.getText());
                query.execute();
                new Common().vtnMensajeExitoInsercion();

            } catch (SQLException e) {
                new Common().vtnAlertaError();
            }
        } else {
            new Common().vtnAlertaError();
        }
    }
    public void newCliente(TextField nombreCliente, TextField apellidoCliente, TextField direccionCliente, TextField poblacionCliente, TextField emailCliente){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO clientes(Nombre, Apellidos, Direccion, Poblacion, Email) VALUES (?,?,?,?,?)");

            query.setString(1, nombreCliente.getText());
            query.setString(2, apellidoCliente.getText());
            query.setString(3, direccionCliente.getText());
            query.setString(4, poblacionCliente.getText());
            query.setString(5, emailCliente.getText());

            System.out.println(nombreCliente.getText() + " " + apellidoCliente.getText() + " " + direccionCliente.getText() + " " + poblacionCliente.getText() + " " + emailCliente.getText());
            query.execute();
            new Common().vtnMensajeExitoInsercion();

        } catch (SQLException e) {
            new Common().vtnAlertaError();
            e.printStackTrace();
        }
    }

}
