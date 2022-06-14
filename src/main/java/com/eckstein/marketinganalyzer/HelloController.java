package com.eckstein.marketinganalyzer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;

public class HelloController {

    private Database db;

    @FXML
    public void initialize()
    {



    }

    public void setDB(Database db) {
        this.db=db;
    }
}