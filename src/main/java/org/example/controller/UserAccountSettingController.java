package org.example.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.UserBO;
import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;

public class UserAccountSettingController {
    public JFXTextField txtPassword;
    public JFXTextField txtUserName;
    public JFXTextField txtConfirmPassword;
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void btnChangeOnAction(ActionEvent actionEvent) {

        UserDTO userDTO = userBO.findCredential(txtUserName.getText());

        if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
            boolean update = userBO.update(new UserDTO(

                    txtUserName.getText(),
                    userDTO.getName(),
                    txtConfirmPassword.getText()
            ));
            if (update){new Alert(Alert.AlertType.CONFIRMATION, "User Password Update successful !").show();}else {
                new Alert(Alert.AlertType.ERROR, "User Password Update not successful !").show();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "User Password Not Same, Please Try Again !").show();
        }
    }
}