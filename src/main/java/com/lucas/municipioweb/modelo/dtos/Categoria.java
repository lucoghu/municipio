package com.lucas.municipioweb.modelo.dtos;

/**
 *
 * @author Lucas Oliva
 */
public class Categoria {
    
    private int idcategoria;
    private TipoCategoria categoria; //uso enum para las categorias

    public Categoria() {
    }

    public Categoria(int idcategoria, TipoCategoria categoria) {
        this.idcategoria = idcategoria;
        this.categoria = categoria;
    }

    public Categoria(TipoCategoria categoria) {
        this.categoria = categoria;
    }

    
    
    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public TipoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoCategoria categoria) {
        this.categoria = categoria;
    }
    
    

}
