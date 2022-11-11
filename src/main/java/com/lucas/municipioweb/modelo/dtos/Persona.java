package com.lucas.municipioweb.modelo.dtos;

/**
 *
 * @author Lucas Oliva
 */
public class Persona {

    private int idpersona;
    private int dni;
    private String nombre;
    private String apellido;
    private String mail;
    private int telef;
    private String user;
    private String clave;
    private boolean admin; //por defecto todas las personas son contribuyentes, es decir no administradores
                           // false para contribuyente

    public Persona(int idpersona, int dni, String nombre, String apellido, String mail, int telef,String user,String clave, boolean admin) {
        this.idpersona = idpersona;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telef = telef;
        this.admin = admin;
    }

    public Persona(int dni, String nombre, String apellido, String mail, int telef, String user, String clave, boolean admin) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telef = telef;
        this.user = user;
        this.clave = clave;
        this.admin = admin;
    }

    
    
    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelef() {
        return telef;
    }

    public void setTelef(int telef) {
        this.telef = telef;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }   
    
}
