package lab1.CustomFilterGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import lab1.Filters.Filters;
import lab1.Filters.Helpers;

import java.awt.image.BufferedImage;

import static javafx.embed.swing.SwingFXUtils.toFXImage;

public class Controller {
    @FXML
    public ChoiceBox filterChoiceBox;
    @FXML
    public LineChart mainChart;
    @FXML
    private Button cancelButton;

    private NumberAxis xAxis;
    private NumberAxis yAxis;

    @FXML
    public void initialize() {
        xAxis = (NumberAxis) mainChart.getXAxis();
        yAxis = (NumberAxis) mainChart.getYAxis();

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(1, 1));
        series.getData().add(new XYChart.Data<>(255, 255));

        mainChart.getData().add(series);

        mainChart.setOnMousePressed(e -> {
            for (XYChart.Data<Number, Number> data : series.getData()) {
                if (e.getSceneX() == data.getXValue().intValue() && e.getSceneY() == data.getYValue().intValue()) {
                    return;
                }
            }
            series.getData().add(new XYChart.Data<>(xAxis.getValueForDisplay(e.getSceneX()),yAxis.getValueForDisplay(e.getSceneY())));
        });
    }

    public void filterChoiceApply(ActionEvent actionEvent) {
        String val = (String) filterChoiceBox.getValue();
        switch (val) {
            case "Inversion":
                mainChart.getData().clear();
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(1, 255));
                series.getData().add(new XYChart.Data<>(255, 1));

                mainChart.getData().add(series);


                for (XYChart.Data<Number, Number> data : series.getData()) {
                    Node node = data.getNode() ;
                    node.setCursor(Cursor.HAND);
                    node.setOnMouseDragged(e -> {
                        if((data.getXValue().intValue() == 1 && data.getYValue().intValue() == 255) || (data.getXValue().intValue() == 255 && data.getYValue().intValue() == 1)) {
                            return;
                        }
                        Point2D pointInScene = new Point2D(e.getSceneX(), e.getSceneY());
                        double yAxisLoc = yAxis.sceneToLocal(pointInScene).getY();
                        Number y = yAxis.getValueForDisplay(yAxisLoc);
                        data.setYValue(y);
                    });
                }
                break;
            case "Brightness correction":
                mainChart.getData().clear();
                XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
                series2.getData().add(new XYChart.Data<>(1, 10));
                series2.getData().add(new XYChart.Data<>(255, 265));

                mainChart.getData().add(series2);
                break;
            case "Contrast enhancement":
                mainChart.getData().clear();
                XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
                series3.getData().add(new XYChart.Data<>(1, 2));
                series3.getData().add(new XYChart.Data<>(255, 510));

                mainChart.getData().add(series3);
                break;
        }
    }

    public void cancelCustomFilters(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void transformImage(ActionEvent actionEvent) {
        if (lab1.MainGUI.Controller.image == null) {
            Alert alr = new Alert(Alert.AlertType.ERROR, "There is no image to process!", ButtonType.OK);
            alr.show();
        }



        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
