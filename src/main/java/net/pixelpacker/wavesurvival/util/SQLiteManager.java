package net.pixelpacker.wavesurvival.util;

import net.pixelpacker.wavesurvival.WavesurvivalMain;
import org.bukkit.block.Block;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteManager {
    static WavesurvivalMain plugin = WavesurvivalMain.getPlugin(WavesurvivalMain.class);
    public static void checkDB(String fileName){
        Path configFolderPath = Paths.get(plugin.getDataFolder().getAbsolutePath());
        File configFolderFile = configFolderPath.toFile();
        if(!configFolderFile.exists()){
            configFolderFile.mkdirs();
            plugin.getLogger().info("Made config folder");
        }
        String url = "jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + "/" + fileName + ".db";
        Path dbFilePath = Paths.get(plugin.getDataFolder().getAbsolutePath()+ "/" + fileName + ".db");
        File dbFile = dbFilePath.toFile();

        if(!dbFile.exists()){
            try(Connection connection = DriverManager.getConnection(url)){
                if(connection != null){
                    plugin.getLogger().info("Command failed. DB doesn't exist");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            plugin.getLogger().info("DB Already Exists... Continuing");
        }
    }

    public static void createDB(String fileName) {
        Path configFolderPath = Paths.get(plugin.getDataFolder().getAbsolutePath());
        File configFolderFile = configFolderPath.toFile();
        if (!configFolderFile.exists()) {
            configFolderFile.mkdirs();
            plugin.getLogger().info("Made config folder");
        }
        String url = "jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + "/" + fileName + ".db";
        Path dbFilePath = Paths.get(plugin.getDataFolder().getAbsolutePath() + "/" + fileName + ".db");
        File dbFile = dbFilePath.toFile();

        if (!dbFile.exists()) {
            try (Connection connection = DriverManager.getConnection(url)) {
                if (connection != null) {
                    DatabaseMetaData meta = connection.getMetaData();
                    plugin.getLogger().info("Made DB");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            plugin.getLogger().info("DB already exists...");
        }
    }
    
    public static void insert(String dbName, String table, String column, String entry){
        checkDB(dbName);
        Connection conn = connect(dbName);
        PreparedStatement statement = null;
        try(conn){
            String sql = "INSERT INTO " + table + "(" + column + ") VALUES(?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, entry);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void addColumn(String dbName, String tableName){
        //TODO:Look into adding the ability to add columns to DBs to allow for creation of more spawnpoints and stuff.
    }

    //Creates a table and column that stores text
    public static void addTextTable(String dbName, String tableName, String column){
        checkDB(dbName);
        Connection conn = connect(dbName);
        Statement statement = null;
        try(conn){
            statement = conn.createStatement();
            String sqlCreate = "CREATE TABLE IF NOT EXISTS " + tableName + "(" + column + " TEXT);";
            statement.executeUpdate(sqlCreate);
            plugin.getLogger().info("Created table");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Creates a table and column that stores integers
    public static void addIntegerTable(String dbName, String tableName, String column){
        checkDB(dbName);
        Connection conn = connect(dbName);
        Statement statement = null;
        try(conn){
            statement = conn.createStatement();
            String sqlCreate = "CREATE TABLE IF NOT EXISTS " + tableName + "(" + column + " INTEGER);";
            statement.executeUpdate(sqlCreate);
            plugin.getLogger().info("Created table");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Creates a table and column that stores floats
    public static void addRealTable(String dbName, String tableName, String column){
        checkDB(dbName);
        Connection conn = connect(dbName);
        Statement statement = null;
        try(conn){
            statement = conn.createStatement();
            String sqlCreate = "CREATE TABLE IF NOT EXISTS " + tableName + "(" + column + " REAL);";
            statement.executeUpdate(sqlCreate);
            plugin.getLogger().info("Created table");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Creates a table and column that stores blobs (Blobs can store any kind of data given to them)
    public static void addBlobTable(String dbName, String tableName, String column){
        checkDB(dbName);
        Connection conn = connect(dbName);
        Statement statement = null;
        try(conn){
            statement = conn.createStatement();
            String sqlCreate = "CREATE TABLE IF NOT EXISTS " + tableName + "(" + column + " BLOB);";
            statement.executeUpdate(sqlCreate);
            plugin.getLogger().info("Created table");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Reads a string from the DB
    public static String readDBString(String dbName, String tableName, String column, int tableEntry){
        Connection conn = connect(dbName);
        String QUERY = "SELECT " + column + " FROM " + tableName;
        try(conn){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            List<String> results = new ArrayList<>();
            while(resultSet.next()){
                results.add(resultSet.getString(column));
            }
            return results.get(tableEntry - 1);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Reads an int from the DB
    public static int readDBInt(String dbName, String tableName, String column, int tableEntry){
        Connection conn = connect(dbName);
        String QUERY = "SELECT " + column + " FROM " + tableName;
        try(conn){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            List<String> results = new ArrayList<>();
            while(resultSet.next()){
                results.add(resultSet.getString(column));
            }
            return Integer.valueOf(results.get(tableEntry - 1));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Reads a float from the DB
    public static float readDBFloat(String dbName, String tableName, String column, int tableEntry){
        Connection conn = connect(dbName);
        String QUERY = "SELECT " + column + " FROM " + tableName;
        try(conn){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            List<String> results = new ArrayList<>();
            while(resultSet.next()){
                results.add(resultSet.getString(column));
            }
            return Float.valueOf(results.get(tableEntry - 1));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Is used to connect to the database
    public static Connection connect(String DBName){
        String url = "jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + "/" + DBName + ".db";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url);
            plugin.getLogger().info("Connected to DB");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return connection;
    }
}
