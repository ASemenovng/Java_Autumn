package unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeAccess {
  public static void main(String[] args) throws InstantiationException, NoSuchFieldException {
    Unsafe unsafe = getUnsafeInstance();
    // Теперь у нас есть экземпляр Unsafe, и мы можем начать использовать его.

    // Пример: Создание объекта без вызова конструктора
    MyClass myObject = (MyClass) unsafe.allocateInstance(MyClass.class);

    // Пример: Манипуляция с полем объекта напрямую
    unsafe.putInt(myObject, unsafe.objectFieldOffset(MyClass.class.getDeclaredField("myField")), 42);

    System.out.println(myObject);
  }

  public static Unsafe getUnsafeInstance() {
    try {
      // Получаем поле 'theUnsafe' из класса Unsafe.
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      // Извлекаем экземпляр Unsafe из поля.
      return (Unsafe) field.get(null);
    } catch (Exception e) {
      // Обработка ошибок при получении экземпляра Unsafe.
      throw new RuntimeException("Unable to get Unsafe instance");
    }
  }

  static class MyClass {
    private int myField;

    @Override
    public String toString() {
      return "MyClass{" +
          "myField=" + myField +
          '}';
    }
  }
}