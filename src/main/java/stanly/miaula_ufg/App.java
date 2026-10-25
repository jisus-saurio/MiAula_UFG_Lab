package stanly.miaula_ufg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import stanly.miaula_ufg.util.HibernateUtil;

/**
 * Clase principal de la aplicación.
 * Aquí empieza el programa y se controla qué pantalla se muestra (Login o Home).
 */
public class App extends Application {

    // La escena es el "contenido" de la ventana; se guarda aquí para poder cambiar de pantalla
    private static Scene scene;

    // Se ejecuta al abrir la aplicación: crea la ventana y muestra el Login primero
    @Override
    public void start(Stage stage) throws IOException {
        // Carga login.fxml en una ventana de 400 x 350
        scene = new Scene(cargar("login"), 400, 350);

        stage.setTitle("Gestión de Aulas UFG"); // Título de la ventana
        stage.setScene(scene);                  // Le pone el contenido a la ventana
        stage.setResizable(false);              // El usuario no puede cambiar el tamaño
        stage.show();                           // Muestra la ventana
    }

    // Cambia la pantalla actual por otra (por ejemplo, del Login al Home)
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(cargar(fxml)); // Reemplaza el contenido por el nuevo FXML
        ajustarVentana(fxml);        // Ajusta el tamaño de la ventana a esa pantalla
    }

    // Da a la ventana el tamaño correcto según la pantalla que se muestra
    private static void ajustarVentana(String fxml) {
        Stage stage = (Stage) scene.getWindow();

        // Si todavía no hay ventana, no hace nada
        if (stage == null) {
            return;
        }

        switch (fxml) {
            case "login":            // Login: ventana pequeña
                stage.setWidth(400);
                stage.setHeight(350);
                break;

            case "home":             // Home: ventana más grande para el formulario
                stage.setWidth(800);
                stage.setHeight(600);
                break;

            default:                 // Cualquier otra pantalla: no cambia el tamaño
                break;
        }

        stage.centerOnScreen(); // Centra la ventana en el monitor
    }

    // Lee un archivo .fxml (por ejemplo "login" -> login.fxml) y lo convierte en pantalla
    private static Parent cargar(String fxml) throws IOException {
        return new FXMLLoader(
                App.class.getResource(fxml + ".fxml")
        ).load();
    }

    // Se ejecuta al cerrar la aplicación: cierra la conexión con la base de datos
    @Override
    public void stop() {
        HibernateUtil.cerrar();
    }

    // Punto de entrada de Java: arranca JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}