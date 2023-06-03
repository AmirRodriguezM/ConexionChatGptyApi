package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
    public class Conexion {

        private Connection connection;

        public Conexion(){
            try{
                Class.forName("org.sqlite.JDBC");
                this.connection = DriverManager.getConnection("jdbc:sqlite:proyectousuarios.db");
            }catch(ClassNotFoundException | SQLException e){
                e.printStackTrace();
            }
        }

        public Connection getConnection() {
            return this.connection;
        }
    }


