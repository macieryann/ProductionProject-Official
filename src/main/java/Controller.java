//------------------------------------------
//  Name: Macie Ryan
//Course: COP 3003
//  File: Controller.java
//------------------------------------------

import static java.lang.Integer.parseInt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Deals with user interaction on the production line GUI.
 *
 * @author Macie Ryan
 */
public class Controller {

  /**
   * The new product name text field.
   */
  @FXML
  private TextField txtProductName;

  /**
   * The new product manufacturer field.
   */
  @FXML
  private TextField txtManufacturer;

  /**
   * The new product item type choice box field.
   */
  @FXML
  private ChoiceBox<ItemType> chbItemType;

  /**
   * The products table.
   */
  @FXML
  private TableView<Product> tblViewProducts;

  /**
   * The product id column on the products table.
   */
  @FXML
  private TableColumn colProductID;

  /**
   * The product name column on the products table.
   */
  @FXML
  private TableColumn colName;

  /**
   * The product manufacturer column on the products table.
   */
  @FXML
  private TableColumn colManufacturer;

  /**
   * The product type column on the products table.
   */
  @FXML
  private TableColumn colType;

  /**
   * The product list view.
   */
  @FXML
  private ListView<Product> productListView;

  /**
   * The combobox for users to choose quantity.
   */
  @FXML
  private ComboBox<String> cmbQuantity;

  /**
   * The text area for production log.
   */
  @FXML
  private TextArea txtAreaProdLog;

  /**
   * The list of products loaded from the database.
   */
  ObservableList<Product> productLine = FXCollections.observableArrayList();

  Connection conn = null;
  Statement stmt = null;

  /**
   * Initializes the production line GUI with additional data.
   */
  public void initialize() throws SQLException {
    connectToDb();

    addProductsToList();

    productLine.clear();

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

    tblViewProducts.setItems(productLine);
  }

  /**
   * Alternative 'initialize method that is not used.
   */
  public void addProduct() throws SQLException {
    connectToDb();

    addProductToDB();

    productLine.clear();

    addProductsToList();

  }

  /**
   * Initializes the database information and creates a connection.
   */
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

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();

    }
  }

  /**
   * Adds product to database (PRODUCT table).
   */
  public void addProductToDB() throws SQLException {
    //STEP 3: Execute a query
    String productNameData = txtProductName.getText();
    ItemType productType = chbItemType.getValue();
    String itc = productType.code;
    String manufacturerNameData = txtManufacturer.getText();
    String insertSql = "INSERT INTO PRODUCT (NAME, TYPE, MANUFACTURER)" + "VALUES ('"
        + productNameData + "','" + itc + "','" + manufacturerNameData + "')";

    stmt.executeUpdate(insertSql);
    System.out.println(insertSql);

  }

  /**
   * Adds products from database to list view.
   */
  public void addProductsToList() throws SQLException {
    String sql = "SELECT * FROM PRODUCT";

    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      System.out.println("Product ID: " + rs.getString(1));
      System.out.println("Product Name: " + rs.getString(2));
      System.out.println("Product Type: " + rs.getString(3));
      System.out.println("Product Manufacturer: " + rs.getString(4));

      String itemTypeString = rs.getString(3);
      ItemType itemTypeFromDB = ItemType.AUDIO;

      switch (itemTypeString) {
        case "AU":
          break;
        case "VI":
          itemTypeFromDB = ItemType.VISUAL;
          break;
        case "AM":
          itemTypeFromDB = ItemType.AUDIO_MOBILE;
          break;
        case "VM":
          itemTypeFromDB = ItemType.VISUAL_MOBILE;
          break;
      }

      productLine.add(new Product(rs.getInt(1), rs.getString(2),
          itemTypeFromDB, rs.getString(4)));

      productListView.setItems(productLine);
    }
  }

  /**
   * Initializes table view on product line tab.
   */
  public void setupProductLineTable() {
    colProductID.setCellValueFactory(new PropertyValueFactory("id"));
    colName.setCellValueFactory(new PropertyValueFactory("name"));
    colManufacturer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    colType.setCellValueFactory(new PropertyValueFactory("type"));

    tblViewProducts.setItems(productLine);
  }

  /**
   * The list of production logs loaded from the database.
   */
  ObservableList<ProductionRecord> listOfItems = FXCollections.observableArrayList();

  /**
   * Record production button Creates a new Product and inserts it into the database in the
   * PRODUCTIONRECORD table
   */
  public void recordProductionBtn(javafx.event.ActionEvent actionEvent) throws SQLException {
    int quantity = parseInt(cmbQuantity.getValue());

    Product product = productListView.getSelectionModel().getSelectedItem();

    ProductionRecord productionRecord = new ProductionRecord(product, quantity);

    Timestamp dateToTimeStamp = new Timestamp(productionRecord.getProdDate().getTime());

    try {
      for (int i = 1; i <= quantity; i++) {
        String insertSql =
            "INSERT INTO PRODUCTIONRECORD (PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED)" + "VALUES ('"
                + product.getId() + "','" + productionRecord.getSerialNum()
                + "','" + dateToTimeStamp + "')";
        stmt.executeUpdate(insertSql);
      }
    } catch (IllegalStateException | SQLException e) {
      e.printStackTrace();
    }

    addProductionRecordToList();
  }

  /**
   * Adds products from database to text area.
   */
  public void addProductionRecordToList() throws SQLException {
    String sql = "SELECT * FROM PRODUCTIONRECORD";

    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      // setting new observable list listOfItems equal to the table
      listOfItems.add(new ProductionRecord(rs.getInt(1), rs.getInt(2),
          rs.getString(3), rs.getDate(4)));

    }
    txtAreaProdLog.setText(String.valueOf(listOfItems));

  }

}