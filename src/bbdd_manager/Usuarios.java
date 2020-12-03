package bbdd_manager;


import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import models.Common;

import javax.naming.NamingEnumeration;
import java.sql.*;

public class Usuarios {


    public void login(TextField txtDni, AnchorPane id_paneLogin, AnchorPane id_base){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("SELECT DNI FROM personal");
            datos = query.executeQuery();
            while(datos.next()){
                if(datos.getString("DNI").compareToIgnoreCase(txtDni.getText()) == 0){
                    id_paneLogin.setVisible(false);
                    id_base.setVisible(true);
                }
            }
        } catch (SQLException e) {
            new Common().vtnAlertaError();
        }
    }

    public void newEmploye(TextField nombre,TextField apellido,TextField numSegSocial,TextField sueldo,TextField dni){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("INSERT INTO personal(Nombre, Apellidos, NumSegSocial, Sueldo, DNI) VALUES (?,?,?,?,?)");

            query.setString(1, nombre.getText());
            query.setString(2, apellido.getText());
            query.setString(3, numSegSocial.getText());
            query.setInt(4, Integer.parseInt(sueldo.getText()));
            query.setString(5, dni.getText());
            query.execute();
            new Common().vtnMensajeExitoInsercion();

        } catch (SQLException e) {
            new Common().vtnAlertaError();
        }
    }
}
