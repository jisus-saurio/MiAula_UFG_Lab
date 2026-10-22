package stanly.miaula_ufg;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import stanly.miaula_ufg.entidades.Usuario;
import stanly.miaula_ufg.util.SesionUsuario;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.hibernate.exception.ConstraintViolationException;
import stanly.miaula_ufg.dao.AulaDAO;
import stanly.miaula_ufg.entidades.Aula;

/**
 * @author stanl
 */
public class HomeController {

    @FXML private Label lblBienvenida;
    @FXML private Label lblMensaje;
    
    @FXML private TextField txtCapacidad;
    @FXML private ComboBox<String> cmbTipoAula;
    @FXML private TextField txtResponsable;
    @FXML private ComboBox<String> cmbEstado;
    
    private final AulaDAO aulaDAO = new AulaDAO();

    @FXML
    private void initialize() {
        Usuario usuario = SesionUsuario.getUsuarioActual();
        if (usuario != null) {
            lblBienvenida.setText("Bienvenido, " + usuario.getCorreo());
        }
        cmbTipoAula.setItems(FXCollections.observableArrayList(
                "Normal", "Centro de cómputo", "Laboratorio", "Especial"));
        cmbEstado.setItems(FXCollections.observableArrayList(
                "Asignada", "Asignada temporalmente", "En mantenimiento", "Disponible"));
    }
    
    @FXML
    private void guardar() {
        String capacidadTexto = txtCapacidad.getText().trim();
        String tipoAula = cmbTipoAula.getValue();
        String responsable = txtResponsable.getText().trim();
        String estado = cmbEstado.getValue();

        
        if (capacidadTexto.isEmpty() || responsable.isEmpty() || tipoAula == null || estado == null) {
            mostrarError("Ningún campo puede estar vacío.");
            return;
        }

        int capacidad;
        try {
            capacidad = Integer.parseInt(capacidadTexto);
        } catch (NumberFormatException e) {
            mostrarError("La capacidad debe ser un número entero.");
            return;
        }
        if (capacidad <= 0) {
            mostrarError("La capacidad debe ser mayor que 0.");
            return;
        }
        if (responsable.length() > 100) {
            mostrarError("El responsable no puede superar los 100 caracteres.");
            return;
        }
        

        Aula aula = new Aula();
        aula.setCapacidad(capacidad);
        aula.setTipoAula(tipoAula);
        aula.setResponsable(responsable);
        aula.setEstado(estado);
     

        try {
            aulaDAO.guardar(aula);
            limpiar();
            mostrarExito("Aula guardada correctamente.");
        } catch (Exception e) {
            if (esAulaDuplicada(e)) {
                mostrarError("Ya existe un aula con ese nombre en ese edificio.");
            } else {
                mostrarError("No se pudo guardar el aula en la base de datos.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void limpiar() {
        txtCapacidad.clear();
        cmbTipoAula.getSelectionModel().clearSelection();
        cmbTipoAula.setValue(null);
        txtResponsable.clear();
        cmbEstado.getSelectionModel().clearSelection();
        cmbEstado.setValue(null);
        // TODO (compañera): limpiar también txtNombre, txtEdificio y cmbPlanta
        lblMensaje.setText("");
    }
    

    @FXML
    private void cerrarSesion() throws IOException {
        SesionUsuario.cerrar();    
        App.setRoot("login");        
    }

    private boolean esAulaDuplicada(Throwable error) {
        while (error != null) {
            if (error instanceof ConstraintViolationException) {
                return true;
            }
            error = error.getCause();
        }
        return false;
    }

    private void mostrarError(String mensaje) {
        lblMensaje.setStyle("-fx-text-fill: red;");
        lblMensaje.setText(mensaje);
    }

    private void mostrarExito(String mensaje) {
        lblMensaje.setStyle("-fx-text-fill: green;");
        lblMensaje.setText(mensaje);
    }
}