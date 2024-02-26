package reflection.private_modification;

import java.lang.reflect.Field;

public class Main {

  public static void main(String[] args) throws Exception {
    Secret secret = new Secret();
    Class<?> cls = secret.getClass();

    // Доступ к приватному полю
    Field field = cls.getDeclaredField("secretCode");
    field.setAccessible(true); // Включаем доступ к приватному полю

    // Получение и изменение значения приватного поля
    System.out.println("Исходное значение: " + field.get(secret));
    field.set(secret, "NEW-SECRET-CODE");
    System.out.println("Новое значение: " + field.get(secret));
    System.out.println("Новое значение через геттер: " + secret.getSecretCode());
  }
}