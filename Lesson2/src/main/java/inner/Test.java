package inner;

public class Test {

  public static void main(String[] args) {
    Invoice invoice = new Invoice();
    invoice.addItem("description", 10, 3.0);

    NetWork blaBlaDram = new NetWork();
    NetWork.Member alex = blaBlaDram.new Member("Alex");
    NetWork.Member john = blaBlaDram.enroll("John");
    System.out.println(blaBlaDram);
  }

}
