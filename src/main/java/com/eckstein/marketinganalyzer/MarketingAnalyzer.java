package com.eckstein.marketinganalyzer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        mainViewController controller = fxmlLoader.getController();
        controller.setDB(db);
        stage.setTitle("MarketingAnalyzer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}