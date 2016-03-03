package lab1.MainGUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lab1.ImageChooserGUI.ImageChooserGUI;
import lab1.Main;

import java.io.IOException;

public class Controller {
    public static Image image;

    @FXML
    private ImageView mainImageView;

    public void showAbout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../AboutGUI/AboutGUI.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("About app");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1, 300, 300));
        stage.show();
    }

    public void quitApp(ActionEvent actionEvent) {
        //TODO: ask about save
        Platform.exit();
    }

    public void openImage(ActionEvent actionEvent) {
        ImageChooserGUI imgCh = new ImageChooserGUI(Main.mainStage);
        if (imgCh.file != null) {
            image = new Image(imgCh.file.toURI().toString());
            mainImageView.setImage(image);
        }
    }

    public void closeImage(ActionEvent actionEvent) {
        mainImageView.setImage(null);
    }

    public void showCustomFilterDialog(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../CustomFilterGUI/CustomFilterGUI.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Custom function for filter");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1, 600, 400));
        stage.show();
    }
}
