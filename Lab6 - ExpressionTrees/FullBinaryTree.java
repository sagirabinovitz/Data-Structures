package il.ac.telhai.ds.trees;

public class FullBinaryTree<T> extends BinaryTree<T> {

    public FullBinaryTree(T value) {
        super(value);
    }

    public FullBinaryTree(T value, BinaryTreeI<T> subLeft, BinaryTreeI<T> subRight) {
        super(value, subLeft, subRight);
        if(subLeft!=subRight&&(subLeft==null||subRight==null)){
            throw new RuntimeException();
        }
    }

    public void setLeft(BinaryTreeI<T> left) {
        if (getRight() == null && left == null) {
            return;
        }
        if (!(left instanceof FullBinaryTree<T>)) {
            throw new IllegalArgumentException("can only add FullBinaryTree nodes.");
        }

        if (getRight() == null) {
            throw new RuntimeException("you cant add a node if right is null");
        }
        super.setLeft(left);
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        if (getLeft() == null && right == null) {
            return;
        }
        if (!(right instanceof FullBinaryTree<T>)) {
            throw new IllegalArgumentException("can only add FullBinaryTree nodes.");
        }

        if (getLeft() == null) {
            throw new RuntimeException("you cant add a node if left is null");
        }
        super.setRight(right);
    }
}
