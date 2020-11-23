package bbdd_manager;


import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Usuarios {

    private Connection getConexion(){
        Connection conexion=null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_sistemas",
                    "root",
                    "root");

        } catch (SQLException throwables) {
            //AQUI SALTA UN MENSAJE DE ERROR SI NO SE CONECTA
        }
        return conexion;
    }
    public void login(TextField txtDni, AnchorPane pane1, AnchorPane pane2){
        Connection conexion=getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("SELECT DNI FROM personal");
            datos = query.executeQuery();
            while(datos.next()){
                if(datos.getString("DNI")==txtDni.getText()){
                    pane1.setVisible(false);
                    pane2.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "DNI incorrecto", "ERROR",JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException e) {
            System.out.println("No es posible acceder a los datos");
        }

    }
}
