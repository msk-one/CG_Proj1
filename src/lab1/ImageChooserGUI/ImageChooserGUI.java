package lab1.ImageChooserGUI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

/**
 * Created by mskas on 03.03.2016.
 */


public class ImageChooserGUI {
    private Desktop desktop;
    private FileChooser fileChooser;

    public File file;

    public ImageChooserGUI(Stage stage) {
        desktop = Desktop.getDesktop();
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("Images", "*.jpeg", "*.jpg", "*.png");

        fileChooser.getExtensionFilters().add(fileExtensions);

        File newFile = fileChooser.showOpenDialog(stage);

        if (newFile != null) {
            file = newFile;
        } else {
            Alert alr = new Alert(Alert.AlertType.WARNING, "You didn't choose any file!", ButtonType.OK);
            alr.show();
        }
    }
}
