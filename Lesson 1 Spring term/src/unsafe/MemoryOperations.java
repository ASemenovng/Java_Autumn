package unsafe;

import static unsafe.UnsafeAccess.getUnsafeInstance;

import sun.misc.Unsafe;

// Методы put и get позволяют выполнять операции чтения и записи данных по указанному адресу памяти.
public class MemoryOperations {
  public static void main(String[] args) {
    Unsafe unsafe = getUnsafeInstance();

    long memoryAddress = unsafe.allocateMemory(8);

    // Запись значения в память
    unsafe.putLong(memoryAddress, 1234567890L);

    // Чтение значения из памяти
    long value = unsafe.getLong(memoryAddress);

    System.out.println("Значение из памяти: " + value);

    unsafe.freeMemory(memoryAddress);
  }
}