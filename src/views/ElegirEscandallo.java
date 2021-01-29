package views;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import models.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ElegirEscandallo {
    Common common = new Common();
    private List<ComboBox> cmbsActivos;
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



    public ObservableList<String> obtenerProductos() {
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


                listaProductos.add(marca + " "+ modelo);
            }
        } catch (SQLException e) {

        }
        return listaProductos;
    }

    public List<Productos> obtenerProductosO() {

        List<Productos> listaProductos = new ArrayList<>();
        Connection connection = common.getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        String nuevo=null;
        try {
            query = connection.prepareStatement("SELECT * FROM `productos` WHERE TipoProducto = 'Componente' ");
            datos = query.executeQuery();
            while(datos.next()){
                int id = datos.getInt("iD_Producto");
                String marca = datos.getString("Marca");
                String modelo = datos.getString("Modelo");

                Productos p = new Productos();
                p.setIdProducto(id);
                p.setMarca(marca);
                p.setModelo(modelo);

                listaProductos.add(p);
            }
        } catch (SQLException e) {

        }
        return listaProductos;
    }


    public void query(int idP, int idE){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO escandallo(id_producto, id_escandallo) VALUES (?, ?)");

            query.setInt(1, idP);
            query.setInt(2, idE);
            query.execute();

        } catch (SQLException e) {
            new Common().vtnAlertaError();
            e.printStackTrace();
        }
    }

    public void insertarEscandallo(List<ComboBox> cmbs){
        int idE=getIdEscandallo();
        cmbsActivos = new ArrayList<>();

        cmbs.forEach(cmb->{
            if(!cmb.isDisabled()){
                cmbsActivos.add(cmb);
            }
        });
        List<Productos> listaP = obtenerProductosO();
        for(int i =0; i<cmbsActivos.size()-1; i++){
            for(Productos p:listaP){
                if(cmbsActivos.get(i).getValue().equals(p.getMarca()+" "+p.getModelo())){
                    System.out.println(cmbsActivos.get(i).getValue());
                    System.out.println(p.getIdProducto());
                    query(p.getIdProducto(), idE);
                }
            }
        }
        new Common().vtnMensajeExitoInsercion();
    }


    public ObservableList<String> cogerNombres(){
        ObservableList<Ordenador> listaOrdenadores = new Common().obtenerOrdenadores();
        ObservableList<String> listaNombres = FXCollections.observableArrayList();

        listaOrdenadores.forEach(o->{
            listaNombres.add(o.getNombre());
        });
        return listaNombres;
    }

    public ObservableList<Productos> ordenadoresTabla(){
        ObservableList<Productos> listaOrdenadores = new Common().obtenerProductos();
        ObservableList<Productos> listaOrdenadoresFinal = FXCollections.observableArrayList();

        listaOrdenadores.forEach(o->{

        });
        return listaOrdenadoresFinal;
    }








}
