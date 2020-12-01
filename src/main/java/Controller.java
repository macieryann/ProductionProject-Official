//------------------------------------------
//  Name: Macie Ryan
//Course: COP 3003
//  File: Controller.java
//------------------------------------------

import static java.lang.Integer.parseInt;

import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.layout.Pane;


public class Controller {

  @FXML
  private Label lblOutput;

  @FXML
  private TabPane tabPane;

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
  private TableView<Product> tblViewProducts;

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
  private ListView<Product> productListView;

  @FXML
  private ComboBox<String> cmbQuantity;

  @FXML
  private Button btnRecordProduction;

  @FXML
  private Tab productionLogTab;

  @FXML
  private TextArea txtAreaProdLog;

  ObservableList<Product> productLine = FXCollections.observableArrayList();

  Connection conn = null;
  Statement stmt = null;

  public void initialize() throws SQLException {
    connectToDb();

    addProductsToList();

    cmbQuantity.setEditable(true);
    for (int i = 1; i <= 10; i++) {
      cmbQuantity.getItems().add(String.valueOf(i));
    }
    cmbQuantity.getSelectionModel().selectFirst();

    setupProductLineTable();

    addProductionRecordToList();

    try {
      addProductsToList();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    for (ItemType itemType : ItemType.values()) {
      chbItemType.getItems().add(itemType);
    }

    chbItemType.getSelectionModel().selectFirst();

    populateProductLineFromDB();

    tblViewProducts.setItems(productLine);
  }

  public void connectToDb() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/HR";

    //  Database credentials
    final String USER = "";
    final String PASS = "";

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      // STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3 = addProductToDB()
      stmt = conn.createStatement();

      // STEP 4: Clean-up environment
      // temporarily commenting out so program will run
      //stmt.close();
      //conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void setupProductLineTable() {
    colProductID.setCellValueFactory(new PropertyValueFactory("id"));
    colName.setCellValueFactory(new PropertyValueFactory("name"));
    colManufacturer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    colType.setCellValueFactory(new PropertyValueFactory("type"));

    tblViewProducts.setItems(productLine);
  }

  public void addProductToDB() throws SQLException {
    //STEP 3: Execute a query
    String productNameData = txtProductName.getText();
    String manufacturerNameData = txtManufacturer.getText();
    ItemType productType = chbItemType.getValue();
    String itc = productType.code;
    String insertSql = "INSERT INTO PRODUCT (NAME, TYPE, MANUFACTURER)" + "VALUES ('"
        + productNameData + "','" + itc + "','" + manufacturerNameData + "')";

    stmt.executeUpdate(insertSql);
    System.out.println(insertSql);

  }

  public void addProductsToList() throws SQLException {
    String sql = "SELECT * FROM PRODUCT";

    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      System.out.println("Product ID: " + rs.getString(1));
      System.out.println("Product Name: " + rs.getString(2));
      System.out.println("Product Type: " + rs.getString(3));
      System.out.println("Product Manufacturer: " + rs.getString(4));

      String itemTypeString = rs.getString(4);
      ItemType itemTypeFromDB = ItemType.AUDIO;

      if (itemTypeString.equals("AU")) {
      } else if (itemTypeString.equals("VI")) {
        itemTypeFromDB = ItemType.VISUAL;
      } else if (itemTypeString.equals("AM")) {
        itemTypeFromDB = ItemType.AUDIO_MOBILE;
      } else if (itemTypeString.equals("VM")) {
        itemTypeFromDB = ItemType.VISUAL_MOBILE;
      }

      productLine.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), itemTypeFromDB));

      productListView.setItems(productLine);
    }
  }

  // method for populating list view.
  public void populateListView(){
    productListView.setItems(productLine);
  }

  public void loadProductList() throws SQLException {

  }

  public void populateProductLineFromDB() {
    // from when I automatically filled the tableView
/*    return FXCollections.observableArrayList(
        new Widget("iPhone","Apple",ItemType.VISUAL),
        new Widget("Airpods","Apple",ItemType.AUDIO),
        new Widget("Switch","Nintendo",ItemType.VISUAL_MOBILE),
        new Widget("Podcast","Spotify",ItemType.AUDIO_MOBILE));*/

    connectToDb();
  }

  ObservableList<ProductionRecord> listOfItems = FXCollections.observableArrayList();

  public void recordProductionBtn(javafx.event.ActionEvent actionEvent) {
      recordProduction();
  }

  public void recordProduction(){
    int quantity = parseInt(cmbQuantity.getValue());

    // instantiate a new product
    Product product = productListView.getSelectionModel().getSelectedItem();

    try{
      for(int i = 0; i <= quantity; i++) {
        ProductionRecord productionRecord = new ProductionRecord(product, quantity);
        Timestamp dateToTimeStamp = new Timestamp(productionRecord.getProdDate().getTime());
        String insertSql =
          "INSERT INTO PRODUCTIONRECORD (PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED)" + "VALUES ('"
            + String.valueOf(product.getId()) + "','" + productionRecord.getSerialNum()
            + "','" + String.valueOf(dateToTimeStamp) + "')";
        stmt.executeUpdate(insertSql);
      }}catch (IllegalStateException e){

      }catch (SQLException e){
        e.printStackTrace();
      }

    txtAreaProdLog.setText(String.valueOf(listOfItems));
  }

  public void addProductionRecordToList() throws SQLException {
    String sql = "SELECT * FROM PRODUCTIONRECORD";

    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      // setting new observable list listOfItems equal to the table
      listOfItems.add(new ProductionRecord(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4)));

    }
    txtAreaProdLog.setText(String.valueOf(listOfItems));

  }

  public void addProduct(javafx.event.ActionEvent actionEvent) throws SQLException {
    connectToDb();

    addProductToDB();

    productLine.clear();

    addProductsToList();

    populateListView();

    loadProductList();
  }

  public void testMultimedia() {
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