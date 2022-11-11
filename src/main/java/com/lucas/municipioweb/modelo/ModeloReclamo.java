package com.lucas.municipioweb.modelo;

import com.lucas.municipioweb.modelo.daos.ReclamoDAO;
import com.lucas.municipioweb.modelo.dtos.Reclamo;
import java.util.Collection;

/**
 *
 * @author Lucas Oliva
 */
public class ModeloReclamo implements Modelo<Reclamo> {
     private ReclamoDAO reclamoDAO;
   

    public ModeloReclamo(ReclamoDAO reclamoDAO) {
        this.reclamoDAO = reclamoDAO;
    }

     @Override
    public Collection<Reclamo> listar() {
        return reclamoDAO.listarReclamos();
    }

    
    @Override
    public int crear(Reclamo r) {
           return reclamoDAO.crearReclamo(r);
     }
    
    
}
