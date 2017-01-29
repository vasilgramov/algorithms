package p03_heapSort;

import java.util.ArrayList;

public class MinHeap {
    private ArrayList<Integer> elements;

    private int count;

    public MinHeap() {
        this.elements = new ArrayList<>();
    }

    public MinHeap(int[] elements) {
        this.elements = new ArrayList<>();

        this.setElements(elements);
    }

    public ArrayList<Integer> getElements() {
        return this.elements;
    }

    private void setElements(int[] elements) {
        for (int element : elements) {
            heapiyUp(element);
        }
    }

    public int size() {
        return this.count;
    }

    public void add(int element) {
        heapiyUp(element);
    }

    public int extractMin() {
        int toReturn = this.elements.get(0);
        int lastElement = this.elements.get(this.count - 1);
        this.elements.set(0, lastElement);
        this.count--;

        heapfifyDown(0);

        return toReturn;
    }

    private void heapiyUp(int num) {
        this.elements.add(this.count, num);
        if (this.count > 0) {
            int index = this.count;
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                int parent = this.elements.get(parentIndex);

                 if (parent < num)
                     break;

                 this.elements.set(parentIndex, num);
                 this.elements.set(index, parent);

                 index = parentIndex;
            }
        }

        this.count++;
    }

    private boolean heapfifyDown(int index) {
        int parent = this.elements.get(index);

        int leftChildIndex = (index * 2) + 1;
        int rightChildIndex = (index * 2) + 2;

        boolean hasLeftChild = leftChildIndex < this.count;
        boolean hasRightChild = rightChildIndex < this.count;

        if (!hasLeftChild && !hasRightChild)
            return true;

        if (hasLeftChild && !hasRightChild) {
            int leftChild = this.elements.get(leftChildIndex);

            if (parent > leftChild) {
                this.elements.set(index, leftChild);
                this.elements.set(leftChildIndex, parent);
            }

            return true;
        }

        if (hasLeftChild && hasRightChild) {
            int leftChild = this.elements.get(leftChildIndex);
            int rightChild = this.elements.get(rightChildIndex);

            if (parent < leftChild && parent < rightChild)
                return true;

            if (leftChild < rightChild) {
                if (leftChild < parent) {
                    this.elements.set(index, leftChild);
                    this.elements.set(leftChildIndex, parent);

                    if (heapfifyDown(leftChildIndex))
                        return true;
                }
            }

            if (rightChild < leftChild) {
                if (rightChild < parent) {
                    this.elements.set(index, rightChild);
                    this.elements.set(rightChildIndex, parent);

                    if (heapfifyDown(rightChildIndex))
                        return true;
                }
            }
        }

        return true;
    }
}
