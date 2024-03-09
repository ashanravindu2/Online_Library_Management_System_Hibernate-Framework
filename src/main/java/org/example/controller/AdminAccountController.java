package org.example.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.dto.AdminDTO;

public class AdminAccountController {
    public JFXTextField txtPassword;
    public JFXTextField txtUserName;
    public JFXTextField txtEmail;
    private final AdminBO adminBO = BOFactory.getInstance().getBO(BOFactory.BOType.ADMIN);

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().equals("") && txtEmail.getText().equals("")|| txtPassword.getText().equals("")){
            return;
        }
        boolean save = adminBO.save(new AdminDTO(
                txtUserName.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        ));
        if (save){
          new Alert(Alert.AlertType.CONFIRMATION,"Admin Saved Successful !").show();
        }
        if (!save) {
            new Alert(Alert.AlertType.ERROR, "Exist Admin try Again !").show();
        }

    }
}
