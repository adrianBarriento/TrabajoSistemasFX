package views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Common;
import models.Poblacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloTablaPoblaciones {
    @FXML
    private TableColumn<Poblacion, Integer> columnaCodPostal= new TableColumn<>("Codigo Postal");
    @FXML
    private TableColumn<Poblacion, String> columnaPoblacion = new TableColumn<>("Poblacion");
    @FXML
    private TableColumn<Poblacion, String> columnaProvincia = new TableColumn<>("Provincia");
    Common c =new Common();

    public void crearTablaPoblaciones(TableView id_tablaPoblaciones){
        this.columnaCodPostal.setCellValueFactory(new PropertyValueFactory<>("cod_postal"));
        this.columnaPoblacion.setCellValueFactory(new PropertyValueFactory<>("poblacion"));
        this.columnaProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));

        id_tablaPoblaciones.getColumns().addAll(columnaCodPostal, columnaPoblacion, columnaProvincia);
    }

    public void llenarTabla(TableView id_tablaPoblaciones){
        ObservableList<Poblacion> data = c.obtenerPoblaciones();
        for(Poblacion p:data){
            System.out.println(p.getCod_postal());
        }

        id_tablaPoblaciones.setItems(data);
    }

    public void borrarPoblacion (TableView<Poblacion> id_TablaPoblaciones){
        Poblacion clienteBorrar = id_TablaPoblaciones.getSelectionModel().getSelectedItem();
        id_TablaPoblaciones.getItems().remove(clienteBorrar);

        Common commmon = new Common();
        Connection conex = commmon.getConexion();
        try {
            Statement consulta = conex.createStatement();
            int valor = consulta.executeUpdate("DELETE FROM poblaciones WHERE cod_postal='" + clienteBorrar.getCod_postal() + "'");
            if(valor == 1){
                commmon.vtnMensajeExitoInsercion();
            } else {
                commmon.vtnAlertaError();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void modificarPoblacion(TableView<Poblacion> id_TablaPoblacion, TextField txtcod_postal, TextField txtpoblacion, TextField txtprovincia){
        Poblacion clienteModificar = id_TablaPoblacion.getSelectionModel().getSelectedItem();

        if(clienteModificar != null){
            Connection conexion=new Common().getConexion();
            PreparedStatement query;
            try {
                query = conexion.prepareStatement("UPDATE poblaciones SET cod_postal = ?, poblacion = ?, provincia = ? WHERE cod_postal ='" + clienteModificar.getCod_postal() + "'");

                query.setInt(1, Integer.parseInt(txtcod_postal.getText()));
                query.setString(2, txtpoblacion.getText());
                query.setString(3, txtprovincia.getText());

                query.execute();

                ObservableList<Poblacion> data = c.obtenerPoblaciones();

                id_TablaPoblacion.setItems(data);
                new Common().vtnMensajeExitoInsercion();

            } catch (SQLException e) {
                new Common().vtnAlertaError();
                e.printStackTrace();
            }
        } else {
            new Common().vtnAlertaError();
        }
    }
    public void newPoblacion(TableView<Poblacion> id_TablaPoblaciones,  TextField txtcod_postal, TextField txtpoblacion, TextField txtprovincia){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO poblaciones(cod_postal, poblacion, provincia) VALUES (?,?,?)");

            query.setInt(1, Integer.parseInt(txtcod_postal.getText()));
            query.setString(2, txtpoblacion.getText());
            query.setString(3, txtprovincia.getText());

            query.execute();
            id_TablaPoblaciones.refresh();

            ObservableList<Poblacion> data = c.obtenerPoblaciones();
            id_TablaPoblaciones.setItems(data);
            new Common().vtnMensajeExitoInsercion();

        } catch (SQLException e) {
            new Common().vtnAlertaError();
            e.printStackTrace();
        }
    }
}
