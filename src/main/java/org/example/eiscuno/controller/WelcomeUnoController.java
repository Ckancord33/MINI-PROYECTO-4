package org.example.eiscuno.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.example.eiscuno.view.GameUnoStage;
import org.example.eiscuno.view.WelcomeUnoStage;

import java.io.IOException;

public class WelcomeUnoController {

    @FXML
    private Ellipse ellipsePlay;

    @FXML
    public void onHandlePlayButton(ActionEvent event)throws IOException {
        GameUnoStage.getInstance();
        WelcomeUnoStage.deleteInstance();
    }

    @FXML
    public void onHandleEnteredPlayButton() {
        ellipsePlay.setRadiusX(ellipsePlay.getRadiusX() + 5);
        ellipsePlay.setRadiusY(ellipsePlay.getRadiusY() + 5);
        DropShadow glow = new DropShadow();
        glow.setColor(Color.RED);
        glow.setRadius(50);
        ellipsePlay.setEffect(glow);
    }

    @FXML
    void onHandleExitedPlayButton(MouseEvent event) {
        ellipsePlay.setRadiusX(ellipsePlay.getRadiusX() - 5);
        ellipsePlay.setRadiusY(ellipsePlay.getRadiusY() - 5);
        ellipsePlay.setEffect(null);
    }

    @FXML
    void onHandleExitButton(ActionEvent event) {
        WelcomeUnoStage.deleteInstance();
    }




}
