public interface BaseMap<K, V> {

  int size();

  V get(Object key);

  boolean containsKey(Object key);

  void put(K key, V value);

  V remove(Object key);

  boolean containsValue(Object value);
}
