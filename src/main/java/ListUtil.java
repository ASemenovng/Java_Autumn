import java.util.AbstractList;
import java.util.List;

public class ListUtil {

    static List<Integer> rangeList(int from, int to) {
        return new AbstractList<>() {
            @Override
            public Integer get(int index) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public int indexOf(Object o) {
                return super.indexOf(o);
            }

            @Override
            public int lastIndexOf(Object o) {
                return super.lastIndexOf(o);
            }

            @Override
            public boolean contains(Object o) {
                return super.contains(o);
            }
        };
    }

    public static List<Integer> medianSort(List<Integer> list) {
        return null;
    }

}
