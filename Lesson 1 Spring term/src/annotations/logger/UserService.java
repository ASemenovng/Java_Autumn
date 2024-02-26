package annotations.logger;

public interface UserService {

  @Loggable
  void addUser(String user);
}

