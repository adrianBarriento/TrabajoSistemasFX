import bbdd_manager.RellenarTablas;
import bbdd_manager.Usuarios;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {
    //TextField del login
    public TextField id_txtLogin;
    public AnchorPane id_paneLogin;
    public AnchorPane id_base;

    //Textfield de crear empleados
    public TextField id_crearEmpleadoNombre;
    public TextField id_crearEmpleadoApellido;
    public TextField id_crearEmpleadoNumSS;
    public TextField id_crearEmpleadoSueldo;
    public TextField id_crearEmpleadoFecha;
    public TextField id_crearEmpleadoDNI;

    //AnchorPanes
    public AnchorPane id_crearEmpleado;
    public AnchorPane id_Gestion;
    public AnchorPane id_Footer;
    public AnchorPane id_tablaGestion;
    public AnchorPane id_cmbSeleccion;

    //Image Views
    public ImageView id_icUsuarios;
    public ImageView id_icGestion;
    public ImageView id_icOperaciones;
    public ImageView id_icSalir;


    //ComboBox
    public ComboBox id_cmbCat_gestiion;

    private ObservableList<String> rellenarComboBox = FXCollections.observableArrayList("Usuarios", "Proveedores", "Clientes");

    public void onExitButtonClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void comprobarEmpleado(MouseEvent mouseEvent){new Usuarios().login(id_txtLogin, id_paneLogin, id_base);}

    public void ventanaCrearEmpleado(MouseEvent mouseEvent){
        id_crearEmpleado.setVisible(true);
        id_Gestion.setVisible(false);
    }
    public void insertarEmpleado(MouseEvent mouseEvent){new Usuarios().newEmploye(id_crearEmpleadoNombre, id_crearEmpleadoApellido, id_crearEmpleadoNumSS, id_crearEmpleadoSueldo, id_crearEmpleadoDNI);}

    //No funciona, pone que es null
    public void ventanaGestion(MouseEvent mouseEvent){
        id_crearEmpleado.setVisible(false);
        id_Gestion.setVisible(true);
        if (null != id_cmbCat_gestiion){
            id_cmbCat_gestiion.setItems(rellenarComboBox);
        }
    }
    public void seleccionarOpcionCombox(MouseEvent mouseEvent){ new RellenarTablas().seleccionarCategoria(id_cmbCat_gestiion); }

}
