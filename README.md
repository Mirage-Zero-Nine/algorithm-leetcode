# LeetCode solutions

This repository is a single-module Java workspace for solving LeetCode
algorithm problems. Solutions are grouped by topic, with shared data
structures and experiments alongside their tests.

## Layout

```text
pom.xml                 Maven build and dependency configuration
src/main/java/
  solutions/            Problem solutions grouped by algorithmic topic
  library/              Shared data structures and problem interfaces
  playground/           Algorithm experiments and examples
src/test/java/
  solutions/            Solution tests (mirroring the production packages)
  library/              Shared-structure and helper tests
  playground/           Experiment tests
  util/                 Test utilities and large fixtures
target/                 Generated Maven output (ignored)
```

The solution topics include backtracking, BFS/DFS, binary search, dynamic
programming, graphs, greedy algorithms, heaps, linked lists, sliding windows,
stacks, tries, two pointers, union-find, and others.

## Requirements and dependencies

- JDK 25
- An external Maven installation; this repository does not include a Maven
  wrapper

Tests use JUnit Jupiter 6. Guava provides general-purpose utilities, and
Lombok generates boilerplate code through annotation processing.
The [pom.xml](pom.xml) is the source of truth for the exact dependency
declarations and versions.

## Build and test

Run Maven commands from the repository root:

```bash
# Run the complete test suite
mvn test

# Run one existing solution test
mvn -Dtest=CamelMatch_1023Test test

# Compile, test, and run verification checks
mvn clean verify

# Optional: build and install the artifact in the local Maven repository
mvn clean install
```

`clean install` is optional; it is not required before running targeted tests.
Surefire test reports are written to `target/surefire-reports/`.
