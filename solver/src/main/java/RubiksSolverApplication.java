import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RubiksSolverApplication {
  public static void main(String[] args) {
    printCube(cube);
    List<char[]> arr = new ArrayList<>();
    arr.add(cube);
    arr.stream()
      .map(RubiksSolverApplication::D)
      .map(RubiksSolverApplication::D)
      .map(RubiksSolverApplication::L)
      .map(RubiksSolverApplication::R)
      .map(RubiksSolverApplication::U_PRIME)
      .map(RubiksSolverApplication::B)
      .map(RubiksSolverApplication::B)
      .map(RubiksSolverApplication::D)
      .map(RubiksSolverApplication::D)
      .map(RubiksSolverApplication::B)
      .map(RubiksSolverApplication::B)
      .map(RubiksSolverApplication::R)
      .map(RubiksSolverApplication::L)
      .map(RubiksSolverApplication::L)
      .map(RubiksSolverApplication::B)
      .map(RubiksSolverApplication::D)
      .map(RubiksSolverApplication::B)
      .map(RubiksSolverApplication::L_PRIME)
      .map(RubiksSolverApplication::D_PRIME)
      .map(RubiksSolverApplication::R_PRIME)
      .map(RubiksSolverApplication::F_PRIME)
      .map(RubiksSolverApplication::U)
      .map(RubiksSolverApplication::F)
      .map(RubiksSolverApplication::F)
      .map(RubiksSolverApplication::U_PRIME)
      .map(RubiksSolverApplication::L_PRIME)
      .map(RubiksSolverApplication::F)
      .map(RubiksSolverApplication::B_PRIME)
      .map(RubiksSolverApplication::U_PRIME)
      .map(RubiksSolverApplication::R)
      .map(RubiksSolverApplication::B_PRIME)
      .map(RubiksSolverApplication::B_PRIME)
      .forEach(RubiksSolverApplication::printCube);
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
  //                       3      4       5
  //                       15     16      17
  //                       27     28      29
  //  36     37     38     39     40      41      42     43     44     45     46     47
  //  48     49     50     51     52      53      54     55     56     57     58     59
  //  60     61     62     63     64      65      66     67     68     69     70     71
  //                       75     76      77
  //                       87     88      89
  //                       99     100     101

  private static char[] U(char[] cube) {
    char[] copy = copyOf(cube);
    //           3  4  5
    //          15 16 17
    //          27 28 29
    // 36 37 38 39 40 41 42 43 44 45 46 47
    //
    //          27 15  3
    //          28 16  4
    //          29 17  5
    // 39 40 41 42 43 44 45 46 47 36 37 38

    char outerEdgesTemp1 = copy[36];
    char outerEdgesTemp2 = copy[37];
    char outerEdgesTemp3 = copy[38];
    for (int i = 36; i < 45; ++i) {
      copy[i] = copy[i + 3];
    }
    copy[45] = outerEdgesTemp1;
    copy[46] = outerEdgesTemp2;
    copy[47] = outerEdgesTemp3;

    char innerCornerTemp = copy[3];
    copy[3] = copy[27];
    copy[27] = copy[29];
    copy[29] = copy[5];
    copy[5] = innerCornerTemp;

    char innerEdgeTemp = copy[4];
    copy[4] = copy[15];
    copy[15] = copy[28];
    copy[28] = copy[17];
    copy[17] = innerEdgeTemp;

    return copy;
  }

  private static char[] U_PRIME(char[] cube) {
    char[] copy = copyOf(cube);
    //           3  4  5
    //          15 16 17
    //          27 28 29
    // 36 37 38 39 40 41 42 43 44 45 46 47
    //
    //           5 17 29
    //           4 16 28
    //           3 15 27
    // 45 46 47 36 37 38 39 40 41 42 43 44

    char outerEdgesTemp1 = copy[45];
    char outerEdgesTemp2 = copy[46];
    char outerEdgesTemp3 = copy[47];
    for (int i = 47; i >= 39; --i) {
      copy[i] = copy[i - 3];
    }
    copy[36] = outerEdgesTemp1;
    copy[37] = outerEdgesTemp2;
    copy[38] = outerEdgesTemp3;

    char innerCornerTemp = copy[3];
    copy[3] = copy[5];
    copy[5] = copy[29];
    copy[29] = copy[27];
    copy[27] = innerCornerTemp;

    char innerEdgeTemp = copy[4];
    copy[4] = copy[17];
    copy[17] = copy[28];
    copy[28] = copy[15];
    copy[15] = innerEdgeTemp;

    return copy;
  }

  private static char[] D(char[] cube) {
    char[] copy = copyOf(cube);
    // 60 61 62  63  64  65 66 67 68 69 70 71
    //           75  76  77
    //           87  88  89
    //           99 100 101
    //
    // 69 70 71 60  61 62 63 64 65 66 67 68
    //          99  87 75
    //          100 88 76
    //          101 89 77

    char outerEdgesTemp1 = copy[69];
    char outerEdgesTemp2 = copy[70];
    char outerEdgesTemp3 = copy[71];
    for (int i = 71; i >= 60; --i) {
      copy[i] = copy[i - 3];
    }
    copy[60] = outerEdgesTemp1;
    copy[61] = outerEdgesTemp2;
    copy[62] = outerEdgesTemp3;

    char innerCornerTemp = copy[75];
    copy[75] = copy[99];
    copy[99] = copy[101];
    copy[101] = copy[77];
    copy[77] = innerCornerTemp;

    char innerEdgeTemp = copy[76];
    copy[76] = copy[87];
    copy[87] = copy[100];
    copy[100] = copy[89];
    copy[89] = innerEdgeTemp;

    return copy;
  }

  private static char[] D_PRIME(char[] cube) {
    char[] copy = copyOf(cube);
    // 60 61 62 63 64  65 66 67 68 69 70 71
    //          75 76  77
    //          87 88  89
    //          99 100 101
    //
    // 63 64 65 66 67 68  69 70 71 60 61 62
    //          77 89 101
    //          76 88 100
    //          75 87 99

    char outerEdgesTemp1 = copy[60];
    char outerEdgesTemp2 = copy[61];
    char outerEdgesTemp3 = copy[62];
    for (int i = 60; i < 69; ++i) {
      copy[i] = copy[i + 3];
    }
    copy[69] = outerEdgesTemp1;
    copy[70] = outerEdgesTemp2;
    copy[71] = outerEdgesTemp3;

    char innerCornerTemp = copy[75];
    copy[75] = copy[77];
    copy[77] = copy[101];
    copy[101] = copy[99];
    copy[99] = innerCornerTemp;

    char innerEdgeTemp = copy[76];
    copy[76] = copy[89];
    copy[89] = copy[100];
    copy[100] = copy[87];
    copy[87] = innerEdgeTemp;

    return copy;
  }

  private static char[] F(char[] cube) {
    char[] copy = copyOf(cube);
    //    27 28 29
    // 38 39 40 41 42
    // 50 51 52 53 54
    // 62 63 64 65 66
    //    75 76 77
    //
    //    62 50 38
    // 75 63 51 39 27
    // 76 64 52 40 28
    // 77 65 53 41 29
    //    66 54 42

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
    char[] copy = copyOf(cube);
    //    27 28 29
    // 38 39 40 41 42
    // 50 51 52 53 54
    // 62 63 64 65 66
    //    75 76 77
    //
    //    42 54 66
    // 29 41 53 65 77
    // 28 40 52 64 76
    // 27 39 51 63 75
    //    38 50 62

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
    char[] copy = copyOf(cube);
    //                       3      4       5
    //
    //
    //  36                                                        44     45     46     47
    //  48                                                        56     57     58     59
    //  60                                                        68     69     70     71
    //
    //
    //                       99     100     101
    //
    //                       44     56      68
    //
    //
    //   5                                                       101     69     57     45
    //   4                                                       100     70     58     46
    //   3                                                        99     71     59     47
    //
    //
    //                       36     48     60

    char outerEdgesTemp1 = copy[3];
    char outerEdgesTemp2 = copy[4];
    char outerEdgesTemp3 = copy[5];
    copy[3] = copy[44];
    copy[44] = copy[101];
    copy[101] = copy[60];
    copy[60] = outerEdgesTemp1;
    copy[4] = copy[56];
    copy[56] = copy[100];
    copy[100] = copy[48];
    copy[48] = outerEdgesTemp2;
    copy[5] = copy[68];
    copy[68] = copy[99];
    copy[99] = copy[36];
    copy[36] = outerEdgesTemp3;

    char innerCornerTemp = copy[45];
    copy[45] = copy[69];
    copy[69] = copy[71];
    copy[71] = copy[47];
    copy[47] = innerCornerTemp;

    char innerEdgeTemp = copy[46];
    copy[46] = copy[57];
    copy[57] = copy[70];
    copy[70] = copy[59];
    copy[59] = innerEdgeTemp;

    return copy;
  }

  private static char[] B_PRIME(char[] cube) {
    char[] copy = copyOf(cube);
    //                       3      4       5
    //
    //
    //  36                                                        44     45     46     47
    //  48                                                        56     57     58     59
    //  60                                                        68     69     70     71
    //
    //
    //                       99     100     101
    //
    //                       60      48     36
    //
    //
    //  99                                                         3     47     59     71
    //  100                                                        4     46     58     70
    //  101                                                        5     45     57     69
    //
    //
    //                       68      56     44

    char outerEdgesTemp1 = copy[3];
    char outerEdgesTemp2 = copy[4];
    char outerEdgesTemp3 = copy[5];
    copy[3] = copy[60];
    copy[60] = copy[101];
    copy[101] = copy[44];
    copy[44] = outerEdgesTemp1;
    copy[4] = copy[48];
    copy[48] = copy[100];
    copy[100] = copy[56];
    copy[56] = outerEdgesTemp2;
    copy[5] = copy[36];
    copy[36] = copy[99];
    copy[99] = copy[68];
    copy[68] = outerEdgesTemp3;

    char innerCornerTemp = copy[45];
    copy[45] = copy[47];
    copy[47] = copy[71];
    copy[71] = copy[69];
    copy[69] = innerCornerTemp;

    char innerEdgeTemp = copy[46];
    copy[46] = copy[59];
    copy[59] = copy[70];
    copy[70] = copy[57];
    copy[57] = innerEdgeTemp;

    return copy;
  }

  private static char[] L(char[] cube) {
    char[] copy = copyOf(cube);
    //                       3
    //                       15
    //                       27
    //  36     37     38     39                                                        47
    //  48     49     50     51                                                        59
    //  60     61     62     63                                                        71
    //                       75
    //                       87
    //                       99
    //
    //                       71
    //                       59
    //                       47
    //  60     48     36     3                                                         99
    //  61     49     37     15                                                        87
    //  62     50     38     27                                                        75
    //                       39
    //                       51
    //                       63
    char outerEdgesTemp1 = copy[3];
    char outerEdgesTemp2 = copy[15];
    char outerEdgesTemp3 = copy[27];
    copy[3] = copy[71];
    copy[71] = copy[75];
    copy[75] = copy[39];
    copy[39] = outerEdgesTemp1;
    copy[15] = copy[59];
    copy[59] = copy[87];
    copy[87] = copy[51];
    copy[51] = outerEdgesTemp2;
    copy[27] = copy[47];
    copy[47] = copy[99];
    copy[99] = copy[63];
    copy[63] = outerEdgesTemp3;

    char innerCornerTemp = copy[36];
    copy[36] = copy[60];
    copy[60] = copy[62];
    copy[62] = copy[38];
    copy[38] = innerCornerTemp;

    char innerEdgeTemp = copy[37];
    copy[37] = copy[48];
    copy[48] = copy[61];
    copy[61] = copy[50];
    copy[50] = innerEdgeTemp;

    return copy;
  }

  private static char[] L_PRIME(char[] cube) {
    char[] copy = copyOf(cube);
    //                       3
    //                       15
    //                       27
    //  36     37     38     39                                                        47
    //  48     49     50     51                                                        59
    //  60     61     62     63                                                        71
    //                       75
    //                       87
    //                       99
    //
    //                       39
    //                       51
    //                       63
    //  38     50     62     75                                                        27
    //  37     49     61     87                                                        15
    //  36     48     60     99                                                        3
    //                       71
    //                       59
    //                       47
    char outerEdgesTemp1 = copy[3];
    char outerEdgesTemp2 = copy[15];
    char outerEdgesTemp3 = copy[27];
    copy[3] = copy[39];
    copy[39] = copy[75];
    copy[75] = copy[71];
    copy[71] = outerEdgesTemp1;
    copy[15] = copy[51];
    copy[51] = copy[87];
    copy[87] = copy[59];
    copy[59] = outerEdgesTemp2;
    copy[27] = copy[63];
    copy[63] = copy[99];
    copy[99] = copy[47];
    copy[47] = outerEdgesTemp3;

    char innerCornerTemp = copy[36];
    copy[36] = copy[38];
    copy[38] = copy[62];
    copy[62] = copy[60];
    copy[60] = innerCornerTemp;

    char innerEdgeTemp = copy[37];
    copy[37] = copy[50];
    copy[50] = copy[61];
    copy[61] = copy[48];
    copy[48] = innerEdgeTemp;

    return copy;
  }

    private static char[] R(char[] cube) {
      char[] copy = copyOf(cube);
      //                                      5
      //                                      17
      //                                      29
      //                                      41      42     43     44     45
      //                                      53      54     55     56     57
      //                                      65      66     67     68     69
      //                                      77
      //                                      89
      //                                      101
      //
      //                                      41
      //                                      53
      //                                      65
      //                                      77      66     54     42     29
      //                                      89      67     55     43     17
      //                                      101     68     56     44     5
      //                                      69
      //                                      57
      //                                      45
      char outerEdgesTemp1 = copy[5];
      char outerEdgesTemp2 = copy[17];
      char outerEdgesTemp3 = copy[29];
      copy[5] = copy[41];
      copy[41] = copy[77];
      copy[77] = copy[69];
      copy[69] = outerEdgesTemp1;
      copy[17] = copy[53];
      copy[53] = copy[89];
      copy[89] = copy[57];
      copy[57] = outerEdgesTemp2;
      copy[29] = copy[65];
      copy[65] = copy[101];
      copy[101] = copy[45];
      copy[45] = outerEdgesTemp3;

      char innerCornerTemp = copy[42];
      copy[42] = copy[66];
      copy[66] = copy[68];
      copy[68] = copy[44];
      copy[44] = innerCornerTemp;

      char innerEdgeTemp = copy[43];
      copy[43] = copy[54];
      copy[54] = copy[67];
      copy[67] = copy[56];
      copy[56] = innerEdgeTemp;

      return copy;
    }

    private static char[] R_PRIME(char[] cube) {
      char[] copy = copyOf(cube);
      //                                      5
      //                                      17
      //                                      29
      //                                      41      42     43     44     45
      //                                      53      54     55     56     57
      //                                      65      66     67     68     69
      //                                      77
      //                                      89
      //                                      101
      //
      //                                      69
      //                                      57
      //                                      45
      //                                      5       44     56     68     101
      //                                      17      43     55     67     89
      //                                      29      42     54     66     77
      //                                      41
      //                                      53
      //                                      65
      char outerEdgesTemp1 = copy[5];
      char outerEdgesTemp2 = copy[17];
      char outerEdgesTemp3 = copy[29];
      copy[5] = copy[69];
      copy[69] = copy[77];
      copy[77] = copy[41];
      copy[41] = outerEdgesTemp1;
      copy[17] = copy[57];
      copy[57] = copy[89];
      copy[89] = copy[53];
      copy[53] = outerEdgesTemp2;
      copy[29] = copy[45];
      copy[45] = copy[101];
      copy[101] = copy[65];
      copy[65] = outerEdgesTemp3;

      char innerCornerTemp = copy[42];
      copy[42] = copy[44];
      copy[44] = copy[68];
      copy[68] = copy[66];
      copy[66] = innerCornerTemp;

      char innerEdgeTemp = copy[43];
      copy[43] = copy[56];
      copy[56] = copy[67];
      copy[67] = copy[54];
      copy[54] = innerEdgeTemp;

      return copy;
    }

  private static char[] copyOf(char[] cube) {
    char[] copy = new char[cube.length];
    System.arraycopy(cube, 0, copy, 0, cube.length);
    return copy;
  }

  private static final char[] SOLUTION = new char[] {
    ' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' ', ' ', ' ', ' ',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    'b', 'b', 'b', 'w', 'w', 'X', 'g', 'g', 'g', 'y', 'y', 'y',
    'b', 'b', 'b', 'w', 'w', 'w', 'g', 'g', 'g', 'y', 'y', 'y',
    ' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ',
    ' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ',
  };

  private static void printCube(char[] cube) {
    for (int j = 0; j < 9; ++j) {
      for (int i = 0; i < 12; ++i) {
        System.out.print(String.format("%1$3s", cube[12 * j + i]));
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private static boolean cubeEquals(char[] cube1, char[] cube2) {
    return Arrays.equals(cube1, cube2);
  }
}
