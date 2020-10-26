import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<AnyType> implements List<AnyType> {
	private static class Node<AnyType> {

		private AnyType data;	
		private Node<AnyType> next;
		
		public Node(AnyType d, Node<AnyType> n) {
			setData(d);
			setNext(n);
		}

		public AnyType getData() {
			return data;
		}

		public void setData(AnyType data) {
			this.data = data;
		}

		public Node<AnyType> getNext() {
			return next;
		}

		public void setNext(Node<AnyType> next) {
			this.next = next;
		}
	}

	private int thesize;
	private int modCount;
	private Node<AnyType> tail;
	public CircularLinkedList() {}

	public int getThesize() {return thesize;}

	public void setThesize(int thesize) {this.thesize = thesize;}

	public int getModCount() {return modCount;}

	public void setModCount(int modCount) {this.modCount = modCount;}

	public Node<AnyType> getTail() {return tail;}

	public void setTail(Node<AnyType> tail) {this.tail = tail;}

	@Override
	public void clear() {
		this.setThesize(0);
		this.setTail(null);
		this.setModCount(modCount++);
	}

	@Override
	public int size() {
		return thesize;
	}

	@Override
	public boolean isEmpty() {	
		if (size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public AnyType get(int index) {
		if (isEmpty() || index > size() || index < 0) {
			throw new NoSuchElementException();
		}

		Node<AnyType> t = tail;
		int i = 0; 
		
		while (i++ != index) { 
			t = t.getNext();
		}
		return t.getData();
	}

	@Override
	public AnyType set(int index, AnyType newValue) {
		if (isEmpty() || index > size() || index < 0) {
			throw new NoSuchElementException();
		}

		Node<AnyType> t = tail;
		AnyType oldData;
		
		int i = 0; 
		
		while (i++ != index) { 
			t = t.getNext();
		}

		oldData = t.getData();
		t.setData(newValue);
		return oldData;
	}

	@Override
	public void add(int index, AnyType newValue) {
		if (index > size() || index < 0) {
			throw new NoSuchElementException();
		}

		if (isEmpty()) {
			add(newValue);
			return;
		}

		Node<AnyType> t = tail.getNext();	
		Node<AnyType> previous = tail;

		int i = 0; 
		
		while (i++ != index) {
			previous = t;
			t = t.getNext();
		}

		Node<AnyType> newNode = new Node<AnyType>(newValue, t);

		previous.setNext(newNode);

		this.thesize++;
		this.modCount++;
	}

	@Override
	public AnyType remove(int index) {
		if (isEmpty() || index > size() || index < 0) {
			throw new NoSuchElementException();
		}

		Node<AnyType> t = tail;
		Node<AnyType> previous = null;
		int i = 0; 
		while (i++ != index) { 
			previous = t;
			t = t.getNext();
		}

		if (index == 0) {
			if (size() == 1) {
				tail = null;
			} 
			else {
				tail.next = t.getNext().getNext();
			}
		} 
		else {
			previous.next = t.getNext();
		}
		this.thesize--;
		this.modCount++;
		return t.getData();
	}

	public void rotate() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<AnyType> head = tail.getNext();
		AnyType tailData = tail.getData();
		tail.setData(head.getData());
		head.setData(tailData);
	}

	public Node<AnyType> getNode(int index, int lower, int upper) {
		if (isEmpty() || index > size() || index < 0) {
			throw new NoSuchElementException();
		}
		Node<AnyType> t = tail;
		int i = 0; 
		while (i++ != index) { 
			t = t.getNext();
		}
		return t;
	}

	@Override
	public Iterator<AnyType> iterator() {
		return new LinkedListIterator(tail.getNext());
	}

	@Override
	public boolean add(AnyType newValue) {
		if (tail == null) {
			Node<AnyType> NewNode = new Node<AnyType>(newValue, null);
			tail = NewNode;
			tail.next = tail;
		} 
		else {
			Node<AnyType> NewNode = new Node<AnyType>(newValue, tail.getNext());
			tail.setNext(NewNode);
			tail = NewNode;
		}
		this.thesize++;
		this.modCount++;
		return true;
	}

	private class LinkedListIterator implements Iterator<AnyType> {
		private Node<AnyType> previous;
		private Node<AnyType> current;
		private int expectedModCount;
		private boolean okToRemove;
		private int nextNode = 0;
		public LinkedListIterator(Node<AnyType> head) {
			current = head;
			okToRemove = true;
		}

		@Override

		public boolean hasNext() {
			if (nextNode < size()) {
				return true;
			}
			return false;
		}

		@Override

		public AnyType next() {
			if (previous == null && current != null) {
				previous = current;
				nextNode++;
				return current.getData();
			}

			if (current != null) {
				previous = current;
				current = current.getNext();
				nextNode++;
				return current.getData();
			}
			return null;
		}

		public void remove() {
			if (this.previous == null) {
				throw new IllegalStateException();
			} 
			else {
				if (isOkToRemove()) {
					try {
						previous.setNext(current.getNext());
						current = previous.getNext(); 			
						this.expectedModCount++;
						CircularLinkedList.this.modCount = this.expectedModCount;
					} 
					catch (IndexOutOfBoundsException arg1) {

						throw new ConcurrentModificationException();
					}
				}
			}
		}

		public boolean isOkToRemove() {
			if (CircularLinkedList.this.modCount != this.expectedModCount) {
				okToRemove = false;
			} 
			else {
				okToRemove = true;
			}
			return okToRemove;
		}
	}
}

