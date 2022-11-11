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
    
 /*   
  // para verficar funcionamiento
   public static void main(String[] args) {
        ModeloPersona per=new ModeloPersona(new PersonaDAO());
        per.listar();
    }*/
}
