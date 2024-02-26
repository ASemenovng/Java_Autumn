package annotations.logger;

import java.lang.reflect.*;

public class LoggingProxyHandler implements InvocationHandler {
  private final Object target;

  public LoggingProxyHandler(Object target) {
    this.target = target;
  }

  public static Object createProxy(Object target) {
    return Proxy.newProxyInstance(target.getClass().getClassLoader(),
        target.getClass().getInterfaces(),
        new LoggingProxyHandler(target));
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.isAnnotationPresent(Loggable.class)) {
      System.out.println("Вызов метода: " + method.getName());
    }
    return method.invoke(target, args);
  }
}
