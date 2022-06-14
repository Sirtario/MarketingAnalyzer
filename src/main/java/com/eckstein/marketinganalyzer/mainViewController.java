package com.eckstein.marketinganalyzer;

import javafx.fxml.FXML;

public class mainViewController {

    private Database db;

    @FXML
    public void initialize()
    {



    }

    public void setDB(Database db) {
        this.db=db;
    }
}