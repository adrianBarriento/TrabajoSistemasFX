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
import models.*;
import views.ModeloTablaClientes;
import views.ModeloTablaEmpleados;
import views.ModeloTablaPoblaciones;
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

    //Textfields de poblaciones
    public TextField id_añadirCodPostal;
    public TextField id_añadirPoblacion;
    public TextField id_añadirProvincia;

    //AnchorPanes
    public AnchorPane id_crearEmpleado;
    public AnchorPane id_Gestion;
    public AnchorPane id_Footer;
    public AnchorPane id_tablaGestion;
    public AnchorPane id_cmbSeleccion;
    public AnchorPane id_TablaClientes;
    public AnchorPane id_tablaVentas;
    public AnchorPane id_poblaciones;

    //Image Views
    public ImageView id_icUsuarios;
    public ImageView id_icGestion;
    public ImageView id_icOperaciones;
    public ImageView id_icSalir;


    //ComboBox
    public ComboBox id_cmbCat_gestiion;
    public ComboBox id_cmbCodigosPostales;

    //Tabla
    public TableView<Employe> id_tabla;
    public TableView<Proveedores> id_tablaProveedores;
    public TableView<Clientes> id_tablaClientes;
    public TableView<Ventas> id_TablaVentas;
    public TableView<Poblacion> id_TablaPoblaciones;

    ModeloTablaVentas modeloTablaVentas = new ModeloTablaVentas();
    ModeloTablaClientes modeloTablaClientes = new ModeloTablaClientes();
    ModeloTablaPoblaciones modeloTablaPoblaciones = new ModeloTablaPoblaciones();
    ModeloTablaEmpleados modeloTablaEmpleados = new ModeloTablaEmpleados();

    private ObservableList<String> rellenarComboBoxGestion = FXCollections.observableArrayList("Usuarios", "Proveedores", "Clientes", "Ventas", "Poblaciones");

    public void onExitButtonClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void comprobarEmpleado(MouseEvent mouseEvent){
        new Usuarios().login(id_txtLogin, id_paneLogin, id_base);

        //llenar toddas las tablas (no estan los metodos hechos)
        modeloTablaVentas.crearTablaVentas(id_TablaVentas);
        modeloTablaClientes.crearTablaClientes(id_tablaClientes);
        modeloTablaPoblaciones.crearTablaPoblaciones(id_TablaPoblaciones);
        modeloTablaEmpleados.crearTabla(id_tabla);
    }

    public void ventanaCrearEmpleado(MouseEvent mouseEvent){
        id_crearEmpleado.setVisible(true);
        id_Gestion.setVisible(false);
        id_tablaGestion.setVisible(false);
        id_poblaciones.setVisible(false);
        id_TablaClientes.setVisible(false);
        id_Gestion.setVisible(false);
        id_tablaVentas.setVisible(false);
    }
    public void insertarEmpleado(MouseEvent mouseEvent){new Usuarios().newEmploye( id_tabla ,id_crearEmpleadoNombre, id_crearEmpleadoApellido, id_crearEmpleadoNumSS, id_crearEmpleadoSueldo, id_crearEmpleadoDNI);}

    public void ventanaGestion(MouseEvent mouseEvent){
        id_poblaciones.setVisible(false);
        id_TablaClientes.setVisible(false);
        id_crearEmpleado.setVisible(false);
        id_Gestion.setVisible(true);
        id_tablaVentas.setVisible(false);
        id_tablaGestion.setVisible(false);
        if (null != id_cmbCat_gestiion){
            id_cmbCat_gestiion.setItems(rellenarComboBoxGestion);
        }

    }

    public void ventanaTabla(MouseEvent mouseEvent){
        id_Gestion.setVisible(false);
        id_crearEmpleado.setVisible(false);
        switch (String.valueOf(id_cmbCat_gestiion.getValue())){
            case "Usuarios":
                id_tablaGestion.setVisible(true);
                modeloTablaEmpleados.llenarTabla(id_tabla);
                break;
            case "Proveedores":
                break;
            case "Clientes":
                id_TablaClientes.setVisible(true);

                modeloTablaClientes.llenarTabla(id_tablaClientes);
                ObservableList<String> codigosPostales = FXCollections.observableArrayList();
                ObservableList<Poblacion> poblaciones = new Common().obtenerPoblaciones();
                for(Poblacion p:poblaciones){
                    codigosPostales.add(p.getPoblacion());
                }
                id_cmbCodigosPostales.setItems(codigosPostales);
                break;
            case "Ventas":
                id_tablaVentas.setVisible(true);
                modeloTablaVentas.llenarTabla(id_TablaVentas);
                break;
            case "Poblaciones":
                id_poblaciones.setVisible(true);
                modeloTablaPoblaciones.llenarTabla(id_TablaPoblaciones);
                break;
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
        new ModeloTablaClientes().modificarCliente(id_tablaClientes, id_crearClienteNombre, id_crearClienteApellido, id_cmbCodigosPostales, id_crearClienteEmail);
    }

    public void crearCliente(MouseEvent mouseEvent){
        new ModeloTablaClientes().newCliente(id_tablaClientes ,id_crearClienteNombre, id_crearClienteApellido, id_cmbCodigosPostales, id_crearClienteEmail);
    }

    public void crearPoblacion(MouseEvent mouseEvent){
        new ModeloTablaPoblaciones().newPoblacion(id_TablaPoblaciones, id_añadirCodPostal, id_añadirPoblacion, id_añadirProvincia);
    }
    public void borrarPoblacion(MouseEvent mouseEvent){
        new ModeloTablaPoblaciones().borrarPoblacion(id_TablaPoblaciones);
    }
    public void modificarPoblacion(MouseEvent mouseEvent){
        new ModeloTablaPoblaciones().modificarPoblacion(id_TablaPoblaciones, id_añadirCodPostal, id_añadirPoblacion, id_añadirProvincia);
    }
    public void crearVenta(MouseEvent mouseEvent){
        new ModeloTablaVentas().newVenta(id_TablaVentas ,id_ClienteVentas, id_ProductoVentas, id_VendedorVentas, id_CantidadVentas);
    }
}
