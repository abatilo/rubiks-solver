public class CubeState {
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
      return CubeOperations.cubeEquals(this.cube, ((CubeState) that).cube);
    }
    return false;
  }
}

