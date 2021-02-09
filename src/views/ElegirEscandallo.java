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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return listaProductos;
    }


    public void query(int idP, int idE, String nombre, int cantidad){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO escandallo(id_producto, id_escandallo, nombre, cantidad) VALUES (?, ?, ?, ?)");

            query.setInt(1, idP);
            query.setInt(2, idE);
            query.setString(3, nombre);
            query.setInt(4, cantidad);
            query.execute();

        } catch (SQLException e) {

        }
    }

    public void insertarEscandallo(List<ComboBox> cmbs, TextField nombre, ComboBox cmbV){
        int idE=getIdEscandallo();
        cmbsActivos = new ArrayList<>();
        cmbs.forEach(cmb->{
            if(!cmb.isDisabled()){
                cmbsActivos.add(cmb);
            }
        });
        int cantidad=1;
        List<Productos> listaP = obtenerProductosO();
        for(int i =0; i<cmbsActivos.size()-1; i++){
            for(Productos p:listaP){
                if(cmbsActivos.get(i).getValue().equals(p.getMarca()+" "+p.getModelo())){
                    String valorCmb=String.valueOf(cmbsActivos.get(i).getValue());
                    cantidad=1;
                    for(int j =i+1; j<cmbsActivos.size()-1; j++){
                        if(valorCmb.equals(String.valueOf(cmbsActivos.get(j).getValue()))){
                            cantidad++;
                        }
                    }
                    query(p.getIdProducto(), idE, nombre.getText(), cantidad);
                }
            }
        }
        new Common().vtnMensajeExitoInsercion();
        cmbV.setItems(cogerNombres());
        for(int o =0; o<cmbs.size(); o++){
            cmbs.get(o).setValue(null);
            if(o>0){
                cmbs.get(o).setDisable(true);
            }
        }
       // cmbsActivos = new ArrayList<>();
    }


    public ObservableList<String> cogerNombres(){
        ObservableList<Ordenador> listaOrdenadores = new Common().obtenerOrdenadores();
        ObservableList<String> listaNombres = FXCollections.observableArrayList();

        listaOrdenadores.forEach(o->{
            listaNombres.add(o.getNombre());
        });
        return listaNombres;
    }

    public ObservableList<Productos> procesoEscandallo(ComboBox cmb){
        Connection connection = new Common().getConexion();
        PreparedStatement query;
        ResultSet datos;
        ObservableList<Productos> listaProductos = FXCollections.observableArrayList();
        List<Integer> idProductos = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();
        int cantidad=0;
        try {
            query = connection.prepareStatement("SELECT id_producto, cantidad FROM escandallo WHERE nombre ='"+cmb.getValue()+"'");
            datos = query.executeQuery();
            while(datos.next()){
                int id = datos.getInt(1);
                cantidad = datos.getInt(2);
                idProductos.add(id);
                cantidades.add(cantidad);
            }
            System.out.println(idProductos.size());

            for(int i = 0; i <idProductos.size(); i++){
                query = connection.prepareStatement("SELECT * FROM productos WHERE Id_Producto = "+idProductos.get(i));
                datos = query.executeQuery();
                while(datos.next()){
                    int idP = datos.getInt(1);
                    String tipo = datos.getString(2);
                    int stock = datos.getInt(3);
                    String marca = datos.getString(4);
                    String modelo = datos.getString(5);
                    int precioC = datos.getInt(6);
                    int precioV = datos.getInt(7);

                    Productos producto = new Productos(idP, tipo, stock, marca, modelo, precioC, precioV, cantidades.get(i));
                    listaProductos.add(producto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }
}
