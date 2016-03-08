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
import lab1.Filters.CustomFunction;
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

    private XYChart.Series<Number, Number> series;

    @FXML
    public void initialize() {
        xAxis = (NumberAxis) mainChart.getXAxis();
        yAxis = (NumberAxis) mainChart.getYAxis();

        series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(1, 1));
        series.getData().add(new XYChart.Data<>(255, 255));

        mainChart.getData().add(series);

        mainChart.setScaleX(1);
        mainChart.setScaleY(1);

        mainChart.setOnMouseClicked(e -> {
            for (XYChart.Data<Number, Number> data : series.getData()) {
                if (e.getX() == data.getXValue().intValue() && e.getY() == data.getYValue().intValue()) {
                    return;
                }
            }

            int startX = (int) e.getX() - 15;
            int startY = (int) e.getY();

            startY = 255 - startY + 15;

            series.getData().add(new XYChart.Data<>(startX, startY));
        });


    }

    public void filterChoiceApply(ActionEvent actionEvent) {
        String val = (String) filterChoiceBox.getValue();
        switch (val) {
            case "Inversion":
                mainChart.getData().clear();
                series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(1, 255));
                series.getData().add(new XYChart.Data<>(255, 1));

                mainChart.getData().add(series);

                mainChart.setScaleX(1);
                mainChart.setScaleY(1);

                mainChart.setOnMouseClicked(e -> {
                    for (XYChart.Data<Number, Number> data : series.getData()) {
                        if (e.getX() == data.getXValue().intValue() && e.getY() == data.getYValue().intValue()) {
                            return;
                        }
                    }

                    int startX = (int) e.getX() - 15;
                    int startY = (int) e.getY();

                    startY = 255 - startY + 15;

                    series.getData().add(new XYChart.Data<>(startX, startY));
                });
                break;
            case "Brightness correction":
                mainChart.getData().clear();
                series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(1, 10));
                series.getData().add(new XYChart.Data<>(255, 265));

                mainChart.getData().add(series);

                mainChart.setScaleX(1);
                mainChart.setScaleY(1);

                mainChart.setOnMouseClicked(e -> {
                    for (XYChart.Data<Number, Number> data : series.getData()) {
                        if (e.getX() == data.getXValue().intValue() && e.getY() == data.getYValue().intValue()) {
                            return;
                        }
                    }

                    int startX = (int) e.getX() - 15;
                    int startY = (int) e.getY();

                    startY = 255 - startY + 15;

                    series.getData().add(new XYChart.Data<>(startX, startY));
                });
                break;
            case "Contrast enhancement":
                mainChart.getData().clear();
                series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(1, 2));
                series.getData().add(new XYChart.Data<>(255, 510));

                mainChart.getData().add(series);

                mainChart.setScaleX(1);
                mainChart.setScaleY(1);

                mainChart.setOnMouseClicked(e -> {
                    for (XYChart.Data<Number, Number> data : series.getData()) {
                        if (e.getX() == data.getXValue().intValue() && e.getY() == data.getYValue().intValue()) {
                            return;
                        }
                    }

                    int startX = (int) e.getX() - 15;
                    int startY = (int) e.getY();

                    startY = 255 - startY + 15;

                    series.getData().add(new XYChart.Data<>(startX, startY));
                });
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
        else {
            BufferedImage workCpy = Helpers.copyBufferedImage(lab1.MainGUI.Controller.workingImage);
            lab1.MainGUI.Controller.workingImage = CustomFunction.transformImageWithSeries(series, workCpy);
            lab1.MainGUI.Controller.image = toFXImage(lab1.MainGUI.Controller.workingImage, null);
        }

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
