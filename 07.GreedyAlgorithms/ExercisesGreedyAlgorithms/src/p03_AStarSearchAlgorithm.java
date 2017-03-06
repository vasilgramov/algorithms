import java.util.*;

public class p03_AStarSearchAlgorithm {

    private static char[][] map =
    {
        { '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' },
        { '-', '-', '-', 'W', '*', '-', '-', '-', '-', '-', '-' },
        { '-', '-', '-', 'W', 'W', 'W', 'W', 'W', '-', '-', '-' },
        { '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' },
        { '-', '-', '-', '-', '-', '-', '-', 'P', '-', '-', '-' },
        { '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' }
    };

//    private static char[][] map =
//    {
//        { '-', '-', '-', '-', '-' },
//        { '-', '-', '*', '-', '-' },
//        { '-', 'W', 'W', 'W', '-' },
//        { '-', '-', '-', '-', '-' },
//        { '-', '-', 'P', '-', '-' },
//        { '-', '-', '-', '-', '-' }
//    };

//    private static char[][] map =
//    {
//        { '-', '-', '-', '-', 'W', '-', '-', '-', 'W', '*', '-' },
//        { '-', 'W', '-', '-', 'W', '-', '-', '-', 'W', '-', '-' },
//        { 'P', '-', 'W', '-', 'W', '-', '-', '-', 'W', '-', '-' },
//        { '-', 'W', '-', '-', 'W', 'W', 'W', '-', 'W', 'W', '-' },
//        { '-', '-', '-', 'W', 'W', '-', '-', '-', '-', 'W', '-' },
//        { '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' }
//    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] playerCoordinates = findObjectCoordinates('P');
        int[] destinationCoordinates = findObjectCoordinates('*');

        AStar aStar = new AStar(map);
        List<int[]> path = aStar.findShortestPath(playerCoordinates, destinationCoordinates);

        for (int[] cell : path) {
            int row = cell[0];
            int col = cell[1];

            map[row][col] = '@';
        }

        printMap();
    }

    private static void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }

            System.out.println();
        }
    }

    private static int[] findObjectCoordinates(char obj) {
        int[] toReturn = new int[2];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == obj) {
                    toReturn[0] = i;
                    toReturn[1] = j;
                }
            }
        }

        return toReturn;
    }

    private static class AStar {
        private static final int INFINITY = Integer.MAX_VALUE / 2 - 2017;

        private char[][] map;

        public AStar(char[][] map) {
            this.map = map;
        }

        public List<int[]> findShortestPath(int[] startCoordinates, int[] endCoordinates) {
            PriorityQueue nonVisitedNode = new PriorityQueue();
            Set<Node> visitedNodes = new HashSet<>();

            Node startNode = new Node(startCoordinates[0], startCoordinates[1]);
            for (int i = startNode.getRow() - 1; i <= startNode.getRow() + 1; i++) {
                for (int j = startNode.getCol() - 1; j <= startNode.getCol() + 1; j++) {
                    if (isInMatrix(i, j) && map[i][j] != 'W' && (startNode.getRow() != i || startNode.getCol() != j)) {
                        Node node = new Node(i, j, startNode);

                        if (i == startNode.getRow() - 1 && j == startNode.getCol() - 1 ||
                                i == startNode.getRow() - 1 && j == startNode.getCol() + 1 ||
                                i == startNode.getRow() + 1 && j == startNode.getCol() - 1 ||
                                i == startNode.getRow() + 1 && j == startNode.getCol() + 1) {
                            node.setGCost(14);
                        } else {
                            node.setGCost(10);
                        }

                        node.setHCost(getDistance(node.getRow(), node.getCol(), endCoordinates[0], endCoordinates[1]));

                        nonVisitedNode.add(node);
                    }
                }
            }
            visitedNodes.add(startNode);

            while (nonVisitedNode.size() > 0) {
                if (nonVisitedNode.peekMin().getRow() == endCoordinates[0] && nonVisitedNode.peekMin().getCol() == endCoordinates[1]) {
                    break;
                }

                Node node = nonVisitedNode.extractMin();

                for (int i = node.getRow() - 1; i <= node.getRow() + 1; i++) {
                    for (int j = node.getCol() - 1; j <= node.getCol() + 1; j++) {
                        if (isInMatrix(i, j) && (node.getRow() != i || node.getCol() != j) && map[i][j] != 'W') {
                             Node newNode = new Node(i, j, node);
                             if (!visitedNodes.contains(newNode)) {
                                 if (i == startNode.getRow() - 1 && j == startNode.getCol() - 1 ||
                                         i == startNode.getRow() - 1 && j == startNode.getCol() + 1 ||
                                         i == startNode.getRow() + 1 && j == startNode.getCol() - 1 ||
                                         i == startNode.getRow() + 1 && j == startNode.getCol() + 1) {
                                     newNode.setGCost(14);
                                 } else {
                                     newNode.setGCost(10);
                                 }

                                 newNode.setHCost(getDistance(i, j, endCoordinates[0], endCoordinates[1]));
                                 nonVisitedNode.add(newNode);
                             }
                         }
                    }
                }

                visitedNodes.add(node);
            }

            List<int[]> cells = new ArrayList<>();
            Node endNode = nonVisitedNode.extractMin();
            while (endNode != startNode) {
                int[] coordinates = new int[2];
                coordinates[0] = endNode.getRow();
                coordinates[1] = endNode.getCol();
                cells.add(coordinates);

                endNode = endNode.getParent();
            }

            return cells;
        }

        private boolean isInMatrix(int row, int col) {
            return row >= 0 && row < map.length && col >= 0 && col < map[row].length;
        }

        private int getDistance(int row, int col, int toRow, int toCol) {
            int deltaX = Math.abs(row - toRow);
            int deltaY = Math.abs(col - toCol);

            if (deltaX > deltaY) {
                return deltaY * 10 + 14 * (deltaX - deltaY);
            } else {
                return deltaX * 14 + 10 * (deltaY - deltaX);
            }
        }
    }

    public static class Node implements Comparable<Node> {
        private static final int INFINITY = Integer.MAX_VALUE / 2 - 2017;

        private int row;
        private int col;
        private Node parent;

        private int GCost;
        private int HCost;

        public Node(int row, int col) {
            this(row, col, null);
        }

        public Node(int row, int col, Node parent) {
            this.row = row;
            this.col = col;
            this.parent = parent;
        }

        public int getRow() {
            return this.row;
        }

        public int getCol() {
            return this.col;
        }

        public Node getParent() {
            return this.parent;
        }

        public int getGCost() {
            return this.GCost;
        }

        public int getHCost() {
            return this.HCost;
        }

        public void setGCost(int GCost) {
            this.GCost = GCost;
        }

        public void setHCost(int HCost) {
            this.HCost = HCost;
        }

        public int getFCost() {
            return this.HCost + this.GCost;
        }

        @Override
        public boolean equals(Object obj) {
            Node other = (Node) obj;

            return this.getCol() == other.getCol() && this.getRow() == other.getRow();
        }

        @Override
        public int hashCode() {
            String rowAndCol = Integer.toString(this.getRow()) + Integer.toString(this.getCol());
            return rowAndCol.hashCode();
        }

        @Override
        public int compareTo(Node other) {
            if (this.getFCost() == other.getFCost()) {
                return Integer.compare(this.getHCost(), other.getHCost());
            }

            return Integer.compare(this.getFCost(), other.getFCost());
        }

        @Override
        public String toString() {
            return String.format("(%d, %d), G:%d, H:%d, F:%d",
                    this.row, this.col, this.GCost, this.HCost, this.getFCost());
        }
    }

    public static class PriorityQueue {
        private Map<Node, Integer> searchCollection;
        private List<Node> heap;

        public PriorityQueue() {
            this.searchCollection = new HashMap<>();
            this.heap = new ArrayList<>();
        }

        public int size() {
            return this.heap.size();
        }

        public boolean contains(Node node) {
            return searchCollection.containsKey(node);
        }

        public Node peekMin() {
            return this.heap.get(0);
        }

        public Node extractMin() {
            Node min = this.heap.get(0);
            Node last = this.heap.get(this.heap.size() - 1);

            this.searchCollection.put(last, 0);
            this.heap.set(0, last);
            this.heap.remove(this.heap.size() - 1);
            if (this.heap.size() > 0)
            {
                this.heapifyDown(0);
            }

            this.searchCollection.remove(min);

            return min;
        }

        public void add(Node node) {
            if (this.searchCollection.containsKey(node)) {
                int oldNodeIndex = this.searchCollection.get(node);
                Node oldNode = this.heap.get(oldNodeIndex);
                if (node.compareTo(oldNode) < 0) {
                    this.searchCollection.put(node, oldNodeIndex);
                    this.heap.set(oldNodeIndex, node);
                    this.heapifyDown(oldNodeIndex);
                }
            } else {
                this.searchCollection.put(node, this.heap.size());
                this.heap.add(node);
            }
        }

        private void heapifyUp(int index) {
            int parent = (index - 1) / 2;
            while (index > 0 && this.heap.get(index).compareTo(this.heap.get(parent)) < 0) {
                Node old = this.heap.get(index);

                this.searchCollection.put(old, parent);
                this.searchCollection.put(this.heap.get(parent), index);

                this.heap.set(index, this.heap.get(parent));
                this.heap.set(parent, old);

                index = parent;
                parent = (index - 1) / 2;
            }
        }


        private void heapifyDown(int index) {
            int leftChild = (2 * index) + 1;
            int rightChild = (2 * index) + 2;
            int smallest = index;

            if (leftChild < this.heap.size() && this.heap.get(leftChild).compareTo(this.heap.get(smallest)) < 0) {
                smallest = leftChild;
            }

            if (rightChild < this.heap.size() && this.heap.get(rightChild).compareTo(this.heap.get(smallest)) < 0) {
                smallest = rightChild;
            }

            if (smallest != index) {
                Node old  = this.heap.get(index);
                this.searchCollection.put(old, smallest);
                this.searchCollection.put(this.heap.get(smallest), index);

                this.heap.set(index, this.heap.get(smallest));
                this.heap.set(smallest, old);

                this.heapifyDown(smallest);
            }
        }
    }
}
