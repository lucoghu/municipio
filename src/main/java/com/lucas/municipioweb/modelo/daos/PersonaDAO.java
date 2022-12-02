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
    private static final String URL = "jdbc:mysql://localhost:3306/municipio?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "baseUTN2021";
    
    private static final String CONSULTA_TODOS = "SELECT * FROM persona";
  
    private static final String CONSULTA_INSERTAR = "INSERT INTO persona VALUES(null, ?, ?, ?, ?, ?,?,?,?)";
    private static final String CONSULTA_ACTUALIZAR = "UPDATE persona SET DNI=?, Nombre=?, Apellido=?, Mail=?, Telefono=? WHERE idpersona=?";
    //private static final String CONSULTA_USER = "SELECT Admin FROM persona WHERE User=? AND Clave=?";
    //private static final String CONSULTA_USER = "SELECT Admin FROM persona WHERE User=?";
    private static final String CONSULTA_USER = "SELECT * FROM persona WHERE User=?";
    private static final String CONSULTA_ID = "SELECT * FROM persona WHERE idpersona=?";
   
    
    public Collection<Persona> listarPersonas()  {
          
        Collection<Persona> personas = new ArrayList<>();
        
         PreparedStatement ps;
         ResultSet rs;
        try{
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS);
             String instruccionSQL= CONSULTA_TODOS;
             ps=con.prepareStatement(instruccionSQL);
             rs=ps.executeQuery();
            
             while(rs.next()){
                //personas.add(new Persona(rs.getInt("idpersona"),rs.getInt("DNI"),rs.getString("Nombre"),rs.getString("Apellido"),rs.getString("Mail"),rs.getInt("Telefono"),rs.getString("User"),rs.getString("Clave"),rs.getInt("Admin")));
               personas.add(new Persona(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getInt(9)));
               }
 
        }catch (SQLException ex) {
            throw new RuntimeException("No se pudo obtener el Listado de Personas", ex);
        }
        return personas;
    }

  
    public int crearPersona(Persona nuevapersona)  { //verificado, inserta correctamente hardcodeado desde DAO
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
            ps.setInt(6, updatepersona.getIdpersona());
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
        ps.setInt(8,per.getAdmin());
        
     }
     
     
        private Persona pullPreparedStatement(ResultSet rs) throws SQLException {
        int id= rs.getInt(1);
        int dni= rs.getInt(2);
        String nombre=rs.getString(3);
        String apellido=rs.getString(4);
        String mail=rs.getString(5);
        int telef= rs.getInt(6);
        String user=rs.getString(7);  // no haría falta setear(pedirlo a la BD este ya que llega por parámetro
        String clave=rs.getString(8); // en buscarCadena(String dato), pero como por parámetro puede llegar 
        int admin=rs.getInt(9); //cualquier String(user, clave, nombre) hay que pedir todo a la BD
        
        Persona p=new Persona(id, dni, nombre, apellido, mail, telef, user, clave, admin);
        return p;
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
     
    public Persona buscarCadena(String dato){ // en este caso buscar por user según la consulta SQL
     Persona persona=null;  //ver como generalizar la consulta para que busque por un String(user,nombre, clave, etc)
     PreparedStatement ps;
     ResultSet rs;
         try {
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
             String instruccionSQL= CONSULTA_USER;
             ps=con.prepareStatement(instruccionSQL);
             ps.setString(1, dato);
             rs=ps.executeQuery(); //se considera que el nombre de usuario no se repite, ver como validar eso
            // if(ps.executeQuery()!=null)  //el campo Admin puede ser null, true(administrador) o false
            // while(rs.next()){
             if(rs.next()){ persona= pullPreparedStatement(rs);}
             
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al validar Persona", ex);
        }   
        return persona;
    }
     
    
     public Persona buscarKey(int dato){ 
     Persona persona=null;
     PreparedStatement ps;
     ResultSet rs;
         try {
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
             String instruccionSQL= CONSULTA_ID;
             ps=con.prepareStatement(instruccionSQL);
             ps.setInt(1, dato);  
             rs=ps.executeQuery(); 
             if(rs.next()){ persona= pullPreparedStatement(rs);}
             
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar Persona", ex);
        }   
        return persona;
    }
             
     
    public Collection<Persona> buscarEntero(int dato){
          return null;
    } 
     
   /*  public int VerificarCadena(String dato){
     return 0;
     }*/
             
   // para verficar funcionamiento
 /*  public static void main(String[] args) {
        PersonaDAO per=new PersonaDAO();
         per.listarPersonas();
        //per.buscarCadena("User1");
        Persona nueva=new Persona(3022, "tercero", "contri3", "sin3@mail", 153, "USER3", "f3", 0);
        System.out.println("Peronas creadas"+per.crearPersona(nueva));
    }*/

}
