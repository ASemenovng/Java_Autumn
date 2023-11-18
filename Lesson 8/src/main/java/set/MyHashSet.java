package set;

import java.util.Iterator;
import java.util.Objects;

    public class MyHashSet<E> implements BaseSet<E> {

        private static final int DEFAULT_CAPACITY = 1 << 4;
        private static final int MAX_CAPACITY = 1 << 30;
        private static final float LOAD_FACTOR = 0.75f;

        private Node<E>[] table;
        private int size;
        private final float loadFactor;
        private int threshold;


        private static int hash(Object key) {
            // your code
            return 0;
        }

        public MyHashSet(float loadFactor) {
            this.loadFactor = loadFactor;
        }

        public MyHashSet() {
            this.loadFactor = LOAD_FACTOR;
        }

        @SuppressWarnings("unchecked")
        private void resize() {
            // your code
        }



        @Override
        public void add(E e) {
            // your code
        }


        @Override
        public void remove(Object o) {
            // your code
        }


        @Override
        public void clear() {
            // your code
        }

        @Override
        public boolean contains(Object o) {
            // your code
            return false;
        }

        @Override
        public boolean isEmpty() {
            // your code
            return false;
        }


        @Override
        public int size() {
            // your code
            return 0;
        }



        @Override
        public boolean equals(Object o) {
            // your code
            return false;
        }



        @Override
        public Iterator<E> iterator() {
            // your code
            // опционально, можно не делать
            return new Iterator<E>() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public E next() {
                    return null;
                }
            };
        }

        @Override
        public String toString() {
            // your code
            return null;
        }


        static class Node<T> {

            final int hash;
            final T key;
            Node<T> next;

            public Node(int hash, T key, Node<T> next) {
                this.hash = hash;
                this.key = key;
                this.next = next;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                Node<?> node = (Node<?>) o;
                return hash == node.hash && Objects.equals(key, node.key) && Objects.equals(
                        next, node.next);
            }

            @Override
            public int hashCode() {
                return Objects.hash(hash, key, next);
            }

            @Override
            public String toString() {
                // your code
                return null;
            }
        }
    }
