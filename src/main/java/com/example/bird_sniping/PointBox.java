package com.example.bird_sniping;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PointBox {

    Label pointLabel;
    AnchorPane gamePane;
    public int point = 0;
    String P = "Points : ";
    public  PointBox(AnchorPane x)
    {
        pointLabel = new Label();
        gamePane = x;
        createBackground();

    }

    private void createBackground() {
        Image bg = new Image(getClass().getResourceAsStream("green_button13.png"));
        BackgroundImage bgi = new BackgroundImage(bg, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        pointLabel.setBackground(new Background(bgi));
        pointLabel.setFont(Font.loadFont(getClass().getResourceAsStream("JFWilwod.ttf"),20));
        pointLabel.setPadding(new Insets(10,10,10,10));
        pointLabel.setText(P+"0");
        pointLabel.setTextFill(Color.DARKGOLDENROD);
        pointLabel.setPrefHeight(49);
        pointLabel.setPrefWidth(190);
        gamePane.getChildren().add(pointLabel);
        pointLabel.setLayoutX(834-20);
        pointLabel.setLayoutY(710-30);

    }
    public void addPoint()
    {
        point++;
        pointLabel.setText(P+point);
    }

    public int getPoint()
    {
        return point;
    }

}
