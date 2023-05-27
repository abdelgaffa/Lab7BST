import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        BinarySearchTree tree = new BinarySearchTree();
        tree.buildTreeFromFile("E:/my projects/2cemester/AhmedLabs/Ahmed's Algo/Lab7 BST/src/Input.txt");

        System.out.println(tree.countNodes());
        System.out.println(tree.computeHeight());
        System.out.println(tree.isBST() ? 1 : 0);
        System.out.println(tree.minValue());
    }
}