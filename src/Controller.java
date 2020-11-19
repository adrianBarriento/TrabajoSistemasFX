import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {
    public TextField id_txtLogin;

    public void onExitButtonClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void comprobarEmpleado(MouseEvent mouseEvent){
        String dni = id_txtLogin.getText();
    }
}
