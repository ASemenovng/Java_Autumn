package annotations.test;

import java.lang.reflect.Method;

public class TestRunner {

  private TestRunner() {}

  public static void runClassTests(Class<?> cls) {
    try {
      Object obj = cls.newInstance();
      Method beforeMethod = null;
      Method afterMethod = null;

      // Идентификация методов Before и After
      for (Method method : cls.getDeclaredMethods()) {
        if (method.isAnnotationPresent(Before.class)) {
          beforeMethod = method;
        } else if (method.isAnnotationPresent(After.class)) {
          afterMethod = method;
        }
      }

      // Выполнение тестов
      for (Method method : cls.getDeclaredMethods()) {
        if (method.isAnnotationPresent(Test.class)) {
          if (beforeMethod != null) {
            beforeMethod.invoke(obj); // Выполнение предварительного метода
          }

          method.invoke(obj); // Выполнение теста

          if (afterMethod != null) {
            afterMethod.invoke(obj); // Выполнение завершающего метода
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
