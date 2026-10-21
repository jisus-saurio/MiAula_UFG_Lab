package stanly.miaula_ufg;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import stanly.miaula_ufg.entidades.Usuario;
import stanly.miaula_ufg.util.SesionUsuario;

/**
 * @author stanl
 */
public class HomeController {

    @FXML private Label lblBienvenida;

    @FXML
    private void initialize() {
        Usuario usuario = SesionUsuario.getUsuarioActual();
        if (usuario != null) {
            lblBienvenida.setText("Bienvenido, " + usuario.getCorreo());
        }
    }

    @FXML
    private void cerrarSesion() throws IOException {
        SesionUsuario.cerrar();    
        App.setRoot("login");        
    }
}