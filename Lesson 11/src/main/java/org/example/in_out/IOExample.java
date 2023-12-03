package org.example.in_out;

import static java.util.stream.Collectors.toList;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.example.serr.Person;

public class IOExample {

  public static final String IO_PATH = "./src/main/java/org/example/in_out";

  public static void main(String[] args) {
    // scanner();
    // writer();
    // readFromFile();
    // createAndWrite();
    // readBytes();
    // writeBytes();
    // IORead();
    // NIORead();
    // copyFile();
    // removeFile();
    // searchFiles();
    // watch();
    // symbolLink();
    // attributes();
    // compareFiles();
    // replace();
    // zip();
    // wordCount();
    // jsonHelper();
  }

  // Чтение и запись ввода-вывода с клавиатуры
  public static void scanner() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Введите ваше имя: ");
    String name = scanner.nextLine();
    System.out.println("Привет, " + name + "!");
  }

  // Введем данные с клавиатуры и сохраним их в файл.
  public static void writer() {
    try (FileWriter writer = new FileWriter(IO_PATH + "/output.txt");
        Scanner scanner = new Scanner(System.in)) {
      System.out.print("Введите текст для записи в файл: ");
      String input = scanner.nextLine();
      writer.write(input);
      System.out.println("Данные успешно записаны в файл.");
    } catch (IOException e) {
      System.out.println("Произошла ошибка при записи в файл: " + e.getMessage());
    }
  }

  // Прочитаем данные из файла и выведем их на экран.
  public static void readFromFile() {
    try {
      Scanner scanner = new Scanner(new File(IO_PATH, "source.txt"));
      while (scanner.hasNextLine()) {
        System.out.println(scanner.nextLine());
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Файл не найден: " + e.getMessage());
    }
  }

  // Создадим новый файл и запишем в него данные.
  public static void createAndWrite() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(IO_PATH + "/new_file.txt"));) {
      writer.write("Пример текста для записи в файл.");
      System.out.println("Данные успешно записаны в новый файл.");
    } catch (IOException e) {
      System.out.println("Произошла ошибка при записи в файл: " + e.getMessage());
    }
  }

  // Прочитаем данные из файла в байтовом виде.
  public static void readBytes() {
    try (FileInputStream inputStream = new FileInputStream(IO_PATH + "/binary_data.dat");) {
      int data;
      while ((data = inputStream.read()) != -1) {
        System.out.print(data + " ");
      }
    } catch (IOException e) {
      System.out.println("Произошла ошибка ввода-вывода: " + e.getMessage());
    }
  }

  // Создадим файл и запишем в него байтовые данные.
  public static void writeBytes() {
    try (FileOutputStream outputStream = new FileOutputStream(IO_PATH + "/binary_data.dat");) {
      byte[] data = {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100};
      outputStream.write(data);
      System.out.println("Данные успешно записаны в бинарный файл.");
    } catch (IOException e) {
      System.out.println("Произошла ошибка ввода-вывода: " + e.getMessage());
    }
  }

  // Пример чтения текстового файла с использованием java.io:
  public static void IORead() {
    try (BufferedReader reader = new BufferedReader(new FileReader(IO_PATH + "/source.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.out.println("Произошла ошибка ввода-вывода: " + e.getMessage());
    }
  }

  // Пример чтения текстового файла с использованием java.nio:
  public static void NIORead() {
    try {
      List<String> lines = Files.readAllLines(Paths.get(IO_PATH,"source.txt"));
      for (String line : lines) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.out.println("Произошла ошибка ввода-вывода: " + e.getMessage());
    }
  }

  // Копирование файла
  public static void copyFile() {
    Path sourceFile = Paths.get(IO_PATH, "source.txt");
    Path targetFile = Paths.get(IO_PATH,"target.txt");

    try {
      Files.copy(sourceFile, targetFile);
      System.out.println("Файл успешно скопирован.");
    } catch (IOException e) {
      System.out.println("Произошла ошибка при копировании файла: " + e.getMessage());
    }
  }

  // Удаление файла
  public static void removeFile() {
    Path fileToDelete = Path.of(IO_PATH,"file_to_delete.txt");
    try {
      Files.delete(fileToDelete);
      System.out.println("Файл успешно удален.");
    } catch (IOException e) {
      System.out.println("Произошла ошибка при удалении файла: " + e.getMessage());
    }
  }

  // Поиск файлов по расширению
  public static void searchFiles() {
    Path directory = Path.of(IO_PATH);
    String targetExtension = ".txt";
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, String.format("*%s", targetExtension))) {
      stream.forEach(file -> System.out.println(file.getFileName()));
    } catch (IOException e) {
      System.out.println("Произошла ошибка при поиске файлов: " + e.getMessage());
    }
  }

  // Мониторинг файловой системы
  public static void watch() {
    Path directory = Path.of(IO_PATH);
    try(WatchService watchService = FileSystems.getDefault().newWatchService();) {
      directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

      System.out.println("Мониторинг директории: " + directory);

      WatchKey key;
      while ((key = watchService.take()) != null) {
        for (WatchEvent<?> event : key.pollEvents()) {
          System.out.println("Новый файл: " + event.context());
        }
        key.reset();
      }
    } catch (IOException | InterruptedException e) {
      System.out.println("Произошла ошибка при мониторинге директории: " + e.getMessage());
    }
  }

  // Создание и чтение символических ссылок
  // Пример создания жесткой ссылки в командной строке:
  // ln source.txt hard_link.txt
  // Пример создания символической ссылки в командной строке:
  // ln -s source.txt symbolic_link.txt
  public static void symbolLink() {
    Path sourceFile = Path.of(IO_PATH,"source.txt");
    Path symbolicLink = Path.of(IO_PATH,"symbolic_link.txt");

    try {
      Files.createSymbolicLink(symbolicLink, sourceFile);
      System.out.println("Символическая ссылка создана: " + symbolicLink);
      System.out.println("Реальный путь: " + Files.readSymbolicLink(symbolicLink));
    } catch (IOException e) {
      System.out.println("Произошла ошибка при работе с символической ссылкой: " + e.getMessage());
    }
  }

  // Атрибуты файла
  public static void attributes() {
    Path file = Path.of(IO_PATH,"source.txt");
    try {
      BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
      System.out.println("Creation Time: " + attributes.creationTime());
      System.out.println("Last Access Time: " + attributes.lastAccessTime());
      System.out.println("Last Modified Time: " + attributes.lastModifiedTime());
      System.out.println("Is Directory: " + attributes.isDirectory());
      System.out.println("Is Regular File: " + attributes.isRegularFile());
      System.out.println("Is Symbolic Link: " + attributes.isSymbolicLink());
      System.out.println("Size: " + attributes.size());
    } catch (IOException e) {
      System.out.println("Произошла ошибка при получении атрибутов файла: " + e.getMessage());
    }
  }

  // Сравнение содержимого двух файлов
  public static void compareFiles() {
    Path file1 = Path.of(IO_PATH,"file_to_delete.txt");
    Path file2 = Path.of(IO_PATH,"source.txt");

    try {
      List<String> content1 = Files.readAllLines(file1);
      List<String> content2 = Files.readAllLines(file2);

      if (content1.equals(content2)) {
        System.out.println("Содержимое файлов идентично.");
      } else {
        System.out.println("Содержимое файлов отличается.");
      }
    } catch (IOException e) {
      System.out.println("Произошла ошибка при чтении файлов: " + e.getMessage());
    }
  }

  // Поиск и замена в файле
  public static void replace() {
    Path file = Path.of(IO_PATH,"file_to_delete.txt");
    String searchWord = "Smth";
    String replaceWord = "Something";

    try {
      List<String> lines = Files.readAllLines(file);
      List<String> replacedLines = lines.stream()
          .map(line -> line.replaceAll(searchWord, replaceWord))
          .collect(toList());
      Files.write(file, replacedLines);
      System.out.println("Слово(а) \"" + searchWord + "\" заменены на \"" + replaceWord + "\".");
    } catch (IOException e) {
      System.out.println("Произошла ошибка при чтении/записи файла: " + e.getMessage());
    }
  }

  // Архивирование и разархивирование файлов
  public static void zip() {
    Path fileToCompress = Path.of(IO_PATH,"file_to_compress.txt");
    Path compressedFile = Path.of(IO_PATH,"compressed_file.zip");

    try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(compressedFile))) {
      ZipEntry entry = new ZipEntry(fileToCompress.getFileName().toString());
      zipOutputStream.putNextEntry(entry);
      byte[] data = Files.readAllBytes(fileToCompress);
      zipOutputStream.write(data, 0, data.length);
      zipOutputStream.closeEntry();
      System.out.println("Файл успешно заархивирован: " + compressedFile);
    } catch (IOException e) {
      System.out.println("Произошла ошибка при архивации файла: " + e.getMessage());
    }
  }

  // Подсчет слов в текстовом файле
  public static void wordCount() {
    Path file = Path.of(IO_PATH,"source.txt");
    try(Stream<String> words = Files.lines(file)) {
      long wordCount = words
          .flatMap(line -> Stream.of(line.split(" ")))
          .count();
      System.out.println("Количество слов в файле: " + wordCount);
    } catch (IOException e) {
      System.out.println("Произошла ошибка при чтении файла: " + e.getMessage());
    }
  }

  // Чтение и запись данных в формате JSON
  public static void jsonHelper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    Path file = Path.of(IO_PATH,"data.json");

    // Запись объекта в JSON
    try {
      JSONObj obj = new JSONObj(100, "Andrew", List.of("first, second, third"));
      objectMapper.writeValue(file.toFile(), obj);
      System.out.println("Данные успешно записаны в файл в формате JSON.");
    } catch (IOException e) {
      System.out.println("Произошла ошибка при записи данных в JSON: " + e.getMessage());
    }

    // Чтение объекта из JSON
    try {
      Person person = objectMapper.readValue(file.toFile(), Person.class);
      System.out.println("Прочитанный объект: " + person);
    } catch (IOException e) {
      System.out.println("Произошла ошибка при чтении данных из JSON: " + e.getMessage());
    }
  }
}
