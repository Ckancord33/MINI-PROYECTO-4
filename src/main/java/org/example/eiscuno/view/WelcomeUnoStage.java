package org.example.eiscuno.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.eiscuno.controller.GameUnoController;

import java.io.IOException;

public class WelcomeUnoStage extends Stage {

    /**
     * Constructor for {@code WelcomeStage}.
     * It loads the welcome screen layout from the FXML file and sets the scene,
     * window title, and icon.
     *
     * @throws IOException If the FXML file cannot be loaded.
     */
    public WelcomeUnoStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/eiscuno/welcome-uno-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setResizable(false);
        getIcons().add(new Image(getClass().getResourceAsStream("/org/example/eiscuno/favicon3.png")));
        scene.getStylesheets().add(getClass().getResource("/org/example/eiscuno/styles/welcomeStyles.css").toExternalForm());
        setTitle("UNO!");
        setScene(scene);
        show();
    }

    /**
     * Holder class to implement the Singleton pattern.
     * This inner static class holds the unique instance of {@code WelcomeStage}.
     */
    private static class WelcomeStageHolder {
        /**
         * The unique instance of {@code WelcomeStage}.
         */
        private static WelcomeUnoStage INSTANCE;
    }

    /**
     * Returns the singleton instance of {@code WelcomeUnoStage}.
     * If the instance does not exist, it is created.
     *
     * @return The singleton instance of {@code WelcomeUnoStage}.
     * @throws IOException If an error occurs while creating the welcome stage.
     */
    public static WelcomeUnoStage getInstance() throws IOException {
        WelcomeUnoStage.WelcomeStageHolder.INSTANCE =
                WelcomeUnoStage.WelcomeStageHolder.INSTANCE != null ?
                        WelcomeUnoStage.WelcomeStageHolder.INSTANCE : new WelcomeUnoStage();
        return WelcomeUnoStage.WelcomeStageHolder.INSTANCE;
    }

    /**
     * Deletes the current instance of {@code WelcomeUnoStage}.
     * This method closes the window and sets the instance to {@code null}, allowing a new one to be created later.
     */
    public static void deleteInstance() {
        WelcomeUnoStage.WelcomeStageHolder.INSTANCE.close();
        WelcomeUnoStage.WelcomeStageHolder.INSTANCE = null;
    }
}
