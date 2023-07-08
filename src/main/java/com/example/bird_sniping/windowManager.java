package com.example.bird_sniping;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class windowManager {

    public  final int MENU_PANEL_WIDTH = 1024;
    public final int MENU_PANEL_HEIGHT = 768;
    public boolean isHelpClicked=false;

    public final String MENU_BACKGROUND = "rsz_28313500_2205_w023_n001_2285b_p1_2285.jpg";

    public AnchorPane menuPane;
    public Scene menuScene;

    public Stage menuStage;
    private Label nameLabel;
    private Label helpLabel;

    public windowManager(){
        menuPane = new AnchorPane();
        menuScene = new Scene(menuPane,MENU_PANEL_WIDTH,MENU_PANEL_HEIGHT);
        menuStage = new Stage();
        menuStage.setScene(menuScene);
        createMenuBackgrounds();
        createPlayButton();
        createHelpButton();
        createQuitButton();
        createNameLabel();
        createHelpAction();
        menuStage.setTitle("Bird Sniping");
        menuStage.getIcons().add(new Image(getClass().getResourceAsStream("rsz_28313500_2205_w023_n001_2285b_p1_2285.jpg")));

    }

    private void createHelpAction() {
        helpLabel = new Label();
        helpLabel.setText("Press R for Reload\n Score 10 Points to Win");
        menuPane.getChildren().add(helpLabel);
        helpLabel.setFont(Font.font("Papyrus",30));
        helpLabel.setPadding(new Insets(10,10,10,10));
        helpLabel.setAlignment(Pos.CENTER);
        helpLabel.setTextFill(Color.DARKRED);
        helpLabel.setEffect(new Glow());
        helpLabel.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("rsz_wood.jpg")), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null)));
        helpLabel.setPrefHeight(160);
        helpLabel.setPrefWidth(345);
        helpLabel.setLayoutX(157+200+150);
        helpLabel.setLayoutY(390-345.0/2+49.0/2 + 100-10);
        helpLabel.setOpacity(0);
    }

    private void createNameLabel() {
        nameLabel = new Label();
        nameLabel.setText("Bird Sniping");
        nameLabel.setFont(Font.loadFont(getClass().getResourceAsStream("JFWilwod.ttf"),50));
        nameLabel.setTextFill(Color.DARKGOLDENROD);
        menuPane.getChildren().add(nameLabel);
        nameLabel.setLayoutX(300);
        nameLabel.setLayoutY(150-50);
        nameLabel.setEffect(new Bloom());
    }

    private void createQuitButton() {
        menuButtons b = new menuButtons("Quit");
        b.setLayoutX(417-150-30-80);
        b.setLayoutY(460);
        menuPane.getChildren().add(b);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                menuStage.close();
            }
        });
    }

    private void createHelpButton() {
        menuButtons b = new menuButtons("Help");
        b.setLayoutX(417-150-30-80);
        b.setLayoutY(390);
        menuPane.getChildren().add(b);
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (isHelpClicked)
                {
                    helpLabel.setOpacity(0);
                    isHelpClicked=false;
                }
                else
                {
                    helpLabel.setOpacity(1);
                    isHelpClicked = true;
                }

            }
        });
    }

    private void createPlayButton() {
        menuButtons b = new menuButtons("Play");
        b.setLayoutX(417-150-30-80);
        b.setLayoutY(320);
        menuPane.getChildren().add(b);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               {

                    gameWindow gameManager = new gameWindow();
                    gameManager.createGameWindow(menuStage);


                }
            }
        });
    }


    public Stage getMenuStage(){
        return menuStage;
    }

    public void createMenuBackgrounds()
    {
        menuPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(MENU_BACKGROUND)), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null)));
    }

}
