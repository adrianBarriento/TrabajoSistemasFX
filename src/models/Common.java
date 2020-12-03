package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import java.sql.*;
import java.util.ArrayList;

public class Common {
    //constantes
    static final String MENSAJE_ERROR="Algo no ha funcionado de manera esperada";
    static final String MENSAJE_EXITO_INSERCION="Se ha insertado correctamente";

    //Ventanas informativas.
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

    //Metodo crear conexion base de datos
    public Connection getConexion(){
        Connection conexion=null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_sistemas",
                    "sistemas",
                    "sistemas");
        } catch (SQLException throwables) {
            new Common().vtnAlertaError();
            System.out.println("Error en conexion");
        }
        return conexion;
    }
    //metodo para sacar datos de un empleado de una tabla de la base de datos
    public ObservableList<Employe> obtenerEmpleados(){
        ObservableList<Employe> listaEmpleados = FXCollections.observableArrayList();
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
                String dni = datos.getString(6);

                Employe empleado = new Employe(nombre, apellidos, numSS, sueldo, dni);

                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            vtnAlertaError();
            System.out.println("Error no en conexion");
        }
        return listaEmpleados;
    }

    //metodo para sacar datos de un cliente de una tabla de la base de datos
    public ArrayList<Clientes> obtenerClientes(){
        ArrayList<Clientes> listaClientes = new ArrayList<>();
        Connection connection = getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = connection.prepareStatement("SELECT * FROM clientes");
            datos = query.executeQuery();
            while(datos.next()){
                String nombre = datos.getString(2);
                String apellidos = datos.getString(3);
                String direccion = datos.getString(4);
                String poblacion = datos.getString(5);
                String email = datos.getString(6);

                Clientes clientes = new Clientes(nombre, apellidos, direccion, poblacion, email);
                listaClientes.add(clientes);
            }
        } catch (SQLException e) {
            vtnAlertaError();
        }
        return listaClientes;
    }

    //metodo para sacar datos de un empleado de una tabla de la base ded atos
    public ArrayList<Proveedores> obtenerProveedores(){
        ArrayList<Proveedores> listaProveedores = new ArrayList<>();
        Connection connection = getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = connection.prepareStatement("SELECT * FROM proveedores");
            datos = query.executeQuery();
            while(datos.next()){
                String nombre = datos.getString(2);
                String articulo = datos.getString(3);

                Proveedores proveedores = new Proveedores(nombre, articulo);
                listaProveedores.add(proveedores);
            }
        } catch (SQLException e) {
            vtnAlertaError();
        }
        return listaProveedores;
    }
}
