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
     
     // Persona T;
        private PersonaDAO personaDAO;
   

    public ModeloPersona(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    @Override
    public Collection<Persona> listar() {
        return personaDAO.listarPersonas();
 
    }

    
    @Override
    public int crear(Persona p) {
           return personaDAO.crearPersona(p);
     }
   
    
    @Override
    public int actualizar(Persona p) {
           return personaDAO.actualizarPersona(p);    
    }
    
    @Override
    public Persona buscarCadena(String dato){
         return personaDAO.buscarCadena(dato);
    }
     
    @Override
    public Persona buscarKey(int dato){
          return personaDAO.buscarKey(dato);
    }
       
      @Override
    public int eliminar(int dato) {
           return personaDAO.eliminarPersona(dato);    
    }
    
    
   @Override
    public Collection<Persona> buscarEntero(int dato){
          return personaDAO.buscarEntero(dato);
    }
          
     // para verficar funcionamiento
  /* public static void main(String[] args) {
        ModeloPersona per=new ModeloPersona(new PersonaDAO());
        per.listar();
    }*/
}
