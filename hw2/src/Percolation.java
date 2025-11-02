import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // Grid variables
    private boolean[][] percGrid; // true(open) or false(closed)
    private int n;

    // Sets variables
    private WeightedQuickUnionUF earth;
    private WeightedQuickUnionUF earthTopOnly;
    private int virtTop;
    private int virtBottom;

    int openSites = 0;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N <= 0");
        n = N;
        percGrid = new boolean[N][N]; // false by default means blocked

        earth = new WeightedQuickUnionUF(N*N + 2);
        earthTopOnly = new WeightedQuickUnionUF(N*N + 1); // Only connected to the top virtual site
        virtTop = N*N; // last-1 index
        virtBottom = N*N+1; // last index
    }

    public void open(int row, int col) {
        // Validity check
        validate(row, col);

        // If open do nothing
        if (isOpen(row, col)) return;

        // Mark as open
        percGrid[row][col] = true;
        openSites++;

        // Check the neighbors and connect
        int index = xyTo1D(row, col);

        // UP
        if (row > 0 && isOpen(row-1, col)) {
            earth.union(index, xyTo1D(row - 1, col));
            earthTopOnly.union(index, xyTo1D(row-1, col));
        }
        // DOWN
        if (row < n-1 && isOpen(row+1, col)) {
            earth.union(index, xyTo1D(row + 1, col));
            earthTopOnly.union(index, xyTo1D(row + 1, col));
        }
        // LEFT
        if (col > 0 && isOpen(row, col-1)) {
            earth.union(index, xyTo1D(row, col - 1));
            earthTopOnly.union(index, xyTo1D(row, col - 1));
        }
        // RIGHT
        if (col < n-1 && isOpen(row, col+1)) {
            earth.union(index, xyTo1D(row, col + 1));
            earthTopOnly.union(index, xyTo1D(row, col + 1));
        }

        // If cell is in the first row
        if (row == 0) {
            earth.union(index, virtTop);
            earthTopOnly.union(index, virtTop);
        }
        // If cell is in the last row
        if (row == n-1) {
            earth.union(index, virtBottom); // only percolation UF
        }
    }

    public boolean isOpen(int row, int col) {
        // Validate and check in the 2D Boolean Array
        validate(row, col);
        return percGrid[row][col];
    }

    public boolean isFull(int row, int col) {
        // True if site is connected to the top
        validate(row, col);
        return isOpen(row, col) && earthTopOnly.connected(xyTo1D(row, col), virtTop);
    }

    public int numberOfOpenSites() {
        // Simply return counter
        return openSites;
    }

    public boolean percolates() {
        // If virtTop and virtBottom are connected - return True
        return earth.connected(virtTop, virtBottom);
    }


    // Convert grid to index
    public int xyTo1D(int row, int col) {
        return (row * n) + col;
    }

    // Validity of correct indexing
    private void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n)
            throw new java.lang.IndexOutOfBoundsException("Invalid Indexing");
    }
}
