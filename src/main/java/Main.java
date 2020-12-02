/*
 * NAME: Macie Ryan
 * CLASS: COP 3003
 * FILE: Main.java
 *
 * Defines the Main class.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * An abstract representation of a product.
 *
 * @author Macie Ryan
 */
public class Main extends Application {

  /**
   * The starting point of the application.
   *
   * @param primaryStage - the root JavaFX container
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    Scene scene = new Scene(root, 710, 413);
    String css = this.getClass().getResource("style.css").toExternalForm();
    scene.getStylesheets().add(css);

    primaryStage.setTitle("HR Production Project");
    primaryStage.setScene(scene);
    primaryStage.show();

  }
}