package com.lucas.municipioweb.modelo.daos;

import com.lucas.municipioweb.modelo.Conexion;
import com.lucas.municipioweb.modelo.dtos.Categoria;
import com.lucas.municipioweb.modelo.dtos.Persona;
import com.lucas.municipioweb.modelo.dtos.Reclamo;
import com.lucas.municipioweb.modelo.dtos.TipoCategoria;

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
    private static final String URL = "jdbc:mysql://localhost:3306/municipio?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "baseUTN2021";
    
   // private static final String CONSULTA_TODOS = "SELECT * FROM reclamo";
    private static final String CONSULTA_TODOS ="SELECT idreclamo, FechaCreacion, FechaResolucion, Domicilio,categoria.*,Comentario, persona.* FROM (reclamo INNER JOIN categoria ON reclamo.Categoria_idcategoria=categoria.idcategoria ) INNER JOIN persona ON reclamo.Persona_idpersona=persona.idpersona";
    private static final String CONSULTA_INSERTAR = "INSERT INTO reclamo VALUES(null, ?, ?, ?, ?, ?, ?)";
    private static final String CONSULTA_DOMICILIO = "SELECT * FROM reclamo WHERE Domicilio=?";
    private static final String CONSULTA_ID = "SELECT * FROM reclamo WHERE idreclamo=?";
    private static final String CONSULTA_ACTUALIZAR = "UPDATE reclamo SET FechaCreacion=?, FechaResolucion=?, Domicilio=?, Persona_idpersona=?, Categoria_idcategoria=? Comentario=? WHERE idreclamo=?";
    private static final String CONSULTA_ELIMINAR = "DELETE FROM reclamo WHERE idreclamo=?";
    //Consulta ID Foranea
    private static final String CONSULTA_IDP = "SELECT idreclamo, FechaCreacion, FechaResolucion, Domicilio,categoria.*,Comentario, persona.* FROM (reclamo INNER JOIN categoria ON reclamo.Categoria_idcategoria=categoria.idcategoria ) INNER JOIN persona ON reclamo.Persona_idpersona=persona.idpersona WHERE Persona_idpersona=?" ;
    
    public Collection<Reclamo> listarReclamos()  {
         Collection<Reclamo> reclamos = new ArrayList<>();
         PreparedStatement ps;
         ResultSet rs;
         try{
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
             String instruccionSQL= CONSULTA_TODOS;
             ps=con.prepareStatement(instruccionSQL);
             rs=ps.executeQuery();
             
             while(rs.next()){
                 reclamos.add(new Reclamo(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getString(4),
                   new Categoria(rs.getInt(5),TipoCategoria.valueOf(rs.getString(6).toUpperCase())) ,rs.getString(7),
                  new Persona(rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getString(11), rs.getString(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getInt(16))));

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
     
     
   //método que lo utliza el adminsitrador, por ejemplo para actualizar fechas
     public int actualizarReclamo(Reclamo updatereclamo)  {
     int actualizado=0;
     PreparedStatement ps;
        try {
            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
            String instruccionSQL= CONSULTA_ACTUALIZAR;
            ps=con.prepareStatement(instruccionSQL);
            fillPreparedStatement(ps,updatereclamo);
            ps.setInt(7, updatereclamo.getIdreclamo());
            actualizado= ps.executeUpdate();
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al modificar Persona", ex);
        }
   
     return actualizado;
     }
     
     
     
     
      private void fillPreparedStatement(PreparedStatement ps, Reclamo rec) throws SQLException {
       ps.setDate(1, (Date) rec.getFechacreacion()); 
       // Categoria c=new Categoria();
       
// el casteo que sugirio la IDE de getFechacreacion() y getFecharesolucion()(setters) es porque
//retornan tipos Date de java.util.Date, y usar ps.setDate se usa el Date de java.sql.Date
//pero si fuese así no debería import java.util.Date; ?????
        ps.setDate(2, (Date) rec.getFecharesolucion());
        ps.setString(3, rec.getDomicilio());
        //ps.setInt(4, rec.getIdcategoria());
        ps.setInt(4, rec.getPersona().getIdpersona());
        ps.setInt(5, rec.getCategoria().getIdcategoria());
        ps.setString(6, rec.getComentario());
         
     }
      
      
       private Reclamo pullPreparedStatement(ResultSet rs) throws SQLException {
        Persona p=new Persona();
        Categoria c=new Categoria();
        int id= rs.getInt(1);
        Date fc= rs.getDate(2);
        Date fr= rs.getDate(3);
        String domicilio=rs.getString(4);
        c.setIdcategoria(rs.getInt(6));
        String comentario=rs.getString(7);
        p.setIdpersona(rs.getInt(5));
        
        Reclamo r=new Reclamo(id, fc, fr, domicilio, c, comentario ,p);
        return r;       
     }
       
      
     public Reclamo buscarCadena(String dato){  //sirve para buscar por Domicilio
     Reclamo reclamo=null;                    
     PreparedStatement ps;
     ResultSet rs;
         try {
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
             String instruccionSQL= CONSULTA_DOMICILIO;
             ps=con.prepareStatement(instruccionSQL);
             ps.setString(1, dato);
             rs=ps.executeQuery(); //se considera que el nombre de usuario no se repite, ver como validar eso
            // if(ps.executeQuery()!=null)  //el campo Admin puede ser null, cero(administrador) o uno(usuario)
            // while(rs.next()){
             if(rs.next()){ reclamo= pullPreparedStatement(rs);}
             
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar Reclamo", ex);
        }   
        return reclamo;
    }
       
     
     public Reclamo buscarKey(int dato){ 
     Reclamo reclamo=null;
     PreparedStatement ps;
     ResultSet rs;
         try {
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
             String instruccionSQL= CONSULTA_ID;
             ps=con.prepareStatement(instruccionSQL);
             ps.setInt(1, dato);  
             rs=ps.executeQuery(); 
             if(rs.next()){ reclamo= pullPreparedStatement(rs);}
             
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar Reclamo", ex);
        }   
        return reclamo;
    }
       
     
     public int eliminarReclamo(int id)  {
      int eliminado=0;
        PreparedStatement ps;
          try {
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
             String instruccionSQL= CONSULTA_ELIMINAR;
             ps=con.prepareStatement(instruccionSQL);
             ps.setInt(1, id);  
             eliminado=ps.executeUpdate();
 //executeUpdate() devuelve 0 si la instrucción no elimina nada, o devuelve la cantidad de registros eliminados.
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar Persona", ex);
        }   
      return eliminado;
     }
       
     
     public  Collection<Reclamo> buscarEntero(int dato)  {
        Collection<Reclamo> reclamos = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
             String instruccionSQL= CONSULTA_IDP;
             ps=con.prepareStatement(instruccionSQL);
             ps.setInt(1, dato);  
             rs=ps.executeQuery();
            while(rs.next()){
                 reclamos.add(new Reclamo(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getString(4),
                     new Categoria(rs.getInt(5),TipoCategoria.valueOf(rs.getString(6).toUpperCase())) ,rs.getString(7),
                     new Persona(rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getString(11), rs.getString(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getInt(16))));
             }
             
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar Reclamo", ex);
        }
        return reclamos;
      }
}