/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author werog
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaCountProductos {
    public String nombreCategoria;
    public int count;
    
}
