import java.util.*;

public class p01_shortestPathInMatrix {

    private static final int INFINITY = Integer.MAX_VALUE / 2 - 2017;

    private static int rows;
    private static int cols;
    private static int[][] matrix;

    private static PriorityQueue<Cell> priorityQueue;

    private static int[][] bestPathToCell;

    private static Map<Cell, List<Cell>> graph;
    private static Cell endCell;

    public static void main(String[] args) {
        readInput();

        buildGraph();

        dijkstra();

        print();
    }

    private static void print() {
        int length = 0;
        Deque<Integer> path = new LinkedList<>();

        Cell cell = endCell;
        while (cell != null) {
            length += matrix[cell.getRow()][cell.getCol()];
            path.addFirst(matrix[cell.getRow()][cell.getCol()]);

            cell = cell.previousCell;
        }

        System.out.println(String.format("Length: %d", length));
        StringBuilder builder = new StringBuilder();
        for (Integer integer : path) {
            builder.append(String.format("%d ", integer));
        }

        System.out.println(String.format("Path: %s", builder.toString().trim()));
    }

    private static void dijkstra() {
        priorityQueue = new PriorityQueue<>();
        bestPathToCell = new int[rows][cols];

        Cell startCell = graph.keySet().iterator().next();

        for (int row = 0; row < bestPathToCell.length; row++) {
            for (int col = 0; col < bestPathToCell[row].length; col++) {
                bestPathToCell[row][col] = INFINITY;
            }
        }

        for (Cell cell : graph.get(startCell)) {
            cell.setPreviousCell(startCell);
            cell.setValue(cell.getValue() + startCell.getValue());
            priorityQueue.enqueue(cell);
            bestPathToCell[cell.getRow()][cell.getCol()] = startCell.getValue() + cell.getValue();
        }

        while (priorityQueue.size() > 0) {
            Cell cell = priorityQueue.extractMin();

            if (cell.getRow() == rows - 1 && cell.getCol() == cols - 1) {
                endCell = cell;
                break;
            }

            List<Cell> neighbors = graph.get(cell);
            for (Cell neighbor : neighbors) {
                int distanceToCurrent = neighbor.getValue() + cell.getValue();

                if (bestPathToCell[neighbor.getRow()][neighbor.getCol()] > distanceToCurrent) {
                    neighbor.setValue(distanceToCurrent);
                    neighbor.setPreviousCell(cell);

                    bestPathToCell[neighbor.getRow()][neighbor.getCol()] = distanceToCurrent;

                    if (priorityQueue.contains(neighbor)) {
                        priorityQueue.decreaseKey(neighbor);
                    } else {
                        priorityQueue.enqueue(neighbor);
                    }
                }
            }
        }
    }

    private static void readInput() {
        Scanner in = new Scanner(System.in);

        rows = Integer.parseInt(in.nextLine());
        cols = Integer.parseInt(in.nextLine());
        matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] currentRow = in.nextLine().split("\\s+");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = Integer.parseInt(currentRow[col]);
            }
        }
    }

    private static void buildGraph() {
        graph = new LinkedHashMap<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = new Cell(row, col, matrix[row][col]);

                List<Cell> neighbours = new ArrayList<>();
                if (isInMatrix(row - 1, col)) {         // up
                    neighbours.add(new Cell(row - 1, col, matrix[row - 1][col]));
                }

                if (isInMatrix(row, col + 1)) {         // right
                    neighbours.add(new Cell(row, col + 1, matrix[row][col + 1]));
                }

                if (isInMatrix(row + 1, col)) {         // down
                    neighbours.add(new Cell(row + 1, col, matrix[row + 1][col]));
                }

                if (isInMatrix(row, col - 1)) {         // left
                    neighbours.add(new Cell(row, col - 1, matrix[row][col - 1]));
                }

                graph.put(cell, neighbours);
            }
        }
    }

    private static boolean isInMatrix(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private static class Cell implements Comparable<Cell> {
        private int row;
        private int col;
        private int value;

        private Cell previousCell;

        public Cell(int row, int col, int value) {
            this.setRow(row);
            this.setCol(col);
            this.setValue(value);
        }

        public int getRow() {
            return this.row;
        }

        private void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return this.col;
        }

        private void setCol(int col) {
            this.col = col;
        }

        public int getValue() {
            return this.value;
        }

        private void setValue(int value) {
            this.value = value;
        }

        public void setPreviousCell(Cell cell) {
            this.previousCell = cell;
        }

        @Override
        public boolean equals(Object obj) {
            Cell cell = (Cell) obj;

            return this.getRow() == cell.getRow() && this.getCol() == cell.getCol();
        }

        @Override
        public int hashCode() {
            String hash = this.getRow() + "" + this.getCol();
            return hash.hashCode();
        }

        @Override
        public int compareTo(Cell other) {
            if (Integer.compare(this.getValue(), other.getValue()) == 0) {
                if (Integer.compare(this.getRow(), other.getRow()) == 0) {
                    return Integer.compare(this.getCol(), other.getCol());
                }

                return Integer.compare(this.getRow(), other.getRow());
            }

            return Integer.compare(this.getValue(), other.getValue());
        }

        @Override
        public String toString() {
            return String.format("%d %d", this.getRow(), this.getCol());
        }
    }

    private static class PriorityQueue<T extends Comparable<T>> {
        private Map<T, Integer> searchCollection;
        private List<T> heap;


        public PriorityQueue() {
            this.heap = new ArrayList<>();
            this.searchCollection = new HashMap<T, Integer>();
        }

        public int size() {
            return this.heap.size();
        }

        public T extractMin() {
            T min = this.heap.get(0);
            T last = this.heap.get(this.heap.size() - 1);
            this.searchCollection.put(last, 0);
            this.heap.set(0, last);
            this.heap.remove(this.heap.size() - 1);
            if (this.heap.size() > 0) {
                this.heapifyDown(0);
            }

            this.searchCollection.remove(min);

            return min;
        }

        public T peekMin() {
            return this.heap.get(0);
        }

        public boolean contains(T element) {
            return this.searchCollection.containsKey(element);
        }

        public void enqueue(T element) {
            this.searchCollection.put(element, this.heap.size());
            this.heap.add(element);
            this.heapifyUp(this.heap.size() - 1);
        }

        private void heapifyDown(int i) {
            int left = (2 * i) + 1;
            int right = (2 * i) + 2;
            int smallest = i;

            if (left < this.heap.size() && this.heap.get(left).compareTo(this.heap.get(smallest)) < 0) {
                smallest = left;
            }

            if (right < this.heap.size() && this.heap.get(right).compareTo(this.heap.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest != i) {
                T old = this.heap.get(i);
                this.searchCollection.put(old, smallest);
                this.searchCollection.put(this.heap.get(smallest), i);
                this.heap.set(i, this.heap.get(smallest));
                this.heap.set(smallest, old);
                this.heapifyDown(smallest);
            }
        }

        private void heapifyUp(int i) {
            int parent = (i - 1) / 2;
            while (i > 0 && this.heap.get(i).compareTo(this.heap.get(parent)) < 0) {
                T old = this.heap.get(i);
                this.searchCollection.put(old, parent);
                this.searchCollection.put(this.heap.get(parent), i);
                this.heap.set(i, this.heap.get(parent));
                this.heap.set(parent, old);

                i = parent;
                parent = (i - 1) / 2;
            }
        }

        public void decreaseKey(T element)
        {
            int index = this.searchCollection.get(element);
            this.heapifyUp(index);
        }
    }
}
