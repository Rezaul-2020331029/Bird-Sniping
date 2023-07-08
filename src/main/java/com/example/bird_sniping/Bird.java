package com.example.bird_sniping;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.util.Random;

public  class Bird {

    String[] imageurls;
    AnchorPane gamePane;
    TranslateTransition translateTransition;
    ParallelTransition parallelTransition;
    double TIME = 2400;
    ImageView imageView = new ImageView();
    Timeline timeline;
    Image[] images;
    Random randomPositionGenerator = new Random();
    private  boolean isreversed = false;
    
    int[] Xrandomiser = {-10,1028};

    int fromX,fromY,toX,toY;
    
    double angle;

    public double imageX,imageY;
    TranslateTransition fallingAnimation;
    double scopePointX,scopePointY;
    

    public Bird (String[] imageurls,AnchorPane pane)
    {
        this.imageurls = imageurls;
        gamePane = pane;
        crateImages();
        crateKeyFrames();
        crateAnimation();
        createFallingAnimation();

    }
    public  void startAnimation()
    {
        setPos();
        gamePane.getChildren().add(imageView);
        parallelTransition.play();
        System.out.println("animation start");
    }

    public void crateAnimation() {
        translateTransition = new TranslateTransition(Duration.millis(TIME),imageView);
        parallelTransition = new ParallelTransition(timeline,translateTransition);
        parallelTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                endAnimation();
                System.out.println("animation Finished");
            };
        });
    }
    public void endAnimation ()
    {
        if (isreversed == true)
            imageView.setScaleY(1);
        gamePane.getChildren().remove(imageView);
        parallelTransition.stop();

    }

    private void crateKeyFrames() {
        timeline = new Timeline();
        long now = 0;
        for (int i = 0;i<3;i++)
        {
            for (int j=0;j<16;j++)
            {
                KeyFrame keyFrame = new KeyFrame(Duration.millis(now),
                        new KeyValue(imageView.imageProperty(),images[j%8]));
                now+=50;
                timeline.getKeyFrames().add(keyFrame);
            }
        }

    }

    public  void crateImages()
    {
        images = new Image[imageurls.length];
        for (int i = 0;i<imageurls.length;i++)
        {
            images[i] = new Image(getClass().getResourceAsStream(imageurls[i]));
        }
        imageView.setFitWidth(180);
        imageView.setFitHeight(150);
    }

    public static double getAngle(double x1,double y1,double x2,double y2) {
        double X = x2-x1;
        double Y = y2-y1;

        return (Math.atan2(Y,X)*(180/Math.PI));
    }
    public void setPos()
    {
        int f = randomPositionGenerator.nextInt(2);
        if (f == 0)
        fromX = -130;
        else
            fromX = 1060;
        if (fromX == -130)
        toX = 1060;
        else
            toX = -130;

        toY = Math.abs( randomPositionGenerator.nextInt(600));
        fromY =  Math.abs( randomPositionGenerator.nextInt(600));
        angle = getAngle(fromX,fromY,toX,toY);
        if (toX==-130){
            imageView.setScaleY(-1);
            isreversed  = true;
        }
        imageView.setRotate(angle);
        System.out.println(imageView.getRotate());
        translateTransition.setFromX(fromX);
        translateTransition.setFromY(fromY);
        translateTransition.setToX(toX);
        translateTransition.setToY(toY);
    }
    public double getimageLayoutX()
    {
        if (imageView.getParent() == null)
        {
            return -10_000;
        }
        else
            return translateTransition.getNode().getTranslateX();
    }
    public double getimageLayoutY()
    {
        if (imageView.getParent() == null)
        {
            return -10_000;
        }
        else
            return translateTransition.getNode().getTranslateY();
    }
    public boolean shoot(double scopeX,double scopeY)
    {
         imageX = getimageLayoutX();
         imageY = getimageLayoutY();
        if (imageX==-10_000 || imageY == -10_000){ System.out.println("error");
            return false;

        }

         scopePointX = scopeX + 105;
         scopePointY = scopeY + 100;
        double birdPointX = imageX  +90;
        double birdpointY = imageY  + 75;
        System.out.println(scopePointX +"   " + scopePointY + "     "+  birdPointX+"    "+birdpointY);
        double distance = Math.sqrt(  (birdpointY - scopePointY)*(birdpointY - scopePointY) + (birdPointX - scopePointX)*(birdPointX - scopePointX));
        System.out.println(distance);
        if (distance >=60)
            return false;
        else
            return  true;
    }
    public  void createFallingAnimation()
    {
        fallingAnimation = new TranslateTransition(Duration.millis(250),imageView);

        fallingAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gamePane.getChildren().remove(imageView);
                fallingAnimation.stop();
            }
        });
    }
    public  void startFallingAnimation()
    {
        fallingAnimation.setFromX(scopePointX);
        fallingAnimation.setFromY(scopePointY);
        fallingAnimation.setToY(880);
        gamePane.getChildren().add(imageView);
        fallingAnimation.play();
    }
}