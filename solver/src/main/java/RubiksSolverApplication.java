import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RubiksSolverApplication {

  public static void main(String[] args) throws InterruptedException {
    ArrayDeque<CubeState> searchGraph = new ArrayDeque<>();
    Set<CubeState> seenBefore = new HashSet<>();
    char[] shuffled = CubeOperations.shuffle(CubeOperations.SOLUTION, 16);
    CubeOperations.printCube(shuffled);
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

        if (CubeOperations.cubeEquals(CubeOperations.SOLUTION, nextStep.getCube())) {
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
          searchGraph.add(new CubeState(CubeOperations.U_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "U' "));
          searchGraph.add(new CubeState(CubeOperations.D_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "D' "));
          searchGraph.add(new CubeState(CubeOperations.F_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "F' "));
          searchGraph.add(new CubeState(CubeOperations.B_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "B' "));
          searchGraph.add(new CubeState(CubeOperations.L_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "L' "));
          searchGraph.add(new CubeState(CubeOperations.R_PRIME(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "R' "));
          searchGraph.add(new CubeState(CubeOperations.U(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "U "));
          searchGraph.add(new CubeState(CubeOperations.D(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "D "));
          searchGraph.add(new CubeState(CubeOperations.F(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "F "));
          searchGraph.add(new CubeState(CubeOperations.B(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "B "));
          searchGraph.add(new CubeState(CubeOperations.L(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "L "));
          searchGraph.add(new CubeState(CubeOperations.R(nextStep.getCube()), nextStep.getDepth() + 1, nextStep.getPath() + "R "));
        }
      }

    } catch (IOException e) {
      // Ignored
    }
  }

}
