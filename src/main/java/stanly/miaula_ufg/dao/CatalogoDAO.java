/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stanly.miaula_ufg.dao;
import java.util.List;
import org.hibernate.Session;
import stanly.miaula_ufg.entidades.EstadoAula;
import stanly.miaula_ufg.entidades.Planta;
import stanly.miaula_ufg.entidades.TipoAula;
import stanly.miaula_ufg.util.HibernateUtil;
/**
 *
 * @author stanl
 */
public class CatalogoDAO {

    // Devuelve todas las plantas, ordenadas de menor a mayor número
    public List<Planta> listarPlantas() {
        // Abre una conexión con la BD (se cierra sola al terminar el try)
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("FROM Planta p ORDER BY p.numero", Planta.class).list();
        }
    }

    // Devuelve todos los tipos de aula (Normal, Laboratorio, etc.)
    public List<TipoAula> listarTiposAula() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("FROM TipoAula t ORDER BY t.id", TipoAula.class).list();
        }
    }

    // Devuelve todos los estados del aula (Asignada, En mantenimiento, etc.)
    public List<EstadoAula> listarEstados() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("FROM EstadoAula e ORDER BY e.id", EstadoAula.class).list();
        }
    }
}