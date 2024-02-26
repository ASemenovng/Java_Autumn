package annotations.logger;

public class Main {

  public static void main(String[] args) {
    UserService userService = (UserService) LoggingProxyHandler.createProxy(new UserServiceImpl());
    userService.addUser("Тестовый пользователь");
  }
}