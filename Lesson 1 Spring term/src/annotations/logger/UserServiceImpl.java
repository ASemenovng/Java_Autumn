package annotations.logger;

public class UserServiceImpl implements UserService {

  @Override
  public void addUser(String user) {
    System.out.println("Пользователь добавлен: " + user);
  }
}
