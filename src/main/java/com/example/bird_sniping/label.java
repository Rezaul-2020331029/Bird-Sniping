package com.example.bird_sniping;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class label extends Label {

    public TranslateTransition translateTransition;
    public  label (String txt)
    {
        this.setText(txt);
        createTransiton();
    }
    public void createTransiton()
    {
        translateTransition = new TranslateTransition(Duration.millis(500),this);
    }
    public void setAnimationPos(double x1,double y1,double x2,double y2)
    {
        translateTransition.setFromX(x1);
        translateTransition.setFromY(y1);
        translateTransition.setToX(x2);
        translateTransition.setToY(y2);
    }
    public void startAnimation()
    {
        translateTransition.play();
    }
}
