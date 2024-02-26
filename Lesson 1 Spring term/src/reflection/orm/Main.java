package reflection.orm;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

  public static void main(String[] args) {
    try (Connection connection = DriverManager.getConnection("jdbc:yourdb:url", "username",
        "password")) {
      SimpleORM orm = new SimpleORM(connection);
      User user = new User(1, "John Doe");
      orm.save(user);
      System.out.println("Пользователь сохранен.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}