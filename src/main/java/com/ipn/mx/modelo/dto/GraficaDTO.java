/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import java.io.Serializable;

/**
 *
 * @author darkdestiny
 */
public class GraficaDTO implements Serializable {

    private int cantidad;
    private String nombre;

    public GraficaDTO() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre").append(getNombre()).append("\n");
        sb.append("Cantidad").append(getCantidad()).append("\n");
        return sb.toString();
    }
}
