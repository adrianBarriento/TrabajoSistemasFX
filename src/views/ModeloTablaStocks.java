package views;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Common;
import models.Productos;
import models.Stocks;

public class ModeloTablaStocks {
    @FXML
    private TableColumn<Stocks, String> columnaMarca = new TableColumn<>("Marca");
    @FXML
    private TableColumn<Stocks, String> columnaModelo = new TableColumn<>("Modelo");
    @FXML
    private TableColumn<Stocks, Float> columnaFecha = new TableColumn<>("Fecha");
    @FXML
    private TableColumn<Stocks, Float> columnaMovimientos = new TableColumn<>("Movimientos");


    Common c =new Common();

    public void crearTablaStocks(TableView<Stocks> id_tablaStocks){

        System.out.println("productos:  "+id_tablaStocks.getWidth());

        this.columnaMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        this.columnaMarca.setPrefWidth(132.5);
        this.columnaModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        this.columnaModelo.setPrefWidth(132.5);
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        this.columnaFecha.setPrefWidth(132);
        this.columnaMovimientos.setCellValueFactory(new PropertyValueFactory<>("Movimientos"));
        this.columnaMovimientos.setPrefWidth(132);

        id_tablaStocks.getColumns().addAll(columnaMarca, columnaModelo, columnaFecha, columnaMovimientos);

    }

    public void llenarTabla(TableView idTablaStocks, int producto){
        ObservableList<Stocks> data = c.getStocks(producto);
        idTablaStocks.setItems(data);
    }

}
