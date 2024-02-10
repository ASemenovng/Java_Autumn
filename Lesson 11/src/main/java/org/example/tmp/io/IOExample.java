package org.example.tmp.io;


import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
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
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.example.in_out.JSONObj;

public class IOExample {

  public static final String IO_PATH = "./src/main/java/org/example/tmp/io";

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
    System.out.println("Ваше имя: ");
    String name = scanner.nextLine();
    System.out.println("Hello, " + name);
  }

  // Введем данные с клавиатуры и сохраним их в файл.
  public static void writer() {
    try(FileWriter writer = new FileWriter(IO_PATH + "/result.txt")) {
      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();
      writer.write(input);
    } catch (IOException e) {
      System.out.println("Ошибка при записи: " + e);
    }
  }

  // Прочитаем данные из файла и выведем их на экран.
  public static void readFromFile() {
    try(Scanner scanner = new Scanner(new File(IO_PATH, "/source.txt"))) {
      while (scanner.hasNextLine()) {
        System.out.println(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  // Создадим новый файл и запишем в него данные.
  public static void createAndWrite() {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(IO_PATH + "/new_file.txt"))) {
      writer.write("Пример данных для записи в файл");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // Прочитаем данные из файла в байтовом виде.
  public static void readBytes() {
    try(FileInputStream inputStream = new FileInputStream(IO_PATH + "/binary_data.dat")) {
      int data;
      while ((data = inputStream.read()) != -1) {
        System.out.println(data + " ");
      }
    } catch (IOException e) {
      System.out.println("Ошибка ввода-вывода: " + e);
    }
  }

  // Создадим файл и запишем в него байтовые данные.
  public static void writeBytes() {
    try (FileOutputStream outputStream = new FileOutputStream(IO_PATH + "/binary_data.dat")) {
      byte[] data = {72, 101, 108, 111, 32, 82, 111, 114, 108, 100};
      outputStream.write(data);
    } catch (IOException e) {
      System.out.println("Ошибка записи: " + e);
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
      System.out.println(e);
    }
  }

  // Пример чтения текстового файла с использованием java.nio:
  public static void NIORead() {
    try {
      List<String> lines = Files.readAllLines(Path.of(IO_PATH, "source.txt"));
      for (var s : lines) {
        System.out.println(s);
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  // Копирование файла
  public static void copyFile() {
    Path sourcePath = Path.of(IO_PATH, "source.txt");
    Path targetPath = Path.of(IO_PATH, "target.txt");

    try {
      Files.copy(sourcePath, targetPath);
      System.out.println("Файл скопирован");
    } catch (IOException e) {
      System.out.println("Ошибка при копировании: " + e);
    }
  }

  // Удаление файла
  public static void removeFile() {
    Path fileToDelete = Path.of(IO_PATH, "file_to_delete.txt");

    try {
      Files.delete(fileToDelete);
      System.out.println("Файл удален");
    } catch (IOException e) {
      System.out.println("Ошибка при удалении: " + e);
    }
  }

  // Поиск файлов по расширению
  public static void searchFiles() {
    Path dir = Path.of(IO_PATH);
    String targetExt = ".txt";
    try (DirectoryStream<Path> directory = Files.newDirectoryStream(dir, String.format("*%s", targetExt))) {
      directory.forEach(file -> System.out.println(file.getFileName()));
    } catch (IOException e) {
      System.out.println("Smth went wrong: " + e);
    }
  }

  // Мониторинг файловой системы
  public static void watch() {
    Path dir = Path.of(IO_PATH);
    try(WatchService watchService = FileSystems.getDefault().newWatchService()) {
      dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

      System.out.println("Мониторинг директории " + dir);

      WatchKey key;
      while ((key = watchService.take()) != null) {
        for (WatchEvent<?> event : key.pollEvents()) {
          System.out.println(event.context());
        }
        key.reset();
      }
    } catch (IOException | InterruptedException e) {
      System.out.println("Ошибка мониторинга: " + e);
    }
  }

  // Создание и чтение символических ссылок
  // Пример создания жесткой ссылки в командной строке:
  // ln source.txt hard_link.txt
  // Пример создания символической ссылки в командной строке:
  // ln -s source.txt symbolic_link.txt
  public static void symbolLink() {
    Path sourcePath = Path.of(IO_PATH, "source.txt");
    Path symbolicLink = Path.of(IO_PATH, "symbolic_link.txt");

    try {
      Files.createSymbolicLink(symbolicLink, sourcePath);
      System.out.println("Символическая ссылка создана " + symbolicLink);
      System.out.println("Реальный путь " + Files.readSymbolicLink(symbolicLink));
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  // Атрибуты файла
  public static void attributes() {
    Path file = Path.of(IO_PATH, "source.txt");
    try {
      BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
      System.out.println("creation time: " + attributes.creationTime());
      System.out.println("last access time: " + attributes.lastAccessTime());
      System.out.println("last modified time: " + attributes.lastModifiedTime());
      System.out.println("is dir: " + attributes.isDirectory());
      System.out.println("is symb link: : " + attributes.isSymbolicLink());
      System.out.println("size: : " + attributes.size());
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  // Сравнение содержимого двух файлов
  public static void compareFiles() {
    Path file1 = Path.of(IO_PATH, "source.txt");
    Path file2 = Path.of(IO_PATH, "result.txt");
    try {
      List<String> lines1 = Files.readAllLines(file1);
      List<String> lines2 = Files.readAllLines(file2);

      System.out.println(lines1.equals(lines2) ? " файлы совпадают" : " файлы различаются");
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  // Поиск и замена в файле
  public static void replace() {
    Path path = Path.of(IO_PATH, "source.txt");
    String searchWord = "smth";
    String replaceWord = "something";

    try {
      List<String> lines = Files.readAllLines(path);
      List<String> replaced = lines.stream()
          .map(line -> line.replaceAll(searchWord, replaceWord))
          .toList();
      Files.write(path, replaced);
      System.out.println("Слова " + searchWord + "заменены на " + replaceWord);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  // Архивирование и разархивирование файлов
  public static void zip() {
    Path fileTpCompress = Path.of(IO_PATH, "source.txt");
    Path compressedFile = Path.of(IO_PATH, "source.zip");

    try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(compressedFile))) {
      ZipEntry entry = new ZipEntry(fileTpCompress.getFileName().toString());
      zipOutputStream.putNextEntry(entry);
      byte[] data = Files.readAllBytes(fileTpCompress);
      zipOutputStream.write(data, 0, data.length);
      zipOutputStream.closeEntry();
      System.out.println("Файл заархивирован");
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  // Подсчет слов в текстовом файле
  public static void wordCount() {
    Path file = Path.of(IO_PATH, "source.txt");
    try (Stream<String> words = Files.lines(file)) {
      long count = words
          .flatMap(line -> Stream.of(line.split(" ")))
          .count();
      System.out.println(count);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  // Чтение и запись данных в формате JSON
  public static void jsonHelper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

    Path file = Path.of(IO_PATH, "data.json");

    try {
      JSONObj obj = new JSONObj(10, "Andrew", List.of("first", "second"));
      objectMapper.writeValue(file.toFile(), obj);
      System.out.println("Данные успешно преобразованы");
    } catch (IOException e) {
      System.out.println("Ошибка при записи " + e);
    }

    try {
      JSONObj obj = objectMapper.readValue(file.toFile(), JSONObj.class);
      System.out.println("Данные успешно прочитаны " + obj);
    } catch (IOException e) {
      System.out.println("Ошибка при чтении " + e);
    }
  }
}
