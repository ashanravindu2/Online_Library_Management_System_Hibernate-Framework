package org.example.controller;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
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
import org.example.dto.BarrowBooksDTO;
import org.example.dto.tm.BarrowBooksTM;
import org.example.dto.tm.TransactionTM;

import java.util.List;

public class BookTransactionController {
    public TableView tblTransacHistory;
    public TableColumn colBranchId;
    public TableColumn colBookId;
    public TableColumn colBookTitle;
    public TableColumn colReturnDate;
    public TableColumn colBarrowDate;
    public TableColumn colStatus;
    private String UserNameSet;
    private Button btnReturn = new Button("Return");

    private final BookTransactionBO bookTransactionBO = (BookTransactionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKTRANSACTION);
    public void initialize() {
        setCellValueFactory();
    }

    private void setDataTable() {

        ObservableList<TransactionTM> resTMS = FXCollections.observableArrayList();
        String userName = this.UserNameSet;
        List<BarrowBooksDTO> barrowBooksDTOS = bookTransactionBO.getAllTransactionIsUser(userName);
        System.out.println("GET");
        for (BarrowBooksDTO barrowBooksDTO : barrowBooksDTOS) {
            resTMS.add(new TransactionTM(
                    barrowBooksDTO.getBooks_id(),
                    barrowBooksDTO.getBooks_title(),
                    barrowBooksDTO.getBarrow_date(),
                    barrowBooksDTO.getReturn_date(),
                    barrowBooksDTO.getBranch_id(),
                    barrowBooksDTO.getReturn_status()

            ));
        }


       tblTransacHistory.setItems(resTMS);
       tblTransacHistory.refresh();
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("books_id"));
        colBookTitle.setCellValueFactory(new PropertyValueFactory<>("books_title"));
        colBarrowDate.setCellValueFactory(new PropertyValueFactory<>("barrow_date"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("return_date"));
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("branch_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("return_status"));
    }

    public void setUserName(String text) {
        this.UserNameSet = text;
    }

    public void mouseEnterAction(MouseEvent mouseEvent) {
        setDataTable();
        setCellValueFactory();
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

    public void mouseExistAction(MouseEvent mouseEvent) {
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
