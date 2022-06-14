package com.eckstein.marketinganalyzer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MarketingAnalyzer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Database db = new Database();
        db.connect();
        if(!db.areTablesThere())
        {
            db.createTableKontakte();
            db.createTableAngebote();
            db.createTableKunden();
            db.createTableAuftraege();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(MarketingAnalyzer.class.getResource("mainView.fxml"));
        mainViewController controller = fxmlLoader.getController();
        controller.setDB(db);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}