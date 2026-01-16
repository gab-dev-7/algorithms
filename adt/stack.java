package adt;

import java.util.NoSuchElementException;

public class stack<E> {

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> head = null;
    private int size = 0;

    public stack() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(E e) {
        head = new Node<>(e, head);
        size++;
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return head.element;
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E answer = head.element;
        head = head.next;
        size--;
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
        stack<Integer> s = new stack<>();
        System.out.println("Is stack empty? " + s.isEmpty());

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        System.out.println("Stack: " + s);
        System.out.println("Size: " + s.size());
        System.out.println("Top element: " + s.top());

        System.out.println("Popped: " + s.pop());
        System.out.println("Stack after pop: " + s);
        System.out.println("Top element: " + s.top());
    }
}
