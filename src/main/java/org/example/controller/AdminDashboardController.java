package org.example.controller;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminDashboardController {
    public Label lblTime;
    public Label lblDate;
    private Thread thread;
    boolean run = true;


    public void initialize() {
        lblDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
        setCurrentTime();
        run = true;
    }

    public void mouseEnterButtonAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GREEN);
            glow.setWidth(15);
            glow.setHeight(15);
            glow.setRadius(15);
            icon.setEffect(glow);
        }

    }

    public void mouseExitButtonAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }

    private void setCurrentTime() {
        thread =  new Thread(()->{
            SimpleDateFormat format=new SimpleDateFormat("hh:mm:ss");
            while (run){
                try {
                    Thread.sleep(1000);
                }catch (Exception ignored){
                }
                final String time=format.format(new Date());
                Platform.runLater(() -> lblTime.setText(time));
            }
        });

        thread.start();
    }
}
