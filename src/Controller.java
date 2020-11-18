import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

public class Controller {

    public void onExitButtonClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }
}
