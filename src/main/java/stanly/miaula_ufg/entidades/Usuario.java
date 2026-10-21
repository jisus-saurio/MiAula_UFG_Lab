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
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "clave", nullable = false, length = 32)
    private String clave; 

    //Constructor 
    public Usuario() {
    }

    //Constructor con parámetros
    public Usuario(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    // Setters
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