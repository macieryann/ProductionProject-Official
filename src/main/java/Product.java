/*
 * NAME: Macie Ryan
 * CLASS: COP 3003
 * FILE: Product.java
 *
 * Defines the Product class.
 */

/**
 * An abstract representation of a product.
 *
 * @author Macie Ryan
 */
public class Product implements Item {

  /**
   * ID Number of product.
   */
  int id;

  /**
   * Item type of the product.
   */
  ItemType type;

  /**
   * Manufacturer of product produced.
   */
  String manufacturer;

  /**
   * Name of product produced.
   */
  String name;

  /**
   * Creates a new product.
   *
   * @param name         - name of product produced
   * @param manufacturer - manufacturer of product produced
   * @param type         - item type of the product produced
   */
  public Product(String name, String manufacturer, ItemType type) {
    this.type = type;
    this.manufacturer = manufacturer;
    this.name = name;
  }

  /**
   * Creates a new product with an ID that is unique to the produced product.
   *
   * @param id           - id of product produced
   * @param name         - name of product produced
   * @param manufacturer - manufacturer of product produced
   * @param type         - item type of the product produced
   */
  public Product(int id, String name, ItemType type, String manufacturer) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * Displays a descriptive string for this product.
   *
   * @return product name, manufacturer, and item type each on their own line
   */
  public String toString() {
    return "Name: " + getName()
         + "\nManufacturer: " + getManufacturer()
        + "\nType: " + type.code;
  }

  /**
   * Gets the recorded product's item type.
   *
   * @return the product item type
   */
  public ItemType getType() {
    return type;
  }

  /**
   * Gets the recorded product's id.
   *
   * @return the product id number
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the recorded product's name.
   *
   * @param name - item type of the product produced
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the recorded product's name.
   *
   * @return the product name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the recorded product's manufacturer.
   *
   * @return the product manufacturer
   */
  public String getManufacturer() {
    return manufacturer;
  }

}
