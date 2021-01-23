/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Producto;
import java.io.Serializable;


/**
 *
 * @author darkdestiny
 */

public class ProductoDTO implements Serializable{
    private Producto entidad;

    public ProductoDTO() {
        entidad = new Producto();
    }

    public ProductoDTO(Producto entidad) {
        this.entidad = entidad;
    }
    

    public Producto getEntidad() {
        return entidad;
    }

    public void setEntidad(Producto entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
 
        sb.append("idproducto").append(getEntidad().getIdProducto()).append("\n");
        sb.append("nombreproducto").append(getEntidad().getNombreProducto()).append("\n");
        sb.append("descripcionproducto").append(getEntidad().getDescripcionProducto()).append("\n");
        sb.append("precio").append(getEntidad().getPrecio()).append("\n");
        sb.append("existencia").append(getEntidad().getExistencia()).append("\n");
        sb.append("idcategoria").append(getEntidad().getIdCategoria().getIdCategoria()).append("\n");
                
        return sb.toString();
    }
    
    
    
}
