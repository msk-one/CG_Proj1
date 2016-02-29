package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public void showAbout(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("About application");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
