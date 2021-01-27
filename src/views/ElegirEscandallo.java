package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import models.Common;


import java.sql.*;
import java.util.List;

public class ElegirEscandallo {
    Common common = new Common();
    public int getIdEscandallo(){
        Connection conexion = common.getConexion();
        Statement query;
        ResultSet datos=null;
        int id = 0;
        try {
            query = conexion.createStatement();
            datos = query.executeQuery("select MAX(id_escandallo) from `escandallo`");

            while(datos.next()){
                id = datos.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id+1;
    }

    public void crearEscandallo(List<ComboBox> cmb){
        boolean relleno=true;
        int contadorRellenas=0;
        while(relleno){
            for(int i =0; i<cmb.size(); i++){
                if(cmb.get(i).getValue().equals("")){
                    relleno = false;
                }else{
                    contadorRellenas++;
                    cmb.get(i+1).setDisable(false);
                }
            }
        }
        System.out.println(contadorRellenas);

    }

    public ObservableList<String> obtenerProductos() {
        System.out.println("entra");
        ObservableList<String> listaProductos = FXCollections.observableArrayList();;
        Connection connection = common.getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        String nuevo=null;
        try {
            query = connection.prepareStatement("SELECT * FROM `productos` WHERE TipoProducto = 'Componente' ");
            datos = query.executeQuery();
            while(datos.next()){
                String marca = datos.getString("Marca");
                String modelo = datos.getString("Modelo");

                System.out.println(marca);
                listaProductos.add(marca + " "+ modelo);
            }
        } catch (SQLException e) {

        }
        return listaProductos;
    }




}
