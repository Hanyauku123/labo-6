/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import com.mysql.jdbc.Connection;
import com.sun.istack.internal.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author LN710Q
 */
public class Conexion {
    private String user;
    private String pass;
    private String driver;
    private String url;
    
    private Connection cnx;
    
    public static Conexion instance;
    
    public synchronized static Conexion conectar(){
        if(instance == null){
            return new Conexion();
        }
        return instance;
    }
    
    private Conexion(){
    cargarCredenciales();
    
    try {
        Class.forName(this.driver);
        cnx = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void cargarCredenciales() {
       user = "root";
       pass = "";
       driver = "com.mysql.jdbc.Driver";
       url = "jdbc:mysql://localhost/filtros";
    }
    
    public Connection getCnx(){
        return cnx;
    }
    
    public void cerrarConexion(){
        instance = null;
    }
}
