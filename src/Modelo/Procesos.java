/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


public class Procesos {
private int id; 
private String nombre; 
private String estado; 
private int tamano; 
private int numero_hilos; 
private String recursos; 

 

    public Procesos(int id, String nombre, String estado, int tamano, int numero_hilos, String recursos) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.tamano = tamano;
        this.numero_hilos = numero_hilos;
        this.recursos = recursos;
    }
  
    public Procesos(String nombre, String estado, int tamano, String recursos) {
        this.nombre = nombre;
        this.estado = estado;
        this.tamano = tamano;
        this.recursos = recursos;
    }

    public Procesos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getNumero_hilos() {
        return numero_hilos;
    }

    public void setNumero_hilos(int numero_hilos) {
        this.numero_hilos = numero_hilos;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }
       @Override
    public String toString() {
        return "Procesos{" + "id=" + id + ", nombre=" + nombre + ", estado=" + estado + ", tamano=" + tamano + ", numero_hilos=" + numero_hilos + ", recursos=" + recursos + '}';
    }
}
