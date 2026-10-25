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
@Table(name = "estados_aula")
public class EstadoAula {

    // Identificador único de cada estado (se genera solo: 1, 2, 3...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nombre del estado. Obligatorio, no se puede repetir, máximo 50 caracteres
    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    // Constructor vacío: Hibernate lo necesita para crear objetos
    public EstadoAula() { }

    // Getters y setters: sirven para leer (get) y cambiar (set) cada dato
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Define qué texto se ve en el ComboBox: el nombre del estado
    @Override
    public String toString() { return nombre; }
}