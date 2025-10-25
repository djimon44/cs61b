public interface DisjointSets {
    /** connects two items P and Q */
    void connect(int p, int q);

    /** cheks to see if two items are connected */
    boolean isConnected(int p, int q);
}
