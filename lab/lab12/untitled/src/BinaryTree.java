

public class BinaryTree<V extends Comparable<V>> {
    private Node<V> root;
    public BinaryTree(Node<V> root) {
        this.root = root;
    }

    public Node<V> getRoot() {
        return this.root;
    }

    public void printInorder() {
        printInOrderHelper(root);
    }
    private void printInOrderHelper(Node<V> node) {
       if(node.getLeft() != null){
           printInOrderHelper(node.getLeft());
       }
        System.out.print(node.getValue() + " ");
        if(node.getRight() != null){
            printInOrderHelper(node.getRight());
        }

    }

    public void printPreorder(){
        printPreorderHelper(root);
    }
    private void printPreorderHelper(Node<V> node) {
        System.out.print(node.getValue() + " ");
        if(node.getLeft()!=null){
            printPreorderHelper(node.getLeft());
            printPreorderHelper(node.getRight());
        }
    }

    public void printPostorder() {
        printPostorderHelper(root);
    }
    private void printPostorderHelper(Node<V> node) {
        if(node.getLeft()!=null){
            printPostorderHelper(node.getLeft());
            printPostorderHelper(node.getRight());
        }
        System.out.print(node.getValue() + " ");
    }

    public V[] flatten() {
        V[] arr = (V[]) new Comparable[5];
        Node<V> node = root;
        flattenHelper(node,arr,0);
        sort(arr);
        return arr;
    }

    public void flattenHelper(Node<V> node, V[] arr, int i){
        arr[i] = node.getValue();
        if(node.getLeft()!=null) {
            flattenHelper(node.getLeft(), arr, i + 1);
            flattenHelper(node.getRight(),arr,arr.length-i-1);
        }

    }
    // bubble sort
    // useful for flatten
    public void sort(V[] a) {
        int i, j;
        V temp;
        boolean swapped = true;
        for (i = 0; i < a.length && swapped; i++) {
            swapped = false;
            for (j = 1; j < a.length - i; j++) {
                if (a[j].compareTo(a[j-1]) < 0) {
                    swapped = true;
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }

    public void invert() {
        invertHelper(root);
    }

    public Node<V> invertHelper(Node<V> node) {
        if(node.getLeft()!=null){
            Node<V> temp = node.getRight();
            node.setRight(node.getLeft());
            node.setLeft(temp);
            invertHelper(node.getRight());
        }
        return null;
    }

    public boolean containsSubtree(BinaryTree<V> other) {
        if (other == null || other.root == null) {
            return true;
        }
        return containsSubtreeHelper(root, other.root);
    }

    private boolean containsSubtreeHelper(Node<V> node1, Node<V> node2) {
        if (node1 == null) {
            return false;
        }
        if (node1.getValue().equals(node2.getValue())) {
            if (checkSubTreeHelper(node1, node2)) {
                return true;
            }
        }
        return containsSubtreeHelper(node1.getLeft(), node2) || containsSubtreeHelper(node1.getRight(), node2);
    }

    private boolean checkSubTreeHelper(Node<V> node1, Node<V> node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (!node1.getValue().equals(node2.getValue())) {
            return false;
        }
        return checkSubTreeHelper(node1.getLeft(), node2.getLeft()) && checkSubTreeHelper(node1.getRight(), node2.getRight());
    }

    public void merge(BinaryTree<V> other) {

        return;
    }

    // Main contains tests for each milestone.
    // Do not modify existing tests.
    // Un-comment tests by removing '/*' and '*/' as you move through the milestones.
    public static void main (String args[]) {
        // Tree given for testing on
        BinaryTree<Integer> p1Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2,
                        new Node<Integer>(4, null, null),
                        new Node<Integer>(5, null, null)),
                new Node<Integer>(3, null, null)));

        // Milestone 1 (Traversing)
        System.out.println("--- Milestone 1 ---");
        System.out.print("Expected: 4 2 5 1 3" + System.lineSeparator() + "Actual: ");
        p1Tree.printInorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 1 2 4 5 3" + System.lineSeparator() + "Actual: ");
        p1Tree.printPreorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 4 5 2 3 1" + System.lineSeparator() + "Actual: ");
        p1Tree.printPostorder();
        System.out.println();

        // Milestone 2 (flatten) -- expected output: 1 2 3 4 5

        System.out.println(System.lineSeparator() + "--- Milestone 2 ---");
        System.out.print("Expected: 1 2 3 4 5" + System.lineSeparator() + "Actual: ");

        Comparable[] array_representation = p1Tree.flatten();
        for (int i = 0; i < array_representation.length; i++) {
            System.out.print(array_representation[i] + " ");
        }
        System.out.println();


        // Milestone 3 (invert)

        System.out.println(System.lineSeparator() + "--- Milestone 3 ---");

        p1Tree.invert();

        System.out.print("Expected: 3 1 5 2 4" + System.lineSeparator() + "Actual: ");
        p1Tree.printInorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 1 3 2 5 4" + System.lineSeparator() + "Actual: ");
        p1Tree.printPreorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 3 5 4 2 1" + System.lineSeparator() + "Actual: ");
        p1Tree.printPostorder();
        System.out.println();


        // Milestone 4 (containsSubtree)

        System.out.println(System.lineSeparator() + "--- Milestone 4 ---");

        p1Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2,
                        new Node<Integer>(4, null, null),
                        new Node<Integer>(5, null, null)),
                new Node<Integer>(3, null, null)));
        BinaryTree<Integer> p2Tree = new BinaryTree<Integer>(new Node<Integer>(2,
                new Node<Integer>(4, null, null),
                new Node<Integer>(5, null, null)));
        BinaryTree<Integer> p3Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2, null, null),
                new Node<Integer>(3, null, null)));
        BinaryTree<Integer> p4Tree = null;

        System.out.print("Expected: true" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p2Tree));
        System.out.println();

        System.out.print("Expected: false" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p3Tree));
        System.out.println();

        System.out.print("Expected: true" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p4Tree));

        
        /*
        // Milestone 5 (merge) *HONORS SECTION*
        System.out.println(System.lineSeparator() + "--- Milestone 5 ---");
        BinaryTree<Integer> p5Tree = new BinaryTree<>(new Node<>(10,
                new Node<>(7,
                        new Node<>(1, null, null),
                        new Node<>(5, null, null)),
                new Node<>(3,
                        new Node<>(9, null, null),
                        null)));
        BinaryTree<Integer> p6Tree = new BinaryTree<>(new Node<>(6,
                new Node<>(7,
                        new Node<>(-3, null, null),
                        new Node<>(6, null, null)),
                new Node<>(4,
                        null,
                            new Node<>(9,
                                new Node<>(4, null, null),
                                    null))));
        BinaryTree<Integer> p7Tree = new BinaryTree<>(new Node<>(10,
                new Node<>(7,
                        new Node<>(1, null, null),
                        new Node<>(6, null, null)),
                new Node<>(4,
                        new Node<>(9, null, null),
                        new Node<>(9,
                                new Node<>(4, null, null),
                                null))));
        p5Tree.merge(p6Tree);
        System.out.print("Expected: ");
        p7Tree.printPreorder();
        System.out.print("\nActual: ");
        p5Tree.printPreorder();
        System.out.println();
        System.out.print("Expected: true" + System.lineSeparator() + "Actual: ");
        System.out.println(p5Tree.containsSubtree(p7Tree));
        System.out.println();
         */
    }
}