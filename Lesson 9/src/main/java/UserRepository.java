import java.util.Optional;

public interface UserRepository {

  Optional<User> findUser(String name);
}
