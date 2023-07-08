package com.example.bird_sniping;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ammoBox {
        Image bulletImage = new Image(getClass().getResourceAsStream("Bullet.png"));
        ImageView[] Bullets;
        public HBox AmmoBox;
        public int currentBullet = 5;
        AnchorPane gamePane;
        public Label reloadLabel;
        public  String FONT = "JFWilwod.ttf";

        public ammoBox(AnchorPane x)
        {
            gamePane = x;
            Bullets = new ImageView[5];
            AmmoBox = new HBox();
            AmmoBox.setPrefHeight(49);
            AmmoBox.setPrefWidth(190);
            createBackground();
            createBullets();
            addBullets();
            createReloadLabel();
        }

    private void createReloadLabel() {
            reloadLabel = new Label();
            reloadLabel.setText("Reload !!!");
            reloadLabel.setFont(Font.loadFont(getClass().getResourceAsStream(FONT),30));
            reloadLabel.setTextFill(Color.DARKRED);
            reloadLabel.setLayoutX(400);
            reloadLabel.setLayoutY(600);
    }

    private void addBullets() {
            AmmoBox.setSpacing(3);
            AmmoBox.getChildren().addAll(Bullets);
            gamePane.getChildren().add(AmmoBox);
    }

    private void createBullets()
        {
            for (int i=0;i<5;i++)
            {
                Bullets[i]  = new ImageView();
                Bullets[i].setImage(bulletImage);
                Bullets[i].setFitWidth(20);
                Bullets[i].setFitHeight(90);
            }
        }
        private void createBackground()
        {
            /*Image bg = new Image(getClass().getResourceAsStream("Whitebg.jpg"));
            BackgroundImage bgi = new BackgroundImage(bg, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
            AmmoBox.setBackground(new Background(bgi));*/
            AmmoBox.setLayoutX(10);
            AmmoBox.setLayoutY(668);


        }
        public void addBullet()
        {
            if (currentBullet == 5)
                return;
            else
            {
                if (currentBullet==0)
                    gamePane.getChildren().remove(reloadLabel);
                AmmoBox.getChildren().add(Bullets[currentBullet]);
                currentBullet++;
            }
        }
        public void removeBullet()
        {
            if (currentBullet==0)
                return;
            else {
                currentBullet--;
                if (currentBullet==0)
                    gamePane.getChildren().add(reloadLabel);
                AmmoBox.getChildren().remove(currentBullet);
            }
        }
        public void setAmmoBoxLayout(double x,double y)
        {
            AmmoBox.setLayoutX(x);
            AmmoBox.setLayoutY(y);
        }
        public void Reload ()  {
            while (currentBullet<5)
            {

                try {
                    addBullet();
                }
                catch (Exception e)
                {
                    System.out.println("Reload Exception");
                }
            }
        }

}
