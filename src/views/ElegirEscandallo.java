package views;

import models.Common;
import models.Employe;

import java.sql.*;

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




}
