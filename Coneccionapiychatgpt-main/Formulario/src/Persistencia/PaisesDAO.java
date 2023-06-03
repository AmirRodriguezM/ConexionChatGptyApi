package Persistencia;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public class PaisesDAO {

        private Connection connection;

        public PaisesDAO(){
            Conexion conexion = new Conexion();
            this.connection = conexion.getConnection();
            this.crearTabla();
        }
 //   String paiss, String capitalfinal, String monedaFinal, String idiomafinal, String year, double val
        private void crearTabla(){
            try{
                Statement statement = this.connection.createStatement();
                statement.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS Paises (id INTEGER PRIMARY KEY, paiss TEXT, capitalfinal TEXT, monedaFinal TEXT, idiomafinal TEXT, ginis FLOAT) ");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        public boolean crear(String paiss, String capitalfinal, String monedaFinal, String idiomafinal, double ginis){
            try {
                PreparedStatement preparedStatement = this.connection.prepareStatement(
                        "INSERT INTO Paises(paiss, capitalfinal, monedaFinal, idiomafinal, ginis) VALUES (?, ?, ?, ?, ?) "
                );
                preparedStatement.setString(1, paiss);
                preparedStatement.setString(2, capitalfinal);
                preparedStatement.setString(3, monedaFinal);
                preparedStatement.setString(4, idiomafinal);
                preparedStatement.setString(5, String.valueOf(ginis));

                preparedStatement.execute();
                return true;
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }

        }

        public ResultSet listar(){
            ResultSet resultSet = null;
            try{
                Statement statement = this.connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM usuarios");
                return resultSet;

            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }

    }

