package deque;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;
    private int size;

    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node i, T j, Node k) {
            prev = i;
            item = j;
            next = k;
        }
    }

    /** Empty List */
    public LinkedListDeque61B() {
        sentinel = new Node(null, null,null );
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newFirstNode = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = newFirstNode;
        sentinel.next = newFirstNode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newLastNode = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = newLastNode;
        sentinel.prev = newLastNode;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel.next;
        while (p != sentinel) {
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        Node p = sentinel.next;
        int count = 0;
        while (p != sentinel) {
            count++;
            p = p.next;
        }
        return count;
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }

        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;

        size--;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }

        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;

        size--;
        return last.item;
    }

    @Override
    public T get(int index) {
        Node p = sentinel.next;
        int count = 0;
        if (index >= 0) {
            while (p != sentinel) {
                if (count == index) {
                    return p.item;
                }
                count++;
                p = p.next;
            }
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return recursiveHelper(sentinel.next, index);
    }

    private T recursiveHelper(Node p, int index) {
        if (p == sentinel) {
            return null;
        }

        if (index == 0) {
            return p.item;
        }

        return recursiveHelper(p.next, index - 1);
    }
}
