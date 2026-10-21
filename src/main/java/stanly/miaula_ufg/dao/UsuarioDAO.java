package stanly.miaula_ufg.dao;

import stanly.miaula_ufg.entidades.Usuario;
import stanly.miaula_ufg.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author stanl
 */
public class UsuarioDAO {   
    
    public Usuario autenticar(String correo, String clave) {
        // HQL utiliza las propiedades de la entidad Java (en minúscula/camelCase)
        String hql = "FROM Usuario u "
                   + "WHERE u.correo = :correo AND u.clave = :clave AND u.estado = 'ACTIVO'";

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.createQuery(hql, Usuario.class)
                    .setParameter("correo", correo)
                    .setParameter("clave", clave)
                    .uniqueResultOptional()
                    .orElse(null);
        }
    }
}