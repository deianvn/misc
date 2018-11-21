import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private double mean;

  private double stddev;

  private double confidenceLo;

  private double confidenceHi;

  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }
    double data[] = new double[trials];
    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);
      int count = 0;
      while (!percolation.percolates()) {
        int row = StdRandom.uniform(n) + 1;
        int col = StdRandom.uniform(n) + 1;
        if (!percolation.isOpen(row, col)) {
          percolation.open(row, col);
          ++count;
        }
      }
      data[i] = (double) count / (double) (n * n);
    }
    mean = StdStats.mean(data);
    if (trials != 1) {
      stddev = StdStats.stddev(data);
      double confidenceOffset = (1.96d * stddev) / Math.sqrt(trials);
      confidenceLo = mean - confidenceOffset;
      confidenceHi = mean + confidenceOffset;
    } else {
      stddev = Double.NaN;
      confidenceLo = Double.NaN;
      confidenceHi = Double.NaN;
    }
  }

  public double mean() {
    return mean;
  }

  public double stddev() {
    return stddev;
  }

  public double confidenceLo() {
    return confidenceLo;
  }

  public double confidenceHi() {
    return confidenceHi;
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException();
    }
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n, trials);
    System.out.println("mean                    = " + stats.mean());
    System.out.println("stddev                  = " + stats.stddev());
    System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
  }

}
