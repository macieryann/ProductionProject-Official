//------------------------------------------
//  Name: Macie Ryan
//Course: COP 3003
//  File: Main.java
//------------------------------------------
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    Scene scene = new Scene(root, 500, 300);
    String css = this.getClass().getResource("style.css").toExternalForm();
    scene.getStylesheets().add(css);

    primaryStage.setTitle("Activity");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}