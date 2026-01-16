# Java Algorithms

Essential algorithms and data structures implemented in Java.

## Contents
- **adt**: Data Structures (Trees, Lists, Queues, Stacks, Union-Find)
- **dp**: Dynamic Programming (Knapsack, LCS, LIS, etc.)
- **graph**: Graph Algorithms (Shortest Paths, MST, Euler/Hamilton Paths)
- **search**: Search algorithms
- **sort**: Sorting algorithms
- **random**: Specialized algorithms (Karatsuba, Word Break)

## Usage

Compile all:
```bash
find . -name "*.java" | xargs javac -d bin
```

Run an algorithm (example):
```bash
java -cp bin sort.quickSort
```