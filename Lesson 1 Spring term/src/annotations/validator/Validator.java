package annotations.validator;

import java.lang.reflect.Field;

public class Validator {

  private Validator() {}

  public static void validate(Object object) throws IllegalAccessException {
    Class<?> clazz = object.getClass();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);

      if (field.isAnnotationPresent(NotNull.class) && field.get(object) == null) {
        throw new IllegalArgumentException(field.getName() + " не должно быть null");
      }

      if (field.isAnnotationPresent(Size.class)) {
        Size size = field.getAnnotation(Size.class);
        String value = (String) field.get(object);

        if (value != null && (value.length() < size.min() || value.length() > size.max())) {
          throw new IllegalArgumentException(field.getName() + " должно быть в диапазоне от "
              + size.min() + " до " + size.max());
        }
      }
    }
  }
}