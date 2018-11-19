import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private WeightedQuickUnionUF wqu;

  private int openSitesCount = 0;

  private final int N;

  private final int FIRST = 0;

  private final int LAST;

  private final boolean[] blocks;

  public Percolation(int n) {
    if (n <= 0) throw new IllegalArgumentException();
    N = n;
    int size = n * n;
    blocks = new boolean[size];
    LAST = size + 1;
    wqu = new WeightedQuickUnionUF(size + 2);
  }

  public void open(int row, int col) {
    validateInput(row, col);
    int index = index(row, col);

    if (!isOpen(index)) {
      blocks[index - 1] = true;
      if (col > 1) doOpen(index, index(row, col - 1));
      if (col < N) doOpen(index, index(row, col + 1));
      if (row > 1) doOpen(index, index(row - 1, col));
      if (row < N) doOpen(index, index(row + 1, col));
      if (row == 1) {
        doOpen(FIRST, index);
      }
      if (wqu.connected(index, FIRST)) {
        for (int i = LAST - 2; i >= LAST - N - 2; i--) {
          if (blocks[i] && wqu.connected(index, i)) {
            doOpen(LAST, i);
          }
        }
      }
      openSitesCount++;
    }
  }

  public boolean isOpen(int row, int col) {
    validateInput(row, col);
    return isOpen(index(row, col));
  }

  public boolean isFull(int row, int col) {
    validateInput(row, col);
    return wqu.connected(FIRST, index(row, col));
  }

  public int numberOfOpenSites() {
    return openSitesCount;
  }

  public boolean percolates() {
    return wqu.connected(FIRST, LAST);
  }

  private boolean isOpen(int index) {
    return blocks[index - 1];
  }

  private void doOpen(int index, int destIndex) {
    if (isOpen(destIndex)) {
      wqu.union(index, destIndex);
    }
  }

  private int index(int row, int col) {
    return (row - 1) * N + col;
  }

  private void validateInput(int row, int col) {
    if (row < 1 || row > N || col < 1 || col > N) {
      throw new IllegalArgumentException();
    }
  }

  public static void main(String[] args) {

  }
}
