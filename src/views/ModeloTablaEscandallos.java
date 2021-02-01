package views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Common;
import models.Productos;
import models.Proveedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloTablaEscandallos {
    @FXML
    private TableColumn<Productos, String> columnaMarca= new TableColumn<>("Marca");
    @FXML
    private TableColumn<Productos, String> columnaModelo= new TableColumn<>("Modelo");
    @FXML
    private TableColumn<Productos, Float> columnaPrecio= new TableColumn<>("Precio");

    Common c =new Common();

    public void crearTablaProveedores(TableView<Productos> idTablaOrdenadores){
        this.columnaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        this.columnaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        idTablaOrdenadores.getColumns().addAll(columnaMarca, columnaModelo, columnaPrecio);
    }

    public void llenarTabla(TableView idTabla){
        ObservableList<Proveedores> data =
        idTabla.setItems();
    }



}
