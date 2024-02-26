package reflection.fields;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

  public static void main(String[] args) throws Exception {
    Class<?> cls = User.class;

    // Получение и вывод полей класса
    System.out.println("Поля:");
    Field[] fields = cls.getDeclaredFields();
    for (Field field : fields) {
      System.out.println(field.getName());
    }

    // Получение и вывод методов класса
    System.out.println("\nМетоды:");
    Method[] methods = cls.getMethods();
    for (Method method : methods) {
      System.out.println(method.getName());
    }
  }
}