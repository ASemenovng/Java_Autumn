package annotations.json;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class JsonSerializer {

  public String serialize(Object object) throws IllegalAccessException {
    Class<?> objectClass = object.getClass();
    Map<String, String> jsonElements = new HashMap<>();
    if (!objectClass.isAnnotationPresent(SerializableObject.class)) {
      throw new RuntimeException(
          "The class " + objectClass.getSimpleName() + " is not marked as SerializableObject");
    }

    for (Field field : objectClass.getDeclaredFields()) {
      if (field.isAnnotationPresent(JsonField.class)) {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        JsonField jsonField = field.getAnnotation(JsonField.class);
        String name = jsonField.name().isEmpty() ? field.getName() : jsonField.name();
        jsonElements.put(name, field.get(object).toString());
        field.setAccessible(accessible);
      }
    }

    String jsonString = jsonElements.entrySet().stream()
        .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
        .reduce("{", (partialString, element) -> {
          if (!"{".equals(partialString)) {
            partialString += ",";
          }
          return partialString + element;
        });
    return jsonString + "}";
  }
}
