import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Node {
    int key;
    Node left, right, parent;

    public Node(int item) {
        key = item;
        left = right = parent = null;
    }
}

class BinarySearchTree {
    Node root;

    // Insert a key
    public void insert(int key) {
        root = insertRec(root, null, key);
    }

    Node insertRec(Node root, Node parent, int key) {
        if (root == null) {
            root = new Node(key);
            root.parent = parent;
        } else {
            if (key < root.key)
                root.left = insertRec(root.left, root, key);
            else if (key > root.key)
                root.right = insertRec(root.right, root, key);
        }
        return root;
    }

    // Count the number of nodes
    public int countNodes() {
        return countNodesRec(root);
    }

    int countNodesRec(Node root) {
        if (root == null)
            return 0;
        else
            return (1 + countNodesRec(root.left) + countNodesRec(root.right));
    }

    // Compute the height of the tree
    public int computeHeight() {
        return computeHeightRec(root);
    }

    int computeHeightRec(Node root) {
        if (root == null)
            return 0;
        else {
            int leftDepth = computeHeightRec(root.left);
            int rightDepth = computeHeightRec(root.right);
            if (leftDepth > rightDepth)
                return (leftDepth + 1);
            else
                return (rightDepth + 1);
        }
    }

    // Check if the tree is a BST
    public boolean isBST() {
        return isBSTRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean isBSTRec(Node node, int min, int max) {
        if (node == null)
            return true;
        if (node.key < min || node.key > max)
            return false;
        return (isBSTRec(node.left, min, node.key - 1) && isBSTRec(node.right, node.key + 1, max));
    }

    // Search a key in the tree
    public Node search(int key) {
        return searchRec(root, key);
    }

    Node searchRec(Node root, int key) {
        if (root == null || root.key == key)
            return root;
        if (root.key > key)
            return searchRec(root.left, key);
        return searchRec(root.right, key);
    }

    // Find the minimum value
    public int minValue() {
        return minValueRec(root);
    }

    int minValueRec(Node root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    // Find the maximum value
    public int maxValue() {
        return maxValueRec(root);
    }

    int maxValueRec(Node root) {
        int maxv = root.key;
        while (root.right != null) {
            maxv = root.right.key;
            root = root.right;
        }
        return maxv;
    }

    // Find the next larger key
    public Node nextLarger(int key) {
        Node current = search(key);
        if (current == null)
            return null;
        if (current.right != null)
            return minValueNode(current.right);
        Node p = current.parent;
        while (p != null && current == p.right) {
            current = p;
            p = p.parent;
        }
        return p;
    }

    // Helper function to find minimum value node
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Delete a node with the given key
    public void deleteKey(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        if (root == null)
            return root;
        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.key = minValue();
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    public void buildTreeFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        int N = scanner.nextInt();
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(scanner.nextInt());
        }
        for (int i = 1; i <= N; i++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            nodes[i].left = (left == 0) ? null : nodes[left];
            nodes[i].right = (right == 0) ? null : nodes[right];
            if (nodes[i].left != null)
                nodes[i].left.parent = nodes[i];
            if (nodes[i].right != null)
                nodes[i].right.parent = nodes[i];
        }
        root = nodes[1];
        scanner.close();
    }

}
