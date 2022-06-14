package com.eckstein.marketinganalyzer;

import java.sql.*;
public class Database {

    private Connection connection;

    public Connection connect()
    {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:MarketingAnalyzer.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        this.connection = c;
        return c;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Checks if all needed tables are there*/
    public boolean areTablesThere()
    {
        return isTableKundenThere() && isTableAngeboteThere() && isTableAuftraegeThere() && isTableKontaktThere();
    }

    private boolean isTableKundenThere() {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet result = databaseMetaData.getTables(null, null, "Kunden", null);
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isTableAuftraegeThere() {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet result = databaseMetaData.getTables(null, null, "Auftraege", null);
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isTableKontaktThere() {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet result = databaseMetaData.getTables(null, null, "Kontakte", null);
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isTableAngeboteThere() {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet result = databaseMetaData.getTables(null, null, "Angebote", null);
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createTableKunden()
    {
        if (isTableKundenThere())
        {
            return;
        }
        try
        {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE Kunden " +
            "(ID INT PRIMARY KEY NOT NULL," +
            " KundenNR INT NOT NULL, " +
            " Firma VARCHAR)";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException e) {
    throw new RuntimeException(e);
}
    }

    public void createTableKontakte()
    {
        if (isTableKontaktThere())
            return;

        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE Kontakte " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " KundenNR           INT    NOT NULL, " +
                    " Kontaktdatum date"+
                    " was            VARCHAR ," +
                    "FOREIGN KEY(KundenNR) REFERENCES Kunden(ID))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTableAngebote()
    {
        if (isTableAngeboteThere())
            return;
        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE Angebote " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " KundenNR INT NOT NULL, " +
                    " Angebotdatum date"+
                    " AngebotNr VARCHAR," +
                    "FOREIGN KEY(KundenNR) REFERENCES Kunden(ID))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTableAuftraege()
    {
        if (isTableAuftraegeThere())
            return;
        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE Auftraege " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " KundenNR           INT    NOT NULL, " +
                    " Auftragsdatum date,"+
                    "AuftragsNR INT,"+
                    " AngebotsNR INT," +
                    "FOREIGN KEY (AuftragsNR) REFERENCES Auftraege(ID)," +
                    "FOREIGN KEY (AngebotsNR) REFERENCES Angebote(ID)," +
                    "FOREIGN KEY (KundenNR)REFERENCES Kunden(ID))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
