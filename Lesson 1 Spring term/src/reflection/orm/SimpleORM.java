package reflection.orm;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SimpleORM {

  private Connection connection;

  public SimpleORM(Connection connection) {
    this.connection = connection;
  }

  public void save(Object entity) throws IllegalAccessException, SQLException {
    Class<?> clazz = entity.getClass();

    if (!clazz.isAnnotationPresent(Table.class)) {
      throw new RuntimeException("Класс " + clazz.getSimpleName() + " не аннотирован как @Table.");
    }

    Table tableAnnotation = clazz.getAnnotation(Table.class);
    String tableName = tableAnnotation.name();

    StringBuilder columnNames = new StringBuilder();
    StringBuilder columnValues = new StringBuilder();
    Map<Integer, Object> valueMap = new HashMap<>();

    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(Column.class)) {
        field.setAccessible(true);
        Column column = field.getAnnotation(Column.class);

        if (columnNames.length() > 0) {
          columnNames.append(", ");
          columnValues.append(", ");
        }

        columnNames.append(column.name());
        columnValues.append("?");
        valueMap.put(valueMap.size() + 1, field.get(entity));
      }
    }

    String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columnNames.toString(),
        columnValues.toString());

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      for (Map.Entry<Integer, Object> entry : valueMap.entrySet()) {
        stmt.setObject(entry.getKey(), entry.getValue());
      }
      stmt.execute();
    }
  }

  // Нужны еще методы retrieve/update/delete (опустим для упрощения примера)
}
