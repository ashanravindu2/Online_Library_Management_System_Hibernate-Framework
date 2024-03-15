package org.example.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.dto.AdminDTO;

public class AdminAccountSettingController {
    public JFXTextField txtPassword;
    public JFXTextField txtUserName;
    public JFXTextField txtConfirmPassword;
    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);

    public void btnChangeOnAction(ActionEvent actionEvent) {

        AdminDTO adminDTO = adminBO.findCredential(txtUserName.getText());
        if (adminDTO == null) {
            new Alert(Alert.AlertType.ERROR, "Admin incorrect try again !").show();
            clearOnAction();
            return;
        } else {

            if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
                boolean update = adminBO.update(new AdminDTO(

                        txtUserName.getText(),
                        adminDTO.getName(),
                        txtConfirmPassword.getText()
                ));
                if (update) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Admin Password Update successful !").show();
                    clearOnAction();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Admin Password Update not successful !").show();
                    clearOnAction();
                }

            } else {
                new Alert(Alert.AlertType.ERROR, "Admin Password Not Same, Please Try Again !").show();
                clearOnAction();
            }
        }
    }

    private void clearOnAction() {
        txtUserName.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }
}