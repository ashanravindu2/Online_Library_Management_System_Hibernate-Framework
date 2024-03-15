package org.example.controller;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookTransactionBO;
import org.example.bo.custom.BooksBO;
import org.example.dto.BarrowBooksDTO;
import org.example.dto.tm.BarrowBooksTM;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ReturnBookController {
    public TableView tblReturnTable;
    public Label lblBookId;
    public TableColumn colBookId;
    public TableColumn colBTitle;
    public TableColumn colGenre;
    public TableColumn BarrowDate;
    public TableColumn colReturnDate;
    public Label lblBookTitle;
    public Label lblRetuenDate;

    private String userName="";
    private final BookTransactionBO bookTransactionBO = (BookTransactionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKTRANSACTION);
    private final BooksBO booksBO = (BooksBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKS);
    public void initialize() {
       setTable();
        setCellFactories();
        tableRowSelectAction();
    }

    private void setTable() {
        ObservableList<BarrowBooksTM> resTMS = FXCollections.observableArrayList();

        List<BarrowBooksDTO> barrowBooksDTOS = bookTransactionBO.getAllReturnBeforeBarrowBooks(userName);
        for (BarrowBooksDTO barrowBooksDTO : barrowBooksDTOS) {
            resTMS.add(new BarrowBooksTM(
                    barrowBooksDTO.getBooks_id(),
                    barrowBooksDTO.getBooks_title(),
                    barrowBooksDTO.getBooks_genre(),
                    barrowBooksDTO.getBarrow_date(),
                    barrowBooksDTO.getReturn_date(),
                    barrowBooksDTO.getBranch_id()
            ));

        }
        tblReturnTable.setItems(resTMS);

    }
    private void tableRowSelectAction() {
        tblReturnTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            BarrowBooksTM selectedItem = (BarrowBooksTM) tblReturnTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                lblBookId.setText(selectedItem.getBooks_id());
                lblBookTitle.setText(selectedItem.getBooks_title());
                setDate(selectedItem.getReturn_date(),selectedItem.getBarrow_date());
            }
        });
    }
    public void setDate(Date returnDate, Timestamp barrowDate){
        // Timestamp minux return date
        long returnDateLong = returnDate.getTime();
        long barrowDateLong = barrowDate.getTime();
        long diff = returnDateLong - barrowDateLong;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if (diffDays==15){
            lblRetuenDate.setStyle("-fx-text-fill: red");
        } else if (diffDays==25) {
            lblRetuenDate.setStyle("-fx-text-fill: orange");
        }else if (diffDays>=28){
            lblRetuenDate.setStyle("-fx-text-fill: green");
        }

        lblRetuenDate.setText(String.valueOf(diffDays));

    }
public void setCellFactories(){
    colBookId.setCellValueFactory(new PropertyValueFactory<>("books_id"));
    colBTitle.setCellValueFactory(new PropertyValueFactory<>("books_title"));
    colGenre.setCellValueFactory(new PropertyValueFactory<>("books_genre"));
    BarrowDate.setCellValueFactory(new PropertyValueFactory<>("barrow_date"));
    colReturnDate.setCellValueFactory(new PropertyValueFactory<>("return_date"));
}

    public void btnReturnAction(MouseEvent mouseEvent) {
        boolean isBookTableUpdate = booksBO.bookUpdateAvl(lblBookId.getText());
        if (isBookTableUpdate) {
            boolean isBookTransactionTableUpdate = bookTransactionBO.bookReturnStatusUpdate(lblBookId.getText(),userName);
            if (isBookTransactionTableUpdate) {
                new Alert(Alert.AlertType.INFORMATION, "Book returned succesfully Thank You !").show();
                setTable();
            }else {
                new Alert(Alert.AlertType.ERROR, "Book return failed try again !").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Book return failed !").show();
        }
    }

    public void mouseEnterAction(MouseEvent mouseEvent) {
        setTable();
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

    public void setUserName(String text) {
        this.userName = text;
    }
    public void btnRefreshMouseEnterAction(MouseEvent mouseEvent) {
        setTable();
    }
}
