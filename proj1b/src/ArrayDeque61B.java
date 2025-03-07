import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    int nextFirst;
    int nextLast;
    int len;

    public ArrayDeque61B() {
        this(8);
    }

    public ArrayDeque61B(int initialSize) {
        len = Math.max(initialSize, 8);
        items = (T[]) new Object[len];
        size = 0;
        nextFirst = len - 1;
        nextLast = 0;
    }

    public void resizeUp() {
        loopResizing(items.length * 2);
    }

    public void resizeDown() {
        loopResizing(items.length / 2);
    }

    public void loopResizing(int newSize) {
        T[] newArray = (T[]) new Object[newSize];

        int start = Math.floorMod(nextFirst+1, items.length);
        for (int i=0; i<size; i++) {
            newArray[i] = items[(start+i) % items.length];
        }

        items = newArray;
        nextFirst = newSize-1;
        nextLast = size;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resizeUp();
        }

        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size += 1;

    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resizeUp();
        }

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
    public int getLength() {
        return items.length;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (size > 8 && size < items.length / 4) {
            resizeDown();
        }

        int index  = Math.floorMod(nextFirst+1, items.length);
        T retVal = items[index];

        nextFirst = index;
        size--;
        return retVal;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (size > 8 && size < items.length / 4) {
            resizeDown();
        }

        int index = Math.floorMod(nextLast-1, items.length);
        T retVal = items[index];

        nextLast = index;
        size--;
        return retVal;
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
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
