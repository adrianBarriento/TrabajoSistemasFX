package table_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import models.Common;
import models.Employe;

public class RellenarTablas {
    private ObservableList<Employe> empleados = FXCollections.observableArrayList();
    public String seleccionarCategoria(ComboBox id_cmbCat_gestiion){
        return (String) id_cmbCat_gestiion.getValue();
    }

    public ObservableList<Employe> getEmpleados(){
        for(Employe e:new Common().obtenerEmpleados()){
            empleados.add(e);
        }
        return empleados;
    }

}
