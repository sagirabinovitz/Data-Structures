package il.ac.telhai.ds.stack;

public class DLinkedList<T> implements List<T>{


    private DNode head;
    private DNode tail;
    private DNode cursor;

    public DLinkedList(){
        head = new DNode(null);
        tail = new DNode(null);
        head.setNext(tail);
        tail.setPrev(head);
        cursor = head;
    }

    private class DNode{
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element){
            this.element = element;
        }

        public T getElement(){
            return element;
        }

        public DNode getNext(){
            return next;
        }

        public void setNext(DNode next){
            this.next = next;
        }

        public DNode getPrev(){
            return prev;
        }

        public void setPrev(DNode prev){
            this.prev = prev;
        }
    }


    @Override
    public void insert(T newElement) {
        if(newElement==null){
            throw new NullPointerException();
        }
        DNode node = new DNode(newElement);
        node.setPrev(cursor);
        node.setNext(cursor.getNext());
        (cursor.getNext()).setPrev(node);
        cursor.setNext(node);
        cursor=node;
    }

    @Override
    public T remove() {
        if(isEmpty()){
            return null;
        }
        T ele = cursor.getElement();
        (cursor.getPrev()).setNext(cursor.getNext());
        (cursor.getNext()).setPrev(cursor.getPrev());

        if(cursor.getNext()==tail){
            if(isEmpty()){
                cursor=head;
            }
            goToBeginning();
        }
        else{
            cursor=cursor.getNext();
        }
        return ele;

    }

    @Override
    public T remove(T element) {
        if(isEmpty()){
            return null;
        }
        DNode temp=cursor;
        goToBeginning();
        while(cursor.getElement()!=element){
            if(cursor==tail){
               cursor=temp;
               return null;
            }
            cursor=cursor.getNext();
        }
        return remove();
    }

    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        cursor = head;
    }

    @Override
    public void replace(T newElement) {
        if(!isEmpty() && newElement!=null) {
            cursor.element = newElement;
        }
        else{
            throw new NullPointerException(null);
        }
    }

    @Override
    public boolean isEmpty() {
        if(head.getNext()==tail){
            return true;
        }
        return false;
    }

    @Override
    public boolean goToBeginning() {
        if(isEmpty()){
            return false;
        }
        cursor=head.getNext();
        return true;
    }

    @Override
    public boolean goToEnd() {
        if(isEmpty()){
            return false;
        }
        cursor=tail.getPrev();
        return true;
    }

    @Override
    public T getNext() {
        if(!hasNext()){
            return null;
        }
        T ele = cursor.getNext().getElement();
        cursor = cursor.getNext();
        return ele;

    }

    @Override
    public T getPrev() {
        if(cursor.getPrev()==head || isEmpty()){
            return null;
        }

        cursor = cursor.getPrev();
        return cursor.getElement();
    }

    @Override
    public T getCursor() {
        return cursor.element;
    }

    @Override
    public boolean hasNext() {
        if(cursor.getNext()==tail || isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public boolean hasPrev() {
        if(cursor.getPrev()==head || isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "il.ac.telhai.ds.stack.DLinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", cursor=" + cursor +
                '}';
    }
}
