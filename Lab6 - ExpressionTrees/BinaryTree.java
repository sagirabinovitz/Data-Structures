package il.ac.telhai.ds.trees;

public class BinaryTree<T> implements BinaryTreeI<T>{
    BinaryTreeI<T> left;
    BinaryTreeI<T> right;
    T val;

    public BinaryTree(T value){
        val = value;
        left = null;
        right = null;
    }

    public BinaryTree(T value, BinaryTreeI<T> subLeft, BinaryTreeI<T> subRight){
        val = value;
        left = subLeft;
        right = subRight;
    }

    @Override
    public BinaryTreeI<T> getLeft() {
        return left;
    }

    @Override
    public BinaryTreeI<T> getRight() {
        return right;
    }

    @Override
    public T getValue() {
        return val;
    }

    @Override
    public void setValue(T value) {
         val=value;
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        this.left=left;
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        this.right=right;
    }

    @Override
    public boolean isLeaf() {
        if(left==null&&right==null){
            return true;
        }
        return false;
    }

    @Override
    public int height() {

        if(isLeaf()){
            return 0;
        }

        int maxLeft=0;
        int maxRight=0;
        int max;
        if(left!=null){
            maxLeft=left.height();
        }
        if(right!=null){
            maxRight=right.height();
        }
        if(maxLeft>=maxRight){
            max = maxLeft;
        }
        else{
            max = maxRight;
        }
        return max+1;

    }

    @Override
    public int size() {

        if(isLeaf()){
            return 1;
        }
        else {
            if (left == null) {
                return right.size() + 1;
            }
            else if (right == null) {
                return left.size() + 1 ;
            }
            else {
                return left.size() + 1 + right.size();
            }
        }

    }

    @Override
    public void clear() {
        setRight(null);
        setLeft(null);
        setValue(null);
    }

    @Override
    public String preOrder() {

        return this.preOrder(" "," ") ;
    }

    @Override
    public String preOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();

        if(left!=null){
            str.append(left.preOrder(separationBeforeVal,separationAfterVal));
        }

        if(right!=null){
            str.append(right.preOrder(separationBeforeVal,separationAfterVal));
        }


        return separationBeforeVal + val + separationAfterVal + str ;
    }

    @Override
    public String inOrder() {

        return this.inOrder(" "," ");
    }

    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();

        if(left!=null){
            str.append(left.inOrder(separationBeforeVal,separationAfterVal));
        }

        str.append(separationBeforeVal);
        str.append(val);
        str.append(separationAfterVal);

        if(right!=null){
            str.append(right.inOrder(separationBeforeVal,separationAfterVal));
        }

        return str.toString();
    }

    @Override
    public String postOrder() {
        return this.postOrder(" "," " );
    }

    @Override
    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();



        if(left!=null){
            str.append(left.postOrder(separationBeforeVal,separationAfterVal));
        }


        if(right!=null){
            str.append(right.postOrder(separationBeforeVal,separationAfterVal));
        }

        str.append(separationBeforeVal);
        str.append(val);
        str.append(separationAfterVal);


        return str.toString();
    }

}
