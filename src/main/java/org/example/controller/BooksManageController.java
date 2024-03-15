package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.BooksBO;
import org.example.bo.custom.BranchBO;
import org.example.dto.BooksDTO;
import org.example.dto.BranchDTO;
import org.example.dto.tm.BooksTM;

public class BooksManageController {
    public Label lblBookId;
    public TextField txtAuthor;
    public TextField txtBookTitle;
    public TableView tblBooks;
    public TableColumn colBookId;
    public TableColumn colBookGenre;
    public TableColumn colBookTitle;
    public TableColumn colBookAuthor;
    public TableColumn colBookAvl;
    public JFXComboBox cmbBookAvl;
    public TextField txtGenre;
    public JFXComboBox cmbBranchId;
    private final BooksBO booksBO = (BooksBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKS);
    private final BranchBO branchBO = (BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);
    public ImageView btnBookSave;

    private ObservableList<String> idList = FXCollections.observableArrayList();
    public void initialize() {
        setData();
        setCellValueFactory();
        setTable();
        tableRowSelectAction();
        setBookId();
        setDisableItemTrue();
    }

    private void setDisableItemTrue() {
        txtAuthor.setDisable(true);
        txtBookTitle.setDisable(true);
        txtGenre.setDisable(true);
        cmbBranchId.setDisable(true);
        cmbBookAvl.setDisable(true);
        btnBookSave.setDisable(true);
        btnBookSave.setOpacity(0.5);
    }
    private void setDisableItemFalse() {
        txtAuthor.setDisable(false);
        txtBookTitle.setDisable(false);
        txtGenre.setDisable(false);
        cmbBranchId.setDisable(false);
        cmbBookAvl.setDisable(false);
        btnBookSave.setDisable(false);
        btnBookSave.setOpacity(1);
    }

    private void setBookId() {
        String bookId = booksBO.getNewBooksId();
        lblBookId.setText(bookId);
        
    }

    private void tableRowSelectAction() {
        tblBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDisableItemFalse();
            BooksDTO selectedItem = (BooksDTO) tblBooks.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                return;
            }
            lblBookId.setText(selectedItem.getBooks_id());
            txtBookTitle.setText(selectedItem.getBooks_title());
            txtAuthor.setText(selectedItem.getBooks_author());
            txtGenre.setText(selectedItem.getBooks_genre());
            cmbBookAvl.setValue(selectedItem.getBooks_avl());
            cmbBranchId.setValue(selectedItem.getBranch_id());
        });
    }

    private void setTable() {
        ObservableList<BooksTM> booksTMS = FXCollections.observableArrayList();
        for (BooksDTO booksDTO : booksBO.getAll()){
            booksTMS.add(new BooksTM(
                    booksDTO.getBooks_id(),
                    booksDTO.getBooks_title(),
                    booksDTO.getBooks_author(),
                    booksDTO.getBooks_genre(),
                    booksDTO.getBooks_avl(),
                    booksDTO.getBranch_id()
            ));
        }
        tblBooks.setItems(booksTMS);
    }

    private void setCellValueFactory(){
        colBookId.setCellValueFactory(new PropertyValueFactory<>("books_id"));
        colBookTitle.setCellValueFactory(new PropertyValueFactory<>("books_title"));
        colBookAuthor.setCellValueFactory(new PropertyValueFactory<>("books_author"));
        colBookGenre.setCellValueFactory(new PropertyValueFactory<>("books_genre"));
        colBookAvl.setCellValueFactory(new PropertyValueFactory<>("books_avl"));

    }

    private void setData() {
        for (String ids : booksBO.getBranchIds()){
            idList.add(ids);
        }
        cmbBranchId.setItems(idList);
        cmbBookAvl.getItems().add("Yes");
    }

    public void btnSaveBookAction(MouseEvent mouseEvent) {
        boolean save = booksBO.save(new BooksDTO(
                lblBookId.getText(),
                txtBookTitle.getText(),
                txtAuthor.getText(),
                txtGenre.getText(),
                cmbBookAvl.getValue().toString(),
                cmbBranchId.getValue().toString()
        ));
        System.out.println( cmbBranchId.getValue().toString());
        if (save){
            new Alert(Alert.AlertType.CONFIRMATION,"Book Saved Successful !").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Exist Book try Again !").show();
        }
        setDisableItemTrue();

    }
    public void btnUpdateBookAction(MouseEvent mouseEvent) {
        boolean update = booksBO.update(new BooksDTO(
                lblBookId.getText(),
                txtBookTitle.getText(),
                txtAuthor.getText(),
                txtGenre.getText(),
                cmbBookAvl.getValue().toString(),
                cmbBranchId.getValue().toString()
        ));

        if (update){
            new Alert(Alert.AlertType.CONFIRMATION,"Book Details Update Successful !").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Book Details Update not Successful !").show();
        }
        setDisableItemTrue();
    }

    public void btnRemoveBookAction(MouseEvent mouseEvent) {
        boolean delete = booksBO.delete(lblBookId.getText());
        if (delete){
            new Alert(Alert.AlertType.CONFIRMATION,"Book Remove Successful !").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Book Remove not Successful !").show();
        }
        setDisableItemTrue();
    }

    public void btnAddNewBookAction(MouseEvent mouseEvent) {
        setDisableItemFalse();

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


}
