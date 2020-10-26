import java.util.Stack;

public class DequeUsingStacks<AnyType> implements Deque<AnyType>{
	
	Stack<AnyType> back;
	Stack<AnyType> front;

	public DequeUsingStacks(){
		
	    back = new Stack<>();
	    front = new Stack<>();
	}

	public int size(){
		
		int s = front.size() + back.size();
		return s;
	}

	public boolean isEmpty(){
		
		if(this.size()==0){
			return true;
		}
		
		return false;
	}

	public AnyType first(){
		
		return front.peek();
	}
	
	public AnyType last(){
		
		return back.peek();
	}

	public void addFirst(AnyType newValue){
		
		front.push(newValue);
	}

	public void addLast(AnyType newValue){
		
		back.push(newValue);
	}
	
	public AnyType removeFirst(){
		
		if(front.isEmpty()){
			return null;
		}
		
		return front.pop();
	}
	
	public AnyType removeLast(){
		
		if(back.isEmpty()){
			return null;
		}
		
		return back.pop();	
	}
}