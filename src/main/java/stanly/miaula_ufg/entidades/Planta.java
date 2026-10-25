/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stanly.miaula_ufg.entidades;
import jakarta.persistence.*;
/**
 *
 * @author stanl
 */
@Entity // Le dice a Hibernate que esta clase es una tabla
@Table(name = "plantas")
public class Planta {

    // Identificador único de cada planta (se genera solo: 1, 2, 3...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Número de la planta. Obligatorio y no se puede repetir
    @Column(name = "numero", nullable = false, unique = true)
    private int numero;

    // Constructor vacío: Hibernate lo necesita para crear objetos
    public Planta() { }

    // Getters y setters: sirven para leer (get) y cambiar (set) cada dato
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    // Define qué texto se ve en el ComboBox: el número de la planta
    @Override
    public String toString() { return String.valueOf(numero); }
}