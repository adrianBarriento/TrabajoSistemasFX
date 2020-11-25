package bbdd_manager;


import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import models.Employe;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Usuarios {

    private Connection getConexion(){
        Connection conexion=null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_sistemas",
                    "sistemas",
                    "sistemas");

        } catch (SQLException throwables) {
            //AQUI SALTA UN MENSAJE DE ERROR SI NO SE CONECTA
        }
        return conexion;
    }
    public void login(TextField txtDni, AnchorPane id_paneLogin, AnchorPane id_base){
        Connection conexion=getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("SELECT DNI FROM personal");
            datos = query.executeQuery();
            while(datos.next()){
                if(datos.getString("DNI").compareToIgnoreCase(txtDni.getText()) == 0){
                    id_paneLogin.setVisible(false);
                    id_base.setVisible(true);
                }else{
                    Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
                    dialogoAlerta.setTitle("DNI no reconocido");
                    dialogoAlerta.setHeaderText(null);
                    dialogoAlerta.setContentText("El DNI no ha sido introducido correctamente. Empleado no identificado");
                    dialogoAlerta.initStyle(StageStyle.UTILITY);
                    dialogoAlerta.showAndWait();
                }
            }
        } catch (SQLException e) {
            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setHeaderText(null);
            dialogoAlerta.setContentText("No se ha podido acceder ya que la base de datos no ha sido lanzada.");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        }
    }

    public void newEmploye(TextField nombre,TextField apellido,TextField numSegSocial,TextField sueldo,TextField dni){
        Connection conexion=getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("INSERT INTO personal(Nombre, Apellido, NumSegSocial, Sueldo, DNI) VALUES (?,?,?,?,?,)");

            query.setString(1, nombre.getText());
            query.setString(2, apellido.getText());
            query.setInt(3, Integer.getInteger(numSegSocial.getText()));
            query.setInt(4, Integer.getInteger(sueldo.getText()));
            query.setString(5, dni.getText());

        } catch (SQLException e) {
            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setHeaderText(null);
            dialogoAlerta.setContentText("No se ha podido acceder ya que la base de datos no ha sido lanzada.");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        }
    }
}
