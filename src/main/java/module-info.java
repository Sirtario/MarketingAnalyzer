module com.eckstein.marketinganalyzer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.eckstein.marketinganalyzer to javafx.fxml;
    exports com.eckstein.marketinganalyzer;
}