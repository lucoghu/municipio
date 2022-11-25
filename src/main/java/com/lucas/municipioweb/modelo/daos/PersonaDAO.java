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
   // private static final String CONSULTA_TODOS = "SELECT idpersona,DNI,Apellido,Mail,Telefono,User,Clave,Admin FROM persona";
    private static final String CONSULTA_INSERTAR = "INSERT INTO persona VALUES(null, ?, ?, ?, ?, ?)";
    private static final String CONSULTA_ACTUALIZAR = "UPDATE persona SET DNI=?, Nombre=?, Apellido=?, Mail=?, Telefono=? WHERE idpersona=?";
    //private static final String CONSULTA_USER = "SELECT Admin FROM persona WHERE User=? AND Clave=?";
    private static final String CONSULTA_USER = "SELECT Admin FROM persona WHERE User=?";
    private static final String CONSULTA_ID = "SELECT * FROM persona WHERE idpersona=?";
    private static final String CONSULTA_ELIMINAR = "DELETE FROM persona WHERE idpersona=?";
    private static final String CONSULTA_ULTIMO_ID_CARGADO = "SELECT MAX(idpersona) FROM persona";
   
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
              // personas.add(new Persona(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getBoolean(9)));
                 personas.add(new Persona(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6))); 
               }
                  for (Persona persona : personas) { //para probar
                     System.out.println(persona.getIdpersona());
                     System.out.println(persona.getDni());
                     System.out.println(persona.getNombre());
                    System.out.println(persona.getApellido());
                    System.out.println(persona.getMail());
                     System.out.println(persona.getTelef());
                    // System.out.println(persona.getUser());
                    // System.out.println(persona.getClave());
                    // System.out.println(persona.getAdmin());   
                     //System.out.println(personas.size());
                    }
        }catch (SQLException ex) {
            throw new RuntimeException("No se pudo obtener el Listado de Personas", ex);
        }
        return personas;
    }

  
    public int crearPersona(Persona nuevapersona)  {
    int insertado=0;
    int idpersona=0;
    PreparedStatement ps;
    ResultSet rs;
        try {
            Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
            String instruccionSQL= CONSULTA_INSERTAR;
            ps=con.prepareStatement(instruccionSQL);
            fillPreparedStatement(ps,nuevapersona);
           // insertado= ps.executeUpdate();
 //executeUpdate() devuelve 0 si la instrucción no modifica nada, o devuelve la cantidad de registros modificados(insertados en este caso)
    
 
 //// esto lo hago para poder devolver el idpersona generado al crear persona, ya que tuve que dividir el registro en 2
           if(insertado!=0){
           ps.executeUpdate();
           ps=con.prepareStatement(CONSULTA_ULTIMO_ID_CARGADO);
           rs=ps.executeQuery();
           if(rs.next()){ idpersona= rs.getInt(1);}
           }
 /////////////////////      
        
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar Persona", ex);
        }
   
    //return insertado;
      return idpersona;
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
        //ps.setString(6, per.getUser());
       // ps.setString(7, per.getClave());
        //ps.setBoolean(8,per.getAdmin());
        
     }
     
     
        private Persona pullPreparedStatement(ResultSet rs) throws SQLException {
        int id= rs.getInt(1);
        int dni= rs.getInt(2);
        String nombre=rs.getString(3);
        String apellido=rs.getString(4);
        String mail=rs.getString(5);
        int telef= rs.getInt(6);
       // String user=rs.getString(7);  // no haría falta setear(pedirlo a la BD este ya que llega por parámetro
       // String clave=rs.getString(8); // en buscarCadena(String dato), pero como por parámetro puede llegar 
       // Boolean admin=rs.getBoolean(9); //cualquier String(user, clave, nombre) hay que pedir todo a la BD
        
        Persona p=new Persona(id, dni, nombre, apellido, mail, telef);
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
     
   // este método hay que modificarlo porque separa la tabla persona en 2, usuario y clave van en la tabla credencial
    public Persona buscarCadena(String dato){ // en este caso buscar por user según la consulta SQL
     Persona persona=null;  //ver como generalizar la consulta para que busque por un String(user,nombre, clave, etc)
     PreparedStatement ps;
     ResultSet rs;
         try {
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
             String instruccionSQL= CONSULTA_USER;
             ps=con.prepareStatement(instruccionSQL);
             ps.setString(1, dato);  // video 260 18 min
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
     
    
     public Persona buscarEntero(int dato){ 
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
      
     public int eliminarPersona(int id)  {
        int eliminada=0;
        PreparedStatement ps;
          try {
             Connection con = Conexion.getConexion(DRIVER, URL, USER, PASS); 
             String instruccionSQL= CONSULTA_ELIMINAR;
             ps=con.prepareStatement(instruccionSQL);
             ps.setInt(1, id);  
             eliminada=ps.executeUpdate();
 //executeUpdate() devuelve 0 si la instrucción no elimina nada, o devuelve la cantidad de registros eliminados.
        } catch (SQLException ex) {
                 throw new RuntimeException("Error de sintaxis SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar Persona", ex);
        }   
      return eliminada;
     }
          
     
   // para verficar funcionamiento
   public static void main(String[] args) {
        PersonaDAO per=new PersonaDAO();
         per.listarPersonas();
        //per.buscarCadena("User1");
       // Persona nueva=new Persona(4243, "sexto", "contri6", "sin6@mail.com", 1566, "USER6", "f6", true);
       // System.out.println("Peronas creadas"+per.crearPersona(nueva));
    }

}
