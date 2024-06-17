import java.awt.Point;

public interface PointList {

    static final int MAX_SIZE = 100;
    void append(Point newPoint);
    void clear();
    boolean isEmpty();
    boolean isFull();
    boolean goToBeginning();
    boolean goToEnd();
    boolean goPrior();
    boolean goToNext();
    Point getCursor();
    String toString();


}
