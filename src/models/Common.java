package models;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import java.sql.*;
import java.util.ArrayList;

public class Common {
    //constantes
    static final String MENSAJE_ERROR="Algo no ha funcionado de manera esperada";
    static final String MENSAJE_EXITO_INSERCION="Se ha insertado correctamente";

    public void vtnAlertaError(){
        Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
        dialogoAlerta.setTitle("ERROR");
        dialogoAlerta.setHeaderText(null);
        dialogoAlerta.setContentText(MENSAJE_ERROR);
        dialogoAlerta.initStyle(StageStyle.UTILITY);
        dialogoAlerta.showAndWait();
    }
    public void vtnMensajeExitoInsercion(){
        Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
        dialogoAlerta.setTitle("ÉXITO");
        dialogoAlerta.setHeaderText(null);
        dialogoAlerta.setContentText(MENSAJE_EXITO_INSERCION);
        dialogoAlerta.initStyle(StageStyle.UTILITY);
        dialogoAlerta.showAndWait();
    }

    public Connection getConexion(){
        Connection conexion=null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_sistemas",
                    "sistemas",
                    "sistemas");
        } catch (SQLException throwables) {
            new Common().vtnAlertaError();
        }
        return conexion;
    }
    //metodo para sacar datos de un empleado de una tabla de la base ded atos
    public ArrayList<Employe> obtenerEmpleados(){
        ArrayList<Employe> listaEmpleados = new ArrayList<>();
        Connection connection = getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = connection.prepareStatement("SELECT * FROM personal");
            datos = query.executeQuery();
            while(datos.next()){
                String nombre = datos.getString(2);
                String apellidos = datos.getString(3);
                int numSS = datos.getInt(4);
                int sueldo = datos.getInt(5);
                String dni = datos.getString(7);

                Employe empleado = new Employe(nombre, apellidos, numSS, sueldo, dni);
                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            vtnAlertaError();
        }
        return listaEmpleados;
    }
}
