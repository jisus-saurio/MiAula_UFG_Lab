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
    public void guardar(Aula aula) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = sesion.beginTransaction();
            try {
                sesion.persist(aula);
                tx.commit();
            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw e;
            }
        }
    }
}
    
