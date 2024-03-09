package org.example.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class UserLoginController {

    public JFXCheckBox checkBox;
    public JFXPasswordField textPassword;
    public JFXTextField textField;
    public JFXRadioButton adminSelect;
    public JFXRadioButton userSelect;
    public AnchorPane rootNodeUser;
    public AnchorPane rootNodeAdmin;

    public void initialize(){

        textField.setManaged(false);
        textField.managedProperty().bind(checkBox.selectedProperty());
        textField.visibleProperty().bind(checkBox.selectedProperty());

        textPassword.managedProperty().bind(checkBox.selectedProperty().not());
        textPassword.visibleProperty().bind(checkBox.selectedProperty().not());

        textField.textProperty().bindBidirectional(textPassword.textProperty());
    }


    public void adminSelectOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/admin_login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNodeUser.getScene().getWindow();
        stage.setTitle("Admin LogIn");
        stage.setScene(scene);

        TranslateTransition tt = new TranslateTransition(Duration.millis(400), scene.getRoot());
        tt.setFromY(-scene.getWidth());
        tt.setToY(0);
        tt.play();
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/userAccount_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.setScene(scene);
        stage.setTitle("Create Account");
        stage.show();
    }

    public void mouseEnterAction(MouseEvent mouseEvent) {
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

    public void mouseExitAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }
}
