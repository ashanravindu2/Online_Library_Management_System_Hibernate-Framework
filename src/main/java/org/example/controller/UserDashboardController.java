package org.example.controller;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDashboardController {
    public Label lblTime;
    public Label lblDate;
    public AnchorPane rootPane;
    private Thread thread;
    boolean run = true;
    @FXML
    private Label lblUserName;



    public void initialize() {
        lblDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
        setCurrentTime();
        run = true;
    }

    public void mouseEnterButtonAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GREEN);
            glow.setWidth(15);
            glow.setHeight(15);
            glow.setRadius(15);
            icon.setEffect(glow);
        }

    }

    public void mouseExitButtonAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }

    private void setCurrentTime() {
        thread =  new Thread(()->{
            SimpleDateFormat format=new SimpleDateFormat("hh:mm:ss");
            while (run){
                try {
                    Thread.sleep(1000);
                }catch (Exception ignored){
                }
                final String time=format.format(new Date());
                Platform.runLater(() -> lblTime.setText(time));
            }
        });

        thread.start();
    }

    public void btnAccountSettingAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/userAccountSetting_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("User Account Setting");
        stage.show();
    }

    public void btnLogOutAction(MouseEvent mouseEvent) {

        new Alert(Alert.AlertType.CONFIRMATION,"Do you want to LogOut ?", ButtonType.YES,ButtonType.NO).showAndWait().ifPresent(buttonType -> {
            if (buttonType== ButtonType.YES){
                run = false;
                thread.interrupt();
                try {
                    Parent root = FXMLLoader.load(this.getClass().getResource("/view/user_login_form.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) this.rootPane.getScene().getWindow();
                    stage.setScene(scene);
                    stage.centerOnScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void btnDashboardOnAction(MouseEvent mouseEvent) {
    }

    public void btnBarrowBookOnAction(MouseEvent mouseEvent) throws IOException {

    /*    Parent root = FXMLLoader.load(this.getClass().getResource("/view/barrowBook_form.fxml"));
        this.rootPane.getChildren().clear();
        this.rootPane.getChildren().add(root);
        BarrowBookController controller = fxmlLoader.getController();
        controller.setUserName(lblUserName.getText());*/

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/barrowBook_form.fxml"));
        Parent root = fxmlLoader.load();
        BarrowBookController controller = fxmlLoader.getController();
        controller.setUserName(lblUserName.getText());
        this.rootPane.getChildren().clear();
        this.rootPane.getChildren().add(root);
    }

    public void btnReturnBookOnAction(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/returnBook_form.fxml"));
        Parent root = fxmlLoader.load();
        ReturnBookController controller = fxmlLoader.getController();
        controller.setUserName(lblUserName.getText());
        this.rootPane.getChildren().clear();
        this.rootPane.getChildren().add(root);

    }

    public void btnNotificationOnAction(MouseEvent mouseEvent) {
    }

    public void btnBookTransacOnAction(MouseEvent mouseEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/book_Transac_form.fxml"));
        Parent root = fxmlLoader.load();
        BookTransactionController controller = fxmlLoader.getController();
        controller.setUserName(lblUserName.getText());
        this.rootPane.getChildren().clear();
        this.rootPane.getChildren().add(root);
    }

    public void setUserName(String text) {
        lblUserName.setText(text);
    }
}
