
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class priorityQueue<E> {

    private ArrayList<E> heap;
    private Comparator<E> comparator;

    public priorityQueue() {
        heap = new ArrayList<>();
        this.comparator = null;
    }

    public priorityQueue(Comparator<E> comparator) {
        heap = new ArrayList<>();
        this.comparator = comparator;
    }

    private int parent(int j) {
        return (j - 1) / 2;
    }

    private int left(int j) {
        return 2 * j + 1;
    }

    private int right(int j) {
        return 2 * j + 2;
    }

    private boolean hasLeft(int j) {
        return left(j) < heap.size();
    }

    private boolean hasRight(int j) {
        return right(j) < heap.size();
    }

    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @SuppressWarnings("unchecked")
    private int compare(E a, E b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else {
            return ((Comparable<E>) a).compareTo(b);
        }
    }

    public void add(E e) {
        heap.add(e);
        upheap(heap.size() - 1);
    }

    private void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) {
                break;
            }
            swap(j, p);
            j = p;
        }
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E answer = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return answer;
    }

    private void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
                    smallChildIndex = rightIndex;
                }
            }
            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0) {
                break;
            }
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {
        System.out.println("--- Min Priority Queue ---");
        priorityQueue<Integer> pq = new priorityQueue<>();
        pq.add(5);
        pq.add(9);
        pq.add(2);
        pq.add(1);
        pq.add(8);

        System.out.println("Peek: " + pq.peek()); // Expected: 1
        System.out.println("Polling elements:");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " "); // Expected: 1 2 5 8 9
        }
        System.out.println();
        
        System.out.println("\n--- Max Priority Queue ---");
        priorityQueue<Integer> maxPq = new priorityQueue<Integer>(Comparator.reverseOrder());
        maxPq.add(5);
        maxPq.add(9);
        maxPq.add(2);
        maxPq.add(1);
        maxPq.add(8);
        
        System.out.println("Peek: " + maxPq.peek()); // Expected: 9
        System.out.println("Polling elements:");
        while (!maxPq.isEmpty()) {
            System.out.print(maxPq.poll() + " "); // Expected: 9 8 5 2 1
        }
        System.out.println();
    }
}
