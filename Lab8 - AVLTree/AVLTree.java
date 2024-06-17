package il.ac.telhai.ds.trees;

import org.w3c.dom.Node;
public class AVLTree<T extends Comparable<T>> {
    T val;
    AVLTree<T> right;
    AVLTree<T> left;
    int height;

    public AVLTree(T val) {
        this.val = val;
        right = null;
        left = null;
        height = 0;
    }


    public AVLTree<T> add(T val) {

//1.add the value

        if(val == null) {
            return this;
        }
        if(val.compareTo(getValue()) >= 0) {
            if(right != null) {
                right.add(val);
            }
            else {
                right = new AVLTree<>(val);
            }
        }
        else {
            if(left != null) {
                left.add(val);
            }
            else {
                left = new AVLTree<>(val);
            }
        }

//2.update heights and balance factor

        if(right != null) {
            right.height = Math.max(right.getRightHeight(), right.getLeftHeight()) + 1;
        }
        if(left != null) {
            left.height = Math.max(left.getLeftHeight(), left.getRightHeight()) + 1;
        }
        height = Math.max(getRightHeight(), getLeftHeight()) + 1;

        int balance = getLeftHeight() - getRightHeight();

//3.checks which rotate is needed (if needed) and rotates according to that

        if(balance > 1 || balance < -1) {
            if(balance < -1) {
                if(right.getLeftHeight() - right.getRightHeight() > 0) {
                    AVLTree<T> rightChild = getRight();
                    right = getRight().getLeft();
                    rightChild.left = getRight().getRight();
                    right.right = rightChild;
                }
                leftRotate();
            }
            else {
                if(left.getLeftHeight() - left.getRightHeight() < 0) {
                    AVLTree<T> leftChild = getLeft();
                    left = getLeft().getRight();
                    leftChild.right = getLeft().getRight();
                    left.left = leftChild;
                }
                rightRotate();
            }
        }
        return this;
    }


    public T getValue() {
        return val;
    }

    public AVLTree<T> getRight() {
        return right;
    }

    public AVLTree<T> getLeft() {
        return left;
    }


    public int getRightHeight() {
        if(right == null) {
            return -1;
        }
        return right.height;
    }

    public int getLeftHeight() {
        if(left == null) {
            return -1;
        }
        return left.height;
    }

    public void leftRotate() {
        AVLTree<T> current = new AVLTree<>(getValue());
        current.right = getRight().getLeft();
        current.left = getLeft();
        val = getRight().getValue();
        left = current;
        right = getRight().getRight();
        if(right != null) {
            right.height = Math.max(right.getRightHeight(), right.getLeftHeight()) + 1;
        }
        if(left != null) {
            left.height = Math.max(left.getLeftHeight(), left.getRightHeight()) + 1;
        }
        height = Math.max(getRightHeight(), getLeftHeight()) + 1;
    }

    public void rightRotate() {
        AVLTree<T> current = new AVLTree<>(getValue());
        current.left = getLeft().getRight();
        current.right = getRight();
        val = left.getValue();
        right = current;
        left = getLeft().getLeft();
        if(right != null) {
            right.height = Math.max(right.getRightHeight(), right.getLeftHeight()) + 1;
        }
        if(left != null) {
            left.height = Math.max(left.getLeftHeight(), left.getRightHeight()) + 1;
        }
        height = Math.max(getRightHeight(), getLeftHeight()) + 1;
    }
}
