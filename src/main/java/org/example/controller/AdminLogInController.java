package org.example.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.dto.AdminDTO;

import java.io.IOException;
import java.util.Objects;

public class AdminLogInController {
    public AnchorPane rootNodeAdmin;
    public JFXPasswordField textPassword;
    public JFXTextField textField;
    public JFXCheckBox checkBox;
    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);
    public JFXTextField txtAdminUserName;


    public void initialize(){

        textField.setManaged(false);
        textField.managedProperty().bind(checkBox.selectedProperty());
        textField.visibleProperty().bind(checkBox.selectedProperty());

        textPassword.managedProperty().bind(checkBox.selectedProperty().not());
        textPassword.visibleProperty().bind(checkBox.selectedProperty().not());

        textField.textProperty().bindBidirectional(textPassword.textProperty());
    }


    public void userSelectOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNodeAdmin.getScene().getWindow();
        stage.setTitle("Admin LogIn");
        stage.setScene(scene);

        TranslateTransition tt = new TranslateTransition(Duration.millis(400), scene.getRoot());
        tt.setFromY(-scene.getWidth());
        tt.setToY(0);
        tt.play();
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/adminAccount_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Create Account");
        stage.show();

    }

    public void btnLogInAction(ActionEvent actionEvent) throws IOException {
      AdminDTO adminDTO = adminBO.findCredential(txtAdminUserName.getText());

        System.out.println(txtAdminUserName.getText());
        System.out.println(textPassword.getText());

        if (adminDTO!=null){
            if (adminDTO.getGmail()!=null && textPassword.getText().equals(adminDTO.getPassword())){
                Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/admin_dashboard_form.fxml"));
                Scene scene = new Scene(rootNode);
                Stage stage = (Stage) this.rootNodeAdmin.getScene().getWindow();
                stage.setTitle("Subarandu Online Library");
                stage.setScene(scene);

                TranslateTransition tt = new TranslateTransition(Duration.millis(800), scene.getRoot());
                tt.setFromY(-scene.getWidth());
                tt.setToY(0);
                tt.play();
                }
            } else {
            new Alert(Alert.AlertType.WARNING,"Empty value or Invalid UserNAme or Password !").show();
        }




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
