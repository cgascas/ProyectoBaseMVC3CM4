/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author darkdestiny
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuario", schema = "public")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    private String nombre;
    private String paterno;
    private String materno;
    private String email;
    private String nombreUsuario;
    private String claveUsuario;
    private String tipoUsuario;
//    @Lob
//    @Column(name = "photo", columnDefinition="BLOB")
//    private byte[] photo;
    private String path;

}
