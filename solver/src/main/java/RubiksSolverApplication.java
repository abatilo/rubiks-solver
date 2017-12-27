import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RubiksSolverApplication {
  public static void main(String[] args) {
    printCube(cube);

    // for (int i = 0; i < 1_000_000; ++i) {
    // long start = System.currentTimeMillis();
    // int repetitions = 0;
    // while ((System.currentTimeMillis() - start) < 60_000) {
    //   cube = U(cube);
    //   if (cubeEquals(cube, correctness)) {
    //     break;
    //   }
    //   ++repetitions;
    // }
    // System.out.println(repetitions);

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
      .forEach(c -> {
        System.out.println(cubeEquals(correctness, c));
        printCube(c);
      });
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

  //                       3      4       5
  //                       15     16      17
  //                       27     28      29
  //  36     37     38     39     40      41      42     43     44     45     46     47
  //  48     49     50     51     52      53      54     55     56     57     58     59
  //  60     61     62     63     64      65      66     67     68     69     70     71
  //                       75     76      77
  //                       87     88      89
  //                       99     100     101
  //
  //                       0      1       2
  //                       3      4       5
  //                       6      7       8
  //  9      10     11     12     13      14      15     16     17     18     19     20
  //  21     22     23     24     25      26      27     28     29     30     31     32
  //  33     34     35     36     37      38      39     40     41     42     43     44
  //                       45     46      47
  //                       48     49      50
  //                       51     52     53

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
    //           3  4  5
    //          15 16 17
    //          27 28 29
    // 36 37 38 39 40 41 42 43 44 45 46 47
    //
    //          27 15  3
    //          28 16  4
    //          29 17  5
    // 39 40 41 42 43 44 45 46 47 36 37 38

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
    //           3  4  5
    //          15 16 17
    //          27 28 29
    // 36 37 38 39 40 41 42 43 44 45 46 47
    //
    //           5 17 29
    //           4 16 28
    //           3 15 27
    // 45 46 47 36 37 38 39 40 41 42 43 44

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
    // 60 61 62  63  64  65 66 67 68 69 70 71
    //           75  76  77
    //           87  88  89
    //           99 100 101
    //
    // 69 70 71 60  61 62 63 64 65 66 67 68
    //          99  87 75
    //          100 88 76
    //          101 89 77

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
    // 60 61 62 63 64  65 66 67 68 69 70 71
    //          75 76  77
    //          87 88  89
    //          99 100 101
    //
    // 63 64 65 66 67 68  69 70 71 60 61 62
    //          77 89 101
    //          76 88 100
    //          75 87 99

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
    'b', 'b', 'b', 'w', 'w', 'X', 'g', 'g', 'g', 'y', 'y', 'y',
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

  private static boolean cubeEquals(char[] cube1, char[] cube2) {
    return Arrays.equals(cube1, cube2);
  }
}
