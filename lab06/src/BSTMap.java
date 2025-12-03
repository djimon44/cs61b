import com.sun.source.doctree.EntityTree;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    int size = 0;

    /** Inserts the key-value pair of KEY and VALUE into this BST
        Replaces the previous value if key already exists */
    @Override
    public void put(K key, V value) {
        if (bst == null) {
            bst = new Entry(key, value);
            size++;
        } else {
            Entry existedBefore = bst.get(key);
            bst = bst.put(key, value);
            if (existedBefore == null) {
                size++;
            }
        }
    }

    /** Return the value corresponding to KEY or null if no such value exists */
    @Override
    public V get(K key) {
        if (bst == null) {
            return null;
        }

        Entry lookup = bst.get(key);
        if (lookup == null) { return null; }
        return lookup.val;
    }

    /** Return true if and only if this BST contains key as
     * the key of some k-v pair
     */
    @Override
    public boolean containsKey(K key) {
        if (bst == null) {
            return false;
        }

        return bst.get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    /** Removes all the mappings from this map */
    @Override
    public void clear() {
        size = 0;
        bst = null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new LinkedHashSet<>();
        collectKeys(bst, keys);
        return keys;
    }

    /** Helper to collect all keys using in order(Left-Node-Right) traversal */
    private void collectKeys(Entry node, Set<K> keys) {
        if (node == null){return;}

        collectKeys(node.left, keys); // Traverse left subtree
        keys.add(node.key); // Add current keys
        collectKeys(node.right, keys); // Traverse right subtree
    }


    @Override
    public V remove(K key) {
        if (bst == null) {
            return null;
        }

        Entry found = bst.get(key);
        if (found == null) {
            return null; // Key not found
        }

        V removedValue = found.val;
        bst = remove(bst, key);
        size--;
        return removedValue;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter();
    }

    /** Keys and values are stored in a bst of Entry objects
     * This variable stores the root pair in this bst */
    private Entry bst;

    /** Represents one node in BST that stores the key-value pair */
    private class Entry {

        Entry(K k, V v) {
            key = k;
            val = v;
            right = null;
            left = null;
        }

        /** Return the entry of this BST whose key is equal to the
         * key, null otherwise */
        Entry get(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }

            int cmp = k.compareTo(key);

            if (cmp < 0) { return (left != null) ? left.get(k) : null; }
            else { return (right != null) ? right.get(k) : null; }
        }

        /** Puts the new Entry node in the BST, if key already exists - changes the value */
        Entry put(K k, V v) {
            if (k.equals(key)) {
                val = v; // update existing value
                return this;
            }

            int cmp = k.compareTo(key);

            if (cmp < 0) {
                // Left subtree
                if (left == null) {
                    left = new Entry(k, v); // If no left child create new node
                } else {
                    left = left.put(k, v); // Recursion
                }
            } else {
                // Right subtree
                if (right == null) {
                    right = new Entry(k, v); // If no right child create new node
                } else {
                    right = right.put(k, v); // Recursion
                }
            }

            return this;
        }


        K key; // Stores key
        V val; // Stores value
        Entry right; // Stores right node
        Entry left; // Stores left node
    }

    /** An iterator that iterates over the keys of BST in Left-Node-Right Traverse order */
    private class BSTMapIter implements Iterator<K> {
        private Stack<Entry> stack; // Stack for LNR traversal

        /** Create a new Iter, using stack and pushing all left children onto the stack*/
        public BSTMapIter() {
            stack = new Stack<>();
            pushLeft(bst);
        }

        /** Push all left children to the stack*/
        private void pushLeft(Entry node) {
            while (node != null) {
                stack.push(node);
                node = node.left; // Traverse
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** Implements Left-Node-Right Traverse */
        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Entry node = stack.pop();

            if (node.right != null) {
                pushLeft(node.right);
            }

            return node.key;
        }
    }

    /** Recursive helper method for deletion */
    public Entry remove(Entry node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            // Key is in left subtree
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            // Found the node - 3 cases:

            // Case 1: No children (leaf)
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2a: Only right child
            if (node.left == null) {
                return node.right;
            }

            // Case 2b: Only left child
            if (node.right == null) {
                return node.left;
            }

            // Case 3: Two children -> Hibbard deeltion
            // Replace with minimum node from the right subtree
            Entry successor = findMin(node.right);
            node.key = successor.key;
            node.val = successor.val;
            node.right = remove(node.right, successor.key);
        }

        return node;
    }

    /** Find minimum(leftmost) node in the subtreee */
    private Entry findMin(Entry node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
