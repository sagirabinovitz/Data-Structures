package il.ac.telhai.ds.stack;

public class DLinkedListStack<T> implements Stack<T>{
    private DLinkedList<T> dlist;
    public DLinkedListStack(){
        dlist = new DLinkedList<T>();
    }
    public void push(T t){
        dlist.goToEnd();
        dlist.insert(t);
    }
    public T pop(){
        if(dlist.isEmpty()){
            return null;
        }
        dlist.goToEnd();
        return dlist.remove();
    }
    public T top(){
        dlist.goToEnd();
        return dlist.getCursor();
    }
    public boolean isEmpty(){
        return dlist.isEmpty();
    }

    public String toString() {
        String str;
        if (dlist.isEmpty()) {
            str = "[]";
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        dlist.goToEnd();
        stringBuilder.append(this.top());

        T temp;
        while (dlist.hasPrev()) {
            stringBuilder.append(", ");
            temp = dlist.getPrev();
            System.out.println(temp);
            stringBuilder.append(temp);
        }
        stringBuilder.append("]");

        str = stringBuilder.toString();

        return str;

    }

}
