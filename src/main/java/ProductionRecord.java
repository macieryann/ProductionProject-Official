import java.util.Date;

public class ProductionRecord {
  int productionNumber;
  int productID;
  String serialNumber;
  Date dateProduced;
  int num;
  String serial;

  public ProductionRecord(int productID){
    this.productID = productID;
    this.serialNumber = "0";
    this.productionNumber = 0;
    this.dateProduced = new Date();

  }

  public ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced){
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  public ProductionRecord(Product product, int productionAmt){
    this.serialNumber = createSerialNumber(product.manufacturer, product, productionAmt);
  }

  public String createSerialNumber(String manufacturer, Product product, int productionAmt){
    String serialNumber;
    serialNumber = manufacturer.substring(0,3)
        + product.getType()
        + String.format("%05d", productionAmt);
    setProdDate(new Date());
    return serialNumber;

  }

  public void setProductionNum(int num){
    productionNumber = num;
  }

  public int getProductionNum(){
    return productionNumber;
  }

  public void setProductID(int num){
    productID = num;
  }

  public int getProductID(){
    return productID;
  }

  public void setSerialNum(String serial){
    serialNumber = serial;
  }

  public String getSerialNum(){
    return serialNumber;
  }

  public void setProdDate(Date dateProduced){
    this.dateProduced = dateProduced;
  }

  public Date getProdDate(){
    return dateProduced;
  }

  public String toString(){
    return "Prod. Num: " + getProductionNum()
        + " Product ID: " + getProductID()
        + " Serial Num: " + getSerialNum()
        + " Date: " + getProdDate();
  }

}