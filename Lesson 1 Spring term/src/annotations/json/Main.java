package annotations.json;

public class Main {

  public static void main(String[] args) {
    Person person = new Person("John Doe", 30);
    JsonSerializer serializer = new JsonSerializer();
    try {
      String json = serializer.serialize(person);
      System.out.println(json);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
