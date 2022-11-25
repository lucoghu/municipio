package com.lucas.municipioweb.modelo;

import java.util.Collection;

/**
 *
 * @author Lucas Oliva
 * @param <T>

 * Para todas la Clases(Tablas) deben implementar los mismos métodos(buscar, leer, listar, etc)
 * pero con tipos de datos distintos, esto se hace con Clase genérica (T)
 */
public interface Modelo<T> {
    
  /*  public Collection<?> listar();
    public Collection<?> crear();
    public Collection<?> actualizar();
    public Collection<?> eliminar();
    public Collection<?> buscar(int id);*/
    
    public Collection<T> listar(); //lista los elementos de una tabla "T"
    public int actualizar(T registro);
    public int crear(T registro);   //inserta elemento en una tabla, o crea un registro nuevo
  //  public void actualizarar(T registro); // actualiza un registro de una tabla "T"
  //  public void eliminar(int id); //elimina un registro con un id determinado
  //  public T buscar(int id); //busca un registro con un id determinado y devuelve un Objeto de la clase T ("tabla")
      //public T buscar(int id);
//    public T buscar(T dato); // si quiero buscar por el nombre de usuario y ver si esta en BD debería
    //implementarlo solamente en la Clase Persona y retornaría un boolean,pero ambas Clases deberan implementar este
   //método, entonces que el retorno de este método sea genérico puedo usarlo en la clase Reclamos para
   //buscar reclamos por Domicilio con retorno de idReclamo
       
 //  public T buscar(<?> dato); // no funciona comodin <?> en lugar de String, <?> podría ser int o String
   public T buscarCadena(String dato); 
   
   public T buscarEntero(int dato); 
   
   public int eliminar(int dato);
 
   
}