package com.eckstein.marketinganalyzer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mainViewController {

    private Database db;

    @FXML
    private TableView AngeboteTable;

    @FXML
    private TableView KundenTable;

    @FXML
    private  TableView AuftraegeTable;

    @FXML
    private TableView KontakteTable;

    @FXML
    private Tab AuftraegeTap;

    @FXML
    private Tab KundenTab;

    @FXML
    private Tab KontakteTap;

    @FXML
    private Tab AngeboteTap;

    public void initialize()
    {

    }

    private void GenerateTableHead(TableView view, ResultSet result) throws SQLException {
        for (int i = 0; i < result.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn<>(result.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            view.getColumns().addAll(col);
        }
    }

    private void FillTableWithContent(TableView view, ResultSet result) throws SQLException {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        while(result.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i = 1; i<= result.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(result.getString(i));
            }
            data.add(row);
        }
        view.setItems(data);
    }

    private void ClearTable(TableView table)
    {
        if(table.getColumns().size()==0){return;}

        int lastelementindex = table.getColumns().size();
        table.getColumns().remove(0,lastelementindex);

        lastelementindex = table.getItems().size();
        table.getItems().remove(0,lastelementindex);
    }

    public void setDB(Database db) {
        this.db=db;
    }

    @FXML
    private void UpdateKundenTable()
    {
        ClearTable(KundenTable);

        try {
            Connection con = db.getConnection();
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Kunden ;";
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();

            GenerateTableHead(KundenTable,resultSet);
            FillTableWithContent(KundenTable, resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void UpdateAuftraegeTable()
    {
        ClearTable(AuftraegeTable);

        try {
            Connection con = db.getConnection();
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Auftraege ;";
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();

            GenerateTableHead(AuftraegeTable,resultSet);
            FillTableWithContent(AuftraegeTable, resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void UpdateKontakteTable()
    {
        ClearTable(KontakteTable);

        try {
            Connection con = db.getConnection();
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Kontakte ;";
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();

            GenerateTableHead(KontakteTable,resultSet);
            FillTableWithContent(KontakteTable, resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void UpdateAngeboteTable()
    {
        ClearTable(AngeboteTable);

        try {
            Connection con = db.getConnection();
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Angebote ;";
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();

            GenerateTableHead(AngeboteTable,resultSet);
            FillTableWithContent(AngeboteTable, resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}