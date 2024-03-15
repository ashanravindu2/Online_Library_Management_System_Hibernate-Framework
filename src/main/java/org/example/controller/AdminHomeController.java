package org.example.controller;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.BooksBO;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;

public class AdminHomeController {
    public Label lblBranchCount;
    public Label lblRegisterBookCount;
    public Label lblUserCount;
    private final BooksBO booksBO = (BooksBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKS);
    private final BranchBO branchBO = (BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);
    private final UserBO userBO= (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        getBranchCount();
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

    public void getBranchCount() {
       int branchCount =  branchBO.getBranchCount();
       lblBranchCount.setText(String.valueOf(branchCount));
       int bookCount = booksBO.getBookCount();
       lblRegisterBookCount.setText(String.valueOf(bookCount));
       int userCount = userBO.getUserCount();
       lblUserCount.setText(String.valueOf(userCount));
    }
}
