package il.ac.telhai.ds.heap;

public class MinHeap<T extends Comparable<T>> {
	int size;
	int cursor;
	T[] array;
	boolean flag=false; //left if false right if true

	@SuppressWarnings({"unchecked","rawtypes"})
	public MinHeap(int length) {
		size=length+1;
		array= (T[]) new Comparable[size];
		cursor=1;

	}
	@SuppressWarnings({"unchecked","rawtypes"})
	public MinHeap(T[] arr) {
		size = arr.length+1;
		array = (T[]) new Comparable[arr.length+1];
		cursor= arr.length+1;
		array[0] = null;
		for (int i = 1; i < size; i++) {
			array[i] = arr[i - 1];
		}
		makeHeap(array);
	}

	private void makeHeap(T[] arr){
		for (int i = (size+1)/2; size-i >= 1; i++){
			siftExist(arr, size-i);
		}
	}

	public boolean isFull() {
		return cursor==size;
	}

	public boolean isEmpty() {
		return cursor==1;
	}

	public void insert(T element) {
		if(isFull()){
			//return;
			throw new RuntimeException("heap is full");
		}
		array[cursor]=element;
		cursor++;
		int index=cursor-1;
		while(index>1){
			if(!siftUp(index)){
				return;
			}
			index = index/2;
		}

	}

	public T getMin() {
		if(isEmpty()){
			throw new RuntimeException("heap is empty");
		}
		return array[1];
	}

	public T deleteMin() {
		if(isEmpty()){
			return null;
		}
		T temp = array[1];
		array[1]=array[cursor-1];
		cursor-=1;
		int index=1;
		while(index<=(cursor-1)/2) {
			if (!siftDown(index)) {
				return temp;
			}
			if (!flag) {
				index = index*2;
			}
			else {
				index=index*2 + 1;
			}
		}
		return temp;

	}

	private boolean siftUp(int index){
		if(array[index].compareTo(array[index/2])<0){
			swap(index,index/2);
			return true;
		}
		return false;
	}

	private void swap(int index1, int index2){
		T temp = array[index1];
		array[index1]=array[index2];
		array[index2]=temp;
	}

	private boolean siftDown(int index){
		if(array[index].compareTo(array[index*2])>0||array[index].compareTo(array[index*2 + 1])>0 ){
			if(Math.min(array[index*2].hashCode(),array[index*2 + 1].hashCode())==array[index*2].hashCode()){
				if(array[index*2]==null){
					return false;
				}
				flag=false;
				swap(index,index*2);
			}
			else{
				if(array[index*2 + 1]==null){
					return false;
				}
				flag=true;
				swap(index,index*2 + 1);
			}
			return true;
		}
		return false;
	}

	private void siftExist(T[] arr, int i){
		boolean left = true;
		boolean right = true;

		if(2*i >= size){
			left = false;
		}
		if(2*i+1 >= size){
			right = false;
		}
		try {
			while(arr[i].compareTo(arr[2*i]) > 0 ||
					arr[i].compareTo(arr[2*i+1]) > 0){
				if(arr[2*i].compareTo(arr[2*i+1]) < 0){
					T temp = arr[2*i];
					arr[2*i] = arr[i];
					arr[i] = temp;
					i = 2*i;
				}
				else {
					T temp = arr[2*i+1];
					arr[2*i+1] = arr[i];
					arr[i] = temp;
					i = 2*i+1;
				}

				if(2*i >= size){
					left = false;
				}
				if(2*i+1 >= size){
					right = false;
				}
			}
		}
		catch (Exception e){
			if(left){
				if(arr[i].compareTo(arr[2*i]) > 0){
					T temp = arr[2*i];
					arr[2*i] = arr[i];
					arr[i] = temp;
				}
			} else if (right) {
				if(arr[i].compareTo(arr[2*i+1]) > 0){
					T temp = arr[2*i+1];
					arr[2*i+1] = arr[i];
					arr[i] = temp;
				}
			}
		}
	}


	@Override
	public String toString() {
		if(isEmpty()){
			return "[]";
		}
		StringBuilder str = new StringBuilder();
		str.append("[");
		str.append(array[1]);
		for( int i = 2; i < cursor ; i++){
			str.append(",");
			str.append(array[i]);
		}
		str.append("]");

		return str.toString();
	}
}


""""
public void siftDown(int index){
    int min;
    int left = index*2;
    int right=(index*2) + 1;

    if(left<=size&&array[left].compareto(array[index]<0){
        min = left;
    }

    if(right<=size&&array[left].compareto(array[index]<0){
        min = right;
    }

    if(array[min]!=null&&min!=index){
        int temp=array[index];
        array[index]=array[min];
        array[min]=temp;
        siftDown(min);
    }
}
"""