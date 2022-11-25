package com.lucas.municipioweb.modelo.daos;

import com.lucas.municipioweb.modelo.Conexion;
import com.lucas.municipioweb.modelo.dtos.Credencial;
import com.lucas.municipioweb.modelo.dtos.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Lucas Oliva
 */
public class CredencialDAO {
    
     private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/municipioweb?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "root";
    
    //private static final String CONSULTA_TODOS = "SELECT * FROM credencial";
    private static final String CONSULTA_TODOS ="SELECT  idcredencial,user, clave, admin, persona.* FROM credencial INNER join persona ON credencial.idpersona=persona.idpersona";
    //aca ver como se le pase el ipersona, ya que no le debe insertar porque es vinculo con personas
    private static final String CONSULTA_INSERTAR = "INSERT INTO credencial VALUES(null, ?, ?, ?, ?)";
    private static final String CONSULTA_USER = "SELECT Admin FROM credencial WHERE User=?";
    
     public Collection<Credencial> listarCredenciales()  {
        
       
        Collection<Credencial> credenciales = new ArrayList<>();
         //Connection con=null;
         //PreparedStatement ps=null;
         //ResultSet rs=null;
         PreparedStatement ps;
         ResultSet rs;
        try{
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
             //String instruccionSQL= "SELECT * FROM persona";
             String instruccionSQL= CONSULTA_TODOS;
             ps=con.prepareStatement(instruccionSQL);
             rs=ps.executeQuery();
            
             while(rs.next()){
               // personas.add(new Persona(rs.getInt("idpersona"),rs.getInt("DNI"),rs.getString("Nombre"),rs.getString("Apellido"),rs.getString("Mail"),rs.getInt("Telefono"),rs.getString("User"),rs.getString("Clave"),rs.getBoolean("Admin")));
              // personas.add(new Persona(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getBoolean(9)));
                 credenciales.add(new Credencial(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getBoolean(4),new Persona(rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10)))); 
               }
        }catch (SQLException ex) {
            throw new RuntimeException("No se pudo obtener el Listado de Personas", ex);
        }
        return credenciales;
       
    }
    
    
    
    public int crearCredencial(Credencial nuevacredencial)  {
    int insertada=0;
    PreparedStatement ps;
        try {
            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
            
            //aca ver como se le pase el ipersona, ya que no le debe insertar porque es vinculo con personas
            String instruccionSQL= CONSULTA_INSERTAR;
            ps=con.prepareStatement(instruccionSQL);
            fillPreparedStatement(ps,nuevacredencial);
            insertada= ps.executeUpdate();
 //executeUpdate() devuelve 0 si la instrucción no modifica nada, o devuelve la cantidad de registros modificados(insertados en este caso)
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar Persona", ex);
        }
    
    return insertada;
    }
    
    
    public int actualizarCredencial(Credencial c){
        return 0;
    }
    
     private void fillPreparedStatement(PreparedStatement ps, Credencial cre) throws SQLException {
        ps.setString(1,cre.getUsuario()); 
        ps.setString(2,cre.getClave());
        ps.setBoolean(3,cre.getAdmin());
        ps.setInt(4, cre.getPersona().getIdpersona());
    }
    
     
     
      public Credencial buscarCadena(String dato){
          return null;
      }
    
     public Credencial buscarEntero(int dato){
          return null;
    }
     
     
      public int eliminarCredencial(int dato) {
           return 0;    
    }
    
    /* Boolean buscarCadena(String dato): NO VA A FUNCIONAR, pero si 
      Mejor desde el Servlet pido a un objeti Credencial, y comparo en el mismo Servlet si coinciden usuario y clave
        y si coinciden tomo el atributo admin
    */
    /*
     public Boolean buscarCadena(String dato){ //devuelve admin ya sea para usuario o clave
     Boolean admin=false;     //La idea era buscar por usuario, y después por clave, y ver en el Servlet si admin
     PreparedStatement ps;  //coinciden, pero no va a funcionar, porque en el caso de una admin=false(BD)
     ResultSet rs;        //no se sabe si encontro al usuari o la clave( admin aca se inicializa en false)
         try {
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
             String instruccionSQL= CONSULTA_USER;
             ps=con.prepareStatement(instruccionSQL);
             ps.setString(1, dato); 
             rs=ps.executeQuery(); 
             if(rs.next()){ 
                 admin=rs.getBoolean(4);
             }
             
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al validar Persona", ex);
        }   
        return admin;
    } */

}
