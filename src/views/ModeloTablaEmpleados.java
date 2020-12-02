package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Common;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Employe;

public class ModeloTablaEmpleados {
    @FXML
    private TableColumn<Employe, String> columnaNombre = new TableColumn<>("Nombre");
    @FXML
    private TableColumn<Employe, String> columnaApellido= new TableColumn<>("Apellido");
    @FXML
    private TableColumn<Employe, Integer> columnaNumSS = new TableColumn<>("NumSegSocial");
    @FXML
    private TableColumn<Employe, Integer> columnaSueldo = new TableColumn<>("Sueldo");
    @FXML
    private TableColumn<Employe, String> columnaDni = new TableColumn<>("DNI");



    private ObservableList<Employe> empleados = FXCollections.observableArrayList();
    private ObservableList<Employe> conseguirEmpleados(){
        for(Employe e:new Common().obtenerEmpleados()){
            empleados.add(e);
        }
        return empleados;
    }

    public void crearTabla(TableView id_tabla){



        //ObservableList<Employe> data = FXCollections.observableArrayList();
        //data.addAll(new Common().obtenerEmpleados());

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.columnaApellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        this.columnaNumSS.setCellValueFactory(new PropertyValueFactory<>("NumSegSocial"));
        this.columnaSueldo.setCellValueFactory(new PropertyValueFactory<>("Sueldo"));
        this.columnaDni.setCellValueFactory(new PropertyValueFactory<>("DNI"));

        Common c =new Common();
        ObservableList<Employe> data = c.obtenerEmpleados();

        id_tabla.setItems(data);
        id_tabla.getColumns().addAll(columnaNombre, columnaApellido, columnaNumSS, columnaSueldo, columnaDni);


    }




}
