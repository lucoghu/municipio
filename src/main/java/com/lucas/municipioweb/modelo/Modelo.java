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
    public int crear(T registro);   //inserta elemento en una tabla, o crea un registro nuevo
  //  public void actualizarar(T registro); // actualiza un registro de una tabla "T"
        }