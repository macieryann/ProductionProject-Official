/*
 * NAME: Macie Ryan
 * CLASS: COP 3003
 * FILE: ProductionRecord.java
 *
 * Defines the ProductionRecord class.
 */
import java.util.Date;

/**
 * Represents a collection of data about a product being produced.
 * @author Macie Ryan
 */
public class ProductionRecord {
  /**
   * Unique production number of each record.
   */
  int productionNumber;

  /**
   * ID Number of product produced.
   */
  int productID;

  /**
   * Serial number of product produced.
   */
  String serialNumber;

  /**
   * Date of product produced.
   */
  Date dateProduced;

  /**
   * Creates a record for a produced product.
   * @param productID - the produced product id
   */
  public ProductionRecord(int productID){
    this.productID = productID;
    this.serialNumber = "0";
    this.productionNumber = 0;
    this.dateProduced = new Date();
  }

  /**
   * Creates a representation of an existing production record.
   * @param productionNumber - the unique production number
   * @param productID - the produced product id
   * @param serialNumber - the serial number of product produced
   * @param dateProduced - the date of production
   */
  public ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced){
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  /**
   * Creates a record for a product produced.
   * @param product - the product being produced
   * @param productionAmt - the number of times this product has been produced
   */
  public ProductionRecord(Product product, int productionAmt){
    createSerialNumber(product.manufacturer, product, productionAmt);
    this.dateProduced = new Date();
  }

  /**
   * Creates a unique serial number for product being produced with the first three letters of the manufacturer name,
   * the two letter item code, then five unique digits
   *
   * @param manufacturer - the manufacturer who produced the product
   * @param product - the product being produced
   * @param productionAmt - the number of times this product has been produced
   */
  public void createSerialNumber(String manufacturer, Product product, int productionAmt){
    String serialNumber;
    serialNumber = manufacturer.substring(0,3)
        + product.getType()
        + String.format("%05d", productionAmt);
    setProdDate(new Date());
    //return serialNumber;
  }

  /**
   * Sets the recorded production number
   * @param num - the production number
   */
  public void setProductionNum(int num){
    productionNumber = num;
  }

  /**
   * Gets the recorded production number
   * @return the production number
   */
  public int getProductionNum(){
    return productionNumber;
  }

  /**
   * Sets the the produced product id
   * @param num - the production id
   */
  public void setProductID(int num){
    productID = num;
  }

  /**
   * Gets the produced product id
   * @return the product id
   */
  public int getProductID(){
    return productID;
  }

  /**
   * Sets the recorded product's serial number
   * @param serial - the serial number
   */
  public void setSerialNum(String serial){
    serialNumber = serial;
  }

  /**
   * Gets the recorded product's serial number
   * @return the product serial number
   */
  public String getSerialNum(){
    return serialNumber;
  }

  /**
   * Sets the recorded date of production
   * @param dateProduced - the production date
   */
  public void setProdDate(Date dateProduced){
    this.dateProduced = dateProduced;
  }

  /**
   * Gets the recorded date of production
   * @return the production date
   */
  public Date getProdDate(){
    return dateProduced;
  }

  /**
   * Displays a string for this production record
   * @return product name, product id, serial number, and date produced on one line
   */
  public String toString(){
    return "Prod. Num: " + getProductionNum()
        + " Product ID: " + getProductID()
        + " Serial Num: " + getSerialNum()
        + " Date: " + getProdDate();
  }

}