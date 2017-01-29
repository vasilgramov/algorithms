package p03_heapSort;

public class Main {
    public static void main(String[] args) {

        /*
        Min Heap / Max Heap
            - extractMin();
            - add(int el);
            - getElements();
            - size();
         */

//        MaxHeap minHeap = new MaxHeap();
        int[] unsortedElements = { 7, 9, 6, 17, 11 };
        MaxHeap minHeap1 = new MaxHeap(unsortedElements);

        minHeap1.add(5);
//        minHeap1.add(4);
//        minHeap1.add(3);
//        minHeap1.add(2);

        while (minHeap1.size() > 0) {
            System.out.println(minHeap1.extractMax());
        }

        System.out.println();
        System.out.println();

        minHeap1.add(-7);
        minHeap1.add(-8);
        minHeap1.add(-10);
        while (minHeap1.size() > 0) {
            System.out.println(minHeap1.extractMax());
        }
    }
}
