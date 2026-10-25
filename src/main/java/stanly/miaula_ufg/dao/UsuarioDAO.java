package stanly.miaula_ufg.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;
import stanly.miaula_ufg.entidades.Usuario;
import stanly.miaula_ufg.util.HibernateUtil;
/**
 *
 * @author stanl
 */
public class UsuarioDAO {

    /**
     * Devuelve el usuario si correo y clave coinciden, o null si no.
     * Si la BD falla lanza excepción (el LoginController muestra el error de conexión).
     */
    public Usuario autenticar(String correo, String clavePlana) {
        // La clave no se guarda en texto plano: se convierte a hash MD5 antes de comparar
        String claveMd5 = md5(clavePlana);
        // Consulta: busca un usuario con ese correo y esa clave (:correo y :clave se reemplazan abajo)
        String hql = "FROM Usuario u WHERE u.correo = :correo AND u.clave = :clave";

        // Abre una conexión con la BD (se cierra sola al terminar el try)
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.createQuery(hql, Usuario.class)
                    .setParameter("correo", correo)   // Reemplaza :correo
                    .setParameter("clave", claveMd5)  // Reemplaza :clave por el hash
                    .uniqueResultOptional()           // Espera como máximo un resultado
                    .orElse(null);                    // Si no hay usuario, devuelve null
        }
    }

    // Convierte un texto en su hash MD5 (ejemplo: "admin123" -> "0192023a7bbd7325...")
    private String md5(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Calcula el hash del texto, en forma de bytes
            byte[] hash = md.digest(texto.getBytes(StandardCharsets.UTF_8));
            // Convierte cada byte a dos letras/números hexadecimales y los une
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Solo ocurre si Java no tiene el algoritmo MD5 (muy raro)
            throw new IllegalStateException("MD5 no disponible", e);
        }
    }
}