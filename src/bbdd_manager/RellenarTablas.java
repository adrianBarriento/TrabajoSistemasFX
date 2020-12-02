package bbdd_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import models.Employe;

public class RellenarTablas {
    public String seleccionarCategoria(ComboBox id_cmbCat_gestiion){
        return (String) id_cmbCat_gestiion.getValue();
    }

    public void tablaUsuarios(){
        ObservableList<Employe> empleados = FXCollections.observableArrayList();

    }

}
