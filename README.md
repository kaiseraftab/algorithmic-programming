# Algorithmic Programming - Final Assignment

## Project Overview
An application that converts a CSV dataset into three custom-built data structures and demonstrates sorting and searching algorithms through a graphical user interface.

## Team Members
- Md Kaiser Aftab
- Abu Hasib

## Data Structures
- **LinkedList** — Singly linked list with generic types
- **HashMap** — Hash map using separate chaining for collision resolution, with automatic resizing
- **Binary Search Tree** — BST with in-order, pre-order, and post-order traversal

## Algorithms
- **Insertion Sort** — O(n²) average, builds sorted portion one element at a time
- **Quick Sort** — O(n log n) average, divide-and-conquer with pivot partitioning
- **Sequential Search** — O(n), linear scan through all elements
- **Binary Search** — O(log n), requires sorted data, halves search space each step

## Features
- Import CSV datasets with optional subset loading
- Convert data into any of the three data structures
- Sort by any field (id, title, year, rating, genre) using custom comparators
- Search by any field using either search algorithm
- Execution speed displayed in seconds and milliseconds
- Output log tracking all operations

## How to Run
### Prerequisites
- Java 17 or higher
- Maven

### Run the application
```bash
mvn clean compile org.openjfx:javafx-maven-plugin:0.0.8:run
```

## Project Structure
