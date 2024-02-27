package unsafe;

import static unsafe.UnsafeAccess.getUnsafeInstance;

import sun.misc.Unsafe;

// Методы allocateMemory и freeMemory позволяют выделять и освобождать память вне области управления памятью Java.
public class MemoryAllocation {
  public static void main(String[] args) {
    Unsafe unsafe = getUnsafeInstance();
    long size = 1024; // Размер в байтах

    // Выделяем память
    long memoryAddress = unsafe.allocateMemory(size);

    // Используем выделенную память
    unsafe.putInt(memoryAddress, 42);

    // Освобождаем память
    unsafe.freeMemory(memoryAddress);
  }
}
