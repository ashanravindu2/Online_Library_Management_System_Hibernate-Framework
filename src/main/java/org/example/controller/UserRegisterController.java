package org.example.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.UserDTO;

public class UserRegisterController {
    public JFXTextField txtPassword;
    public JFXTextField txtUserName;
    public JFXTextField txtEmail;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().equals("") && txtEmail.getText().equals("")|| txtPassword.getText().equals("")){
            return;
        }
        boolean save = userBO.save(new UserDTO(
                txtUserName.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        ));
        if (save){
            new Alert(Alert.AlertType.CONFIRMATION,"User Saved Successful !").show();
        }
        if (!save) {
            new Alert(Alert.AlertType.ERROR, "Exist User try Again !").show();
        }

    }


}
