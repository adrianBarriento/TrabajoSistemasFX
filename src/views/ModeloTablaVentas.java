package views;

import com.mysql.cj.xdevapi.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;

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
    private TableColumn<Ventas, Float> columnaPrecioUnitario = new TableColumn<>("Precio Unitario");
    @FXML
    private TableColumn<Ventas, Float> columnaPrecio = new TableColumn<>("Importe");

    Common c =new Common();

    public void crearTablaVentas(TableView<Ventas> id_tablaVentas){
        System.out.println("ventas:  "+id_tablaVentas.getWidth());
        this.columnaProducto.setCellValueFactory(new PropertyValueFactory<>("productoString"));
        this.columnaProducto.setPrefWidth(101.00);
        this.columnaCliente.setCellValueFactory(new PropertyValueFactory<>("clienteString"));
        this.columnaCliente.setPrefWidth(101.00);
        this.columnaVendedor.setCellValueFactory(new PropertyValueFactory<>("vendedorString"));
        this.columnaVendedor.setPrefWidth(101.00);
        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.columnaCantidad.setPrefWidth(75.00);
        this.columnaPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        this.columnaPrecioUnitario.setPrefWidth(91.00);
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.columnaPrecio.setPrefWidth(70.00);

        id_tablaVentas.getColumns().addAll(columnaProducto, columnaCliente, columnaVendedor,  columnaCantidad, columnaPrecioUnitario, columnaPrecio);
    }

    public void llenarTabla(TableView id_tablaVentas){
        ObservableList<Ventas> data = c.obtenerVentas();
        id_tablaVentas.setItems(data);
    }
    public void newVenta(TableView id_tablaVentas, ComboBox cliente, ComboBox producto, ComboBox vendedor, TextField cantidad){


        int idProducto=0, stock=0;
        ObservableList<Productos> listProductos = new Common().obtenerProductos();
        for(Productos p:listProductos){
            String prod = p.getMarca()+" "+p.getModelo();
            if(prod.equals(String.valueOf(producto.getValue()))){
                idProducto = p.getIdProducto();
                stock = p.getStock();
            }
        }





        if(Integer.parseInt(cantidad.getText())<stock){
           Connection conexion=new Common().getConexion();
           PreparedStatement query;

           stock = stock-(Integer.parseInt(cantidad.getText()));
            try {
                query = conexion.prepareStatement("UPDATE `productos` SET `Stock` = ? WHERE `Id_Producto` = " +idProducto);
                query.setInt(1, stock);
                query.execute();
            } catch (SQLException e) {
                new Common().vtnAlertaError();
                e.printStackTrace();
            }
           int idCliente=0;
           ObservableList<Clientes> listClientes = new Common().obtenerClientes();
           for(Clientes c:listClientes){
               String cl = c.getNombre()+" "+c.getApellidos();
               if(cl.equals(String.valueOf(cliente.getValue()))){
                   idCliente = c.getIdCliente();
               }
           }


           int idVendedor=0;
           ObservableList<Employe> listVendedores = new Common().obtenerEmpleados();
           for(Employe e:listVendedores){
               String emp = e.getNombre()+" "+e.getApellido();
               if(emp.equals(String.valueOf(vendedor.getValue()))){
                   idVendedor = e.getIdEmpleado();
               }
           }

           try {
               query = conexion.prepareStatement("INSERT INTO ventas(id_cliente, id_producto, id_personal, cantidad) VALUES (?,?,?,?)");

               query.setInt(1, idCliente);
               query.setInt(2, idProducto);
               query.setInt(3, idVendedor);
               query.setInt(4, Integer.parseInt(cantidad.getText()));

               query.execute();
               ObservableList<Ventas> data = c.obtenerVentas();

               id_tablaVentas.setItems(data);
               c.vtnMensajeExitoInsercion();
           } catch (SQLException e) {
               new Common().vtnAlertaError();
               e.printStackTrace();
           }
       }else{
            new Common().vtnAlertaStock(stock);
        }
    }
}
