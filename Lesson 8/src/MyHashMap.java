import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> implements BaseMap<K, V> {

  // The default initial capacity - MUST be a power of two.
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  // The maximum capacity, used if a higher value is implicitly specified by
  // either of the constructors with arguments. Here, we define capacity to be
  // the number of buckets in the hash table.
  private static final int MAXIMUM_CAPACITY = 1 << 30;

  // The load factor used when not specified in constructor.
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;

  // The load factor for the hash table.
  private final float loadFactor;

  // The table, resized as necessary. Length MUST always be a power of two.
  // The Entry class is really a primitive linked list; each Entry object has
  // a pointer to the next item of the linked list, or null if that Entry
  // corresponds to the "end" of the linked list. This approach uses slightly
  // less memory, and is in fact the technique that the pre-Java 8 JDK uses.
  // The size of the table is the number of buckets.
  private Entry<K, V>[] table;

  // The number of key-value mappings contained in this map. Note that this is
  // different from the table capacity.
  private int size;

  // The next size value at which to resize (capacity * load factor).
  private int threshold;

  /**
   * Constructs an empty HashMap with the specified initial capacity and load
   * factor.
   *
   * @param initialCapacity the initial capacity
   * @param loadFactor      the load factor
   * @throws IllegalArgumentException if the initial capacity is non-positive, or
   *                                  the load factor is non-positive or NaN
   */
  @SuppressWarnings("unchecked")
  public MyHashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
    }
    if (initialCapacity > MAXIMUM_CAPACITY) {
      initialCapacity = MAXIMUM_CAPACITY;
    }
    if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
      throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
    }

    // Find a power of 2 >= initialCapacity
    int capacity = 1;
    while (capacity < initialCapacity) {
      capacity <<= 1;
    }

    this.loadFactor = loadFactor;
    this.threshold = (int) (capacity * loadFactor);
    this.table = (Entry<K, V>[]) new Entry[capacity];
  }

  /**
   * Constructs an empty HashMap with the specified initial capacity and the
   * default load factor (0.75).
   *
   * @param initialCapacity the initial capacity.
   * @throws IllegalArgumentException if the initial capacity is non-positive.
   */
  public MyHashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
  }

  /**
   * Constructs an empty HashMap with the default initial capacity (16) and the
   * default load factor (0.75).
   */
  public MyHashMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  /**
   * Applies a supplemental hash function to a given hashCode, which defends
   * against poor quality hash functions. This is critical because HashMap uses
   * power-of-two length hash tables that otherwise encounter collisions for
   * hashCodes that do not differ in lower bits. Note: Null keys always map to
   * hash 0, thus index 0.
   * <p>
   * Additionally, this method truncates the hash to be a valid bucket based on
   * the length parameter, which represents the number of hash table buckets.
   */
  static int hash(int h, int length) {
    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
    h ^= (h >>> 20) ^ (h >>> 12);
    h ^= (h >>> 7) ^ (h >>> 4);
    return h & (length - 1);
  }


  @Override
  public int size() {
    return size;
  }

  @Override
  public V get(Object key) {
    if (key == null) {
      if (table[0] == null) {
        return null;
      } else {
        Entry<K, V> tE = table[0];
        while (tE.key != null) {
          if (tE.next == null) {
            return null;
          } else {
            tE = tE.next;
          }
        }
        return tE.value;
      }
    } else {
      int hashKey = hash(key.hashCode(), table.length);
      if (table[hashKey] == null) {
        return null;
      } else {
        Entry<K, V> tE = table[hashKey];
        while (tE.key == null || !(tE.key.equals(key))) {
          if (tE.next == null) {
            return null;
          } else {
            tE = tE.next;
          }
        }
        return tE.value;
      }
    }

  }

  @Override
  public boolean containsKey(Object key) {
    if (key == null) {
      if (table[0] == null) {
        return false;
      } else {
        Entry<K, V> tE = table[0];
        while (tE.key != null) {
          if (tE.next == null) {
            return false;
          } else {
            tE = tE.next;
          }
        }
        return true;
      }
    } else {
      Entry<K, V> tE = table[hash(key.hashCode(), table.length)];
      if (tE == null) {
        return false;
      }
      while (tE.key == null || !(tE.key.equals(key))) {
        if (tE.next == null) {
          return false;
        } else {
          tE = tE.next;
        }
      }
      return true;
    }
  }

  @Override
  public void put(K key, V value) {
    // 1. Hash the key.
    // 2. Walk the appropriate bucket.
    // 3. If you find a matching key, replace its value with value.
    // Otherwise, make a new entry and insert it at the front of the bucket.
    // 4. Don't forget to resize the array and update the size variable as
    // necessary. Remember, to resize if this call to put causes the new size
    // to be greater than or equal to the threshold.

    // returns the previous value associated with key, or null if
    // there
    // was no mapping for key. (A null return can also indicate that the map
    // previously
    // associated null with key).

    // resize if necessary
    if (size >= this.threshold) {
      resize(2 * table.length);
    }
    if (key == null) {
      if (table[0] == null) {
        Entry<K, V> newE = new Entry<>(null, value, null);
        table[0] = newE;
        size++;
      } else {
        Entry<K, V> e = table[0];
        while (e.key != null) {
          if (e.next == null) {
            Entry<K, V> newE = new Entry<>(null, value, table[0]);
            table[0] = newE;
            size++;
            return; // no previous mapping for key so should return null
          } else {
            e = e.next;
          }
        }
        e.value = value;
      }
    } else {
      int hashKey = hash(key.hashCode(), table.length);
      if (table[hashKey] == null) {
        Entry<K, V> newE = new Entry<>(key, value, null);
        table[hashKey] = newE;
        size++;
      } else {
        Entry<K, V> e = table[hashKey];
        while (e.key == null || !(e.key.equals(key))) {
          if (e.next == null) {
            Entry<K, V> newE = new Entry<>(key, value, table[hashKey]);
            table[hashKey] = newE;
            size++;
            return; // no previous mapping for key so should return null
          } else {
            e = e.next;
          }
        }
        e.value = value;
      }
    }
  }

  /**
   * Rehashes the contents of this map into a new array with a larger capacity.
   * This method should be called automatically when the number of keys in this
   * map reaches its threshold.
   * <p>
   * If current capacity is MAXIMUM_CAPACITY, this method should not resize the
   * map, but instead set threshold to Integer.MAX_VALUE. This has the effect of
   * preventing future calls.
   *
   * @param newCapacity the new capacity, MUST be a power of two; must be greater
   *                    than current capacity unless current capacity is
   *                    MAXIMUM_CAPACITY (in which case value is irrelevant).
   *                    However, there is no need to invoke an exception if an
   *                    invalid capacity is passed in; since this is a helper
   *                    method only used by your implementation internally, you
   *                    can guarantee that invalid capacities are not passed in.
   */
  void resize(int newCapacity) {
    // 1. Save the old hash table.
    // 2. Instantiate a new hash table (unless, of course, the current
    // capacity is MAXIMUM_CAPACITY).
    // 3. Re-hash the old table into it. That is, re-hash all the keys as if you
    // were
    // reinserting them, in order from index 0 to the end, from the head of the
    // linked
    // list to its end.
    // 4. Set the new table threshold.

    // NOTE: You do not need to worry about resizing down.
    if (table.length == MAXIMUM_CAPACITY) {
      threshold = Integer.MAX_VALUE;
    } else {
      Entry<K, V>[] copyOfTable = Arrays.copyOf(table, table.length);
      @SuppressWarnings("unchecked")
      Entry<K, V>[] newHashTable = (Entry<K, V>[]) new Entry[newCapacity];
      table = newHashTable;
      size = 0;
      for (int i = 0; i < copyOfTable.length; i++) {
        if (copyOfTable[i] != null) {
          Entry<K, V> e = copyOfTable[i];
          while (e != null) {
            put(e.key, e.value);
            e = e.next;
          }
        }
      }
      threshold = (int) (newCapacity * loadFactor);
    }
  }

  @Override
  public V remove(Object key) {
    if (key == null) {
      if (table[0] == null) {
        return null;
      } else {
        Entry<K, V> tE = table[0];
        if (table[0].key == null) { // if the head of the ll is null
          Entry<K, V> keyToRemove = table[0];
          V vR = keyToRemove.value;
          table[0] = keyToRemove.next;
          size--;
          return vR;
        } else {
          Entry<K, V> prev = null;
          while (tE.key != null) {
            if (tE.next == null) {
              return null;
            } else {
              prev = tE;
              tE = tE.next;
            }
          }
          V vR = tE.value;
          prev.next = tE.next;
          size--;
          return vR;
        }
      }
    } else {
      int hashKey = hash(key.hashCode(), table.length);
      if (table[hashKey] == null) {
        return null;
      } else {
        Entry<K, V> tE = table[hashKey];
        if (table[hashKey].key.equals(key)) { // if the head of the ll is null
          Entry<K, V> keyToRemove = table[hashKey];
          V vR = keyToRemove.value;
          table[hashKey] = keyToRemove.next;
          size--;
          return vR;
        } else {
          Entry<K, V> prev = null;
          while (tE.key == null || !(tE.key.equals(key))) {
            if (tE.next == null) {
              return null;
            } else {
              prev = tE;
              tE = tE.next;
            }
          }
          V vR = tE.value;
          Objects.requireNonNull(prev).next = tE.next;
          size--;
          return vR;
        }
      }
    }
  }

  @Override
  public boolean containsValue(Object value) {
    if (value == null) {
      for (var e : table) {
        while (e != null) {
          if (e.value == null) {
            return true;
          } else {
            e = e.next;
          }
        }
      }
    } else {
      for (var e : table) {
        while (e != null) {
          if (e.value == null) {
            e = e.next;
          } else if (e.value.equals(value)) {
            return true;
          } else {
            e = e.next;
          }
        }
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return "MyHashMap{" +
        "table=" + Arrays.toString(table) +
        '}';
  }

  static class Entry<K, V> {
    private final K key;
    private V value;
    Entry<K, V> next;

    Entry(K k, V v, Entry<K, V> n) {
      value = v;
      key = k;
      next = n;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public V setValue(V value) {
      V oldValue = this.value;
      this.value = value;
      return oldValue;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Entry<?, ?> entry = (Entry<?, ?>) o;
      return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(key, value);
    }

    @Override
    public String toString() {
      return "Entry{" + "key=" + key + ", value=" + value + '}';
    }
  }
}