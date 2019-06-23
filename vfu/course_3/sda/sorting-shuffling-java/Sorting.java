import java.util.*;

class Sorting {

  public static void main(String... args) {
    arraySort();
  }

  private static void arraySort() {
    Integer[] a1 = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    List<Integer> l1 = Arrays.asList(a1);
    Collections.shuffle(l1, new Random());
    for (int num : l1) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

}
