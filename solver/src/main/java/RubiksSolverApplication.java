public class RubiksSolverApplication {
  public static void main(String[] args) {
    printCube(cube);
    printCube(U(cube));
    printCube(U_PRIME(U(cube)));
  }

  private static char[] cube = new char[] {
    ' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    ' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ',
  };

  private static final char[] SOLUTION = new char[] {
    ' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    ' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ',
  };

  private static void printCube(char[] cube) {
    for (int j = 0; j < 9; ++j) {
      for (int i = 0; i < 12; ++i) {
        System.out.print(cube[12 * j + i]);
      }
      System.out.println();
    }
    System.out.println();
  }

  private static char[] U(char[] cube) {
    char[] copy = copyToNew(cube);
    return copy;
  }

  private static char[] U_PRIME(char[] cube) {
    char[] copy = copyToNew(cube);
    return copy;
  }

  private static char[] D(char[] cube) {
    char[] copy = copyToNew(cube);
    return copy;
  }

  private static char[] D_PRIME(char[] cube) {
    char[] copy = copyToNew(cube);
    return copy;
  }

  private static char[] F(char[] cube) {
    char[] copy = copyToNew(cube);
    //    27 28 29
    // 38 39 40 41 42
    // 50 51 52 53 54
    // 62 63 64 65 66
    //    75 76 77

    char outerEdgesTemp1 = copy[27];
    char outerEdgesTemp2 = copy[28];
    char outerEdgesTemp3 = copy[29];
    copy[29] = copy[38];
    copy[28] = copy[50];
    copy[27] = copy[62];
    copy[38] = copy[75];
    copy[50] = copy[76];
    copy[62] = copy[77];
    copy[75] = copy[66];
    copy[76] = copy[54];
    copy[77] = copy[42];
    copy[42] = outerEdgesTemp1;
    copy[54] = outerEdgesTemp2;
    copy[66] = outerEdgesTemp3;

    char innerCornerTemp = copy[41];
    copy[41] = copy[39];
    copy[39] = copy[63];
    copy[63] = copy[65];
    copy[65] = innerCornerTemp;

    char innerEdgeTemp = copy[40];
    copy[40] = copy[51];
    copy[51] = copy[64];
    copy[64] = copy[53];
    copy[53] = innerEdgeTemp;

    return copy;
  }

  private static char[] F_PRIME(char[] cube) {
    char[] copy = copyToNew(cube);
    //    27 28 29
    // 38 39 40 41 42
    // 50 51 52 53 54
    // 62 63 64 65 66
    //    75 76 77

    char outerEdgesTemp1 = copy[27];
    char outerEdgesTemp2 = copy[28];
    char outerEdgesTemp3 = copy[29];
    copy[27] = copy[42];
    copy[28] = copy[54];
    copy[29] = copy[66];
    copy[42] = copy[77];
    copy[54] = copy[76];
    copy[66] = copy[75];
    copy[75] = copy[38];
    copy[76] = copy[50];
    copy[77] = copy[62];
    copy[38] = outerEdgesTemp3;
    copy[50] = outerEdgesTemp2;
    copy[62] = outerEdgesTemp1;

    char innerCornerTemp = copy[41];
    copy[41] = copy[65];
    copy[65] = copy[63];
    copy[63] = copy[39];
    copy[39] = innerCornerTemp;

    char innerEdgeTemp = copy[40];
    copy[40] = copy[53];
    copy[53] = copy[64];
    copy[64] = copy[51];
    copy[51] = innerEdgeTemp;
    return copy;
  }

  private static char[] B(char[] cube) {
    char[] copy = copyToNew(cube);
    return copy;
  }

  private static char[] B_PRIME(char[] cube) {
    char[] copy = copyToNew(cube);
    return copy;
  }

  private static char[] L(char[] cube) {
    char[] copy = copyToNew(cube);
    return copy;
  }

  private static char[] L_PRIME(char[] cube) {
    char[] copy = copyToNew(cube);
    return copy;
  }

    private static char[] R(char[] cube) {
      char[] copy = copyToNew(cube);
      return copy;
    }

    private static char[] R_PRIME(char[] cube) {
      char[] copy = copyToNew(cube);
      return copy;
    }

  private static char[] copyToNew(char[] cube) {
    char[] copy = new char[cube.length];
    for (int i = 0; i < copy.length; ++i) {
      copy[i] = cube[i];
    }
    return copy;
  }
}
