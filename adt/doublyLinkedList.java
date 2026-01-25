
import java.util.NoSuchElementException;

public class doublyLinkedList<E> {

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public doublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return header.next.element;
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return trailer.prev.element;
    }

    public void addFirst(E e) {
        addBetween(e, header, header.next);
    }

    public void addLast(E e) {
        addBetween(e, trailer.prev, trailer);
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(header.next);
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(trailer.prev);
    }

    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.next = newest;
        successor.prev = newest;
        size++;
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.prev;
        Node<E> successor = node.next;
        predecessor.next = successor;
        successor.prev = predecessor;
        size--;
        return node.element;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = header.next;
        while (current != trailer) {
            sb.append(current.element);
            if (current.next != trailer) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        doublyLinkedList<Integer> list = new doublyLinkedList<>();
        System.out.println("Is list empty? " + list.isEmpty());

        list.addFirst(1);
        list.addLast(2);
        list.addFirst(0);
        list.addLast(3);

        System.out.println("List: " + list);
        System.out.println("Size: " + list.size());
        System.out.println("First element: " + list.first());
        System.out.println("Last element: " + list.last());

        list.removeFirst();
        System.out.println("List after removing first: " + list);
        
        list.removeLast();
        System.out.println("List after removing last: " + list);
    }
}
