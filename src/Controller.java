import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableView;
import bbdd_manager.Usuarios;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.*;
import views.*;

import java.util.ArrayList;
import java.util.List;

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

    //textFields de productos
    public TextField txtModelo;
    public TextField txtMarca;

    //Textfields de compras
    public TextField id_CantidadCompras;
    public TextField txt_ComprasProveedor;
    public TextField id_PrecioCompra;
    public TextField id_PrecioVenta;

    //AnchorPanes
    public AnchorPane id_Gestion;
    public AnchorPane id_tablaGestion;
    public AnchorPane id_TablaClientes;
    public AnchorPane id_tablaVentas;
    public AnchorPane id_poblaciones;
    public AnchorPane id_segundoCombo;
    public AnchorPane d_tablaProductos;
    public AnchorPane id_tablaCompras;

    //Image Views
    public ImageView id_icUsuarios;
    public ImageView id_icGestion;
    public ImageView id_icOperaciones;
    public ImageView id_icSalir;


    //ComboBox
    public ComboBox id_cmbCat_gestiion;
    public ComboBox id_cmbComercio;
    public ComboBox id_cmbCodigosPostales;
    public ComboBox id_cmbProductoTipo;
    public ComboBox cmb_VentasProducto;
    public ComboBox cmb_VentasCliente;
    public ComboBox cmb_VentasVendedor;
    public ComboBox cmb_ComprasProducto;
    public ComboBox cmb_ComprasProveedor;

    //Tabla
    public TableView<Employe> id_tabla;
    public TableView<Proveedores> id_tablaProveedores;
    public TableView<Clientes> id_tablaClientes;
    public TableView<Ventas> id_TablaVentas;
    public TableView<Poblacion> id_TablaPoblaciones;
    public TableView<Productos> id_tablaProducto;
    public TextField id_nombreProveedor;
    public AnchorPane id_TablaProveedores;
    public TableView id_TablaCompras;
    public AnchorPane id_AnchorPanePC;
    public TableView id_tablaPC;
    public TextField id_txtNombrePC;

    //Opciones
    public ImageView CuartaOpcion;
    public ImageView TerceraOpcion;
    public ImageView SegundaOpcion;
    public ImageView PrimeraOpcion;

    //ESCANDALLOS
    List<ComboBox> listaCmb = new ArrayList<>();
    public ComboBox cmb_componente_1;
    public ComboBox cmb_componente_2;
    public ComboBox cmb_componente_3;
    public ComboBox cmb_componente_4;
    public ComboBox cmb_componente_5;
    public ComboBox cmb_componente_6;
    public ComboBox cmb_componente_7;
    public ComboBox cmb_componente_8;
    public ComboBox cmb_componente_9;
    public ComboBox cmb_componente_10;
    public ComboBox cmb_componente_11;
    public ComboBox cmb_componente_12;
    public ComboBox cmb_componente_13;
    public ComboBox cmb_componente_14;
    public ComboBox cmb_componente_15;
    public ComboBox cmb_componente_16;
    public ComboBox cmbOrdenador;
    public AnchorPane id_Escandallo;
    public ImageView id_icEscandallo;



    ModeloTablaProductos modeloTablaProductos = new ModeloTablaProductos();
    ModeloTablaVentas modeloTablaVentas = new ModeloTablaVentas();
    ModeloTablaClientes modeloTablaClientes = new ModeloTablaClientes();
    ModeloTablaPoblaciones modeloTablaPoblaciones = new ModeloTablaPoblaciones();
    ModeloTablaEmpleados modeloTablaEmpleados = new ModeloTablaEmpleados();
    ModeloTablaProveedores modeloTablaProveedores = new ModeloTablaProveedores();
    ModeloTablaCompras modeloTablaCompras = new ModeloTablaCompras();
    ModeloTablaEscandallos modeloTablaEscandallos = new ModeloTablaEscandallos();
    private List<ComboBox> llenarCmb(){
        listaCmb.add(cmb_componente_1);
        listaCmb.add(cmb_componente_2);
        listaCmb.add(cmb_componente_3);
        listaCmb.add(cmb_componente_4);
        listaCmb.add(cmb_componente_5);
        listaCmb.add(cmb_componente_6);
        listaCmb.add(cmb_componente_7);
        listaCmb.add(cmb_componente_8);
        listaCmb.add(cmb_componente_9);
        listaCmb.add(cmb_componente_10);
        listaCmb.add(cmb_componente_11);
        listaCmb.add(cmb_componente_12);
        listaCmb.add(cmb_componente_13);
        listaCmb.add(cmb_componente_14);
        listaCmb.add(cmb_componente_15);
        listaCmb.add(cmb_componente_16);
        return listaCmb;
    }

    private ObservableList<String> rellenarComboBoxGestion = FXCollections.observableArrayList( "Proveedores", "Clientes","Productos", "Poblaciones");

    private ObservableList<String> rellenarComboBoxComercio = FXCollections.observableArrayList("Ventas", "Compras");

    private ObservableList<String> rellenarComboBoxProductoTipo = FXCollections.observableArrayList("Movil", "Ordenador", "Componente");

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
        modeloTablaProductos.crearTablaProductos(id_tablaProducto);
        modeloTablaProveedores.crearTablaProveedores(id_tablaProveedores);
        modeloTablaCompras.crearTablaCompras(id_TablaCompras);
        modeloTablaEscandallos.crearTabla(id_tablaPC);
    }

    public void ventanaCrearEmpleado(MouseEvent mouseEvent){
        PrimeraOpcion.setVisible(true);
        SegundaOpcion.setVisible(false);
        TerceraOpcion.setVisible(false);
        CuartaOpcion.setVisible(false);

        modeloTablaEmpleados.llenarTabla(id_tabla);
        id_Gestion.setVisible(false);
        id_tablaGestion.setVisible(true);
        id_poblaciones.setVisible(false);
        id_TablaClientes.setVisible(false);
        id_Gestion.setVisible(false);
        id_tablaVentas.setVisible(false);
        id_TablaProveedores.setVisible(false);
        d_tablaProductos.setVisible(false);
        id_segundoCombo.setVisible(false);
        id_tablaCompras.setVisible(false);
        id_Escandallo.setVisible(false);
        id_AnchorPanePC.setVisible(false);
    }

    public void ventanaEscandallos(MouseEvent mouseEvent){
        modeloTablaEscandallos.llenarTabla(id_tablaPC, cmbOrdenador);
        id_Escandallo.setVisible(false);
        id_Gestion.setVisible(false);
        id_tablaGestion.setVisible(false);
        id_poblaciones.setVisible(false);
        id_TablaClientes.setVisible(false);
        id_Gestion.setVisible(false);
        id_tablaVentas.setVisible(false);
        id_TablaProveedores.setVisible(false);
        d_tablaProductos.setVisible(false);
        id_segundoCombo.setVisible(false);
        id_tablaCompras.setVisible(false);
        id_AnchorPanePC.setVisible(true);
    }
    public void ventanaCrearEscandallo(MouseEvent mouseEvent){
        PrimeraOpcion.setVisible(false);
        SegundaOpcion.setVisible(false);
        TerceraOpcion.setVisible(false);
        CuartaOpcion.setVisible(true);

        id_Escandallo.setVisible(true);
        id_Gestion.setVisible(false);
        id_tablaGestion.setVisible(false);
        id_poblaciones.setVisible(false);
        id_TablaClientes.setVisible(false);
        id_Gestion.setVisible(false);
        id_tablaVentas.setVisible(false);
        id_TablaProveedores.setVisible(false);
        d_tablaProductos.setVisible(false);
        id_segundoCombo.setVisible(false);
        id_tablaCompras.setVisible(false);
        id_AnchorPanePC.setVisible(false);

        cmbOrdenador.setItems(new ElegirEscandallo().cogerNombres());

        listaCmb = llenarCmb();
        for(int i =0; i<listaCmb.size(); i++){
            listaCmb.get(i).setItems(new ElegirEscandallo().obtenerProductos());
            int finalI = i;
            listaCmb.get(i).valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object o, Object t1) {
                    if(listaCmb.get(finalI).getValue()==null){
                        listaCmb.get(finalI+1).setDisable(true);
                    }else{
                        listaCmb.get(finalI+1).setDisable(false);
                    }
                }
            });
        }

    }
    public void insertarEmpleado(MouseEvent mouseEvent){new Usuarios().newEmploye( id_tabla ,id_crearEmpleadoNombre, id_crearEmpleadoApellido, id_crearEmpleadoNumSS, id_crearEmpleadoSueldo, id_crearEmpleadoDNI);}

    public void ventanaGestion(MouseEvent mouseEvent){
        PrimeraOpcion.setVisible(false);
        SegundaOpcion.setVisible(true);
        TerceraOpcion.setVisible(false);
        CuartaOpcion.setVisible(false);

        id_poblaciones.setVisible(false);
        id_TablaClientes.setVisible(false);
        id_Gestion.setVisible(true);
        id_tablaVentas.setVisible(false);
        id_tablaGestion.setVisible(false);
        id_TablaProveedores.setVisible(false);
        d_tablaProductos.setVisible(false);
        id_segundoCombo.setVisible(false);
        id_tablaCompras.setVisible(false);
        id_Escandallo.setVisible(false);
        id_AnchorPanePC.setVisible(false);
        if (null != id_cmbCat_gestiion){
            id_cmbCat_gestiion.setItems(rellenarComboBoxGestion);
        }
    }

    public void ventanaComercio(MouseEvent mouseEvent){
        PrimeraOpcion.setVisible(false);
        SegundaOpcion.setVisible(false);
        TerceraOpcion.setVisible(true);
        CuartaOpcion.setVisible(false);

        id_poblaciones.setVisible(false);
        id_TablaClientes.setVisible(false);
        id_Gestion.setVisible(false);
        id_segundoCombo.setVisible(true);
        id_tablaVentas.setVisible(false);
        id_tablaGestion.setVisible(false);
        id_TablaProveedores.setVisible(false);
        d_tablaProductos.setVisible(false);
        id_tablaCompras.setVisible(false);
        id_Escandallo.setVisible(false);
        id_AnchorPanePC.setVisible(false);
        if (null != id_cmbComercio){
            id_cmbComercio.setItems(rellenarComboBoxComercio);
        }

    }

    public void ventanaTabla(MouseEvent mouseEvent){
        id_Gestion.setVisible(false);
        switch (String.valueOf(id_cmbCat_gestiion.getValue())){
            case "Proveedores":
                id_TablaProveedores.setVisible(true);
                modeloTablaProveedores.llenarTabla(id_tablaProveedores);
                break;
            case "Clientes":
                id_TablaClientes.setVisible(true);
                modeloTablaClientes.llenarTabla(id_tablaClientes);
                ObservableList<String> codigosPostales = FXCollections.observableArrayList();
                ObservableList<Poblacion> poblaciones = new Common().obtenerPoblaciones();
                for(Poblacion p:poblaciones){
                    codigosPostales.add(p.getPoblacion()+" "+p.getCod_postal());
                }
                id_cmbCodigosPostales.setItems(codigosPostales);
                break;
            case "Poblaciones":
                id_poblaciones.setVisible(true);
                modeloTablaPoblaciones.llenarTabla(id_TablaPoblaciones);
                break;

            case "Productos":
                d_tablaProductos.setVisible(true);
                modeloTablaProductos.llenarTabla(id_tablaProducto);
                id_cmbProductoTipo.setItems(rellenarComboBoxProductoTipo);
                break;
        }

    }

    public void ventanaTablaComercio(MouseEvent mouseEvent){
        id_segundoCombo.setVisible(false);
        switch (String.valueOf(id_cmbComercio.getValue())){
            case "Ventas":
                id_tablaVentas.setVisible(true);
                modeloTablaVentas.llenarTabla(id_TablaVentas);
                ObservableList<String> productosVenta = FXCollections.observableArrayList();
                ObservableList<Productos> listProductosVenta = new Common().obtenerProductos();
                for(Productos p:listProductosVenta){
                    productosVenta.add(p.getMarca()+" "+p.getModelo());
                }
                cmb_VentasProducto.setItems(productosVenta);

                ObservableList<String> clientes = FXCollections.observableArrayList();
                ObservableList<Clientes> listClientes = new Common().obtenerClientes();
                for(Clientes c:listClientes){
                    clientes.add(c.getNombre()+" "+c.getApellidos());
                }
                cmb_VentasCliente.setItems(clientes);

                ObservableList<String> vendedores = FXCollections.observableArrayList();
                ObservableList<Employe> litsVendedores = new Common().obtenerEmpleados();
                for(Employe p:litsVendedores){
                    vendedores.add(p.getNombre()+" "+ p.getApellido());
                }
                cmb_VentasVendedor.setItems(vendedores);
                break;
            case "Compras":
                id_tablaCompras.setVisible(true);
                modeloTablaCompras.llenarTabla(id_TablaCompras);
                ObservableList<String> productosCompra = FXCollections.observableArrayList();
                ObservableList<Productos> listProductosCompra = new Common().obtenerProductos();
                for(Productos p:listProductosCompra){
                    productosCompra.add(p.getMarca()+" "+p.getModelo());
                }
                cmb_ComprasProducto.setItems(productosCompra);

                ObservableList<String> proveedores = FXCollections.observableArrayList();
                ObservableList<Proveedores> listProveedores = new Common().obtenerProveedores();
                for(Proveedores c:listProveedores){
                    proveedores.add(c.getNombre());
                }
                cmb_ComprasProveedor.setItems(proveedores);
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
        new ModeloTablaVentas().newVenta(id_TablaVentas ,cmb_VentasCliente, cmb_VentasProducto, cmb_VentasVendedor, id_CantidadVentas);
    }

    public void crearCompra(MouseEvent mouseEvent){
        new ModeloTablaCompras().newCompra(id_TablaCompras ,cmb_ComprasProveedor, txt_ComprasProveedor, cmb_ComprasProducto,id_CantidadCompras, id_PrecioCompra, id_PrecioVenta);
    }

    public void borrarProveedor(MouseEvent mouseEvent){
        new ModeloTablaProveedores().borrarProveedores(id_tablaProveedores);
    }

    public void modificarProveedor(MouseEvent mouseEvent){
        new ModeloTablaProveedores().modificarProveedores(id_tablaProveedores, id_nombreProveedor);
    }

    public void crearProveedor(MouseEvent mouseEvent){
        new ModeloTablaProveedores().newProveedor(id_tablaProveedores, id_nombreProveedor);
    }

    public void crearProducto(MouseEvent mouseEvent){
        new ModeloTablaProductos().newProducto(id_tablaProducto, id_cmbProductoTipo, txtMarca , txtModelo);
    }

    public void borrarProducto(MouseEvent mouseEvent){
        new ModeloTablaProductos().borrarProductos(id_tablaProducto);
    }

    public void modificarProducto(MouseEvent mouseEvent){
        new ModeloTablaProductos().modificarProducto(id_tablaProducto, id_cmbProductoTipo, txtMarca , txtModelo);
    }

    public void insertarEscandallo(MouseEvent mouseEvent){
        new ElegirEscandallo().insertarEscandallo(listaCmb, id_txtNombrePC);
    }
}
