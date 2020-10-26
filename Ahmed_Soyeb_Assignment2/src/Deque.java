public interface Deque<AnyType>{
	  int size();

	  boolean isEmpty();

	  AnyType first();

	  AnyType last();

	  void addFirst(AnyType newValue);

	  void addLast(AnyType newValue);

	  AnyType removeFirst();

	  AnyType removeLast();
}