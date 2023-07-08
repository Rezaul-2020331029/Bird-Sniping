package com.example.bird_sniping;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class gameWindow {

    public final int GAME_WINDOW_WIDTH = 1024;
    public final int GAME_WINDOW_HEIGHT = 768;
    AnchorPane gamePane;
    Scene gameScene;
    Stage gameStage;
    boolean isfirstAnimation_shown = false;
    public final double scopeRadius = 10;
    public final double birdRadius  = 80;
    ammoBox Ammobox;

    TranslateTransition translateTransition1;
    ParallelTransition parallelTransition1;
    Timeline timeline;
    public  AnimationTimer gameloop;

    long elapsedTime;
    long currentTime;

    public String[] Rbird;
    public String[] Ybird;
    public Bird[] Birds;
    public Random random = new Random();
    public Bird currentBird = null;
    public PointBox pointBox;
    public  int relasedBirds=0;
    public Label finishLabel1;
    public Label finishLabel2;
    TranslateTransition finisingAnimation1;
    TranslateTransition finishingAnimation2;
    public final int pointTowin=10;
    public final int totalBird = 15;
    private boolean isRpressed= false;
    public boolean canFire=true;

    private PauseTransition delaytransition = new PauseTransition(Duration.seconds(2));
    private PauseTransition shootingDelay = new PauseTransition(Duration.millis(400));

    gameWindow()
    {
        initializeGameWindow();
        initializeMouseListeners();
        createBirds();
        createBackground();
        createAmmobox();
        createPointBox();
        createFinishingAnimation();
        gameStage.setTitle("Bird Sniping");
        gameStage.getIcons().add(new Image(getClass().getResourceAsStream("rsz_28313500_2205_w023_n001_2285b_p1_2285.jpg")));
        gameScene.setCursor(Cursor.NONE);

    }

    private void initializeGameWindow() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,1024,768);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setResizable(false);
    }
    private void shotDelay (Runnable action)
    {
        shootingDelay.setOnFinished(actionEvent -> action.run());
        shootingDelay.play();
    }

    private void createFinishingAnimation() {
        finishLabel1 = new Label();
        finishLabel2 = new Label();
        finishLabel1.setPrefWidth(600);
        finishLabel2.setPrefWidth(600);
        finishLabel1.setFont(Font.font("Papyrus",70));
        finishLabel2.setFont(Font.font("Papyrus",70));
        finishLabel1.setText("You Win !!!");
        finishLabel1.setPrefHeight(400);
        finishLabel2.setPrefHeight(400);
        finishLabel2.setText("You Lose");
        finishLabel1.setTextFill(Color.GOLD);
        finishLabel2.setTextFill(Color.RED);
        gamePane.getChildren().add(finishLabel1);
        gamePane.getChildren().add(finishLabel2);
        finisingAnimation1 = new TranslateTransition(Duration.seconds(1.5),finishLabel1);
        finisingAnimation1.setNode(finishLabel1);
        finishingAnimation2 = new TranslateTransition(Duration.millis(1500),finishLabel2);
        finishingAnimation2.setNode(finishLabel2);
        finishLabel1.setLayoutY(-300);
        finishLabel1.setLayoutX(300);
        finishLabel2.setLayoutY(-300);
        finishLabel2.setLayoutX(300);
        finisingAnimation1.setFromY(finishLabel1.getLayoutY());
        finishingAnimation2.setFromY(finishLabel2.getLayoutY());
        finisingAnimation1.setToY(450);
        finishingAnimation2.setToY(450);

    }
    private void delay( Runnable action)
    {
        delaytransition.setOnFinished(event -> action.run());
        delaytransition.play();
    }

    private void createPointBox() {
        pointBox = new PointBox(gamePane);
    }

    private void createAmmobox() {
        Ammobox = new ammoBox(gamePane);
    }

    private void createBirds() {
        Rbird = new String[8];
        for (int i=0;i<8;i++)
        {
            Rbird[i] = "Rbird"+(i+1)+".png";
        }
        Ybird = new String[8];
        for (int i=0;i<8;i++)
        {
            Ybird[i] = "Ybird"+(i+1)+".png";
        }

        Birds = new Bird[2];
        Birds[0] = new Bird(Rbird,gamePane);
        Birds[1] = new Bird(Ybird,gamePane);
    }

    public void flyBirds()
    {
        int selectedBird = random.nextInt(2);
        currentBird = Birds[selectedBird];
        Birds[selectedBird].startAnimation();
    }

    private void createBackground() {
        Image bg = new Image(getClass().getResourceAsStream("rsz_8917315_44780.jpg"));
        BackgroundImage bgi = new BackgroundImage(bg, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        gamePane.setBackground(new Background(bgi));


    }

    private void initializeMouseListeners() {

        ImageView scope = new ImageView(new Image(getClass().getResourceAsStream("pngegg.png")));
        scope.setFitWidth(210);
        scope.setFitHeight(200);
        gamePane.getChildren().add(scope);
        gameScene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scope.setLayoutX(mouseEvent.getX() - scope.getFitWidth() / 2);
                scope.setLayoutY(mouseEvent.getY() - scope.getFitHeight() / 2);
                // System.out.println("Scope  : " + scope.getLayoutX());
                //System.out.println("Mouse  : " + mouseEvent.getX());
            }
        });

        gameScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scope.setLayoutX(mouseEvent.getX() - scope.getFitWidth() / 2);
                scope.setLayoutY(mouseEvent.getY() - scope.getFitHeight() / 2);
                //System.out.println("Scope  : " + scope.getLayoutX());
                //System.out.println("Mouse  : " + mouseEvent.getX());
            }
        });

        gameScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if ( canFire) {
                    if (currentBird == null) return;
                    if (Ammobox.currentBullet == 0) return;
                    Ammobox.removeBullet();
                    boolean f = currentBird.shoot(scope.getLayoutX(), scope.getLayoutY());
                    canFire = false;
                    shotDelay(()->dummy());
                    if (f) {
                        pointBox.addPoint();
                        currentBird.endAnimation();
                        currentBird.startFallingAnimation();
                    } else
                        System.out.println("failed");
                }
            }
        });

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode()==KeyCode.ESCAPE) goToMenu();
                if (keyEvent.getCode() == KeyCode.R)
                {
                    if (isRpressed==false)
                    {
                        isRpressed = true;
                        delay(()->Ammobox.Reload());
                        System.out.println("Reloaded");
                    }
                }
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.R)
                    isRpressed = false;
            }
        });


    }

    public void createGameWindow(Stage menuStage)
    {
        menuStage.hide();

        gameStage.show();
        currentTime = System.nanoTime();
        createGameLoop();
    }

    public void createGameLoop() {

        gameloop = new AnimationTimer() {
            @Override
            public void handle(long l)
            {
                elapsedTime = l - currentTime;
                if (elapsedTime>=3_000_000_000l)
                {
                    if (relasedBirds==totalBird) {
                        gameloop.stop();
                        winOrLose();
                        return;
                    }
                    flyBirds();

                    relasedBirds++;

                    currentTime = l;

                }
            }
        };
        gameloop.start();
    }

    public double getAngle(double fromX,double fromY,double toX,double toY){
        double X =  toX- fromX;
        double Y = toY - fromY;
        return  -(Math.atan2(Y,X)*(180/Math.PI));
    }

    public void winOrLose()
    {
        if (pointBox.point>=pointTowin)
            finisingAnimation1.play();
        else{
            finishingAnimation2.play();
            System.out.println("dfd");
        }

    }
    public void goToMenu()
    {
        gameloop.stop();
        gameStage.hide();
        windowManager windowmanager = new windowManager();
        Stage stage = windowmanager.getMenuStage();
        stage.setResizable(false);
        stage.show();
    }
    private void dummy()
    {
        canFire = true;
    }

}
