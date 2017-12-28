# rubiks-solver

This rubik's cube solver works using an iterative deepening breadth first
search approach. This solver can NOT solve all configurations in its current
state, but it's still under active development.

rubiks-solver will scramble a random cube and attempt to solve it. There's a
limit to how deep into the state graph that it will try to go. If it can't find
a solution before running out of cube configurations, the solver will just give
up.

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

## Benchmarks

TODO

## Contributing

Fork the project and submit a PR and one of the maintainers will be in touch.

## Authors

* Aaron Batilo - Developer / maintainer - [abatilo](https://github.com/abatilo)

See also the list of [contributors](https://github.com/abatilo/rubiks-solver/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
