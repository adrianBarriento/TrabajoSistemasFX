package views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Clientes;
import models.Common;
import models.Ventas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloTablaVentas {
    @FXML
    private TableColumn<Ventas, String> columnaCliente = new TableColumn<>("Cliente");
    @FXML
    private TableColumn<Ventas, String> columnaProducto= new TableColumn<>("Producto");
    @FXML
    private TableColumn<Ventas, String> columnaVendedor = new TableColumn<>("Vendedor");
    @FXML
    private TableColumn<Ventas, Integer> columnaCantidad = new TableColumn<>("Cantidad");
    @FXML
    private TableColumn<Ventas, Float> columnaPrecio = new TableColumn<>("Precio");

    public void crearTablaClientes(TableView id_tablaVentas){

        this.columnaCliente.setCellValueFactory(new PropertyValueFactory<>("Cliente"));
        this.columnaProducto.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        this.columnaVendedor.setCellValueFactory(new PropertyValueFactory<>("Vendedor"));
        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));

        Common c =new Common();
        ObservableList<Ventas> data = c.obtenerVentas();

        id_tablaVentas.setItems(data);
        id_tablaVentas.getColumns().addAll(columnaProducto, columnaCliente, columnaVendedor, columnaPrecio);

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
