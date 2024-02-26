package annotations.validator;

public class Main {

  public static void main(String[] args) {
    User user = new User();
    user.setName(null); // Это вызовет исключение, так как имя не должно быть null
    user.setUsername("a"); // Исключение из-за длины строки

    try {
      Validator.validate(user);
    } catch (IllegalAccessException | IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }
}

