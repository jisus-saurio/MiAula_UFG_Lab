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

@Entity // Le dice a Hibernate que esta clase es una tabla
@Table(name = "aulas", uniqueConstraints = {
    // Regla: no pueden repetirse la misma combinación de nombre + edificio
    @UniqueConstraint(name = "uk_aula_nombre_edificio", columnNames = {"nombre", "edificio"})
})
public class Aula {

    // Identificador único de cada aula (se genera solo: 1, 2, 3...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre o código del aula (ej. "B-204"). Obligatorio, máximo 50 caracteres
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    // Edificio donde está el aula. Obligatorio, máximo 50 caracteres
    @Column(name = "edificio", nullable = false, length = 50)
    private String edificio;

    // Cuántas personas caben en el aula. Obligatorio
    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    // Persona a cargo del aula. Obligatorio, máximo 100 caracteres
    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    // Relación: muchas aulas pueden estar en una misma planta.
    // En la tabla se guarda solo el id de la planta (columna planta_id)
    @ManyToOne(optional = false)
    @JoinColumn(name = "planta_id", nullable = false)
    private Planta planta;

    // Relación: muchas aulas pueden ser del mismo tipo (columna tipo_aula_id)
    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_aula_id", nullable = false)
    private TipoAula tipoAula;

    // Relación: muchas aulas pueden tener el mismo estado (columna estado_id)
    @ManyToOne(optional = false)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoAula estado;

    // Constructor vacío: Hibernate lo necesita para crear objetos
    public Aula() { }

    // Getters y setters: sirven para leer (get) y cambiar (set) cada dato
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEdificio() { return edificio; }
    public void setEdificio(String edificio) { this.edificio = edificio; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public Planta getPlanta() { return planta; }
    public void setPlanta(Planta planta) { this.planta = planta; }

    public TipoAula getTipoAula() { return tipoAula; }
    public void setTipoAula(TipoAula tipoAula) { this.tipoAula = tipoAula; }

    public EstadoAula getEstado() { return estado; }
    public void setEstado(EstadoAula estado) { this.estado = estado; }
}