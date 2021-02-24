package views;

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

public class ModeloTablaCompras {
    @FXML
    private TableColumn<Compras, String> columnaProducto= new TableColumn<>("Producto");
    @FXML
    private TableColumn<Compras, String> columnaProveedor = new TableColumn<>("Proveedor");

    private TableColumn<Compras, Integer> columnaCantidad = new TableColumn<>("Cantidad");
    @FXML
    private TableColumn<Compras, Float> columnaPrecioUnitario = new TableColumn<>("Precio Unitario");
    @FXML
    private TableColumn<Compras, Float> columnaPrecio = new TableColumn<>("Importe");

    Common c =new Common();

    public void crearTablaCompras(TableView<Compras> id_tablaCompras){
        this.columnaProducto.setCellValueFactory(new PropertyValueFactory<>("productoString"));
        this.columnaProducto.setPrefWidth(129.4);
        this.columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedorString"));
        this.columnaProveedor.setPrefWidth(142.4);
        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.columnaCantidad.setPrefWidth(89.4);
        this.columnaPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        this.columnaPrecioUnitario.setPrefWidth(89.4);
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        id_tablaCompras.getColumns().addAll(columnaProducto, columnaProveedor,  columnaCantidad, columnaPrecioUnitario, columnaPrecio);
    }

    public void llenarTabla(TableView id_tablaCompras){
        ObservableList<Compras> data = c.obtenerCompras();
        id_tablaCompras.setItems(data);
    }
    public void newCompra(TableView id_tablaCompras, ComboBox cmb_proveedor, TextField txtProveedor, ComboBox cmbProducto , TextField cantidad, TextField precioCompra,TextField precioVenta){
        Connection conexion=new Common().getConexion();
        String proveedor = String.valueOf(cmb_proveedor.getValue());
        String producto = String.valueOf(cmbProducto.getValue());

        if(!((txtProveedor.getText()).equalsIgnoreCase(""))){
            proveedor = txtProveedor.getText();
            PreparedStatement query;
            try {
                query = conexion.prepareStatement("INSERT INTO proveedores(Nombre) VALUES (?)");

                query.setString(1, txtProveedor.getText());
                query.execute();
                ObservableList<Proveedores> data = c.obtenerProveedores();
            } catch (SQLException e) {
                new Common().vtnAlertaError();
                e.printStackTrace();
            }
        }

        int idProducto=0, stock=0;
        ObservableList<Productos> listProductos = new Common().obtenerProductos();
        for(Productos p:listProductos){
            String prod = p.getMarca()+" "+p.getModelo();
            if(prod.equals(String.valueOf(cmbProducto.getValue()))){
                idProducto = p.getIdProducto();
                stock = p.getStock();
            }
        }
        PreparedStatement query;
        stock = stock+(Integer.parseInt(cantidad.getText()));

        if((!((precioCompra.getText()).equalsIgnoreCase("")))&&(!((precioVenta.getText()).equalsIgnoreCase("")))){
            try {
                query = conexion.prepareStatement("UPDATE `productos` SET `Stock` = ?, `PrecioCompra` = ?, `PrecioVenta` = ? WHERE `Id_Producto` = " +idProducto);
                query.setInt(1, stock);
                query.setFloat(2, Float.parseFloat(precioCompra.getText()));
                query.setFloat(3, Float.parseFloat(precioVenta.getText()));
                query.execute();
            } catch (SQLException e) {
                new Common().vtnAlertaError();
                e.printStackTrace();
            }
        }


        int idProveedor=0;
        ObservableList<Proveedores> listProveedores = new Common().obtenerProveedores();
        for(Proveedores c:listProveedores){
            String cl = c.getNombre();
            if(cl.equals(proveedor)){
                idProveedor = c.getId();
            }
        }

        try {
            query = conexion.prepareStatement("INSERT INTO compras(idProducto, idProveedor, cantidad) VALUES (?,?,?)");

            query.setInt(1, idProducto);
            query.setInt(2, idProveedor);
            query.setInt(3, Integer.parseInt(cantidad.getText()));

            query.execute();
            ObservableList<Compras> data = c.obtenerCompras();

            id_tablaCompras.setItems(data);
            c.vtnMensajeExitoInsercion();
        } catch (SQLException e) {
            new Common().vtnAlertaError();
            e.printStackTrace();
        }
    }
}
