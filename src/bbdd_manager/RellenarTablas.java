package bbdd_manager;

import javafx.scene.control.ComboBox;

public class RellenarTablas {
    public String seleccionarCategoria(ComboBox id_cmbCat_gestiion){
        return (String) id_cmbCat_gestiion.getValue();
    }


}
