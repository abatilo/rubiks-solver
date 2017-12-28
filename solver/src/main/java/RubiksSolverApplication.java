import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class RubiksSolverApplication {
  private static final ArrayDeque<CubeState> searchGraph = new ArrayDeque<>();
  private static final Set<CubeState> seenBefore = new HashSet<>();

  public static void main(String[] args) throws InterruptedException {
    List<char[]> arr = new ArrayList<>();
    arr.add(cube);
    char[] shuffled = shuffle(SOLUTION, 16);

    printCube(shuffled);
    searchGraph.add(new CubeState(shuffled, 0, ""));

    try {
      Set<CubeState> solvableStates = new HashSet<>();
      Scanner scanner = new Scanner(new FileInputStream("solvable.txt"));
      while (scanner.hasNextLine()) {
        solvableStates.add(new CubeState(scanner.nextLine().toCharArray(), 0, ""));
      }

      boolean solvable = true;
      while (!searchGraph.isEmpty()) {
        CubeState nextStep = searchGraph.remove();

        if (cubeEquals(SOLUTION, nextStep.getCube())) {
          System.out.println(nextStep.getPath());
          break;
        }

        if (solvable && solvableStates.contains(nextStep)) {
          solvable = false;
          searchGraph.clear();
          searchGraph.add(new CubeState(nextStep.getCube(), 0, nextStep.getPath()));
          continue;
        }

        // Avoid blowing the heap
        if (nextStep.getDepth() > 5) {
          continue;
        }

        if (!seenBefore.contains(nextStep)) {
          seenBefore.add(nextStep);
          searchGraph.add(new CubeState(U_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "U' "));
          searchGraph.add(new CubeState(D_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "D' "));
          searchGraph.add(new CubeState(F_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "F' "));
          searchGraph.add(new CubeState(B_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "B' "));
          searchGraph.add(new CubeState(L_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "L' "));
          searchGraph.add(new CubeState(R_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "R' "));
          searchGraph.add(new CubeState(U(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "U "));
          searchGraph.add(new CubeState(D(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "D "));
          searchGraph.add(new CubeState(F(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "F "));
          searchGraph.add(new CubeState(B(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "B "));
          searchGraph.add(new CubeState(L(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "L "));
          searchGraph.add(new CubeState(R(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "R "));
        }
      }

    } catch (IOException e) {
      // Ignored
    }
  }

  private static class CubeState {
    private final char[] cube;
    private final int depth;
    private final String path;
    public CubeState(char[] cube, int depth, String path) {
      this.cube = cube;
      this.depth = depth;
      this.path = path;
    }

    public char[] getCube() {
      return this.cube;
    }

    public int getDepth() {
      return this.depth;
    }

    public String getPath() {
      return this.path;
    }

    @Override
    public int hashCode() {
      int hashCode = 0;
      for (int i = 0; i < this.cube.length; ++i) {
        hashCode = 37 * hashCode + (int) this.cube[i];
      }
      return hashCode;
    }

    @Override
    public boolean equals(Object that) {
      if (that instanceof CubeState) {
        return cubeEquals(this.cube, ((CubeState) that).cube);
      }
      return false;
    }
  }

  private static char[] cube = new char[] {
    'r', 'r', 'r',
    'r', 'r', 'r',
    'r', 'r', 'r',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'o', 'o', 'o',
    'o', 'o', 'o',
    'o', 'o', 'o'
  };

  //                       0      1       2
  //                       3      4       5
  //                       6      7       8
  //  9      10     11     12     13      14      15     16     17     18     19     20
  //  21     22     23     24     25      26      27     28     29     30     31     32
  //  33     34     35     36     37      38      39     40     41     42     43     44
  //                       45     46      47
  //                       48     49      50
  //                       51     52      53

  private static char[] correctness = new char[] {
    'o', 'y', 'w',
    'g', 'r', 'o',
    'g', 'g', 'w',
    'b', 'o', 'o', 'y', 'w', 'r', 'b', 'b', 'o', 'g', 'o', 'w',
    'r', 'b', 'g', 'r', 'w', 'w', 'b', 'g', 'r', 'w', 'y', 'y',
    'y', 'g', 'o', 'y', 'b', 'r', 'g', 'o', 'r', 'y', 'b', 'b',
    'b', 'y', 'w',
    'y', 'o', 'w',
    'r', 'r', 'g'
  };

  private static char[] U(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[9];
    char outerEdgesTemp2 = copy[10];
    char outerEdgesTemp3 = copy[11];
    for (int i = 9; i < 18; ++i) {
      copy[i] = copy[i + 3];
    }
    copy[18] = outerEdgesTemp1;
    copy[19] = outerEdgesTemp2;
    copy[20] = outerEdgesTemp3;

    char innerCornerTemp = copy[0];
    copy[0] = copy[6];
    copy[6] = copy[8];
    copy[8] = copy[2];
    copy[2] = innerCornerTemp;

    char innerEdgeTemp = copy[1];
    copy[1] = copy[3];
    copy[3] = copy[7];
    copy[7] = copy[5];
    copy[5] = innerEdgeTemp;

    return copy;
  }

  private static char[] U_PRIME(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[18];
    char outerEdgesTemp2 = copy[19];
    char outerEdgesTemp3 = copy[20];
    for (int i = 20; i >= 12; --i) {
      copy[i] = copy[i - 3];
    }
    copy[9] = outerEdgesTemp1;
    copy[10] = outerEdgesTemp2;
    copy[11] = outerEdgesTemp3;

    char innerCornerTemp = copy[0];
    copy[0] = copy[2];
    copy[2] = copy[8];
    copy[8] = copy[6];
    copy[6] = innerCornerTemp;

    char innerEdgeTemp = copy[1];
    copy[1] = copy[5];
    copy[5] = copy[7];
    copy[7] = copy[3];
    copy[3] = innerEdgeTemp;

    return copy;
  }

  private static char[] D(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[42];
    char outerEdgesTemp2 = copy[43];
    char outerEdgesTemp3 = copy[44];
    for (int i = 44; i >= 33; --i) {
      copy[i] = copy[i - 3];
    }
    copy[33] = outerEdgesTemp1;
    copy[34] = outerEdgesTemp2;
    copy[35] = outerEdgesTemp3;

    char innerCornerTemp = copy[45];
    copy[45] = copy[51];
    copy[51] = copy[53];
    copy[53] = copy[47];
    copy[47] = innerCornerTemp;

    char innerEdgeTemp = copy[46];
    copy[46] = copy[48];
    copy[48] = copy[52];
    copy[52] = copy[50];
    copy[50] = innerEdgeTemp;

    return copy;
  }

  private static char[] D_PRIME(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[33];
    char outerEdgesTemp2 = copy[34];
    char outerEdgesTemp3 = copy[35];
    for (int i = 33; i < 42; ++i) {
      copy[i] = copy[i + 3];
    }
    copy[42] = outerEdgesTemp1;
    copy[43] = outerEdgesTemp2;
    copy[44] = outerEdgesTemp3;

    char innerCornerTemp = copy[45];
    copy[45] = copy[47];
    copy[47] = copy[53];
    copy[53] = copy[51];
    copy[51] = innerCornerTemp;

    char innerEdgeTemp = copy[46];
    copy[46] = copy[50];
    copy[50] = copy[52];
    copy[52] = copy[48];
    copy[48] = innerEdgeTemp;

    return copy;
  }

  private static char[] F(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[6];
    char outerEdgesTemp2 = copy[7];
    char outerEdgesTemp3 = copy[8];
    copy[8] = copy[11];
    copy[7] = copy[23];
    copy[6] = copy[35];
    copy[11] = copy[45];
    copy[23] = copy[46];
    copy[35] = copy[47];
    copy[45] = copy[39];
    copy[46] = copy[27];
    copy[47] = copy[15];
    copy[15] = outerEdgesTemp1;
    copy[27] = outerEdgesTemp2;
    copy[39] = outerEdgesTemp3;

    char innerCornerTemp = copy[14];
    copy[14] = copy[12];
    copy[12] = copy[36];
    copy[36] = copy[38];
    copy[38] = innerCornerTemp;

    char innerEdgeTemp = copy[13];
    copy[13] = copy[24];
    copy[24] = copy[37];
    copy[37] = copy[26];
    copy[26] = innerEdgeTemp;

    return copy;
  }

  private static char[] F_PRIME(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[6];
    char outerEdgesTemp2 = copy[7];
    char outerEdgesTemp3 = copy[8];
    copy[6] = copy[15];
    copy[7] = copy[27];
    copy[8] = copy[39];
    copy[15] = copy[47];
    copy[27] = copy[46];
    copy[39] = copy[45];
    copy[45] = copy[11];
    copy[46] = copy[23];
    copy[47] = copy[35];
    copy[11] = outerEdgesTemp3;
    copy[23] = outerEdgesTemp2;
    copy[35] = outerEdgesTemp1;

    char innerCornerTemp = copy[14];
    copy[14] = copy[38];
    copy[38] = copy[36];
    copy[36] = copy[12];
    copy[12] = innerCornerTemp;

    char innerEdgeTemp = copy[13];
    copy[13] = copy[26];
    copy[26] = copy[37];
    copy[37] = copy[24];
    copy[24] = innerEdgeTemp;
    return copy;
  }

  private static char[] B(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[0];
    char outerEdgesTemp2 = copy[1];
    char outerEdgesTemp3 = copy[2];
    copy[0] = copy[17];
    copy[17] = copy[53];
    copy[53] = copy[33];
    copy[33] = outerEdgesTemp1;
    copy[1] = copy[29];
    copy[29] = copy[52];
    copy[52] = copy[21];
    copy[21] = outerEdgesTemp2;
    copy[2] = copy[41];
    copy[41] = copy[51];
    copy[51] = copy[9];
    copy[9] = outerEdgesTemp3;

    char innerCornerTemp = copy[18];
    copy[18] = copy[42];
    copy[42] = copy[44];
    copy[44] = copy[20];
    copy[20] = innerCornerTemp;

    char innerEdgeTemp = copy[19];
    copy[19] = copy[30];
    copy[30] = copy[43];
    copy[43] = copy[32];
    copy[32] = innerEdgeTemp;

    return copy;
  }

  private static char[] B_PRIME(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[0];
    char outerEdgesTemp2 = copy[1];
    char outerEdgesTemp3 = copy[2];
    copy[0] = copy[33];
    copy[33] = copy[53];
    copy[53] = copy[17];
    copy[17] = outerEdgesTemp1;
    copy[1] = copy[21];
    copy[21] = copy[52];
    copy[52] = copy[29];
    copy[29] = outerEdgesTemp2;
    copy[2] = copy[9];
    copy[9] = copy[51];
    copy[51] = copy[41];
    copy[41] = outerEdgesTemp3;

    char innerCornerTemp = copy[18];
    copy[18] = copy[20];
    copy[20] = copy[44];
    copy[44] = copy[42];
    copy[42] = innerCornerTemp;

    char innerEdgeTemp = copy[19];
    copy[19] = copy[32];
    copy[32] = copy[43];
    copy[43] = copy[30];
    copy[30] = innerEdgeTemp;

    return copy;
  }

  private static char[] L(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[0];
    char outerEdgesTemp2 = copy[3];
    char outerEdgesTemp3 = copy[6];
    copy[0] = copy[44];
    copy[44] = copy[45];
    copy[45] = copy[12];
    copy[12] = outerEdgesTemp1;
    copy[3] = copy[32];
    copy[32] = copy[48];
    copy[48] = copy[24];
    copy[24] = outerEdgesTemp2;
    copy[6] = copy[20];
    copy[20] = copy[51];
    copy[51] = copy[36];
    copy[36] = outerEdgesTemp3;

    char innerCornerTemp = copy[9];
    copy[9] = copy[33];
    copy[33] = copy[35];
    copy[35] = copy[11];
    copy[11] = innerCornerTemp;

    char innerEdgeTemp = copy[10];
    copy[10] = copy[21];
    copy[21] = copy[34];
    copy[34] = copy[23];
    copy[23] = innerEdgeTemp;

    return copy;
  }

  private static char[] L_PRIME(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[0];
    char outerEdgesTemp2 = copy[3];
    char outerEdgesTemp3 = copy[6];
    copy[0] = copy[12];
    copy[12] = copy[45];
    copy[45] = copy[44];
    copy[44] = outerEdgesTemp1;
    copy[3] = copy[24];
    copy[24] = copy[48];
    copy[48] = copy[32];
    copy[32] = outerEdgesTemp2;
    copy[6] = copy[36];
    copy[36] = copy[51];
    copy[51] = copy[20];
    copy[20] = outerEdgesTemp3;

    char innerCornerTemp = copy[9];
    copy[9] = copy[11];
    copy[11] = copy[35];
    copy[35] = copy[33];
    copy[33] = innerCornerTemp;

    char innerEdgeTemp = copy[10];
    copy[10] = copy[23];
    copy[23] = copy[34];
    copy[34] = copy[21];
    copy[21] = innerEdgeTemp;

    return copy;
  }

  private static char[] R(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[2];
    char outerEdgesTemp2 = copy[5];
    char outerEdgesTemp3 = copy[8];
    copy[2] = copy[14];
    copy[14] = copy[47];
    copy[47] = copy[42];
    copy[42] = outerEdgesTemp1;
    copy[5] = copy[26];
    copy[26] = copy[50];
    copy[50] = copy[30];
    copy[30] = outerEdgesTemp2;
    copy[8] = copy[38];
    copy[38] = copy[53];
    copy[53] = copy[18];
    copy[18] = outerEdgesTemp3;

    char innerCornerTemp = copy[15];
    copy[15] = copy[39];
    copy[39] = copy[41];
    copy[41] = copy[17];
    copy[17] = innerCornerTemp;

    char innerEdgeTemp = copy[16];
    copy[16] = copy[27];
    copy[27] = copy[40];
    copy[40] = copy[29];
    copy[29] = innerEdgeTemp;

    return copy;
  }

  private static char[] R_PRIME(char[] cube) {
    char[] copy = copyOf(cube);

    char outerEdgesTemp1 = copy[2];
    char outerEdgesTemp2 = copy[5];
    char outerEdgesTemp3 = copy[8];
    copy[2] = copy[42];
    copy[42] = copy[47];
    copy[47] = copy[14];
    copy[14] = outerEdgesTemp1;
    copy[5] = copy[30];
    copy[30] = copy[50];
    copy[50] = copy[26];
    copy[26] = outerEdgesTemp2;
    copy[8] = copy[18];
    copy[18] = copy[53];
    copy[53] = copy[38];
    copy[38] = outerEdgesTemp3;

    char innerCornerTemp = copy[15];
    copy[15] = copy[17];
    copy[17] = copy[41];
    copy[41] = copy[39];
    copy[39] = innerCornerTemp;

    char innerEdgeTemp = copy[16];
    copy[16] = copy[29];
    copy[29] = copy[40];
    copy[40] = copy[27];
    copy[27] = innerEdgeTemp;

    return copy;
  }

  private static char[] copyOf(char[] cube) {
    char[] copy = new char[cube.length];
    System.arraycopy(cube, 0, copy, 0, cube.length);
    return copy;
  }

  private static final char[] SOLUTION = new char[] {
    'r', 'r', 'r',
    'r', 'r', 'r',
    'r', 'r', 'r',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'o', 'o', 'o',
    'o', 'o', 'o',
    'o', 'o', 'o'
  };

  private static void printCube(char[] cube) {
    for (int i = 0; i < 3; ++i) {
      System.out.print("   ");
      for (int j = 3 * i; j < 3 * i + 3; ++j) {
        System.out.print(cube[j]);
      }
      System.out.println();
    }
    for (int i = 9; i < 21; ++i) {
      System.out.print(cube[i]);
    }
    System.out.println();
    for (int i = 21; i < 33; ++i) {
      System.out.print(cube[i]);
    }
    System.out.println();
    for (int i = 33; i < 45; ++i) {
      System.out.print(cube[i]);
    }
    System.out.println();
    System.out.print("   ");
    for (int i = 45; i < 48; ++i) {
      System.out.print(cube[i]);
    }
    System.out.println();
    System.out.print("   ");
    for (int i = 48; i < 51; ++i) {
      System.out.print(cube[i]);
    }
    System.out.println();
    System.out.print("   ");
    for (int i = 51; i < 54; ++i) {
      System.out.print(cube[i]);
    }
    System.out.println();
    System.out.println();
  }

  private static void printCubeFlat(char[] cube) {
    for (int i = 0; i < cube.length; ++i) {
      System.out.print(cube[i]);
    }
    System.out.println();
  }

  private static boolean cubeEquals(char[] cube1, char[] cube2) {
    return Arrays.equals(cube1, cube2);
  }

  private static char[] shuffle(char[] startCube, int steps) {
    char[] copy = copyOf(startCube);
    List<Function<char[], char[]>> operations = new ArrayList<>();

    operations.add((cube) -> { return U(cube); });
    operations.add((cube) -> { return U_PRIME(cube); });
    operations.add((cube) -> { return D(cube); });
    operations.add((cube) -> { return D_PRIME(cube); });
    operations.add((cube) -> { return F(cube); });
    operations.add((cube) -> { return F_PRIME(cube); });
    operations.add((cube) -> { return B(cube); });
    operations.add((cube) -> { return B_PRIME(cube); });
    operations.add((cube) -> { return L(cube); });
    operations.add((cube) -> { return L_PRIME(cube); });
    operations.add((cube) -> { return R(cube); });
    operations.add((cube) -> { return R_PRIME(cube); });

    for (int i = 0; i < steps; ++i) {
      int idx = ThreadLocalRandom.current().nextInt(0, 12);
      copy = operations.get(idx).apply(copy);
    }
    return copy;
  }
}
