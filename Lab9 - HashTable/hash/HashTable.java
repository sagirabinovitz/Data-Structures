package il.ac.telhai.ds.hash;


import il.ac.telhai.ds.linkedlist.DLinkedList;
import il.ac.telhai.ds.misc.Person;

import java.util.ArrayList;

public class HashTable<V> {
	public static final int DEF_MAX_HASH_SIZE = 10;
	DLinkedList<V>[] arr;
	int size;
	int eleNum;



	/**
	 * constructor
	 * constructs a hash-table of max-size "DEF_MAX_HASH_SIZE".
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public HashTable() {
		eleNum=0;
		size=DEF_MAX_HASH_SIZE;
		arr = new DLinkedList[size];
		for(int i=0;i<size;i++){
			arr[i]=new DLinkedList<V>();
		}
	}
	
	/**
	 * constructs a hash-table of size 'hashSize'.
	 * @param hashSize, the size of the constructed hash-table.
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public HashTable (int hashSize){
		eleNum=0;
		if(hashSize<=0){
			throw new RuntimeException("size must be positive");
		}
		size=hashSize;
		arr = new DLinkedList[size];
		for(int i=0;i<size;i++){
			arr[i]=new DLinkedList<V>();
		}
	}


    private boolean containsLinkedList(V val,DLinkedList<V> index){
		if(index.isEmpty()){
			return false;
		}
		index.goToBeginning();
		if(index.getCursor().equals(val)){
			return true;
		}
		while(index.hasNext()){
			index.getNext();
			if(index.getCursor().equals(val)){
				return true;
			}
		}

		return false;
	}

	/**
	 * @param val
	 * @return true if the hash-table contains val, otherwise return false
	 */
	public boolean contains(V val) {
		if(containsLinkedList(val,arr[hashFunc(val)])){
			return true;
		}
		return false;
	}

	private int hashFunc(V val){
		int intVal = Math.abs(val.hashCode());
		return intVal%size;
	}

	/**
	 * Add val to the hash-table.
	 * 
	 * @param val
	 * @return If the val has already existed in the the hash-table, it will not be
	 *         added again. Return true if the val was added successfully. Otherwise
	 *         return false.
	 */
	public boolean add(V val) {
		if(contains(val)){
			return false;
		}
		arr[hashFunc(val)].insert(val);
		eleNum++;
		return true;
	}

	/**
	 * Remove val from the hash-table.
	 * 
	 * @param val
	 * @return Return true if the val was removed successfully. Otherwise return
	 *         false.
	 */
	public boolean remove(V val) {
		if(!contains(val)){
			return false;
		}
		arr[hashFunc(val)].goToBeginning();
		if(arr[hashFunc(val)].getCursor().equals(val)){
			arr[hashFunc(val)].remove();
			eleNum--;
			return true;
		}
		while (arr[hashFunc(val)].hasNext()){
			arr[hashFunc(val)].getNext();
			if(arr[hashFunc(val)].getCursor().equals(val)){
				arr[hashFunc(val)].remove();
				eleNum--;
				return true;
			}

		}
		return false;
	}

	/**
	 * clear all the data in the hash-table
	 */
	public void clear() {
		for(int i=0;i<size;i++){
			arr[i].clear();
		}
		eleNum=0;
	}

	/**
	 * @return true if the hash-table is empty, otherwise return false.
	 */
	public boolean isEmpty() {
		return eleNum==0;
	}
}