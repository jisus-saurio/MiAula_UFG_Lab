# MiAula UFG Lab

Sistema de gestión de aulas.
---

## Integrantes

- **Fabiola Alexandra Deleón Martínez** - `DM100925`
- **Jesús Stanley Arce Dueñas** - `AD101225`
- **Brandon Steven García Rodríguez** - `GR100726`
- **Daniel Enrique Galdámez Colocho** - `GC100726`
- **Ana Beatriz Chávez Vásquez** - `CV100726`

---

## Descripción

Este proyecto tiene como objetivo administrar y controlar la información relacionada con aulas, plantas, tipos de aula, estados y usuario dentro del sistema de gestión de aulas de la UFG.

---

## Base de datos

A continuación se incluye el script SQL de creación de la base de datos:

```sql
CREATE DATABASE IF NOT EXISTS ufg_gestion_aulas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ufg_gestion_aulas;

DROP TABLE IF EXISTS aulas;

CREATE TABLE IF NOT EXISTS usuarios (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) NOT NULL UNIQUE,
    clave  VARCHAR(32)  NOT NULL
);

CREATE TABLE IF NOT EXISTS plantas (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tipos_aula (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS estados_aula (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE aulas (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(50)  NOT NULL,
    edificio     VARCHAR(50)  NOT NULL,
    capacidad    INT          NOT NULL,
    responsable  VARCHAR(100) NOT NULL,
    planta_id    INT NOT NULL,
    tipo_aula_id INT NOT NULL,
    estado_id    INT NOT NULL,
    CONSTRAINT uk_aula_nombre_edificio UNIQUE (nombre, edificio),
    CONSTRAINT fk_aula_planta FOREIGN KEY (planta_id)    REFERENCES plantas(id),
    CONSTRAINT fk_aula_tipo   FOREIGN KEY (tipo_aula_id) REFERENCES tipos_aula(id),
    CONSTRAINT fk_aula_estado FOREIGN KEY (estado_id)    REFERENCES estados_aula(id)
);

INSERT IGNORE INTO plantas (numero) VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10);
INSERT IGNORE INTO tipos_aula (nombre) VALUES ('Normal'),('Centro de cómputo'),('Laboratorio'),('Especial');
INSERT IGNORE INTO estados_aula (nombre) VALUES ('Asignada'),('Asignada temporalmente'),('En mantenimiento'),('Disponible');

-- Usuario de prueba: admin@ufg.edu.sv / admin123 (la clave se guarda con hash MD5)
INSERT IGNORE INTO usuarios (correo, clave) VALUES ('admin@ufg.edu.sv', MD5('admin123'));
