package stanly.miaula_ufg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import stanly.miaula_ufg.util.HibernateUtil;

/**
 * stanlyy
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(cargar("login"), 400, 350);

        stage.setTitle("Login JavaFX + Hibernate");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(cargar(fxml));
        ajustarVentana(fxml);
    }

    private static void ajustarVentana(String fxml) {
        Stage stage = (Stage) scene.getWindow();

        if (stage == null) {
            return;
        }

        switch (fxml) {
            case "login":
                stage.setWidth(400);
                stage.setHeight(350);
                break;

            case "home":
                stage.setWidth(800);
                stage.setHeight(600);
                break;

            default:
                break;
        }

        stage.centerOnScreen();
    }

    private static Parent cargar(String fxml) throws IOException {
        return new FXMLLoader(
                App.class.getResource(fxml + ".fxml")
        ).load();
    }

    @Override
    public void stop() {
        HibernateUtil.cerrar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}