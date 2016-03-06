package lab1.CustomFilterGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Button cancelButton;

    public void filterChoiceApply(ActionEvent actionEvent) {

    }

    public void cancelCustomFilters(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void transformImage(ActionEvent actionEvent) {


        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
