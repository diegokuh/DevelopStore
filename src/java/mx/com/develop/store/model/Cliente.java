/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.develop.store.model;

/**
 *
 * @author Curso
 */
public class Cliente {
    private String nombre;
    private int edad;
    private String direccion;
    private String telefono;
    private String usuario;
    private String contrasenia;

    public Cliente() {
    }

    public Cliente(String nombre, int edad, String direccion, String telefono, String usuario, String contrasenia) {
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", edad=" + edad + ", direccion=" + direccion + ", telefono=" + telefono + ", usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }
    
    
}
