package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Common;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Employe;

import java.sql.*;

public class ModeloTablaEmpleados {
    @FXML
    private TableColumn<Employe, String> columnaNombre = new TableColumn<>("Nombre");
    @FXML
    private TableColumn<Employe, String> columnaApellido= new TableColumn<>("Apellido");
    @FXML
    private TableColumn<Employe, Integer> columnaNumSS = new TableColumn<>("NumSegSocial");
    @FXML
    private TableColumn<Employe, Integer> columnaSueldo = new TableColumn<>("Sueldo");
    @FXML
    private TableColumn<Employe, String> columnaDni = new TableColumn<>("dni");

    Common c =new Common();
    public void crearTabla(TableView id_tabla){

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.columnaApellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        this.columnaNumSS.setCellValueFactory(new PropertyValueFactory<>("NumSegSocial"));
        this.columnaSueldo.setCellValueFactory(new PropertyValueFactory<>("Sueldo"));
        this.columnaDni.setCellValueFactory(new PropertyValueFactory<>("dni"));

        id_tabla.getColumns().addAll(columnaNombre, columnaApellido, columnaNumSS, columnaSueldo, columnaDni);

    }

    public void llenarTabla(TableView id_tabla){
        ObservableList<Employe> data = c.obtenerEmpleados();

        id_tabla.setItems(data);
    }

    public void borrar (TableView<Employe> id_tabla){
        Employe empleadoBorrar = id_tabla.getSelectionModel().getSelectedItem();
        id_tabla.getItems().remove(empleadoBorrar);

        Common commmon = new Common();
        Connection conex = commmon.getConexion();
        try {
            Statement consulta = conex.createStatement();
            int valor = consulta.executeUpdate("DELETE FROM personal WHERE DNI='" + empleadoBorrar.getDni() + "'");
            if(valor == 1){
                commmon.vtnMensajeExitoInsercion();
            } else {
                commmon.vtnAlertaError();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void modificar(TableView<Employe> id_tabla, TextField nombre, TextField apellido, TextField numSegSocial, TextField sueldo, TextField dni){
        Employe empleadoModificar = id_tabla.getSelectionModel().getSelectedItem();

        if(empleadoModificar != null){
            Connection conexion=new Common().getConexion();
            PreparedStatement query;
            try {
                query = conexion.prepareStatement("UPDATE personal SET Nombre = ?, Apellidos = ?, NumSegSocial = ?, Sueldo = ?, DNI = ? WHERE DNI ='" + empleadoModificar.getDni() + "'");

                query.setString(1, nombre.getText());
                query.setString(2, apellido.getText());
                query.setLong(3, Long.parseLong(numSegSocial.getText()));
                query.setInt(4, Integer.parseInt(sueldo.getText()));
                query.setString(5, dni.getText());
                query.execute();
                ObservableList<Employe> data = c.obtenerEmpleados();
                id_tabla.setItems(data);
                new Common().vtnMensajeExitoInsercion();

            } catch (SQLException e) {
                new Common().vtnAlertaError();
            }
        } else {
            new Common().vtnAlertaError();
        }

    }


}
