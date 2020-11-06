//------------------------------------------
//  Name: Macie Ryan
//Course: COP 3003
//  File: Controller.java
//------------------------------------------
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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
  private ChoiceBox<ItemType> chbItemType;

  @FXML
  private Button btnAddProduct;

  @FXML
  private TableView<Widget> tblViewProducts;

  @FXML
  private TableColumn colProductID;

  @FXML
  private TableColumn colName;

  @FXML
  private TableColumn colManufacturer;

  @FXML
  private TableColumn colType;

  @FXML
  private Tab produceTab;

  @FXML
  private ComboBox<String> cmbQuantity;

  @FXML
  private Button btnRecordProduction;

  @FXML
  private TextArea txtAreaProdLog;

  @FXML
  private Tab productionLogTab;

/*  // Update table on Product Line Tab
  public void setUpProductLineTab(){
    colName.setCellValueFactory(new PropertyValueFactory("name"));
    colName.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    colName.setCellValueFactory(new PropertyValueFactory("type"));
    tblViewProducts.setItems(productLine);
  }*/

  public void initialize(){
    testMultimedia();
    cmbQuantity.setEditable(true);
    for(int i = 1; i <= 10; i++)
    cmbQuantity.getSelectionModel().selectFirst();

    for(ItemType itemType : ItemType.values()){
      chbItemType.getItems().add(itemType);
    }

    chbItemType.getSelectionModel().selectFirst();

    testMultimedia();

    ObservableList<Widget> one = setUpProductLine();
    colProductID.setCellValueFactory(new PropertyValueFactory("id"));
    colName.setCellValueFactory(new PropertyValueFactory("Name"));
    colManufacturer.setCellValueFactory(new PropertyValueFactory("Manufacturer"));
    colType.setCellValueFactory(new PropertyValueFactory("Type"));
    tblViewProducts.setItems(one);
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
      ItemType productType = chbItemType.getValue();
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

  public static ObservableList<Widget> setUpProductLine(){
    return FXCollections.observableArrayList(
        new Widget("iPhone","Apple",ItemType.VISUAL),
        new Widget("Airpods","Apple",ItemType.AUDIO),
        new Widget("Switch","Nintento",ItemType.VISUAL_MOBILE),
        new Widget("Podcast","Spotify",ItemType.AUDIO_MOBILE));

  }

  public void recordProduction(javafx.event.ActionEvent actionEvent) {
    // add text from ProductionRecord to product log text area
/*    Product Product = new Product(txtProductName.getText(), txtManufacturer.getText(), ItemType.AUDIO){};
    ProductionRecord pr = new ProductionRecord(Product, Product.getId());
    txtAreaProdLog.appendText(String.valueOf(pr));*/
    System.out.println("Hello");
  }

  public void addProduct(javafx.event.ActionEvent actionEvent){
    connectToDb();
  }

  public static void testMultimedia() {
    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
        "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
        MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();

    productList.add(newAudioProduct);

    productList.add(newMovieProduct);

    for (MultimediaControl i : productList) {
      System.out.println(i);

      i.play();
      i.stop();
      i.next();
      i.previous();
    }
  }
}