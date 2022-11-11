package com.lucas.municipioweb.modelo.daos;

import com.lucas.municipioweb.modelo.Conexion;
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
public class PersonaDAO {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/municipioweb?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "baseUTN2021";
    
    private static final String CONSULTA_TODOS = "SELECT * FROM persona";
    private static final String CONSULTA_INSERTAR = "INSERT INTO persona VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CONSULTA_ACTUALIZAR = "UPDATE persona SET DNI=?, Nombre=?, Apellido=?,Mail=?, Telefono=?, User=?, Clave=?, Admin=? WHERE idpersona=?";
    private static final String CONSULTA_USER = "SELECT Admin FROM persona WHERE User=? AND Clave=?";
    
    public Collection<Persona> listarPersonas()  {
        
        
        Collection<Persona> personas = new ArrayList<>();
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
             personas.add(new Persona(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getBoolean(9)));
                 }
                  for (Persona persona : personas) { //para probar
                      System.out.println(persona.getApellido());
                      System.out.println(persona.getIdpersona());
                      System.out.println(persona.getDni());
                     //System.out.println(personas.size());
                    }
        }catch (SQLException ex) {
            throw new RuntimeException("No se pudo obtener el Listado de Personas", ex);
        }
        return personas;
    }

    
    public int crearPersona(Persona nuevapersona)  {
    int insertado=0;
    PreparedStatement ps;
        try {
            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
            String instruccionSQL= CONSULTA_INSERTAR;
            ps=con.prepareStatement(instruccionSQL);
            fillPreparedStatement(ps,nuevapersona);
            insertado= ps.executeUpdate();
 //executeUpdate() devuelve 0 si la instrucción no modifica nada, o devuelve la cantidad de registros modificados(insertados en este caso)
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar Persona", ex);
        }
   
    return insertado;
    }
    
    
    
     public int actualizarPersona(Persona updatepersona)  {
    int actualizado=0;
    PreparedStatement ps;
        try {
            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
            String instruccionSQL= CONSULTA_ACTUALIZAR;
            ps=con.prepareStatement(instruccionSQL);
            fillPreparedStatement(ps,updatepersona);
            ps.setInt(9, updatepersona.getIdpersona());
            actualizado= ps.executeUpdate();
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al modificar Persona", ex);
        }
   
    return actualizado;
    }
    
    
     private void fillPreparedStatement(PreparedStatement ps, Persona per) throws SQLException {
        ps.setInt(1, per.getDni());
        ps.setString(2, per.getNombre());
        ps.setString(3, per.getApellido());
        ps.setString(4, per.getMail());
        ps.setInt(5, per.getTelef());
        ps.setString(6, per.getUser());
        ps.setString(7, per.getClave());
        ps.setBoolean(8,per.isAdmin());
     }
     
     
    /* 
     public Persona buscar(String u, String p){
         boolean valido=false;
         boolean esuser;
         PreparedStatement ps;
         try {
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
             String instruccionSQL= CONSULTA_USER;
             ps=con.prepareStatement(instruccionSQL);
             if(ps.executeQuery()!=null)  //el campo Admin puede ser null, true(administrador) o false
                 valido=ps.executeQuery();
              else
                 esuser=ps.execute();
             
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al validar Persona", ex);
        }
       return 0;
     
     }*/
             
             
   // para verficar funcionamiento
   public static void main(String[] args) {
        PersonaDAO per=new PersonaDAO();
        per.listarPersonas();
       // Persona nueva=new Persona(4243, "sexto", "contri6", "sin6@mail.com", 1566, "USER6", "f6", true);
       // System.out.println("Peronas creadas"+per.crearPersona(nueva));
    }
  
}
