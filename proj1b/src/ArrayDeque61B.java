import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    int nextFirst;
    int nextLast;
    int len = 8;

    public ArrayDeque61B() {
        items = (T[]) new Object[len];
        size = 0;
        nextFirst = len - 1;
        nextLast = 0;
    }

    @Override
    public void addFirst(T x) {
//        if (size == items.length) {
//            resize(items.length * 2);
//        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size += 1;

    }

    @Override
    public void addLast(T x) {
//        if (size = items.length) {
//            resize(items.length * 2);
//        }
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int index = Math.floorMod(nextFirst + 1, items.length);

        for (int i = 0; i < size; i++) {
            returnList.add(items[index]);
            index = Math.floorMod(index + 1, items.length);
        }

        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if (index > len || index < 0) {
            return null;
        }
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
