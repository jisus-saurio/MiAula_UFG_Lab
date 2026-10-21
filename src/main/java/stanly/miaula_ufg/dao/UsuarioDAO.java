package stanly.miaula_ufg.dao;

import stanly.miaula_ufg.entidades.Usuario;
import stanly.miaula_ufg.util.HibernateUtil;
import org.hibernate.Session;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class UsuarioDAO {    
    
    public Usuario autenticar(String correo, String clavePlana) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(clavePlana.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            String claveMd5 = sb.toString();

            String hql = "FROM Usuario u WHERE u.correo = :correo AND u.clave = :clave";

            try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
                return sesion.createQuery(hql, Usuario.class)
                        .setParameter("correo", correo)
                        .setParameter("clave", claveMd5)
                        .uniqueResultOptional()
                        .orElse(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}