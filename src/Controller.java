import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {
    //TextField del login
    public TextField id_txtLogin;

    //Textfield de crear empleados
    public TextField id_crearEmpleadoNombre;
    public TextField id_crearEmpleadoApellido;
    public TextField id_crearEmpleadoNumSS;
    public TextField id_crearEmpleadoSueldo;
    public TextField id_crearEmpleadoFecha;
    public TextField id_crearEmpleadoDNI;


    public void onExitButtonClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void comprobarEmpleado(MouseEvent mouseEvent){
        String dni = id_txtLogin.getText();
    }
}
