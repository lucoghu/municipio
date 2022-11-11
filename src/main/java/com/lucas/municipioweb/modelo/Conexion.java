 
package com.lucas.municipioweb.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lucas Oliva
 */
public class Conexion {

    private static Connection con;  // Patron Singleton para asegurarse que haya una sola instancia de conexión
               // entonces para que Conexion sea Singleton, se declara un atributo estático y constructor privado
    private Conexion(){}

    public static Connection getConexion(String driver, String url, String user, String pass) {
        if (con == null) {
            try {
                Runtime.getRuntime().addShutdownHook(new MiShutDownHook()); // Agregar hook de fin de programa, 
                //un hook es básicamente como un Listener de java Swing que espera que finalize 
                //este método getConexion, para cerrar la conexion una sola vez
                
                Class.forName(driver); // Chequeo de Driver (sujeto a excepciones)
                con = DriverManager.getConnection(url, user, pass); // Obtener la conexión
                System.out.println("Conexión exitosa: " + con.getClass().getName());
            } catch (SQLException ex) {
                      ex.printStackTrace();
                      
            }catch (ClassNotFoundException ex) {
                throw new RuntimeException("No se encuentra driver " + driver +ex.getMessage() , ex);
               //ex.printStackTrace();
                
            } catch (Exception ex) {
                throw new RuntimeException("No se pudo establecer conexión con la Base de Datos", ex);
            }
        }
        return con;
    }

    private static class MiShutDownHook extends Thread {

        /* Justo antes de finalizar el programa la JVM invocará
           a este método donde podemos cerrar la conexion */
        @Override
        public void run() {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Connection liberada");
                }
            } catch (Exception ex) {
                System.out.println("Error liberando Connection al finalizar app: " + ex.getMessage());
            }
        }
    }
}

