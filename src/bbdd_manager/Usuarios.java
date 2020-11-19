package bbdd_manager;


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
    public void login(TextField txtDni){
        Connection conexion=getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("SELECT DNI FROM personal");
            datos = query.executeQuery();
        } catch (SQLException e) {
            System.out.println("No es posible acceder a los datos");
        }

    }
}
