package com.lucas.municipioweb.modelo.dtos;

/**
 *
 * @author Lucas Oliva
 */
public class Credencial {
    private int idcredencial;
    private String usuario;
    private String clave;
    private boolean admin; //por defecto todas las personas son contribuyentes, es decir no administradores
                           // false para contribuyente
    private Persona persona;

    public Credencial(int idcredencial, String usuario, String clave, boolean admin, Persona persona) {
        this.idcredencial = idcredencial;
        this.usuario = usuario;
        this.clave = clave;
        this.admin = admin;
        this.persona = persona;
    }

    
    public Credencial(String usuario, String clave, boolean admin, Persona persona) {
        this.usuario = usuario;
        this.clave = clave;
        this.admin = admin;
        this.persona = persona;
    }

    public int getIdcredencial() {
        return idcredencial;
    }

    public void setIdcredencial(int idcredencial) {
        this.idcredencial = idcredencial;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
  
     

}
