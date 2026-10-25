package stanly.miaula_ufg;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import stanly.miaula_ufg.dao.UsuarioDAO;
import stanly.miaula_ufg.entidades.Usuario;
import stanly.miaula_ufg.util.SesionUsuario;

/**
 * Controlador de la pantalla de Login.
 */
public class LoginController {

    // Elementos de la pantalla (conectados con login.fxml por su fx:id)
    @FXML private TextField txtCorreo;      // Campo donde se escribe el correo
    @FXML private PasswordField txtClave;   // Campo de la clave (oculta lo que se escribe)
    @FXML private Label lblMensaje;         // Muestra los mensajes de error

    // Clase que consulta los usuarios en la base de datos
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Botón Ingresar
    @FXML
    private void ingresar() {
        // 1. Lee lo que escribió el usuario (trim quita espacios sobrantes del correo)
        String correo = txtCorreo.getText().trim();
        String clave = txtClave.getText();

        // 2. Si falta el correo o la clave, avisa y no sigue
        if (correo.isEmpty() || clave.isEmpty()) {
            lblMensaje.setText("Ingrese correo y contraseña.");
            return;
        }

        try {
            // 3. Busca en la base de datos un usuario con ese correo y clave
            Usuario usuario = usuarioDAO.autenticar(correo, clave);

            // 4. Si no lo encuentra, muestra error y borra la clave escrita
            if (usuario == null) {
                lblMensaje.setText("Credenciales incorrectas.");
                txtClave.clear();
                return;
            }

            // 5. Si lo encuentra, guarda al usuario como "sesión activa" y abre el Home
            SesionUsuario.iniciar(usuario);
            App.setRoot("home");

        } catch (Exception e) {
            // Si algo falla (por ejemplo, MySQL apagado), avisa que no hay conexión
            lblMensaje.setText("No se pudo conectar con la base de datos.");
            e.printStackTrace();
        }
    }
}