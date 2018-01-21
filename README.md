# rubiks-solver

This rubik's cube solver works using an iterative deepening breadth first
search approach. This solver can NOT solve all configurations but it has a fast
execution time of approximately 2 seconds, with only a 5MB prebuilt index being
needed.

rubiks-solver will scramble a random cube and attempt to solve it. There's a
limit to how deep into the state graph that it will try to go. If it can't find
a solution before running out of cube configurations, the solver will just give
up.

It was important for me to try to minimize the size of the index required by
the solver. Other solvers that are guaranteed to find a solution, even if the
solution is not optimal, can have indexes that take upwards of hours to build.

For example, the
[Kociemba](https://github.com/hkociemba/RubiksCube-TwophaseSolver) official
implementation needs an 80MB index which can take anywhere from `1/2 to 6 hours
to create them, depending on the hardware.`

[This](https://github.com/brownan/Rubiks-Cube-Solver) implementation of a
Rubik's cube solver will find the shortest possible solve, but it works by
effectively generating a full index of every state that's less than or equal to
[20 moves](http://www.cube20.org/) away from being solved.

## Getting Started

### Prerequisites

Requires Java 8 and Gradle 4.4 to be installed.

### Build Instructions
```
git clone https://github.com/abatilo/rubiks-solver.git
cd rubiks-solver
./gradlew :solver:clean :solver:shadowJar
java -jar solver/rubiks-solver.jar
```

## Cube representation
Each cube is represented as follows:
```java
//              0   1   2
//              3   4   5
//              6   7   8
//  9   10  11  12  13  14  15  16  17  18  19  20
//  21  22  23  24  25  26  27  28  29  30  31  32
//  33  34  35  36  37  38  39  40  41  42  43  44
//              45  46  47
//              48  49  50
//              51  52  53
public static final char[] SOLUTION = new char[] {
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
```

The positions are oriented such that you are looking directly at the white face
of a cube, and the other faces are wrapped, relative to the white face. This
means that the red cube tiles that are at index 0, 1, or 2, are the red tiles
that would be affected by a `B` or `B'` operation. They are the red tiles that
are furthest from the white face.

## Approach
This solver will attempt to find any of the cube states that exist in
[solvable.txt](./solvable.txt).

This list of cube configurations are all cubes that are 6 moves away from being
solved. The reason that we picked 6 moves from being solved, is because that
was the upper limit on my computer with the default JVM settings for max heap
size.

It was also, coincidentally, the largest number of moves away from being solved
that would take less than 5 seconds to solve. 5 seconds was an arbitrary value
for an upper time bound.

Ultimately, this means that if while our BFS is traversing the graph space, we
run across one of these solvable states, we clear our traversal queue and start
over and pretend we have a 6 move cube to solve.

Since at each cube state, we have the following 12 possible moves:
`U D L R F B U' D' L' R' F' B'`

That means that per state we get into, that's 12 more nodes we add to our
queue. The size of the queue expands rapidly. Because of this, we will only
traverse down to a certain depth in the graph space. If we can't find a
solution or a solvable state within that graph space, then we just give up.

## Further work
We could easily have several levels of solvable states which is just adding
more iterations to our iterative BFS approach. Unfortunately, this would
increase the size of our index which is something we wanted to minimize.

At one point, we had attempted to introduce a completion heuristic to help
guide the graph traversals. The heuristic that we tried involved how many tiles
were already in their correct position. This did really poorly, as mostly
solved cubes are not necessarily close to being fully solved, as many
operations will intentionally make the cube less solved in order to move pieces
around. It's entirely possible that a better heuristic could be used.

## Contributing

Fork the project and submit a PR and one of the maintainers will be in touch.

## Authors

* Aaron Batilo - Developer / maintainer - [abatilo](https://github.com/abatilo)

See also the list of [contributors](https://github.com/abatilo/rubiks-solver/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
