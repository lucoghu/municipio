package com.lucas.municipioweb.modelo.daos;

import com.lucas.municipioweb.modelo.Conexion;
import com.lucas.municipioweb.modelo.dtos.Reclamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
//import java.time.LocalDate;
//import java.util.Date;  // da error al importar

/**
 *
 * @author Lucas Oliva
 */
public class ReclamoDAO {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/instituto";
    private static final String USER = "root";
    private static final String PASS = "root";
    
    private static final String CONSULTA_TODOS = "SELECT * FROM reclamo";
    private static final String CONSULTA_INSERTAR = "INSERT INTO reclamo VALUES(null, ?, ?, ?, ?, ?, ?)";
    
    public Collection<Reclamo> listarReclamos()  {
        Collection<Reclamo> reclamos = new ArrayList<>();
         PreparedStatement ps;
         ResultSet rs;
        try{
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
             String instruccionSQL= "CONSULTA_TODOS";
             ps=con.prepareStatement(instruccionSQL);
             rs=ps.executeQuery();
             
             while(rs.next()){
                reclamos.add(new Reclamo(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getString(4),rs.getInt(5),rs.getInt(6) ,rs.getString(7)));
             }
        }catch (SQLException ex) {
            throw new RuntimeException("No se pudo obtener el Listado de Reclamos", ex);
        }
        return reclamos;
    }
    
    
     public int crearReclamo(Reclamo nuevoreclamo)  {
    int insertado=0;
    PreparedStatement ps;
        try {
            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
            String instruccionSQL= CONSULTA_INSERTAR;
            ps=con.prepareStatement(instruccionSQL);
            fillPreparedStatement(ps,nuevoreclamo);
            insertado= ps.executeUpdate();
 //executeUpdate() devuelve 0 si la instrucción no modifica nada, o devuelve la cantidad de registros modificados(insertados en este caso)
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar Reclamo", ex);
        }
   
    return insertado;
    }
     
     //seguir con Actualizar reclamo
     
     
     
     
      private void fillPreparedStatement(PreparedStatement ps, Reclamo rec) throws SQLException {
        ps.setDate(1, (Date) rec.getFechacreacion()); 
// el casteo que sugirio la IDE de getFechacreacion() y getFecharesolucion()(setters) es porque
//retornan tipos Date de java.util.Date, y usar ps.setDate se usa el Date de java.sql.Date
//pero si fuese así no debería import java.util.Date; ?????
        ps.setDate(2, (Date) rec.getFecharesolucion());
        ps.setString(3, rec.getDomicilio());
        ps.setInt(4, rec.getIdpersona());
        ps.setInt(5, rec.getIdcategoria());
        ps.setString(6, rec.getComentario());
        
     }
}
