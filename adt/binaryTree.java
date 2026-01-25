
public class binaryTree<E> {

    public static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;

        public Node(E element) {
            this.element = element;
            left = null;
            right = null;
        }
    }

    private Node<E> root;

    public binaryTree() {
        root = null;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.element + " ");
        printInOrder(node.right);
    }

    public void printPreOrder() {
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.element + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    public void printPostOrder() {
        printPostOrder(root);
        System.out.println();
    }

    private void printPostOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.element + " ");
    }

    public static void main(String[] args) {
        binaryTree<Integer> tree = new binaryTree<>();

        /*
              1
             / \
            2   3
           / \
          4   5
        */
        Node<Integer> root = new Node<>(1);
        tree.setRoot(root);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);

        System.out.println("In-order traversal:");
        tree.printInOrder(); // Expected: 4 2 5 1 3

        System.out.println("Pre-order traversal:");
        tree.printPreOrder(); // Expected: 1 2 4 5 3

        System.out.println("Post-order traversal:");
        tree.printPostOrder(); // Expected: 4 5 2 3 1
    }
}
