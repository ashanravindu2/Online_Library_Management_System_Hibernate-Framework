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
import org.example.bo.custom.BookTransactionBO;
import org.example.bo.custom.BooksBO;
import org.example.dto.BarrowBooksDTO;
import org.example.dto.BookTransactionDTO;
import org.example.dto.BooksDTO;
import org.example.dto.tm.BarrowBooksTM;
import org.example.dto.tm.BooksTM;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class BarrowBookController {

    public TableView tblBook;
    @FXML
    private JFXComboBox<String> cmbBranchId;

    @FXML
    private JFXComboBox<String> cmbBookId;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBarrowDate;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colBranchId;

    @FXML
    private Label lblBookAuthor;

    @FXML
    private ImageView btnsearch;


    @FXML
    private Label lblBookAvl;


    @FXML
    private Label lblGenre;
    
    @FXML
    private TextField txtBookTitle;

    @FXML
    private Label lblUserNameSet;

    private final BookTransactionBO bookTransactionBO = (BookTransactionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKTRANSACTION);
    private final BooksBO booksBO = (BooksBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKS);
    private ObservableList<String> branchesAvl = FXCollections.observableArrayList();
    private ObservableList<String> bookIdAvl = FXCollections.observableArrayList();
    private String transacId;


    public void initialize() {
        setDisableItemTrue();
        generateTransacId();
   setCellValueFactory();
        setTable();
    }

    private void setTable() {
        ObservableList<BarrowBooksTM> resTMS = FXCollections.observableArrayList();
        String sss = lblUserNameSet.getText();
        System.out.println("User Name xxxx setTable:"+sss);
        List<BarrowBooksDTO> barrowBooksDTOS = bookTransactionBO.getAllBarroeBooks(lblUserNameSet.getText());
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
        tblBook.setItems(resTMS);
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("books_id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("books_title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("books_genre"));
        colBarrowDate.setCellValueFactory(new PropertyValueFactory<>("barrow_date"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("return_date"));
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("branch_id"));
    }

    private void generateTransacId() {
        String transacId = bookTransactionBO.generateTransacId();
        this.transacId=transacId;
    }

    private void setDisableItemTrue() {
        cmbBranchId.setDisable(true);
        cmbBookId.setDisable(true);
    }


    @FXML
    void btnBarrowAction(MouseEvent event) {
        String branchId = cmbBranchId.getValue();
        String bookId = cmbBookId.getValue();
        String transacId = this.transacId;
        Date returnDate = Date.valueOf(LocalDate.now().plusDays(30).toString());
        String username = lblUserNameSet.getText();

        boolean isUpdate = booksBO.bookAvlUpdate(bookId,branchId);
        if (isUpdate==true) {
            boolean isBarrow = bookTransactionBO.save(new BookTransactionDTO(transacId,returnDate,bookId,username));
            if (isBarrow==true) {
                System.out.println("suceefulecef");
            }
        }


    }
    public boolean getAvilableBook(){
        List<BooksDTO> booksDTOS = booksBO.isSearchBookTitle(txtBookTitle.getText());
        if (booksDTOS.isEmpty()) {

            return false;
        }else {
            for (BooksDTO booksDTO : booksDTOS) {
                lblBookAvl.setText(booksDTO.getBooks_author());//avl
                lblGenre.setText(booksDTO.getBooks_genre());//genre
                txtBookTitle.setText(booksDTO.getBooks_avl());//title
                lblBookAuthor.setText(booksDTO.getBooks_title());//author
                branchesAvl.add(booksDTO.getBranch_id());
                bookIdAvl.add(booksDTO.getBooks_id()) ;
                cmbBranchId.setItems(branchesAvl);//branch
                cmbBookId.setItems(bookIdAvl);//book id
            }
            return true;
        }


    }

    @FXML
    void btnSearchAction(MouseEvent event) {
        boolean isAvl = getAvilableBook();
        if (isAvl) {
            cmbBranchId.setDisable(false);
            cmbBookId.setDisable(false);
            btnsearch.setDisable(true);
            btnsearch.setOpacity(0.5);
            lblBookAvl.setStyle("-fx-text-fill: green");
        } else if (isAvl==false) {
            btnsearch.setDisable(false);
            btnsearch.setOpacity(1);
            NotFoundComponent();
        }
    }



    @FXML
    void mouseEnterAction(MouseEvent mouseEvent) {
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
    @FXML
    void searchTxtClickSearchBtnDisablefalse(MouseEvent event) {
        cmbBranchId.setDisable(true);
        cmbBookId.setDisable(true);
        btnsearch.setDisable(false);
        btnsearch.setOpacity(1);
        clearComponent();
    }
    public void clearComponent(){
        lblBookAvl.setText("");
        lblGenre.setText("");
        txtBookTitle.setText("");
        lblBookAuthor.setText("");
        cmbBranchId.getItems().clear();
        cmbBookId.getItems().clear();
    }
    public void NotFoundComponent(){
        lblBookAvl.setStyle("-fx-text-fill: red");
        lblBookAvl.setText("This book can't be found");

    }


    public void setUserName(String userName) {
        System.out.println("User Name setUserStartup: "+userName);
        lblUserNameSet.setText(userName);
    }
}
