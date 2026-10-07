# Algorithmic Graph Theory (ATG) — Coursework

Coursework for the second semester of university, from the course **Algorithmic Graph Theory** (*Algoritmická teória grafov*, ATG). The repository contains implementations of four classic graph algorithms, each in its own folder.

> **Note:** Code comments are written in **Slovak**.

## Contents

| Folder | Algorithm | Problem it solves |
| --- | --- | --- |
| [`CPM_ATG_Strelkov`](./CPM_ATG_Strelkov) | **CPM** (Critical Path Method) | Project scheduling: earliest/latest start times, slack, and the critical path |
| [`FordovFulkersonov_ATG_Strelkov`](./FordovFulkersonov_ATG_Strelkov) | **Ford–Fulkerson** | Maximum flow in a flow network |
| [`Kruskalov_ATG_Strelkov`](./Kruskalov_ATG_Strelkov) | **Kruskal's algorithm** | Minimum spanning tree of a weighted graph |
| [`LabelSet_ATG_Strelkov`](./LabelSet_ATG_Strelkov) | **Label-set algorithm** | Shortest paths in a weighted graph |

## Algorithms in brief

### CPM — Critical Path Method
Models a project as an acyclic directed graph of activities with durations. A forward pass computes the earliest start times, a backward pass computes the latest ones, and activities with zero slack form the **critical path** — the sequence that determines the minimum total project duration.

### Ford–Fulkerson
Finds the **maximum flow** from a source to a sink in a network with edge capacities. It repeatedly finds an augmenting path in the residual graph and pushes flow along it until no such path exists.

### Kruskal's algorithm
Builds a **minimum spanning tree** by sorting edges by weight and adding each one that does not create a cycle (checked with a union-find / disjoint-set structure).

### Label-set algorithm
A label-based approach to the **shortest path problem**: each vertex carries a label (its current best distance), and the labels are iteratively improved until they become final.

## Getting started

```bash
git clone https://github.com/adoptium33/algorithms.git
cd algorithms
```

Each algorithm is self-contained, so open the folder you are interested in and build and run it from there. Input data and run instructions, where needed, are in the respective folder.

## Credits

The file `Graf.java` was written by the course instructor, RNDr. Zuzana Borčinová, PhD.

## Author

**Daniil Strelkov** — Informatics student at the Faculty of Management Science and Informatics (FRI), University of Žilina.

GitHub: [@adoptium33](https://github.com/adoptium33)
