package inner;

import java.util.List;

public class Invoice {

  //
  private List<Item> items;

  public void addItem(String description, int quantity, double unitPrice) {
    Item item = new Item(description, quantity, unitPrice);
  }

  // почему private конструктор доступен
  private static class Item {
    String description;
    int quantity;
    double unitPrice;

    private Item(String description, int quantity, double unitPrice) {
      this.description = description;
      this.quantity = quantity;
      this.unitPrice = unitPrice;
    }

    double price() {
      return quantity * unitPrice;
    }
  }

}
