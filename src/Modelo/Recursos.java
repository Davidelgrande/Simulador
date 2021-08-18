/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author user
 */
public class Recursos {
   private String nombre; 
   private Procesos proceso; 
   private boolean estado; 
   
   public Recursos(String nombre, Procesos proceso, boolean estado) {
        this.nombre = nombre;
        this.proceso = proceso;
        this.estado = estado;
    }

    public Recursos() {
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Procesos getProceso() {
        return proceso;
    }

    public void setProceso(Procesos proceso) {
        this.proceso = proceso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

 
   
}
