/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stanly.miaula_ufg.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import stanly.miaula_ufg.entidades.Aula;
import stanly.miaula_ufg.util.HibernateUtil;

/**
 *
 * @author F
 */
public class AulaDAO {

    // Revisa si ya existe un aula con ese nombre en ese edificio
    public boolean existe(String nombre, String edificio) {
        // Abre una conexión con la BD (se cierra sola al terminar el try)
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            // Cuenta cuántas aulas tienen ese nombre y ese edificio
            Long total = sesion.createQuery(
                    "SELECT COUNT(a) FROM Aula a WHERE a.nombre = :n AND a.edificio = :e", Long.class)
                    .setParameter("n", nombre)    // Reemplaza :n por el nombre
                    .setParameter("e", edificio)  // Reemplaza :e por el edificio
                    .getSingleResult();
            // Si hay más de 0, significa que ya existe
            return total > 0;
        }
    }

    // Guarda un aula nueva en la base de datos
    public void guardar(Aula aula) {
        // Abre una conexión con la BD (se cierra sola al terminar el try)
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            // Inicia una transacción: o se guarda todo bien, o no se guarda nada
            Transaction tx = sesion.beginTransaction();
            try {
                sesion.persist(aula); // Prepara el aula para guardarla
                tx.commit();          // Confirma y guarda los cambios en la BD
            } catch (RuntimeException e) {
                // Si algo falla, deshace los cambios para no dejar datos a medias
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw e; // Pasa el error al controlador para que muestre el mensaje
            }
        }
    }
}