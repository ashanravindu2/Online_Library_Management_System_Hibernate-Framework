package org.example.controller;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookTransactionBO;
import org.example.bo.custom.BooksBO;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;

public class UserHomeController {
    public Label lblBranchCount;
    public Label lblRegisterBookCount;
    public Label lblUserCount;
    private final BooksBO booksBO = (BooksBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKS);
    private final BranchBO branchBO = (BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);
    private final UserBO userBO= (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    private final BookTransactionBO bookTransactionBO = (BookTransactionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKTRANSACTION);
    public Label username;
    private String userNames;


    public void initialize() {
        getBranchCount();
    }

    public void mouseEnterAction(MouseEvent mouseEvent) {
        getBranchCount();
       // setUserName(userNames);
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
        getBranchCount();
      //  setUserName(username.getText());
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }

    public void getBranchCount() {
        username.setText(this.userNames);

       int branchCount =  branchBO.getOpenBranchCount();
       lblBranchCount.setText(String.valueOf(branchCount));
       int bookCount = booksBO.getBookCount();
       lblRegisterBookCount.setText(String.valueOf(bookCount));
        String userName = this.userNames;
       int transacBoookCount = bookTransactionBO.getBookCountBarrow(userName);
       lblUserCount.setText(String.valueOf(transacBoookCount));
    }

    public void setUserName(String text) {
        this.userNames = text;
        username.setText(userNames);
    }
}
