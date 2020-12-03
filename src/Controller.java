import javafx.scene.control.TableView;
import bbdd_manager.Usuarios;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Employe;
import views.ModeloTablaEmpleados;

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

    //Tabla
    public TableView<Employe> id_tabla;

    private ObservableList<String> rellenarComboBox = FXCollections.observableArrayList("Usuarios", "Proveedores", "Clientes");

    public void onExitButtonClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void comprobarEmpleado(MouseEvent mouseEvent){new Usuarios().login(id_txtLogin, id_paneLogin, id_base);}

    public void ventanaCrearEmpleado(MouseEvent mouseEvent){
        id_crearEmpleado.setVisible(true);
        id_Gestion.setVisible(false);
        id_tablaGestion.setVisible(false);
    }
    public void insertarEmpleado(MouseEvent mouseEvent){new Usuarios().newEmploye(id_crearEmpleadoNombre, id_crearEmpleadoApellido, id_crearEmpleadoNumSS, id_crearEmpleadoSueldo, id_crearEmpleadoDNI);}

    public void ventanaGestion(MouseEvent mouseEvent){
        id_crearEmpleado.setVisible(false);
        id_Gestion.setVisible(true);
        id_tablaGestion.setVisible(false);
        if (null != id_cmbCat_gestiion){
            id_cmbCat_gestiion.setItems(rellenarComboBox);
        }
    }

    public void ventanaTabla(MouseEvent mouseEvent){
        id_tablaGestion.setVisible(true);
        id_Gestion.setVisible(false);
        id_crearEmpleado.setVisible(false);

        switch (String.valueOf(id_cmbCat_gestiion.getValue())){
            case "Usuarios":
                new ModeloTablaEmpleados().crearTabla(id_tabla);
                break;
            case "Proveedores":
                break;
            case "Clientes":
                break;
        }

    }

    public void seleccionarRow(MouseEvent mouseEvent){
        new ModeloTablaEmpleados().seleccionar(id_tabla);
    }

}
