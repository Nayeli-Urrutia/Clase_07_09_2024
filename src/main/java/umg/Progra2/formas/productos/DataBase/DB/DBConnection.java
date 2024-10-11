package umg.Progra2.formas.productos.DataBase.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Ruta de conexión a la base de datos SQLite
    private static final String URL = "jdbc:sqlite:C:\\Users\\Brayan\\OneDrive\\Escritorio\\SQLITE\\Base_Datos_sqLite\\db_telebot.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);


    }
}