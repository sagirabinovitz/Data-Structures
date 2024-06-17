import java.awt.*;

public class ArrayPointList implements PointList {
    int size;
    Point arr[];
    int cursor;

    public ArrayPointList(){
        arr = new Point[MAX_SIZE];
        size = 0;
        cursor = 0;
    }
    public ArrayPointList(int n){
        arr = new Point[n];
        size = 0;
        cursor = 0;
    }
    @Override
    public void append(Point newPoint) {
        if(isFull()){
            System.out.println("The array of points is full");
            return;
        }
        arr[size] = newPoint;
        cursor = size;
        size++;
    }

    @Override
    public void clear() {
        size = 0;
        cursor = 0;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        if(arr.length == size){
            return true;
        }
        return false;
    }

    @Override
    public boolean goToBeginning() {
        if(isEmpty()){
            return false;
        }
        cursor=0;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if(isEmpty()){
            return false;
        }
        cursor = size-1;
        return true;
    }

    @Override
    public boolean goToNext() {
        if(cursor >=0 && cursor < size-1){
            cursor++;
            return true;
        }
        return false;
    }

    @Override
    public boolean goPrior() {
        if(cursor > 0 && cursor <= size-1 ){
            cursor--;
            return true;
        }
        return false;
    }

    @Override
    public Point getCursor() {
        if (isEmpty()){
            return null;
        }
        Point newPoint = new Point(arr[cursor]);
        return newPoint;
    }
    public String toString(){
        return super.toString();
    }
}
