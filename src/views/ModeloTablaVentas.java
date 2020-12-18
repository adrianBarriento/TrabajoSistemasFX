package views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Clientes;
import models.Common;
import models.Ventas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloTablaVentas {
    @FXML
    private TableColumn<Ventas, String> columnaProducto= new TableColumn<>("Producto");
    @FXML
    private TableColumn<Ventas, String> columnaCliente = new TableColumn<>("Cliente");
    @FXML
    private TableColumn<Ventas, String> columnaVendedor = new TableColumn<>("Vendedor");
    @FXML
    private TableColumn<Ventas, Integer> columnaCantidad = new TableColumn<>("Cantidad");
    @FXML
    private TableColumn<Ventas, Float> columnaPrecio = new TableColumn<>("Precio");

    Common c =new Common();

    public void crearTablaVentas(TableView<Ventas> id_tablaVentas){
        this.columnaProducto.setCellValueFactory(new PropertyValueFactory<>("productoString"));
        this.columnaCliente.setCellValueFactory(new PropertyValueFactory<>("clienteString"));
        this.columnaVendedor.setCellValueFactory(new PropertyValueFactory<>("vendedorString"));
        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));



        id_tablaVentas.getColumns().addAll(columnaProducto, columnaCliente, columnaVendedor,  columnaCantidad, columnaPrecio);

    }

    public void llenarTabla(TableView id_tablaVentas){
        ObservableList<Ventas> data = c.obtenerVentas();
        id_tablaVentas.setItems(data);
    }
    public void newVenta(TableView id_tablaVentas, TextField idCliente, TextField idProducto, TextField idVendedor, TextField cantidad){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        try {
            query = conexion.prepareStatement("INSERT INTO encargos(id_cliente, id_producto, id_personal, cantidad) VALUES (?,?,?,?)");

            query.setInt(1, Integer.parseInt(idCliente.getText()));
            query.setInt(2, Integer.parseInt(idProducto.getText()));
            query.setInt(3, Integer.parseInt(idVendedor.getText()));
            query.setInt(4, Integer.parseInt(cantidad.getText()));

            query.execute();
            ObservableList<Ventas> data = c.obtenerVentas();

            id_tablaVentas.setItems(data);
            c.vtnMensajeExitoInsercion();
        } catch (SQLException e) {
            new Common().vtnAlertaError();
            e.printStackTrace();
        }
    }
}
