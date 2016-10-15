package textgen;

import java.util.AbstractList;
import java.util.Objects;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		Objects.requireNonNull(element);
		if (size == Integer.MAX_VALUE){
			return false;
		}
		LLNode<E> newNode = new LLNode(element, tail.prev, tail);
		tail.prev.next = newNode;
		tail.prev = newNode;
		size++;
		return true;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element )
	{
		Objects.requireNonNull(element);
		if (size == Integer.MAX_VALUE || index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		if (index == size){
			add(element);
			return;
		}
		LLNode<E> currentNode = getNthNode(index);
		LLNode<E> newNode = new LLNode<E>(element, currentNode.prev, currentNode);
		currentNode.prev.next = newNode;
		currentNode.prev = newNode;
		size++;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		return (E) getNthNode(index).data;
	}

	private LLNode getNthNode(int index){

		if (size == 0 || index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> currentNode = head.next;
		while (--index >= 0){
			currentNode = currentNode.next;
		}
		return currentNode;
	}

	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (size == 0 || index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> currentNode = getNthNode(index);
		currentNode.next.prev = currentNode.prev;
		currentNode.prev.next = currentNode.next;
		size--;
		return currentNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		Objects.requireNonNull(element);
		if (size == 0 || index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> currentNode = getNthNode(index);
		E prevData = currentNode.data;
		currentNode.data = element;
		return prevData;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (E node: this) {
			sb.append(node.toString());
		}
		sb.append("]");
		return sb.toString();
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e, LLNode p, LLNode n){
		this.data = e;
		this.prev = p;
		this.next = n;
	}

}
