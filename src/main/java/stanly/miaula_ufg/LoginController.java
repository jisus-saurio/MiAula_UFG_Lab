package stanly.miaula_ufg;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import stanly.miaula_ufg.dao.UsuarioDAO;
import stanly.miaula_ufg.entidades.Usuario;
import stanly.miaula_ufg.util.SesionUsuario;

/**
 * @author stanl
 */
public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtClave;
    @FXML private Label lblMensaje;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void ingresar() {
        String correo = txtCorreo.getText().trim();
        String clave = txtClave.getText();

        if (correo.isEmpty() || clave.isEmpty()) {
            lblMensaje.setText("Ingrese correo y contraseña.");
            return;
        }

        try {
            Usuario usuario = usuarioDAO.autenticar(correo, clave);

            if (usuario == null) {
                lblMensaje.setText("Credenciales incorrectas.");
                txtClave.clear();
                return;
            }

            SesionUsuario.iniciar(usuario);  
            App.setRoot("home");              

        } catch (Exception e) {
            lblMensaje.setText("No se pudo conectar con la base de datos.");
            e.printStackTrace();
        }
    }
}