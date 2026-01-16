package adt;

import java.util.NoSuchElementException;

public class queue<E> {

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public queue() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.next = newest;
        }
        tail = newest;
        size++;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.element;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E answer = head.element;
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
        return answer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        queue<Integer> q = new queue<>();
        System.out.println("Is queue empty? " + q.isEmpty());

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        System.out.println("Queue: " + q);
        System.out.println("Size: " + q.size());
        System.out.println("First element: " + q.first());

        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Queue after dequeue: " + q);
        System.out.println("First element: " + q.first());
    }
}
