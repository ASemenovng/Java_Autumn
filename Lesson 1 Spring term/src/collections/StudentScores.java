package collections;

import java.util.concurrent.ConcurrentSkipListMap;

public class StudentScores {
  public static void main(String[] args) {
    ConcurrentSkipListMap<String, Double> scores = new ConcurrentSkipListMap<>();

    scores.put("Анна", 85.0);
    scores.put("Дмитрий", 92.0);
    scores.put("Светлана", 76.5);
    scores.put("Игорь", 88.0);

    // При добавлении "Екатерина", коллекция останется отсортированной
    scores.put("Екатерина", 91.0);

    // Получение и отображение всех записей
    scores.forEach((id, score) -> System.out.println(id + ": " + score));

    // Допустим, нам нужно обновить балл студента
    scores.computeIfPresent("Светлана", (id, score) -> score + 5.0); // Повышаем балл Светланы

    System.out.println("\nОбновленные баллы:");
    scores.forEach((id, score) -> System.out.println(id + ": " + score));
  }
}
