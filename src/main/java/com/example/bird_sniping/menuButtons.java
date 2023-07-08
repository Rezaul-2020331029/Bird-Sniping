package com.example.bird_sniping;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class menuButtons extends Button {

    //private  Image im = new Image("yellow_button00.jpg");
    private final String FONT = "JFWilwod.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('green_button05.png');";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('green_button02.png');";

    public menuButtons(String text)
    {
        setText(text);
        setMenuButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_FREE_STYLE);
        setListeners();
        //setPadding(new Insets(10,10,10,10));
    }
    private void setMenuButtonFont()
    {
        try {
            setFont(Font.loadFont(getClass().getResourceAsStream(FONT),25));
        } catch (Exception e) {
            System.out.println("not found");

        }
    }

    private void setBUTTON_PRESSED_STYLE()
    {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() +4 );
    }
    private void setBUTTON_FREE_STYLE()
    {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() -4 );
    }
    private void setListeners()
    {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                    setBUTTON_PRESSED_STYLE();

            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                    setBUTTON_FREE_STYLE();

            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new SepiaTone());
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }
    public menuButtons getMenuButton()
    {
        return this;
    }

}
