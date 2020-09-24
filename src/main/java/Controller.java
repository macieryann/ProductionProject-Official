//------------------------------------------
//  Name: Macie Ryan
//Course: COP 3003
//  File: Controller.java
//------------------------------------------
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
  private ChoiceBox<String> chbItemType;

  @FXML
  private Button btnAddProduct;

  @FXML
  private Tab produceTab;

  @FXML
  private ComboBox<String> cmbQuantity;

  @FXML
  private Button btnRecordProduction;

  @FXML
  private Tab productionLogTab;

  public void addProduct(javafx.event.ActionEvent actionEvent){
    connectToDb();
  }

  public void recordProductionDisplay(javafx.event.ActionEvent actionEvent){
    System.out.println("Production displayed");
  }

  @FXML
  void recordProduction(ActionEvent event) {
    System.out.println("Product Added.");
  }

  public void initialize(){
    //connectToDb();
    cmbQuantity.setEditable(true);
    for(int i = 1; i <= 10; i++){
      cmbQuantity.getItems().add(String.valueOf(i));
    }
    cmbQuantity.getSelectionModel().selectFirst();
    chbItemType.getSelectionModel().selectFirst();
    chbItemType.getItems().add(String.valueOf("AUDIO"));
    chbItemType.getItems().add(String.valueOf("AUDIO_MOBILE"));
    chbItemType.getItems().add(String.valueOf("VISUAL"));
  }

  public void connectToDb(){
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/HR";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String productNameData = txtProductName.getText();
      String manufacturerNameData = txtManufacturer.getText();
      String productType = chbItemType.getValue();
      String insertSql = "INSERT INTO PRODUCT (NAME, TYPE, MANUFACTURER)" + "VALUES ('"
          + productNameData + "','" + productType + "','" + manufacturerNameData + "')";
      stmt.executeUpdate(insertSql);
      System.out.println(insertSql);

      String sql = "SELECT * FROM PRODUCT";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        System.out.println("Product ID: " + rs.getString(1));
        System.out.println("Product Name: " + rs.getString(2));
        System.out.println("Product Type: " + rs.getString(3));
        System.out.println("Product Manufacturer: " + rs.getString(4));
      }

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}