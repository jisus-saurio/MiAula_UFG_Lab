/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stanly.miaula_ufg.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author stanl
 */
public class HibernateUtil {
    // La "fábrica" de conexiones. Se crea una sola vez y se reutiliza en todo el programa
    private static SessionFactory sessionFactory;

    // Devuelve la fábrica de conexiones (la crea si todavía no existe)
    // "synchronized" evita que dos partes del programa la creen al mismo tiempo
    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Lee hibernate.cfg.xml (datos de la BD y entidades) y crea la fábrica
                sessionFactory = new Configuration()
                        .configure()
                        .buildSessionFactory();
            } catch (Exception ex) {
                // Si falla (por ejemplo, MySQL apagado), muestra el error y avisa
                System.err.println("Error al crear la SessionFactory.");
                ex.printStackTrace();
                throw new IllegalStateException("No se pudo conectar con la base de datos", ex);
            }
        }
        return sessionFactory;
    }

    // Cierra la conexión con la base de datos (App.java lo llama al cerrar el programa)
    public static synchronized void cerrar() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }
}