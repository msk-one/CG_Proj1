package lab1.MainGUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lab1.Filters.Filters;
import lab1.Filters.Helpers;
import lab1.ImageChooserGUI.ImageChooserGUI;
import lab1.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import static javafx.embed.swing.SwingFXUtils.fromFXImage;
import static javafx.embed.swing.SwingFXUtils.toFXImage;

public class Controller {
    public static Image image;
    public static BufferedImage workingImage;

    @FXML
    private ImageView mainImageView;
    @FXML
    private ChoiceBox convFilterChooser;
    @FXML
    private ChoiceBox funcFilterChooser;
    @FXML
    private Label defaultBrightCoeff;
    @FXML
    private Label defaultContrastCoeff;
    @FXML
    private Label defaultGamma;

    @FXML
    public void initialize() {
        defaultBrightCoeff.setText("For brighteness correction coefficient value is set to: "+Helpers.brightCoeff);
        defaultContrastCoeff.setText("For contrast enchacement: "+Helpers.contrastCoeff);
        defaultGamma.setText("For gamma correction: "+Helpers.gamma);
    }

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

    public void openImage(ActionEvent actionEvent) throws IOException {
        ImageChooserGUI imgCh = new ImageChooserGUI(Main.mainStage);
        if (imgCh.file != null) {
            image = new Image(imgCh.file.toURI().toString());
            workingImage = fromFXImage(image, null);
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

    public void transformUsingConvFilter(ActionEvent actionEvent) {
        String val = (String) convFilterChooser.getValue();
        if (image == null) {
            Alert alr = new Alert(Alert.AlertType.ERROR, "There is no image to process!", ButtonType.OK);
            alr.show();
        }
        else {
            BufferedImage workCpy = Helpers.copyBufferedImage(workingImage);
            switch (val) {
                case "Blur":
                    workingImage = Filters.convBlurFilter(workCpy);
                    image = toFXImage(workingImage, null);
                    mainImageView.setImage(image);
                    break;
                case "Gaussian smoothing":
                    workingImage = Filters.convGaussSmoothFilter(workCpy);
                    image = toFXImage(workingImage, null);
                    mainImageView.setImage(image);
                    break;
                case "Sharpen":
                    workingImage = Filters.convSharpenFilter(workCpy);
                    image = toFXImage(workingImage, null);
                    mainImageView.setImage(image);
                    break;
                case "Edge detection":
                    workingImage = Filters.convEdgeDetectFilter(workCpy);
                    image = toFXImage(workingImage, null);
                    mainImageView.setImage(image);
                    break;
                case "Emboss":
                    workingImage = Filters.convEmbossFilter(workCpy);
                    image = toFXImage(workingImage, null);
                    mainImageView.setImage(image);
                    break;
            }
        }
    }

    public void transformUsingFuncFilter(ActionEvent actionEvent) {
        String val = (String) funcFilterChooser.getValue();
        if (image == null) {
            Alert alr = new Alert(Alert.AlertType.ERROR, "There is no image to process!", ButtonType.OK);
            alr.show();
        }
        else {
            BufferedImage workCpy = Helpers.copyBufferedImage(workingImage);
            switch (val) {
                case "Inversion":
                    workingImage = Filters.funcInversionFilter(workCpy);
                    image = toFXImage(workingImage, null);
                    mainImageView.setImage(image);
                    break;
                case "Brightness correction":
                    workingImage = Filters.funcBrightCorrFilter(workCpy);
                    image = toFXImage(workingImage, null);
                    mainImageView.setImage(image);
                    break;
                case "Contrast enhancement":
                    workingImage = Filters.funcContrastEnchFilter(workCpy);
                    image = toFXImage(workingImage, null);
                    mainImageView.setImage(image);
                    break;
                case "Gamma correction":
                    workingImage = Filters.funcGammaCorrFilter(workCpy);
                    image = toFXImage(workingImage, null);
                    mainImageView.setImage(image);
                    break;
            }
        }
    }
}
