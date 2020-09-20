import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

  @FXML
  private Label lblOutput;

  public TabPane tabPane;
  @FXML
  private Pane pane;
  @FXML
  private Tab tab;
  @FXML
  private Label Produce;

  @FXML
  private Tab productLineTab;

  @FXML
  private TextField txtProductName;

  @FXML
  private TextField txtManufacturer;

  @FXML
  private Button btnAddProduct;

  @FXML
  private Tab produceTab;

  @FXML
  private Button btnRecordProduction;

  @FXML
  private Tab productionLogTab;

  @FXML
  void addProduct(ActionEvent event) throws SQLException {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/BicycleDB";

    Connection conn = null;
    Statement stmt = null;

    System.out.println("Inserting records into the table...");
    stmt = conn.createStatement();

    String sql = "INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO', 'Apple', 'iPod' )";
    stmt.executeUpdate(sql);

  }

  @FXML
  void recordProduction(ActionEvent event) {
    System.out.println("Product Added.");
  }

  @FXML
  private TextArea taOutput;

  public void initialize() {

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/BicycleDB";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //Class.forName(new org.h2.Driver());

      //STEP 2: Open a connection
      //conn = DriverManager.getConnection(DB_URL, USER, PASS);
      conn = DriverManager.getConnection(DB_URL);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql = "SELECT * FROM Bike";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        taOutput.appendText(rs.getString(1) + "\n");
      }

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      taOutput.appendText(e.toString());
    } catch (ClassNotFoundException e) {
      taOutput.appendText(e.toString());
    }
  }
}
