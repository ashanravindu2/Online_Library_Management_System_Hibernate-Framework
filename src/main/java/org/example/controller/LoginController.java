package org.example.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

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
    }

}
