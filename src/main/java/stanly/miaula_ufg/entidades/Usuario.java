package stanly.miaula_ufg.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author stanl
 */
@Entity // Le dice a Hibernate que esta clase es una tabla
@Table(name = "usuarios")
public class Usuario {

    // Identificador único de cada usuario (se genera solo: 1, 2, 3...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Correo del usuario. Obligatorio, no se puede repetir, máximo 100 caracteres
    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    // Clave del usuario guardada como hash MD5 (nunca en texto plano).
    // Mide 32 caracteres porque ese es el largo de un hash MD5
    @Column(name = "clave", nullable = false, length = 32)
    private String clave; 

    // Constructor vacío: Hibernate lo necesita para crear objetos
    public Usuario() {
    }

    // Constructor con parámetros: crea un usuario con correo y clave de una vez
    public Usuario(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    // Getters: sirven para leer cada dato
    public Integer getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    // Setters: sirven para cambiar cada dato
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}