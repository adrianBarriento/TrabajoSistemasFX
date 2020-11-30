package views;

import table_manager.RellenarTablas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Employe;

public class ModeloTablaEmpleados {
    @FXML
    private TableView<Employe> tablaEmpleados = new TableView<>();
    @FXML
    private TableColumn<Employe, String> columnaNombre = new TableColumn<>("Nombre");
    @FXML
    private TableColumn<Employe, String> columnaApellido= new TableColumn<>("Apellido");
    @FXML
    private TableColumn<Employe, Integer> columnaNumSS = new TableColumn<>("Número de la seg.Social");
    @FXML
    private TableColumn<Employe, Integer> columnaSueldo = new TableColumn<>("Sueldo");
    @FXML
    private TableColumn<Employe, String> columnaDni = new TableColumn<>("DNI");
    public void crearTabla(){
        tablaEmpleados.getColumns().addAll(columnaNombre, columnaApellido, columnaNumSS, columnaSueldo, columnaDni);
        for(Employe e:new RellenarTablas().getEmpleados()){
            tablaEmpleados.getItems().add(e);
        }


    }




}
