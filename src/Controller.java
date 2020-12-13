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
import models.Clientes;
import models.Employe;
import models.Proveedores;
import models.Ventas;
import views.ModeloTablaClientes;
import views.ModeloTablaEmpleados;
import views.ModeloTablaVentas;

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
    public TextField id_crearEmpleadoDNI;

    //Textfields de crear clientes
    public TextField id_crearClienteNombre;
    public TextField id_crearClienteApellido;
    public TextField id_crearClienteDireccion;
    public TextField id_crearClientePoblacion;
    public TextField id_crearClienteEmail;

    //Textfields de ventas
    public TextField id_ProductoVentas;
    public TextField id_ClienteVentas;
    public TextField id_VendedorVentas;
    public TextField id_CantidadVentas;

    //AnchorPanes
    public AnchorPane id_crearEmpleado;
    public AnchorPane id_Gestion;
    public AnchorPane id_Footer;
    public AnchorPane id_tablaGestion;
    public AnchorPane id_cmbSeleccion;
    public AnchorPane id_TablaClientes;
    public AnchorPane id_tablaVentas;

    //Image Views
    public ImageView id_icUsuarios;
    public ImageView id_icGestion;
    public ImageView id_icOperaciones;
    public ImageView id_icSalir;


    //ComboBox
    public ComboBox id_cmbCat_gestiion;

    //Tabla
    public TableView<Employe> id_tabla;
    public TableView<Proveedores> id_tablaProveedores;
    public TableView<Clientes> id_tablaClientes;
    public TableView id_TablaVentas;



    private ObservableList<String> rellenarComboBox = FXCollections.observableArrayList("Usuarios", "Proveedores", "Clientes", "Ventas");

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

        id_Gestion.setVisible(false);
        id_crearEmpleado.setVisible(false);

        switch (String.valueOf(id_cmbCat_gestiion.getValue())){
            case "Usuarios":
                id_tablaGestion.setVisible(true);
                new ModeloTablaEmpleados().crearTabla(id_tabla);
                break;
            case "Proveedores":
                break;
            case "Clientes":
                id_TablaClientes.setVisible(true);
                new ModeloTablaClientes().crearTablaClientes(id_tablaClientes);
                break;
            case "Ventas":
                id_tablaVentas.setVisible(true);
                new ModeloTablaVentas().crearTablaClientes(id_TablaVentas);
        }

    }

    public void borrarEmpleado(MouseEvent mouseEvent){
        new ModeloTablaEmpleados().borrar(id_tabla);
    }

    public void modificarEMpleado(MouseEvent mouseEvent){
        new ModeloTablaEmpleados().modificar(id_tabla, id_crearEmpleadoNombre, id_crearEmpleadoApellido, id_crearEmpleadoNumSS, id_crearEmpleadoSueldo, id_crearEmpleadoDNI);
    }

    public void borrarCliente(MouseEvent mouseEvent){
        new ModeloTablaClientes().borrarCliente(id_tablaClientes);
    }

    public void modificarCliente(MouseEvent mouseEvent){
        new ModeloTablaClientes().modificarCliente(id_tablaClientes, id_crearClienteNombre, id_crearClienteApellido, id_crearClienteDireccion, id_crearClientePoblacion, id_crearClienteEmail);
    }

    public void crearCliente(MouseEvent mouseEvent){
        new ModeloTablaClientes().newCliente(id_crearClienteNombre, id_crearClienteApellido, id_crearClienteDireccion, id_crearClientePoblacion, id_crearClienteEmail);
    }
}
