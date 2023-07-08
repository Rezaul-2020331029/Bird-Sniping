package com.example.bird_sniping;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application
    {
    @Override
    public void start(Stage stage) throws IOException {

        windowManager windowmanager = new windowManager();
        stage = windowmanager.getMenuStage();
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}