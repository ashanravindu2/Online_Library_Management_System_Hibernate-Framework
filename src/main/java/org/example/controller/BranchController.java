package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.BranchBO;
import org.example.dto.BranchDTO;
import org.example.dto.tm.BranchTM;

public class BranchController {

    public TextField txtBranchContact;
    public Label lblBranchId;
    public TextField txtBranchLoacation;
    public TableView tblBranch;
    public TableColumn colBranchContact;
    public TableColumn colBranchId;
    public TableColumn colBranchLocation;
    public TableColumn colBranchAvl;
    public JFXComboBox cmbBranchAvl;
    @FXML
    private ImageView btnSave;

    private final BranchBO branchBO = (BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);


    public void initialize() {
        setData();
        setCellValueFactory();
        setTable();
        tableRowSelectAction();
        setBranchId();
        setDisableItemTrue();

   }

    private void setDisableItemTrue() {
        txtBranchContact.setDisable(true);
        txtBranchLoacation.setDisable(true);
        cmbBranchAvl.setDisable(true);
        btnSave.setDisable(true);
        btnSave.setOpacity(0.5);
    }
    private void setDisableItemFlase() {
        txtBranchContact.setDisable(false);
        txtBranchLoacation.setDisable(false);
        cmbBranchAvl.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setOpacity(1);
    }

    private void tableRowSelectAction() {

        tblBranch.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDisableItemFlase();
            BranchTM selectedItem = (BranchTM) tblBranch.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                return;
            }
            btnSave.setDisable(true);
            btnSave.setOpacity(0.5);
            lblBranchId.setText(selectedItem.getBranch_id());
            txtBranchLoacation.setText(selectedItem.getBranch_location());
            txtBranchContact.setText(selectedItem.getBranch_contact());
            cmbBranchAvl.setValue(selectedItem.getBranch_avl());
        });
    }

    private void setTable() {
        ObservableList<BranchTM> branchTMS = FXCollections.observableArrayList();
        for (BranchDTO branchDTO : branchBO.getAll()){
            branchTMS.add(new BranchTM(
                    branchDTO.getBranch_id(),
                    branchDTO.getBranch_location(),
                    branchDTO.getBranch_contact(),
                    branchDTO.getBranch_avl()
            ));
        }
        tblBranch.setItems(branchTMS);
    }

    private void setCellValueFactory() {
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("branch_id"));
        colBranchLocation.setCellValueFactory(new PropertyValueFactory<>("branch_location"));
        colBranchContact.setCellValueFactory(new PropertyValueFactory<>("branch_contact"));
        colBranchAvl.setCellValueFactory(new PropertyValueFactory<>("branch_avl"));
    }

    private void setData() {
        cmbBranchAvl.getItems().addAll("Open", "Close");
    }


    private void setBranchId() {
        String id = branchBO.getNewBranchId();
        lblBranchId.setText(id);
    }

    public void mouseEnterAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.1);
            scaleT.setToY(1.1);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GREEN);
            glow.setWidth(10);
            glow.setHeight(10);
            glow.setRadius(10);
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

    public void btnSaveBranchAction(MouseEvent mouseEvent) {
        if (txtBranchLoacation.getText().equals("") && txtBranchContact.getText().equals("") || cmbBranchAvl.getValue().toString().equals("")) {
            return;
        }
        boolean save = branchBO.save(new BranchDTO(
                lblBranchId.getText(),
                txtBranchLoacation.getText(),
                txtBranchContact.getText(),
                cmbBranchAvl.getValue().toString()));
        System.out.println(cmbBranchAvl.getValue().toString());

        if (save){
            new Alert(Alert.AlertType.CONFIRMATION,"Branch Saved Successful !").show();
            setTable();
            clearAll();
        }else {
            new Alert(Alert.AlertType.ERROR, "Exist Branch try Again !").show();
            clearAll();
        }
        setDisableItemTrue();
    }

    public void btnAddNewBranchAction(MouseEvent mouseEvent) {
        setDisableItemFlase();
    }

    public void btnBranchUpdateAction(MouseEvent mouseEvent) {
            boolean update = branchBO.update(new BranchDTO(
                    lblBranchId.getText(),
                    txtBranchLoacation.getText(),
                    txtBranchContact.getText(),
                    cmbBranchAvl.getValue().toString()
            ));
            if (update){
                clearAll();
                setTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Branch Update Successful !").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Branch Update not Successful !").show();
                clearAll();
            }
            setDisableItemTrue();
    }

    public void btnBranchDeleteAction(MouseEvent mouseEvent) {
        boolean delete = branchBO.delete(lblBranchId.getText());
        if (delete){
            new Alert(Alert.AlertType.CONFIRMATION,"Branch Delete Successful !").show();
            clearAll();
            setTable();
        }else {
            new Alert(Alert.AlertType.ERROR, "Branch Delete not Successful !").show();
            clearAll();
        }
        setDisableItemTrue();
    }
    public void clearAll() {
        lblBranchId.setText("");
        txtBranchLoacation.setText("");
        txtBranchContact.setText("");
        cmbBranchAvl.setValue("");
    }
}
