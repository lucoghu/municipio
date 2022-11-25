package com.lucas.municipioweb.modelo;

import com.lucas.municipioweb.modelo.daos.PersonaDAO;
import com.lucas.municipioweb.modelo.dtos.Persona;
import java.util.Collection;

/**
 *
 * @author Lucas Oliva
 * 
 * 
 */
public class ModeloPersona implements Modelo<Persona> {
   // public class ModeloPersona<Persona> implements Modelo<Persona> {
     
     // Persona T;
    private PersonaDAO personaDAO;
   

    public ModeloPersona(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    @Override
    public Collection<Persona> listar() {
        
       //Collection<Persona> personas=personaDAO.listarPersonas();  //para prueba
        // System.out.println("com.lucas.municipioweb.modelo.ModeloPersona.listar()");//para prueba
    
        return personaDAO.listarPersonas();
 
    }

    
    @Override
    public int crear(Persona p) {
           return personaDAO.crearPersona(p);
     }
  /* 
      @Override
    public Persona buscar(Persona p) {
           return personaDAO.buscar(p);
    
 */  
    /* @Override
    public Reclamo buscar(<?> dato){
          return reclamoDAO.buscar(dato);
    }*/
    
    @Override
    public int actualizar(Persona p) {
           return personaDAO.actualizarPersona(p);    
    }
    
    @Override
    public Persona buscarCadena(String dato){
         return personaDAO.buscarCadena(dato);
    }
      
    @Override
    public Persona buscarEntero(int dato){
          return personaDAO.buscarEntero(dato);
    }
          
     @Override
    public int eliminar(int dato) {
           return personaDAO.eliminarPersona(dato);    
    }
    
     // para verficar funcionamiento
  /* public static void main(String[] args) {
        ModeloPersona per=new ModeloPersona(new PersonaDAO());
        per.listar();
    }*/
}
