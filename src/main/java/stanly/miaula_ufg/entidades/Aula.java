package stanly.miaula_ufg.entidades;
import jakarta.persistence.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author stanl
 */

@Entity
@Table(name = "aulas", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre", "edificio"})
})
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre; 

    @Column(nullable = false, length = 50)
    private String edificio; 

    @Column(nullable = false)
    private int planta; 

    @Column(nullable = false)
    private int capacidad; 

    @Column(nullable = false, length = 50)
    private String tipoAula; 

    @Column(nullable = false, length = 100)
    private String responsable; 

    @Column(nullable = false, length = 50)
    private String estado; 

    public Aula() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEdificio() { return edificio; }
    public void setEdificio(String edificio) { this.edificio = edificio; }

    public int getPlanta() { return planta; }
    public void setPlanta(int planta) { this.planta = planta; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getTipoAula() { return tipoAula; }
    public void setTipoAula(String tipoAula) { this.tipoAula = tipoAula; }

    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
