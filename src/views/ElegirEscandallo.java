package views;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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


    public void query(int idP, int idE, String nombre){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO escandallo(id_producto, id_escandallo, nombre) VALUES (?, ?, ?)");

            query.setInt(1, idP);
            query.setInt(2, idE);
            query.setString(3, nombre);
            query.execute();

        } catch (SQLException e) {
            new Common().vtnAlertaError();
            e.printStackTrace();
        }
    }

    public void insertarEscandallo(List<ComboBox> cmbs, TextField nombre){
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
                    query(p.getIdProducto(), idE, nombre.getText());
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


    public ObservableList<Productos> procesoEscandallo(ComboBox cmb){
        Connection connection = new Common().getConexion();
        PreparedStatement query;
        ResultSet datos;
        ObservableList<Productos> listaProductos = FXCollections.observableArrayList();
        List<Integer> idProductos = new ArrayList<>();
        try {
            query = connection.prepareStatement("SELECT id_producto FROM escandallo WHERE nombre ='"+cmb.getValue()+"'");
            datos = query.executeQuery();
            while(datos.next()){
                int id = datos.getInt(1);
                idProductos.add(id);
            }
            System.out.println(idProductos.size());

            for(int id:idProductos){
                query = connection.prepareStatement("SELECT * FROM productos WHERE Id_Producto = "+id);
                datos = query.executeQuery();
                while(datos.next()){
                    int idP = datos.getInt(1);
                    String tipo = datos.getString(2);
                    int stock = datos.getInt(3);
                    String marca = datos.getString(4);
                    String modelo = datos.getString(5);
                    int precioC = datos.getInt(6);
                    int precioV = datos.getInt(7);

                    Productos producto = new Productos(idP, tipo, stock, marca, modelo, precioC, precioV);
                    listaProductos.add(producto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }
}
