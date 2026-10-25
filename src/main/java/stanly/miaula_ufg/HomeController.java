package stanly.miaula_ufg;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.exception.ConstraintViolationException;
import stanly.miaula_ufg.dao.AulaDAO;
import stanly.miaula_ufg.dao.CatalogoDAO;
import stanly.miaula_ufg.entidades.Aula;
import stanly.miaula_ufg.entidades.EstadoAula;
import stanly.miaula_ufg.entidades.Planta;
import stanly.miaula_ufg.entidades.TipoAula;
import stanly.miaula_ufg.entidades.Usuario;
import stanly.miaula_ufg.util.SesionUsuario;

/**
 * Controlador de la pantalla Home (formulario para registrar aulas).
 */
public class HomeController {

    // Elementos de la pantalla (conectados con home.fxml por su fx:id)
    @FXML private Label lblBienvenida;   // Saludo con el correo del usuario
    @FXML private Label lblMensaje;      // Muestra errores o mensajes de éxito

    @FXML private TextField txtNombre;
    @FXML private TextField txtEdificio;
    @FXML private ComboBox<Planta> cmbPlanta;
    @FXML private TextField txtCapacidad;
    @FXML private ComboBox<TipoAula> cmbTipoAula;
    @FXML private TextField txtResponsable;
    @FXML private ComboBox<EstadoAula> cmbEstado;

    // Clases que hablan con la base de datos
    private final AulaDAO aulaDAO = new AulaDAO();             // Guarda aulas
    private final CatalogoDAO catalogoDAO = new CatalogoDAO(); // Lee los datos de los combos

    // Se ejecuta solo al abrir la pantalla
    @FXML
    private void initialize() {
        // Muestra el correo del usuario que inició sesión
        Usuario usuario = SesionUsuario.getUsuarioActual();
        if (usuario != null) {
            lblBienvenida.setText("Bienvenido, " + usuario.getCorreo());
        }

        // Llena los 3 combos con datos de la base de datos
        try {
            cmbPlanta.setItems(FXCollections.observableArrayList(catalogoDAO.listarPlantas()));
            cmbTipoAula.setItems(FXCollections.observableArrayList(catalogoDAO.listarTiposAula()));
            cmbEstado.setItems(FXCollections.observableArrayList(catalogoDAO.listarEstados()));
        } catch (Exception e) {
            mostrarError("No se pudieron cargar los datos de los combos desde la base de datos.");
            e.printStackTrace();
        }
    }

    // Botón Guardar: valida los datos y guarda el aula
    @FXML
    private void guardar() {
        // 1. Lee lo que el usuario escribió o eligió (trim quita espacios sobrantes)
        String nombre = txtNombre.getText().trim();
        String edificio = txtEdificio.getText().trim();
        Planta planta = cmbPlanta.getValue();
        String capacidadTexto = txtCapacidad.getText().trim();
        TipoAula tipoAula = cmbTipoAula.getValue();
        String responsable = txtResponsable.getText().trim();
        EstadoAula estado = cmbEstado.getValue();

        // 2. Ningún campo puede estar vacío
        if (nombre.isEmpty() || edificio.isEmpty() || planta == null
                || capacidadTexto.isEmpty() || tipoAula == null
                || responsable.isEmpty() || estado == null) {
            mostrarError("Ningún campo puede estar vacío.");
            return;
        }

        // 3. Los textos no pueden ser más largos que lo que cabe en la base de datos
        if (nombre.length() > 50 || edificio.length() > 50) {
            mostrarError("El nombre y el edificio no pueden superar los 50 caracteres.");
            return;
        }
        if (responsable.length() > 100) {
            mostrarError("El responsable no puede superar los 100 caracteres.");
            return;
        }

        // 4. La planta debe ser mayor que 0
        if (planta.getNumero() <= 0) {
            mostrarError("La planta debe ser un entero mayor que 0.");
            return;
        }

        // 5. La capacidad debe ser un número entero mayor que 0
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

        try {
            // 6. No se permiten dos aulas con el mismo nombre en el mismo edificio
            if (aulaDAO.existe(nombre, edificio)) {
                mostrarError("Ya existe un aula con ese nombre en ese edificio.");
                return;
            }

            // 7. Crea el aula con los datos del formulario
            Aula aula = new Aula();
            aula.setNombre(nombre);
            aula.setEdificio(edificio);
            aula.setPlanta(planta);
            aula.setCapacidad(capacidad);
            aula.setTipoAula(tipoAula);
            aula.setResponsable(responsable);
            aula.setEstado(estado);

            // 8. La guarda en la base de datos, limpia el formulario y avisa
            aulaDAO.guardar(aula);
            limpiar();
            mostrarExito("Aula guardada correctamente.");
        } catch (Exception e) {
            // Si algo falla, muestra un mensaje según el tipo de error
            if (esAulaDuplicada(e)) {
                mostrarError("Ya existe un aula con ese nombre en ese edificio.");
            } else {
                mostrarError("No se pudo guardar el aula en la base de datos.");
                e.printStackTrace();
            }
        }
    }

    // Botón Limpiar: deja el formulario vacío
    @FXML
    private void limpiar() {
        txtNombre.clear();
        txtEdificio.clear();
        txtCapacidad.clear();
        txtResponsable.clear();
        cmbPlanta.getSelectionModel().clearSelection();
        cmbTipoAula.getSelectionModel().clearSelection();
        cmbEstado.getSelectionModel().clearSelection();
        lblMensaje.setText("");
    }

    // Cerrar sesión: borra al usuario actual y vuelve al Login
    @FXML
    private void cerrarSesion() throws IOException {
        SesionUsuario.cerrar();
        App.setRoot("login");
    }

    // Revisa si el error fue por un aula repetida (nombre + edificio)
    private boolean esAulaDuplicada(Throwable error) {
        while (error != null) {
            if (error instanceof ConstraintViolationException) {
                return true;
            }
            error = error.getCause(); // Busca en la causa del error
        }
        return false;
    }

    // Muestra un mensaje en rojo (error)
    private void mostrarError(String mensaje) {
        lblMensaje.setStyle("-fx-text-fill: red;");
        lblMensaje.setText(mensaje);
    }

    // Muestra un mensaje en verde (éxito)
    private void mostrarExito(String mensaje) {
        lblMensaje.setStyle("-fx-text-fill: green;");
        lblMensaje.setText(mensaje);
    }
}