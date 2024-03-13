package org.example.controller;

import javafx.animation.ScaleTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BarrowBookController {
    public void btnBarrowAction(MouseEvent mouseEvent) {
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
