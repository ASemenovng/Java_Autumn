package reflection.tostring;

import java.lang.reflect.Field;

public class ReflectionToString {

  public static String universalToString(Object obj) {
    Class<?> objClass = obj.getClass();
    StringBuilder result = new StringBuilder();
    result.append(objClass.getSimpleName()).append("[");

    Field[] fields = objClass.getDeclaredFields();
    boolean firstField = true;
    for (Field field : fields) {
      field.setAccessible(true); // Дает доступ к приватным полям
      if (!firstField) {
        result.append(", ");
      }
      firstField = false;
      try {
        result.append(field.getName())
            .append("=")
            .append(field.get(obj)); // Получение значения поля
      } catch (IllegalArgumentException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    result.append("]");
    return result.toString();
  }

  public static void main(String[] args) {
    MyClass myClass = new MyClass(1, "ReflectionToString Example");
    System.out.println(ReflectionToString.universalToString(myClass));
  }
}
